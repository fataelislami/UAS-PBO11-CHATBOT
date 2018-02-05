
package LineSDK;

import Controller.CariMasjid;
import Controller.JadwalSholat;
import Controller.Kalender;
import Controller.Quran;
import Interface.interCariMasjid;
import Interface.interJdwlSholat;
import Interface.interKalender;
import Interface.interQuran;
import LineSDK.Payload;
import Model.Result;
import Model.User;
import com.google.gson.Gson;
import com.linecorp.bot.client.LineMessagingServiceBuilder;
import com.linecorp.bot.client.LineSignatureValidator;
import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.action.PostbackAction;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TemplateMessage;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.message.template.ButtonsTemplate;
import com.linecorp.bot.model.message.template.ConfirmTemplate;
import com.linecorp.bot.model.message.template.Template;
import com.linecorp.bot.model.profile.UserProfileResponse;
import com.linecorp.bot.model.response.BotApiResponse;
import database.Config;
import database.Dao;
import database.DaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import retrofit2.Response;

import javax.activation.DataSource;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value="/linesdk")
public class LineBotController
{
    @Autowired
    @Qualifier("com.linecorp.channel_secret")
    String lChannelSecret;
    private UserProfileResponse sender;

    @Autowired
    @Qualifier("com.linecorp.channel_access_token")
    String lChannelAccessToken;

    /**
     *
     */
//    @Autowired
//    Dao mDao;

