package Controller;

import Model.*;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

import java.util.HashMap;

public interface ServiceAPI {
    @GET("api/solat/apis.php")
    Call<mJadwalSholat> getJadwal(@QueryMap HashMap<String, String> params);

    @GET("QuranAPI.php")
    Call<mQuran> getQuran(@QueryMap HashMap<String,String> params);

    @GET("api/kalender/api.php")
    Call<mKalender> getKalender(@QueryMap HashMap<String,String> params);

    @GET("api/masjid/api.php")
    Call<mCariMasjid> getDataMasjid(@QueryMap HashMap<String,String> params);

    @GET("botbeta/webhook/index.php/ApiVideo")
    Call<mKajian> getKajian();
}
