package dmt.appsolution.co.dmt.services.consumeRest;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.List;

import dmt.appsolution.co.dmt.constants.Constants;
import dmt.appsolution.co.dmt.services.entity.Article;
import dmt.appsolution.co.dmt.services.interfaces.ArticleInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Martin on 17/01/2018.
 */

public class ArticleRest extends AsyncTask<Void, Void, Void> {
    private Context context;

    public ArticleRest(Context context) {
        this.context = context;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        Retrofit builder = new Retrofit.Builder()
                .baseUrl(Constants.Companion.getREST_URL())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final ArticleInterface articleInterface = builder.create(ArticleInterface.class);
        Call <List<Article>> call = articleInterface.getListArticle();
        call.enqueue(new Callback<List<Article>>() {
            @Override
            public void onResponse(Call<List<Article>> call, Response<List<Article>> response) {
                List<Article> list = response.body();
                for (Article article: list)
                    Toast.makeText(context, article.getDate(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<Article>> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return null;
    }

}
