package thunderbytes.com.formulanews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;

import thunderbytes.com.formulanews.Managers.HttpManager;
import thunderbytes.com.formulanews.Tasks.HttpGetTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HttpGetTask httpTask = (HttpGetTask) new HttpGetTask().execute();
        String response = httpTask.getResponse();
    }
}
