package dmt.appsolution.co.dmt.services.interfaces;

import dmt.appsolution.co.dmt.services.lists.LugarList;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by davic on 15/01/2018.
 */

public interface LugarInterface {
    @GET("lugar")
    Call<LugarList> getListPlace();
}
