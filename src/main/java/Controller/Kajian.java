package Controller;

import Interface.interKajian;
import Model.Content;
import Model.Result;
import Model.mKajian;
import com.linecorp.bot.client.LineMessagingServiceBuilder;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.action.MessageAction;
import com.linecorp.bot.model.action.PostbackAction;
import com.linecorp.bot.model.action.URIAction;
import com.linecorp.bot.model.message.TemplateMessage;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.message.VideoMessage;
import com.linecorp.bot.model.message.template.CarouselColumn;
import com.linecorp.bot.model.message.template.CarouselTemplate;
import com.linecorp.bot.model.response.BotApiResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Kajian {

    public void getKajian(interKajian callback){
        ServiceAPI client=ClientAPI.createService(ServiceAPI.class);
        Call<mKajian> call=client.getKajian();
        call.enqueue(new Callback<mKajian>() {
            @Override
            public void onResponse(Call<mKajian> call, Response<mKajian> response) {
                List<Content> data=response.body().getContent();
                callback.onSuccess(data);
            }

            @Override
            public void onFailure(Call<mKajian> call, Throwable t) {

            }
        });
    }

    public void replyToUser(String rToken,String lChannelAccessToken, List<Content> value){
        CarouselTemplate carouselTemplate = new CarouselTemplate(
                Arrays.asList(
                        new CarouselColumn(value.get(0).getImageUrl(), value.get(0).getUsername(), "Di Play aja kak!", Arrays.asList(
                                new PostbackAction("Tonton Kajian",
                                        "video#0")
                        )),
                        new CarouselColumn(value.get(1).getImageUrl(), value.get(1).getUsername(), "Di Play aja kak!", Arrays.asList(
                                new PostbackAction("Tonton Kajian",
                                        "video#1")
                        )),
                        new CarouselColumn(value.get(2).getImageUrl(), value.get(2).getUsername(), "Di Play aja kak!", Arrays.asList(
                                new PostbackAction("Tonton Kajian",
                                        "video#2")
                        )),
                        new CarouselColumn(value.get(3).getImageUrl(), value.get(3).getUsername(), "Di Play aja kak!", Arrays.asList(
                                new PostbackAction("Tonton Kajian",
                                        "video#3")
                        )),
                        new CarouselColumn(value.get(4).getImageUrl(), value.get(4).getUsername(), "Di Play aja kak!", Arrays.asList(
                                new PostbackAction("Tonton Kajian",
                                        "video#4")
                        ))
                        ));
        TemplateMessage templateMessage = new TemplateMessage("Kajian nih"+value.get(4).getImageUrl(), carouselTemplate);
        TextMessage textMessage =new TextMessage("Halo kak, ini video kajiannya..");
        List<com.linecorp.bot.model.message.Message> message=new ArrayList<>();
        message.add(textMessage);
        message.add(templateMessage);
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

    public void replyVideo(String rToken,String lChannelAccessToken, List<Content> value,int index){
        VideoMessage oVideo=new VideoMessage(value.get(index).getVideoUrl(),value.get(index).getImageUrl());
        ReplyMessage replyMessage = new ReplyMessage(rToken,oVideo);

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
