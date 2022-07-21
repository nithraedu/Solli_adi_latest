package nithra.tamil.word.game.solliadi;



import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;

public interface Retrofitstart {
    @FormUrlEncoded
    @POST("solliadi/game_1_16/android.php")
    Call<List<Downloading_pogo>> getdata(@FieldMap Map<String, String> defaultData);
}
