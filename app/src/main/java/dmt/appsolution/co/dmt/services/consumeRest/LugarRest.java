package dmt.appsolution.co.dmt.services.consumeRest;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import dmt.appsolution.co.dmt.constants.Constants;
import dmt.appsolution.co.dmt.services.interfaces.LugarInterface;
import dmt.appsolution.co.dmt.services.lists.LugarList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by davic on 15/01/2018.
 */

public class LugarRest extends AsyncTask<Void, Void, Void>{
    private AppCompatActivity activity;
    private LugarList lugarList;
    public LugarRest(AppCompatActivity activity){
        this.activity = activity;
        this.lugarList = new LugarList();
    }
    @Override
    protected Void doInBackground(Void... voids) {
        Retrofit builder = new Retrofit.Builder()
                .baseUrl(Constants.Companion.getURL())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final LugarInterface productInterface = builder.create(LugarInterface.class);
        Call<LugarList> call = productInterface.getListPlace();
        call.enqueue(new Callback<LugarList>() {
            @Override
            public void onResponse(Call<LugarList> call, Response<LugarList> response) {
                LugarList lugarListaux = response.body();
                for (Lugar lugar:lugarListaux.getPlaces()) {
                    if(lugar.getId_lugar() == Integer.parseInt(Constants.Companion.getID()))
                        lugarList.addPlace(lugar);
                }
            }

            @Override
            public void onFailure(Call<LugarList> call, Throwable t) {
                Toast.makeText(activity.getBaseContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }

        });
        return null;
    }

    public LugarList getLugarList() {
        return lugarList;
    }

    public void setLugarList(LugarList lugarList) {
        this.lugarList = lugarList;
    }
}