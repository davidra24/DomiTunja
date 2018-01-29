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

import dmt.appsolution.co.dmt.R;
import dmt.appsolution.co.dmt.activities.MenuActivity;
import dmt.appsolution.co.dmt.constants.Constants;
import dmt.appsolution.co.dmt.services.entity.Foto;
import dmt.appsolution.co.dmt.services.entity.Lugar;
import dmt.appsolution.co.dmt.services.entity.TipoLugar;
import dmt.appsolution.co.dmt.services.interfaces.FotosInterface;
import dmt.appsolution.co.dmt.services.interfaces.LugarInterface;
import dmt.appsolution.co.dmt.services.interfaces.TipoLugarInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DomiRest extends AsyncTask<Void, Integer, Void> {

    private AppCompatActivity activity;
    private ProgressBar progressBar;
    private int progreso = 0;
    public DomiRest(AppCompatActivity activity) {
        this.activity = activity;
        this.progressBar = activity.findViewById(R.id.progressBarSplash);
        this.progressBar.getProgressDrawable().setColorFilter(
                Color.WHITE, android.graphics.PorterDuff.Mode.SRC_IN);
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
                .build();
       loadPlaces(builder);
       loadPhotos(builder);
       loadTypes(builder);
        while (progreso < progressBar.getMax()){
            progreso++;
            publishProgress(progreso);
            SystemClock.sleep(30);
        }
       return null;
    }


    private void loadPlaces(Retrofit builder) {
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
            }

            @Override
            public void onFailure(Call<List<Lugar>> call, Throwable t) {
                Toast.makeText(activity.getApplicationContext(), "Error cargando lugares.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadPhotos(Retrofit builder) {
        final FotosInterface fotosInterface = builder.create(FotosInterface.class);
        Call<List<Foto>> call = fotosInterface.getListPhotos();
        call.enqueue(new Callback<List<Foto>>() {
            @Override
            public void onResponse(Call<List<Foto>> call, Response<List<Foto>> response) {
                List<Foto> fotoList = response.body();
                for (Foto foto: fotoList)
                    Constants.Companion.getPhotoList().add(foto);
            }

            @Override
            public void onFailure(Call<List<Foto>> call, Throwable t) {
                Toast.makeText(activity.getBaseContext(), "Error cargando imagenes.", Toast.LENGTH_SHORT).show();
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
                Constants.Companion.getRestaurantType().add(new TipoLugar(Constants.Companion.getALL_FOOD(), "Todo", 0));
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


    @Override
    protected void onPostExecute(Void aVoid) {
        progressBar.setVisibility(View.INVISIBLE);
        while(true){
            if(Constants.Companion.getRestaurantList().size() != 0) {
                activity.startActivity(new Intent(activity.getBaseContext(), MenuActivity.class));
                activity.finish();
                break;
            }
        }
    }
}
