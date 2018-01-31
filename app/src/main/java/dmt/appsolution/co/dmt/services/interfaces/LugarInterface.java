package dmt.appsolution.co.dmt.services.interfaces;

import java.util.List;

import dmt.appsolution.co.dmt.services.entity.Lugar;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by davic on 15/01/2018.
 */

public interface LugarInterface {
    @GET("lugares/getLugaresCercaJSON.php")
    Call<List<Lugar>> getListPlace();
}
