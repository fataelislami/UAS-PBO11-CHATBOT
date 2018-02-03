package Controller;

import Model.mJadwalSholat;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

import java.util.HashMap;

public interface ServiceAPI {
    @GET("api/solat/apis.php")
    Call<mJadwalSholat> getJadwal(@QueryMap HashMap<String, String> params);
}
