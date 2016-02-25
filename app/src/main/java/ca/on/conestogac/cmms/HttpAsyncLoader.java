package ca.on.conestogac.cmms;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by user on 2016-02-23.
 */
public class HttpAsyncLoader  extends AsyncTaskLoader<String> {
    private String url = null;
    private String method = null;
    private String param = null;


    public HttpAsyncLoader(Context context, String url, String method, String param) {
        super(context);
        this.url = url;
        this.method = method;
        this.param = param;
    }

    @Override
    public String loadInBackground() {
        HttpURLConnection conn = null;
        try {
            if(method.compareTo("GET") == 0){
                //String combinedUrl = url + "?" + URLEncoder.encode(param, "UTF-8");   // don't want to convert "="
                String combinedUrl = url + "?" + param;
                Utility.logDebug(new URL(combinedUrl).toString());
                conn = (HttpURLConnection) new URL(combinedUrl).openConnection();
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                conn.setUseCaches(false);
                conn.connect();
            } else {
                Utility.logDebug(new URL(url).toString());
                Utility.logDebug(param);

                conn = (HttpURLConnection) new URL(url).openConnection();
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.setUseCaches(false);
                //conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestProperty("charset", "utf-8");
                //conn.setRequestProperty("Content-Length", "" + Integer.toString(param.getBytes().length));
                conn.connect();
                Writer writer = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
                writer.write(param);
                writer.close();
            }


            if( conn.getResponseCode() == HttpURLConnection.HTTP_OK ){
                StringBuffer response = new StringBuffer();
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                while ((inputLine = reader.readLine()) != null) {
                    response.append(inputLine);
                }
                return response.toString();
            }
        } catch (IOException e) {
            Utility.logError(e.getMessage());
        } finally {
            if( conn != null ){
                conn.disconnect();
            }
        }
        return null;
    }
}
