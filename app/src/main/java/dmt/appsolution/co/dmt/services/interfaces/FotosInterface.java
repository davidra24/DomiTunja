package dmt.appsolution.co.dmt.services.interfaces;

import java.util.List;

import dmt.appsolution.co.dmt.services.entity.Foto;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Martin on 25/01/2018.
 */

public interface FotosInterface {
    @GET("photos")
    Call<List<Foto>> getListPhotos();
}
