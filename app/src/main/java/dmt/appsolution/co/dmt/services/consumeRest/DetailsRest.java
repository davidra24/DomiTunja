package dmt.appsolution.co.dmt.services.consumeRest;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.concurrent.TimeUnit;

import dmt.appsolution.co.dmt.activities.RestaurantActivity;
import dmt.appsolution.co.dmt.constants.Constants;
import dmt.appsolution.co.dmt.services.entity.LugarDetalles;
import dmt.appsolution.co.dmt.services.interfaces.LugarDetallesInterface;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Martin on 30/01/2018.
 */

public class DetailsRest extends AsyncTask<Void, Integer, Void> {
    private AppCompatActivity activity;
    private Bundle bundle;

    public DetailsRest(AppCompatActivity activity, Bundle bundle) {
        this.activity = activity;
        this.bundle = bundle;
    }

    @Override
    protected void onPreExecute() {
        publishProgress(0);
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
        loadDetails(builder);
        return null;
    }

    private void loadDetails(Retrofit builder) {
        final LugarDetallesInterface lugarDetallesInterface = builder.create(LugarDetallesInterface.class);
        Call<LugarDetalles> call =
                lugarDetallesInterface.getListPlaceDetails(Integer.parseInt(Constants.Companion.getID_LUGAR()));
        call.enqueue(new Callback<LugarDetalles>() {
            @Override
            public void onResponse(Call<LugarDetalles> call, Response<LugarDetalles> response) {
                Constants.Companion.setLugarDetalles(response.body());
                Intent intent = new Intent(activity, RestaurantActivity.class);
                intent.putExtras(bundle);
                activity.startActivity(intent);
                activity.overridePendingTransition(0, 0);
                activity.finish();
            }

            @Override
            public void onFailure(Call<LugarDetalles> call, Throwable t) {

            }
        });
    }
}
