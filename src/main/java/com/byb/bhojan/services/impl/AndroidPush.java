package com.byb.bhojan.services.impl;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import com.byb.bhojan.services.FCMDeviceServices;
import com.byb.bhojan.util.ProjectConstant;

@Service
public class AndroidPush {

  Logger logger = Logger.getLogger(AndroidPush.class);

  @Autowired
  private FCMDeviceServices FCMdeviceServices;

  @SuppressWarnings("unchecked")
  @Async
  public void sendPushNotification(String title, String message, String userId) {

    String fcmToken = userId;// FCMdeviceServices.getFCMtokenByUserId(userId);

    if (fcmToken != null) {
      JSONObject pushOuterObj = new JSONObject();

      JSONObject pushInnerObj = new JSONObject();

      pushInnerObj.put("title", title);
      pushInnerObj.put("message", message);

      pushOuterObj.put("data", pushInnerObj);
      pushOuterObj.put("to", fcmToken);

      String pushMessage = pushOuterObj.toJSONString();

      // Create connection to send FCM Message request.

      try {
        System.out.println(pushMessage);
        URL url = new URL("https://fcm.googleapis.com/fcm/send");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestProperty("Authorization", "key=" + ProjectConstant.SERVER_KEY);
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);

        // Send FCM message content.
        OutputStream outputStream = conn.getOutputStream();
        outputStream.write(pushMessage.getBytes());

        System.out.println(conn.getResponseCode());
        System.out.println(conn.getResponseMessage());
      } catch (Exception e) {
        // TODO: handle exception
        e.printStackTrace();
      }
    } else {
      logger.info("FCM token not found for userId: " + userId);
    }

  }

  public static void main(String[] args) throws Exception {
    String title = "Title";
    String message = "Message";
    String userId = "82b798aa-5113-4e27-80e1-99a52e220a34";
    new AndroidPush().sendPushNotification(title, message, userId);
  }

}
