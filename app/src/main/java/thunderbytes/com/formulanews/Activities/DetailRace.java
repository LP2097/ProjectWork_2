package thunderbytes.com.formulanews.Activities;

import android.annotation.SuppressLint;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Array;

import thunderbytes.com.formulanews.Broadcast.NotificationPublisher;
import thunderbytes.com.formulanews.Fragments.DetailFragment;
import thunderbytes.com.formulanews.Managers.NotificationManager;
import thunderbytes.com.formulanews.Models.Race;
import thunderbytes.com.formulanews.R;

public class DetailRace extends AppCompatActivity {

    private static final String FRAGMENT_TAG = "tag fragment";
    ListView listView;
    private Race vRace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_layout);

        TextView mTitle = findViewById(R.id.title_detailActivity);
        ImageView mCircuit = findViewById(R.id.circuitRace);
        Button mFP1 = findViewById(R.id.btn_fp1);
        Button mFP2 = findViewById(R.id.btn_fp2);
        Button mFP3 = findViewById(R.id.btn_fp3);
        Button mQualification = findViewById(R.id.btn_qualif);
        Button mRace = findViewById(R.id.btn_race);
        ImageButton mNotification = findViewById(R.id.buttonNotification);


        Bundle vBundle =  getIntent().getExtras();
        if (vBundle != null){
            vRace = (Race) vBundle.getSerializable("ITEM_RACE");
            mTitle.setText(vRace.getRaceName());
            int id = getResources().getIdentifier("thunderbytes.com.formulanews:drawable/" + vRace.getCircuit().getLocation().getCountry().toString().toLowerCase(), null, null);
            mCircuit.setImageResource(id);
        }

        mFP1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnSelected(mFP1);
                Button[] arrayBtn = new Button[]{mFP2,mFP3,mQualification,mRace};
                setBtnsNotSelect(arrayBtn);
            }
        });

        mFP2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnSelected(mFP2);
                Button[] arrayBtn = new Button[]{mFP1,mFP3,mQualification,mRace};
                setBtnsNotSelect(arrayBtn);
            }
        });

        mFP3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnSelected(mFP3);
                Button[] arrayBtn = new Button[]{mFP1,mFP2,mQualification,mRace};
                setBtnsNotSelect(arrayBtn);
            }
        });

        mQualification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnSelected(mQualification);
                Button[] arrayBtn = new Button[]{mFP1,mFP2,mFP3,mRace};
                setBtnsNotSelect(arrayBtn);
            }
        });

        mRace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = 0;
                String Name = "Sebastian Vettel";
                String Time = "1.11.143";
                String Parting = "--";

                btnSelected(mRace);
                Button[] arrayBtn = new Button[]{mFP1,mFP2,mFP3,mQualification};
                setBtnsNotSelect(arrayBtn);

                //launchDetailFragment(id, Name, Time, Parting);
            }
        });

        launchDetailFragment();

        mNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enableNotification();
                if(!vRace.isNotify())
                {
                    vRace.setNotify(true);
                    mNotification.setImageResource(R.drawable.bell_select);
                }
                else {
                    vRace.setNotify(false);
                    mNotification.setImageResource(R.drawable.bell);
                }

            }
        });
    }

    private void enableNotification(){
        NotificationManager.setReminder(this, NotificationPublisher.class,DetailRace.class,vRace);
    }

    private void launchDetailFragment(){
        FragmentTransaction vFT = getFragmentManager().beginTransaction();
        DetailFragment vDetailFragment = DetailFragment.newIstance();
        vFT.add(R.id.fragmentItem, vDetailFragment, FRAGMENT_TAG);

        vFT.commit();
    }

    void btnSelected(Button vBtn)
    {
        vBtn.setTextColor(Color.BLACK);
        vBtn.setBackgroundColor(Color.WHITE);
    }

    void setBtnsNotSelect(Button[] vBtn)
    {
        for (int i = 0; i < vBtn.length; i++)
        {
            vBtn[i].setBackgroundColor(getResources().getColor(R.color.red));
            vBtn[i].setTextColor(Color.WHITE);
        }
    }

}

