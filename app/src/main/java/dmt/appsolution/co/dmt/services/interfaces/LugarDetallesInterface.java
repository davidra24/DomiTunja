package dmt.appsolution.co.dmt.services.interfaces;

import java.util.List;

import dmt.appsolution.co.dmt.services.entity.LugarDetalles;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Martin on 30/01/2018.
 */

public interface LugarDetallesInterface {

    @GET("lugares/getLugarDetailJSON.php")
    Call<LugarDetalles> getListPlaceDetails(@Query("idLugar") int idLugar);
}
