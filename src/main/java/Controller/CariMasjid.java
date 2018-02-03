package Controller;

import Interface.interCariMasjid;
import Model.Result;
import Model.mCariMasjid;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.HashMap;
import java.util.List;

public class CariMasjid {
    public void getDataMasjid(interCariMasjid callback){
        ServiceAPI client=new ClientAPI().createService(ServiceAPI.class);
        HashMap<String,String> params=new HashMap<>();
        params.put("lat","-6.888519");
        params.put("lng","107.618493");
        Call<mCariMasjid> call=client.getDataMasjid(params);
        call.enqueue(new Callback<mCariMasjid>() {
            @Override
            public void onResponse(Call<mCariMasjid> call, Response<mCariMasjid> response) {
                List<Result> data=response.body().getResult();
                String[] nama={data.get(0).getNama()};
                callback.onSuccess(nama);

            }

            @Override
            public void onFailure(Call<mCariMasjid> call, Throwable t) {

            }
        });
    }
}
