package dmt.appsolution.co.dmt.services.consumeRest;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;
import java.util.concurrent.TimeUnit;

import dmt.appsolution.co.dmt.R;
import dmt.appsolution.co.dmt.activities.MenuActivity;
import dmt.appsolution.co.dmt.constants.Constants;
import dmt.appsolution.co.dmt.services.entity.Lugar;
import dmt.appsolution.co.dmt.services.entity.TipoLugar;
import dmt.appsolution.co.dmt.services.interfaces.LugarInterface;
import dmt.appsolution.co.dmt.services.interfaces.TipoLugarInterface;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DomiRest extends AsyncTask<Void, Integer, Void> {

    private AppCompatActivity activity;
    private ProgressBar progressBar;
    private int progreso = 0;
    private boolean hasEnd = false;

    public DomiRest(AppCompatActivity activity) {
        this.activity = activity;
        this.progressBar = activity.findViewById(R.id.progressBarSplash);
    }

    @Override
    protected void onPreExecute() {
        publishProgress(0);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected Void doInBackground(Void... voids) {
        Retrofit builder = new Retrofit.Builder()
                .baseUrl(Constants.Companion.getREST_URL())
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient().newBuilder()
                        .connectTimeout(1, TimeUnit.MINUTES)
                        .readTimeout(30, TimeUnit.SECONDS)
                        .writeTimeout(30, TimeUnit.SECONDS)
                        .build())
                .build();
       loadPlaces(builder);
       loadTypes(builder);
        while (progreso <= progressBar.getMax()){
            progreso++;
            onProgressUpdate(progreso);
            SystemClock.sleep(20);
        }

       return null;
    }

    private void loadPlaces(final Retrofit builder) {
        final LugarInterface lugarInterface = builder.create(LugarInterface.class);
        Call<List<Lugar>> call = lugarInterface.getListPlace();
        call.enqueue(new Callback<List<Lugar>>() {
            @Override
            public void onResponse(Call<List<Lugar>> call, Response<List<Lugar>> response) {
                List<Lugar> lugarList = response.body();
                for (Lugar lugar : lugarList) {
                    Constants.Companion.getRestaurantList().add(lugar);
                }
                Constants.Companion.getRestaurantList().add(new Lugar());
                progressBar.setVisibility(View.INVISIBLE);
                activity.startActivity(new Intent(activity.getBaseContext(), MenuActivity.class));
                activity.finish();
            }

            @Override
            public void onFailure(Call<List<Lugar>> call, Throwable t) {
                Toast.makeText(activity.getApplicationContext(), "Error cargando lugares.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadTypes(Retrofit builder) {
        final TipoLugarInterface tipoLugarInterface = builder.create(TipoLugarInterface.class);
        Call<List<TipoLugar>> call = tipoLugarInterface.getListTypePlace();
        call.enqueue(new Callback<List<TipoLugar>>() {
            @Override
            public void onResponse(Call<List<TipoLugar>> call, Response<List<TipoLugar>> response) {
                List<TipoLugar> tipoLugarList = response.body();
                Constants.Companion.getRestaurantType().add(new TipoLugar("",
                        Constants.Companion.getALL_FOOD(), "Todo", "Todo"));
                for (TipoLugar tipoLugar: tipoLugarList)
                    Constants.Companion.getRestaurantType().add(tipoLugar);
            }

            @Override
            public void onFailure(Call<List<TipoLugar>> call, Throwable t) {
                Toast.makeText(activity.getApplicationContext(), "Error cargando tipos.", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    protected void onProgressUpdate(Integer... values) {
        progressBar.setProgress(values[0]);
    }
}
