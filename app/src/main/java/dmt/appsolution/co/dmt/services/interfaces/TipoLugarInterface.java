package dmt.appsolution.co.dmt.services.interfaces;

import java.util.List;

import dmt.appsolution.co.dmt.services.entity.TipoLugar;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Martin on 23/01/2018.
 */

public interface TipoLugarInterface {
    @GET("types")
    Call<List<TipoLugar>> getListTypePlace();
}
