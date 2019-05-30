package thunderbytes.com.formulanews.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import thunderbytes.com.formulanews.Broadcast.NotificationPublisher;
import thunderbytes.com.formulanews.Managers.NotificationManager;
import thunderbytes.com.formulanews.R;

public class DetailRace extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_race);


        TextView vTitle = findViewById(R.id.title_detailActivity);
        ImageView vCircuit = findViewById(R.id.circuitRace);

        listView = findViewById(R.id.listViewResult);
    }

    private void enableNotification(){
        NotificationManager.setReminder(this, NotificationPublisher.class,DetailRace.class,2000);
    }
}
