package Controller;

import Interface.interJdwlSholat;
import Model.mJadwalSholat;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.HashMap;

public class JadwalSholat {

    public void getJadwal(String kota, interJdwlSholat callback){
        ServiceAPI client=new ClientAPI().createService(ServiceAPI.class);
        HashMap<String,String> params=new HashMap<>();

        params.put("lokasi",kota);
        Call<mJadwalSholat> call = client.getJadwal(params);
        call.enqueue(new Callback<mJadwalSholat>() {
            @Override
            public void onResponse(Call<mJadwalSholat> call, Response<mJadwalSholat> response) {
                String status=response.body().getStatus();
                String shubuh=response.body().getShubuh();
                String dzuhur=response.body().getDzuhur();
                String ashar=response.body().getAshar();
                String maghrib=response.body().getMaghrib();
                String isya=response.body().getIsya();
                String[] data={status,shubuh,dzuhur,ashar,maghrib,isya};
                callback.onSuccess(data);

            }

            @Override
            public void onFailure(Call<mJadwalSholat> call, Throwable t) {

            }
        });


    }
}
