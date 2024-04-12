package nithra.tamil.word.game.solliadi;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Retrofitstart {

    @FormUrlEncoded
    @POST("solliadi/solliadi.php")
    Call<List<HashMap<String,String>>> getdownloadcheckdata(@FieldMap Map<String, String> defaultData);

    @FormUrlEncoded
    @POST("solliadi/solliadi.php")
    Call<List<HashMap<String,String>>> getdownloadcheck1data(@FieldMap Map<String, String> defaultData);

    @FormUrlEncoded
    @POST("solliadi/solliadi1.php")
    Call<List<HashMap<String,String>>> getdeletezipdata(@FieldMap Map<String, String> defaultData);

    @FormUrlEncoded
    @POST("solliadi/gamedata.php")
        Call<List<HashMap<String,String>>> getgamestatusdata(@FieldMap Map<String, String> defaultData);


    @FormUrlEncoded
    @POST("solliadi/solliadi.php")
    Call<List<HashMap<String,String>>> getClue_downloadcheckdata(@FieldMap Map<String, String> defaultData);

    @FormUrlEncoded
    @POST("solliadi/solliadi1.php")
    Call<List<HashMap<String,String>>> getClue_newdowndata(@FieldMap Map<String, String> defaultData);

    @FormUrlEncoded
    @POST("solliadi/game_1_16/android.php")
    Call<List<HashMap<String,String>>> getFindDifferenceBetweenPictures_downpicdata(@FieldMap Map<String, String> defaultData);

    @FormUrlEncoded
    @POST("solliadi/game_1_16/android.php")
    Call<List<HashMap<String,String>>> getFindwordsfromPictures_downpicdata(@FieldMap Map<String, String> defaultData);

    @FormUrlEncoded
    @POST("solliadi/userstatus_prize.php")
    Call<List<HashMap<String,String>>> getGameStates_userstates_senddata(@FieldMap Map<String, String> defaultData);

    @FormUrlEncoded
    @POST("solliadi/regisrtation.php")
    Call<List<HashMap<String,String>>> getMainlogindata(@FieldMap Map<String, String> defaultData);

    @FormUrlEncoded
    @POST("solliadi/regisrtation.php")
    Call<List<HashMap<String,String>>> getMainlogin_otp_senddata(@FieldMap Map<String, String> defaultData);

    @FormUrlEncoded
    @POST("solliadi/solliadi.php")
    Call<List<HashMap<String,String>>> getnew_main_gameListdata(@FieldMap Map<String, String> defaultData);

    @FormUrlEncoded
    @POST("solliadi/solliadi1.php")
    Call<List<HashMap<String,String>>> getnew_main_gameList_deletezipnewdata(@FieldMap Map<String, String> defaultData);

    @FormUrlEncoded
    @POST("solliadi/solliadi.php")
    Call<List<HashMap<String,String>>> getnew_main_gameList_downloadcheck1data(@FieldMap Map<String, String> defaultData);


    @FormUrlEncoded
    @POST("solliadi/regisrtation.php")
    Call<List<HashMap<String,String>>> getotp_verification_otp_senddata(@FieldMap Map<String, String> defaultData);

    @FormUrlEncoded
    @POST("solliadi/regisrtation.php")
    Call<List<HashMap<String,String>>> getotp_verification_availcheckdata(@FieldMap Map<String, String> defaultData);

    @FormUrlEncoded
    @POST("solliadi/solliadi.php")
    Call<List<HashMap<String,String>>> getPicture_Game_Hard_data(@FieldMap Map<String, String> defaultData);

    @FormUrlEncoded
    @POST("solliadi/solliadi1.php")
    Call<List<HashMap<String,String>>> getPicture_Game_Hard_newdowndata(@FieldMap Map<String, String> defaultData);

    @FormUrlEncoded
    @POST("solliadi/missingdata.php")
    Call<List<HashMap<String,String>>> getPicture_Game_Hard_downpicdata(@FieldMap Map<String, String> defaultData);

    @FormUrlEncoded
    @POST("solliadi/extrawords.php")
    Call<List<HashMap<String,String>>> getSollukkul_Sol_send_extraworddata(@FieldMap Map<String, String> defaultData);

    @FormUrlEncoded
    @POST("solliadi/solliadi.php")
    Call<List<HashMap<String,String>>> getSollukkul_Sol_downloadcheckdata(@FieldMap Map<String, String> defaultData);

    @FormUrlEncoded
    @POST("solliadi/solliadi1.php")
    Call<List<HashMap<String,String>>> getSollukkul_Sol_newdowndata(@FieldMap Map<String, String> defaultData);

    @FormUrlEncoded
    @POST("solliadi/extrawords.php")
    Call<List<HashMap<String,String>>> getWordGameHard_send_extraworddata(@FieldMap Map<String, String> defaultData);

    @FormUrlEncoded
    @POST("solliadi/solliadi.php")
    Call<List<HashMap<String,String>>> getWordGameHard_downloadcheckdata(@FieldMap Map<String, String> defaultData);

    @FormUrlEncoded
    @POST("solliadi/solliadi1.php")
    Call<List<HashMap<String,String>>> getWordGameHard_newdowndata(@FieldMap Map<String, String> defaultData);

    @FormUrlEncoded
    @POST("solliadi/userstatus_prize.php")
    Call<List<HashMap<String,String>>> getStatesActivity_userstates_senddata(@FieldMap Map<String, String> defaultData);
}
