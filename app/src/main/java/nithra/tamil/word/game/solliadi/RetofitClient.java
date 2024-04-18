package nithra.tamil.word.game.solliadi;

import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetofitClient {
    //   private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    private static final String BASE_URL = "https://nithra.mobi/";
    private static final String BASE_URL2 = "https://www.nithra.mobi/";

    OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(2, TimeUnit.MINUTES) // Connection timeout
            .readTimeout(2, TimeUnit.MINUTES)
            .writeTimeout(2, TimeUnit.MINUTES)
            .addInterceptor(chain -> {
                Request newRequest = chain.request().newBuilder().build();
                return chain.proceed(newRequest);
            })
            .build();


    public Retrofit RetrofitExample() {
        return new Retrofit.Builder()
                .client(client)
                .baseUrl(BASE_URL)
                //.addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()))
                .build();
    }

    public Retrofit Retrofit2ndBaseUrl() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL2)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

}
