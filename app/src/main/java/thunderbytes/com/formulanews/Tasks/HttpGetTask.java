package thunderbytes.com.formulanews.Tasks;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;

import thunderbytes.com.formulanews.Managers.HttpManager;

public class HttpGetTask extends AsyncTask<String, Void, String> {
    private Exception exception;
    private String res;

    protected String doInBackground(String... urls) {
        String url = urls[0];
        String response = null;
        try {
          response = HttpManager.GET(url);
        }catch (IOException e){
            exception = e;
            Log.e("IO", e.toString());
        }
        res = response;
        return response;
    }

    protected void onPostExecute(String response){

    }

    public String getResponse(){
        return res;
    }
}