    @RequestMapping(value="/callback", method=RequestMethod.POST)
    public ResponseEntity<String> callback(
            @RequestHeader("X-Line-Signature") String aXLineSignature,
            @RequestBody String aPayload) {
        final String text = String.format("The Signature is: %s",
                (aXLineSignature != null && aXLineSignature.length() > 0) ? aXLineSignature : "N/A");
        System.out.println(text);
        final boolean valid = new LineSignatureValidator(lChannelSecret.getBytes()).validateSignature(aPayload.getBytes(), aXLineSignature);
        System.out.println("The signature is: " + (valid ? "valid" : "tidak valid"));
        if (aPayload != null && aPayload.length() > 0) {
            System.out.println("Payload: " + aPayload);
        }
        Gson gson = new Gson();
        Payload payload = gson.fromJson(aPayload, Payload.class);

        String msgText = payload.events[0].message.text.toLowerCase();
//        msgText = msgText.toLowerCase();
        String postBack = " ";
        String eventType = payload.events[0].type;
        String messageType=payload.events[0].message.type;
        String messageId=payload.events[0].message.id;

        sender = getUserProfile(payload.events[0].source.userId);

        if (eventType.equals("join")) {
            if (payload.events[0].source.type.equals("group")) {
                replyToUser(payload.events[0].replyToken, "Hello Group");
            }
            if (payload.events[0].source.type.equals("room")) {
                replyToUser(payload.events[0].replyToken, "Hello Room");
            }
        }

        DaoImpl oDao = new DaoImpl();
        List<String> oList = oDao.getByUserId(payload.events[0].source.userId);
        String flag = oList.get(1);
        if (flag.equals("cari masjid")) {

            if (eventType.equals("message")) {

                replyToUser(payload.events[0].replyToken, "Message ID : "+messageId+"TYPE : "+messageType);
//                if (msgText.contains("/reset")) {
//                    DaoImpl obj = new DaoImpl();
//                    int update= obj.UpdateFlag(sender.getUserId(),"default");
//                    if (update!=0){
//                        replyToUser(payload.events[0].replyToken, "BOT Telah direset");
//                    }
//                }
//                if (msgText.contains("/check")) {
//                    replyToUser(payload.events[0].replyToken, "Kamu dalam sesi cari masjid"+messageType);
//                }
//                if (messageType.equals("location")){
//                   replyToUser(payload.events[0].replyToken,"Lokasi Terdeteksi");
//                }

            }
        } else {
        if (eventType.equals("message")) {
            msgText = payload.events[0].message.text;
            msgText = msgText.toLowerCase();
            String idUser = payload.events[0].source.userId;
            if (msgText.contains("bot leave")) {
                if (payload.events[0].source.type.equals("group")) {
                    leaveGR(payload.events[0].source.groupId, "group");
                } else if (payload.events[0].source.type.equals("room")) {
                    leaveGR(payload.events[0].source.roomId, "room");
                }
            }
            if (msgText.contains("/carimasjid")) {
                DaoImpl obj = new DaoImpl();
                  int update= obj.UpdateFlag(sender.getUserId(),"cari masjid");
                  if (update!=0){
                      replyToUser(payload.events[0].replyToken, "Kirimkan Lokasi Saat Ini");
                  }
            }
            if (msgText.contains("register")) {
                DaoImpl obj = new DaoImpl();
                User oUser = new User(sender.getUserId(), "default", sender.getDisplayName(), "0", "0");
                int id = obj.RegisterUser(oUser);
                if (id == 1) {
                    replyToUser(payload.events[0].replyToken, "Data Kamu Berhasil Masuk Database");
                } else {
                    replyToUser(payload.events[0].replyToken, "Gagal, Kamu Sudah Terdaftar!");
                }
            }
            if (msgText.contains("/check")) {
                replyToUser(payload.events[0].replyToken, "Kamu dalam sesi default");
            }
            if (msgText.contains("/id")) {
                replyToUser(payload.events[0].replyToken, "ID KAMU : " + sender.getUserId());
            }
            if (msgText.contains("kalender")) {

                Date oTanggal = new Date();
                String Tanggal = new SimpleDateFormat("dd-mm-yyyy").format(oTanggal);
                Kalender oKal = new Kalender();
                oKal.getKalender(Tanggal, new interKalender() {
                    @Override
                    public void onSuccess(String[] value) {
                        replyToUser(payload.events[0].replyToken, "Ini Tanggalnya " + value[1]);
                    }
                });
            }
            if (msgText.contains("cari masjid")) {
                CariMasjid obj = new CariMasjid();
                obj.getDataMasjid(new interCariMasjid() {
                    @Override
                    public void onSuccess(List<Result> value) {
                        obj.replyToUser(payload.events[0].replyToken, lChannelAccessToken, value);
                    }
                });
            }
            if (msgText.contains("kelompok")) {
                replyToUser(payload.events[0].replyToken, "BOT KELOMPOK 3");
            }
            if (msgText.contains("test")) {
                replyToUser(payload.events[0].replyToken, "TERHUBUNG OKE");
            }
            if (msgText.contains("nama")) {
                replyToUser(payload.events[0].replyToken, "ilham prasetyo");
            }
            if (msgText.contains("daniel")) {
                replyToUser(payload.events[0].replyToken, "Fransiskus Xaverius Daniel S");
            }
            if (msgText.contains("nim ilham")) {
                replyToUser(payload.events[0].replyToken, "10116496");
            }
            if (msgText.equals("coba ini")) {
                String[] word = msgText.split("\\s");

                replyToUser(payload.events[0].replyToken, "GET KATA : " + word[1]);
            }
            if (msgText.substring(0, 2).contains("qs")) {
                String[] data = msgText.split("\\s");
                String[] dataquran = data[1].split(":");
                Quran obj = new Quran();
                obj.getQuran(dataquran[0], dataquran[1], new interQuran() {
                    @Override
                    public void onSuccess(String[] value) {
                        String data = dataquran[0] + "_" + dataquran[1];
                        obj.replyToUser(payload.events[0].replyToken, lChannelAccessToken, value, data);
                    }
                });
            }
            if (msgText.substring(0, 13).contains("jadwal sholat")) {


                String[] kota = msgText.split("\\s");
                JadwalSholat obj = new JadwalSholat();
                obj.getJadwal(kota[2], new interJdwlSholat() {
                    @Override
                    public void onSuccess(String[] value) {

                        ButtonsTemplate buttonsTemplate = new ButtonsTemplate(
                                "https://islamify.id/imagebot/FIXJADWALL.png",
                                "Sekarang Menuju Waktu",
                                "Isya " + value[5],
                                Arrays.asList(
                                        new PostbackAction("Shubuh " + value[1], "#"),
                                        new PostbackAction("Dzuhur " + value[2], "#1"),
                                        new PostbackAction("Ashar " + value[3], "#"),
                                        new PostbackAction("Maghrib " + value[4], "#")
                                ));

                        replyTemplateToUser(
                                payload.events[0].replyToken,
                                "Jadwal Sholat Hari Ini"
                                , buttonsTemplate
                        );
                    }
                });


            }

        }
        if (eventType.equals("postback")) {
            postBack = payload.events[0].postback.data;
            if (postBack.substring(0, 5).contains("next_")) {
                String[] dataayat = postBack.split("_");
                int next = Integer.parseInt(dataayat[2]) + 1;
                String datanext = Integer.toString(next);
                Quran obj = new Quran();
                obj.getQuran(dataayat[1], datanext, new interQuran() {
                    @Override
                    public void onSuccess(String[] value) {
                        String data = dataayat[1] + "_" + datanext;
                        obj.replyToUser(payload.events[0].replyToken, lChannelAccessToken, value, data);
                    }
                });
            }
            if (postBack.equals("#1")) {
                replyToUser(payload.events[0].replyToken, "Button Clicked #1");
            }

        }
    }



        return new ResponseEntity<String>(HttpStatus.OK);
    }

