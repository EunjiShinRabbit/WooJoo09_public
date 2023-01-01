package com.WooJoo09.service;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class sens_sms {
    public static int makeVerifyCode() {
        return (int) (Math.floor(Math.random() * 999999 - 100000) + 100000);
    }

    public static boolean sendSms(String phoneNumber, int verifyCode) {
        String hostNameUrl = "https://sens.apigw.ntruss.com";     		// 호스트 URL
        String requestUrl= "/sms/v2/services/";                   		// 요청 URL
        String requestUrlType = "/messages";                      		// 요청 URL
        String accessKey = /* 자신의 인증키 */;     	// 네이버 클라우드 플랫폼 회원에게 발급되는 개인 인증키
        String secretKey = /* 자신의 service secret */; 		// 2차 인증을 위해 서비스마다 할당되는 service secret
        String serviceId = /* 자신의 service Id */;  	// 프로젝트에 할당된 SMS 서비스 ID
        String method = "POST";											// 요청 method
        String timestamp = Long.toString(System.currentTimeMillis());   // current timestamp (epoch)
        requestUrl += serviceId + requestUrlType;
        String apiUrl = hostNameUrl + requestUrl;

        String smsContent = "우주공구 인증번호는 ["+verifyCode+"] 입니다.";

        // JSON 을 활용한 body data 생성
        JSONObject toJson = new JSONObject();
        JSONArray toArr = new JSONArray();
        JSONObject bodyJson = new JSONObject();

        toJson.put("subject","");				// 메시지 제목 * LMS Type에서만 사용할 수 있습니다.
        toJson.put("content","");				// 메시지 내용 * Type별로 최대 byte 제한이 다릅니다.* SMS: 80byte / LMS: 2000byte
        toJson.put("to", phoneNumber);					// 수신번호 목록  * 최대 50개까지 한번에 전송할 수 있습니다.

        toArr.add(toJson);

        bodyJson.put("type","sms");				// 메시지 Type (sms | lms)
        bodyJson.put("contentType","COMM");			// 메시지 내용 Type (AD | COMM) * AD: 광고용, COMM: 일반용 (default: COMM) * 광고용 메시지 발송 시 불법 스팸 방지를 위한 정보통신망법 (제 50조)가 적용됩니다.
        bodyJson.put("countryCode","82");		// 국가 전화번호
        bodyJson.put("from", /* 자신의 등록된 번호 */);	// 발신번호 * 사전에 인증/등록된 번호만 사용할 수 있습니다.
//	    bodyJson.put("subject","");				// 메시지 제목 * LMS Type에서만 사용할 수 있습니다.
        bodyJson.put("content", smsContent);	// 메시지 내용 * Type별로 최대 byte 제한이 다릅니다.* SMS: 80byte / LMS: 2000byte
        bodyJson.put("messages", toArr);

        String body = bodyJson.toJSONString();

        System.out.println(body);

        try {

            URL url = new URL(apiUrl);

            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setUseCaches(false);
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setRequestProperty("content-type", "application/json");
            con.setRequestProperty("x-ncp-apigw-timestamp", timestamp);
            con.setRequestProperty("x-ncp-iam-access-key", accessKey);
            con.setRequestProperty("x-ncp-apigw-signature-v2", makeSignature(requestUrl, timestamp, method, accessKey, secretKey));
            con.setRequestMethod(method);
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());

            wr.write(body.getBytes());
            wr.flush();
            wr.close();

            int responseCode = con.getResponseCode();
            BufferedReader br;
            System.out.println("responseCode" +" " + responseCode);
            if(responseCode==202) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }

            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();

            System.out.println(response.toString());
            return true;

        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public static String makeSignature(String url, String timestamp, String method, String accessKey, String secretKey) throws NoSuchAlgorithmException, InvalidKeyException {
        String space = " ";                    // one space
        String newLine = "\n";                 // new line

        String message = new StringBuilder()
                .append(method)
                .append(space)
                .append(url)
                .append(newLine)
                .append(timestamp)
                .append(newLine)
                .append(accessKey)
                .toString();

        SecretKeySpec signingKey;
        String encodeBase64String;
        try {

            signingKey = new SecretKeySpec(secretKey.getBytes("UTF-8"), "HmacSHA256");
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(signingKey);
            byte[] rawHmac = mac.doFinal(message.getBytes("UTF-8"));
            encodeBase64String = Base64.getEncoder().encodeToString(rawHmac);
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            encodeBase64String = e.toString();
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }
        return encodeBase64String;
    }
}
