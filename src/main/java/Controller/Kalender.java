package Controller;

import Interface.interKalender;
import Model.mKalender;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.HashMap;

public class Kalender {
    public void getKalender(String date,interKalender callback){
        ServiceAPI client=new ClientAPI().createService(ServiceAPI.class);
        HashMap<String,String> params=new HashMap<>();
        params.put("date",date);
        Call<mKalender> call=client.getKalender(params);
        call.enqueue(new Callback<mKalender>() {
            @Override
            public void onResponse(Call<mKalender> call, Response<mKalender> response) {
                String status=response.body().getStatus();
                String hijriyah=response.body().getHijriyah();
                String masehi=response.body().getMasehi();
                String code=response.body().getCode().toString();
                String[] data={status,hijriyah,masehi,code};
                callback.onSuccess(data);
            }

            @Override
            public void onFailure(Call<mKalender> call, Throwable t) {

            }
        });
    }
}
