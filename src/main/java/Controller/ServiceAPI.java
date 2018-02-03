package Controller;

import Model.mJadwalSholat;
import Model.mKalender;
import Model.mQuran;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

import java.util.HashMap;

public interface ServiceAPI {
    @GET("api/solat/apis.php")
    Call<mJadwalSholat> getJadwal(@QueryMap HashMap<String, String> params);

    @GET("QuranAPI.php")
    Call<mQuran> getQuran(@QueryMap HashMap<String,String> params);

    @GET("api/kalender/api.php?")
    Call<mKalender> getKalender(@QueryMap HashMap<String,String> params);
}
