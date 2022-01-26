package com.qa.client;

import com.sun.net.httpserver.Headers;
import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

public class RestClient {

    //Get Method
    public void get(String url) throws ClientProtocolException, IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse httpResponse = httpClient.execute(httpGet);

        //response code
        int responseCode = httpResponse.getStatusLine().getStatusCode();
        System.out.println("Response Code ---> "+responseCode);

        //Response JSON
        String jsonResponse = EntityUtils.toString(httpResponse.getEntity());
        JSONObject jsonObj = new JSONObject(jsonResponse);
        System.out.println("JSON Response --->"+jsonObj);

        //All Headers
        Header[] headers = httpResponse.getAllHeaders();

        HashMap<String,String> hashmap = new HashMap<String,String>();
        for (Header header : headers){
            hashmap.put(header.getName(),header.getValue());
        }
        System.out.println("All Headers ---> "+hashmap);
    }

}
