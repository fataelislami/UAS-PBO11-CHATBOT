package Controller;

import Interface.interQuran;
import Model.mQuran;
import com.linecorp.bot.client.LineMessagingServiceBuilder;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.action.PostbackAction;
import com.linecorp.bot.model.message.AudioMessage;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TemplateMessage;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.message.template.ConfirmTemplate;
import com.linecorp.bot.model.response.BotApiResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Quran {

    public void getQuran(String namaSurat, String ayat, interQuran callback){
        ServiceAPI client=new ClientAPI().createService(ServiceAPI.class);
        HashMap<String,String> params=new HashMap<>();
        params.put("namaSurat",namaSurat);
        params.put("ayat",ayat);

        Call<mQuran> call=client.getQuran(params);
        call.enqueue(new Callback<mQuran>() {
            @Override
            public void onResponse(Call<mQuran> call, Response<mQuran> response) {
                String namaSurat=response.body().getNamasurat();
                String nomorSurat=response.body().getNomor();
                String ayat=response.body().getAyat();
                String terjemah=response.body().getTerjemah();
                String url=response.body().getUrl();
                String totalayat=response.body().getTotalayat().toString();
                String[] data={namaSurat,nomorSurat,ayat,terjemah,url,totalayat};
                callback.onSuccess(data);

            }

            @Override
            public void onFailure(Call<mQuran> call, Throwable t) {

            }
        });
    }
    public void replyToUser(String rToken,String lChannelAccessToken, String[] value,String data){
        ConfirmTemplate confirmTemplate = new ConfirmTemplate(
                "Surat : "+value[0]+"\nNext Ayat ?",
                new PostbackAction("Quran", "No!"),
                new PostbackAction("Next", "next_"+data,"Next"+data)
        );
        TemplateMessage templateMessage = new TemplateMessage("Confirm alt text", confirmTemplate);
        TextMessage textMessage = new TextMessage(value[2]);//AYAT
        TextMessage textMessage2 = new TextMessage(value[3]);//TERJEMAH
        AudioMessage audioMessage=new AudioMessage(value[4],240000);
        TemplateMessage templateMessagez = new TemplateMessage("Quran",confirmTemplate);
        List<Message> message=new ArrayList<>();
        message.add(textMessage);
        message.add(textMessage2);
        message.add(audioMessage);
        message.add(templateMessagez);
        ReplyMessage replyMessage = new ReplyMessage(rToken,message);

        try {
            Response<BotApiResponse> response = LineMessagingServiceBuilder
                    .create(lChannelAccessToken)
                    .build()
                    .replyMessage(replyMessage)
                    .execute();
            System.out.println("Reply Message: " + response.code() + " " + response.message());
        } catch (IOException e) {
            System.out.println("Exception is raised ");
            e.printStackTrace();
        }
    }
}
