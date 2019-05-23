package thunderbytes.com.formulanews.Tasks;

import android.os.AsyncTask;
import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

import thunderbytes.com.formulanews.Interfaces.IDataWrapper;
import thunderbytes.com.formulanews.Managers.HttpManager;
import thunderbytes.com.formulanews.Wrappers.RaceMRDataWrapper;

public class HttpGetTask extends AsyncTask<String, Void, String> {

    public interface OnPostExecution{
        void onHttpGetFinished(IDataWrapper jsonObject);
    }

    private OnPostExecution listener;
    private IDataWrapper wrapper;

    public HttpGetTask(OnPostExecution context, IDataWrapper wrapper){
        if(context instanceof OnPostExecution){
            this.wrapper = wrapper;
            listener = context;
        }
    }

   /* private Class<T> castClass;

    public HttpGetTask<T> setCastClass(Class<T> castClass){
        this.castClass = castClass;
        return this;
    }*/

    protected String doInBackground(String... urls) {
        String url = urls[0]+".json";
        String response = null;
        try {
          response = HttpManager.GET(url);
        }catch (IOException e){
            Log.e("THROWN", e.toString());
        }
        return response;
    }

    protected void onPostExecute(String response){
        if(response != null) {
            byte[] jsonData = response.getBytes();

            ObjectMapper objectMapper = new ObjectMapper();
            IDataWrapper jsonObject;
            //Log.d("CLASS",castClass.toString());

            try {
                jsonObject = objectMapper.readValue(jsonData, wrapper.getClass());
            } catch (Exception e) {
                jsonObject = null;
                Log.e("THROWN", e.toString());
            }

            listener.onHttpGetFinished(jsonObject);
        }
        listener = null;
    }
}
