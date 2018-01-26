package dmt.appsolution.co.dmt.services.interfaces;

import dmt.appsolution.co.dmt.services.entity.Comentario;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Martin on 24/01/2018.
 */

public interface ComentarioInterface {
    @POST("comments")
    @FormUrlEncoded
    Call<Comentario> createComment(@Body Comentario comentario);
}
