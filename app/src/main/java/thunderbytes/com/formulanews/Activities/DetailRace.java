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
        TextView vFP1 = findViewById(R.id.labelFP1);
        TextView vFP2 = findViewById(R.id.labelFP2);
        TextView vFP3 = findViewById(R.id.labelFP3);
        TextView vQualification = findViewById(R.id.labelQualification);
        TextView vRace = findViewById(R.id.labelRace);
        Button vNotification = findViewById(R.id.buttonNotification);

        listView = findViewById(R.id.listViewResult);

        vNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enableNotification();
            }
        });
    }

    private void enableNotification(){
        NotificationManager.setReminder(this, NotificationPublisher.class,DetailRace.class,2000);
    }
}
