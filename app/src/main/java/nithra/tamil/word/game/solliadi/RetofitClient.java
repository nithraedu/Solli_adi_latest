package nithra.tamil.word.game.solliadi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetofitClient {
    //   private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    private static final String BASE_URL = "https://nithra.mobi/";
    private static final String BASE_URL2 = "https://www.nithra.mobi/";
    private Retrofit retrofit;

    public Retrofit RetrofitExample() {
        return retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public Retrofit Retrofit2ndBaseUrl() {
        return retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL2)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

}
