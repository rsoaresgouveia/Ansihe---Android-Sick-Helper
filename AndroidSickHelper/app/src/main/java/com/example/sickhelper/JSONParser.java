package com.example.sickhelper;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;
 
public class JSONParser {
 
    static InputStream is = null;
    static JSONObject jObj = null;
    static String json = "";
 
    public JSONParser() {
    }
    
    public JSONObject getJSONFromUrl(final String url) {

        try {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);

            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            is = httpEntity.getContent();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            
            is.close();
            json = sb.toString();
        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }

        try {
            jObj = new JSONObject(json);
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }

        return jObj;

    }
    
 
    public JSONObject makeHttpRequest(String url, String method, List<NameValuePair> params) {
 
        try {
             if("POST".equals(method)){
                DefaultHttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(url);
                httpPost.setEntity(new UrlEncodedFormEntity(params));

				System.out.println("Chegou parser");

			    System.out.println(httpPost);

                 //erro aqui - aplicativo para de funcionar
                HttpResponse httpResponse = httpClient.execute(httpPost);

                System.out.println("Chegou parser1.0");

                HttpEntity httpEntity = httpResponse.getEntity();

				System.out.println("Chegou parser1.1");

                is = httpEntity.getContent();
				System.out.println("Chegou parser2");

            }else if("GET".equals(method)){
				System.out.println("Chegou parser3");
                DefaultHttpClient httpClient = new DefaultHttpClient();
                String paramString = URLEncodedUtils.format(params, "utf-8");
                url += "?" + paramString;
                HttpGet httpGet = new HttpGet(url);
				System.out.println("Chegou parser4");

                HttpResponse httpResponse = httpClient.execute(httpGet);
                HttpEntity httpEntity = httpResponse.getEntity();
                is = httpEntity.getContent();
				System.out.println("Chegou parser5");

            }           
 
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
 
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "iso-8859-1"), 8);

            System.out.println("Chegou parser 2.1");

            StringBuilder sb = new StringBuilder();

            System.out.println("Chegou parser 2.2");

            String line = null;

            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");

                System.out.println("Chegou parser 2.3");
            }

            is.close();

            json = sb.toString();
            System.out.println(json);

        } catch (Exception e) {
            System.out.println("Error converting result " + e.toString());
            System.out.println("Chegou parser catch");
        }
 
        try {
            jObj = new JSONObject(json);

            System.out.println("Chegou parser 2.4");

        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }

        System.out.println("fkm");
        System.out.println(jObj);
        return jObj;

 
    }
}