    private void getMessageData(String message, String targetID) throws IOException{
        if (message!=null){
            pushMessage(targetID, message);
        }
    }
    private UserProfileResponse getUserProfile(String userId) {
        Response<UserProfileResponse> response = null;
        try {
            response = LineMessagingServiceBuilder
                    .create(lChannelAccessToken)
                    .build()
                    .getProfile(userId)
                    .execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (response.isSuccessful()) return response.body();
        else System.out.println(response.code() + " " + response.message());
        return null;
    }

    private void replyToUser(String rToken, String messageToUser){
        TextMessage textMessage = new TextMessage(messageToUser);
        ReplyMessage replyMessage = new ReplyMessage(rToken,textMessage);

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
    private void replyTemplateToUser(String rToken, String alt, Template template){
        TemplateMessage oTemplate=new TemplateMessage(alt,template);

        ReplyMessage replyMessage = new ReplyMessage(rToken, oTemplate);
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

    private void pushMessage(String sourceId, String txt){
        TextMessage textMessage = new TextMessage(txt);
        PushMessage pushMessage = new PushMessage(sourceId,textMessage);
        try {
            Response<BotApiResponse> response = LineMessagingServiceBuilder
                    .create(lChannelAccessToken)
                    .build()
                    .pushMessage(pushMessage)
                    .execute();
            System.out.println(response.code() + " " + response.message());
        } catch (IOException e) {
            System.out.println("Exception is raised ");
            e.printStackTrace();
        }
    }

    private void leaveGR(String id, String type){
        try {
            if (type.equals("group")){
                Response<BotApiResponse> response = LineMessagingServiceBuilder
                        .create(lChannelAccessToken)
                        .build()
                        .leaveGroup(id)
                        .execute();
                System.out.println(response.code() + " " + response.message());
            } else if (type.equals("room")){
                Response<BotApiResponse> response = LineMessagingServiceBuilder
                        .create(lChannelAccessToken)
                        .build()
                        .leaveRoom(id)
                        .execute();
                System.out.println(response.code() + " " + response.message());
            }
        } catch (IOException e) {
            System.out.println("Exception is raised ");
            e.printStackTrace();
        }
    }

    //START HERE

}
