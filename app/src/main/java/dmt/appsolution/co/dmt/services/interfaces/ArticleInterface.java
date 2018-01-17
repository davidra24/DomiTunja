package dmt.appsolution.co.dmt.services.interfaces;

import java.util.List;

import dmt.appsolution.co.dmt.services.entity.Article;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Martin on 17/01/2018.
 */

public interface ArticleInterface {
    @GET("article")
        Call<List<Article>> getListArticle();
}
