package Controller;

import com.linecorp.bot.client.LineMessagingServiceBuilder;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.message.ImagemapMessage;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.message.imagemap.ImagemapArea;
import com.linecorp.bot.model.message.imagemap.ImagemapBaseSize;
import com.linecorp.bot.model.message.imagemap.URIImagemapAction;
import com.linecorp.bot.model.response.BotApiResponse;
import retrofit2.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CariKiblat {

    public void replyImageMap(String rToken,String lChannelAccessToken){
        String imageUrl="https://islamify.id/iimap/carikiblat";
        ImagemapMessage imagemapMessage=new ImagemapMessage( "https://islamify.id/iimap/carikiblat",
                "Kuy Cari Kiblatnya",
                new ImagemapBaseSize(1040, 1040),
                Arrays.asList(
                        new URIImagemapAction(
                                "line://nv/location",
                                new ImagemapArea(
                                        0, 0, 1040, 1040
                                )
                        )
                )
        );
        TextMessage textMessage =new TextMessage("Coba kirimkan lokasinya kak, klik gambar dibawah");
        List<Message> message=new ArrayList<>();
        message.add(textMessage);
        message.add(imagemapMessage);
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
