package thunderbytes.com.formulanews.Managers;

import android.util.Log;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpManager {
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    private static OkHttpClient httpClient = new OkHttpClient();

    public static String GET(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            String body = response.body().string();
            Log.i("HTTP", "GET: "+body);
            return body;
        }
    }

    String POST(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = httpClient.newCall(request).execute()) {
            return response.body().string();
        }
    }

}
