package thunderbytes.com.formulanews.Activities;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import thunderbytes.com.formulanews.Broadcast.NotificationPublisher;
import thunderbytes.com.formulanews.Fragments.DetailFragmentDate;
import thunderbytes.com.formulanews.Fragments.DetailFragmentRasult;
import thunderbytes.com.formulanews.MainActivity;
import thunderbytes.com.formulanews.Managers.NotificationManager;
import thunderbytes.com.formulanews.Models.Race;
import thunderbytes.com.formulanews.R;

public class DetailRace extends AppCompatActivity {


    private static final String FRAGMENT_TAG = "FRAGMENT_INFO";
    private static final String SHARED_PREF_NOTIFICATION = "notification";
    private String KEY_NOTIFICATION;
    private Race vRace;
    private int vRaceNumber;
    private boolean notify = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_layout);

        TextView mTitle = findViewById(R.id.title_detailActivity);
        ImageView mCircuit = findViewById(R.id.circuitRace);
        ImageButton mNotification = findViewById(R.id.buttonNotification);

        Button mClock = findViewById(R.id.btn_clock);
        Button mResult = findViewById(R.id.btn_result);

        Button mQualification = findViewById(R.id.btn_qualif);
        Button mRace = findViewById(R.id.btn_race);


        mQualification.setVisibility(View.GONE);
        mRace.setVisibility(View.GONE);


        Bundle vBundle =  getIntent().getExtras();
        if (vBundle != null){
            vRace = (Race) vBundle.getSerializable("ITEM_RACE");
            vRaceNumber = vBundle.getInt("RACE_NUMBER");

            mTitle.setText(vRace.getRaceName());
            int id = getResources().getIdentifier("thunderbytes.com.formulanews:drawable/" + vRace.getCircuit().getLocation().getCountry().toString().toLowerCase(), null, null);
            mCircuit.setImageResource(id);
        }

        //controllare se la data è già passata o meno, se è già passata disattivare il click
        //della notifica

        KEY_NOTIFICATION = vRace.getRaceName();

        mClock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnSelected(mClock);
                Button[] arrayBtn = new Button[]{mResult};
                setBtnsNotSelect(arrayBtn);

                mQualification.setVisibility(View.GONE);
                mRace.setVisibility(View.GONE);

                launchClockFragment();
            }
        });

        mResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnSelected(mResult);
                Button[] arrayBtn = new Button[]{mClock};
                setBtnsNotSelect(arrayBtn);

                mQualification.setVisibility(View.VISIBLE);
                mRace.setVisibility(View.VISIBLE);

            }
        });


        mQualification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnSelected(mQualification);
                Button[] arrayBtn = new Button[]{mRace};
                setBtnsNotSelect(arrayBtn);

                launchResultFragment("qualifying", vRaceNumber);
            }
        });

        mRace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btnSelected(mRace);
                Button[] arrayBtn = new Button[]{mQualification};
                setBtnsNotSelect(arrayBtn);

                launchResultFragment("race", vRaceNumber);
            }
        });


        mNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!vRace.isNotify())
                {
                    vRace.setNotify(true);
                    enableNotification();
                    mNotification.setImageResource(R.drawable.bell_select);
                }
                else {
                    vRace.setNotify(false);
                    disableNotification();
                    mNotification.setImageResource(R.drawable.bell);
                }

            }
        });

        vRace.setNotify(getNotification());
        if(vRace.isNotify())
        {
            mNotification.setImageResource(R.drawable.bell_select);

        }


        //faccio visualizzare di default il bottone dell'orario premuto
        btnSelected(mClock);
        Button[] arrayBtn = new Button[]{mResult};
        setBtnsNotSelect(arrayBtn);

        mQualification.setVisibility(View.GONE);
        mRace.setVisibility(View.GONE);

        launchClockFragment();

    }

    private void enableNotification(){
        NotificationManager.setReminder(this, NotificationPublisher.class,DetailRace.class,vRace);
        Toast.makeText(this, "Notifica Attivata", Toast.LENGTH_SHORT).show();
    }

    private void disableNotification(){
        NotificationManager.cancelReminder(this,NotificationPublisher.class);
        Toast.makeText(this, "Notifica Disattivata", Toast.LENGTH_SHORT).show();
    }

    private void launchClockFragment(){

        FragmentManager manager = getFragmentManager();
        FragmentTransaction vFT = getFragmentManager().beginTransaction();

        DetailFragmentDate vDetailFragmentRasult = DetailFragmentDate.newIstance();
        Bundle vBundle = new Bundle();
        vBundle.putSerializable("ITEM_RACE", vRace);
        vDetailFragmentRasult.setArguments(vBundle);

        // Se il fragmnet non esiste lo crea e basta, se invece esiste già un fragment lo rimpiazza
        // con quello esistente.
        if(manager.findFragmentByTag(FRAGMENT_TAG) != null){
            vFT.replace(R.id.fragmentItem, vDetailFragmentRasult, FRAGMENT_TAG);
        }else{
            vFT.add(R.id.fragmentItem, vDetailFragmentRasult, FRAGMENT_TAG);
        }

        vFT.commit();
    }

    private void launchResultFragment(String aTypeRace, int aRaceNumber){

        FragmentManager manager = getFragmentManager();
        FragmentTransaction vFT = getFragmentManager().beginTransaction();

        DetailFragmentRasult vDetailFragmentRasult = DetailFragmentRasult.newIstance();
        Bundle vBundle = new Bundle();
        vBundle.putString("ITEM_TYPE_RACE", aTypeRace);
        vBundle.putInt("ITEM_RACE_NUMBER", aRaceNumber);
        vDetailFragmentRasult.setArguments(vBundle);

        // Se il fragmnet non esiste lo crea e basta, se invece esiste già un fragment lo rimpiazza
        // con quello esistente.
        if(manager.findFragmentByTag(FRAGMENT_TAG) != null){
            vFT.replace(R.id.fragmentItem, vDetailFragmentRasult, FRAGMENT_TAG);
        }else{
            vFT.add(R.id.fragmentItem, vDetailFragmentRasult, FRAGMENT_TAG);
        }

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

    public void onBackPressed() {
        int backStackEntryCount = getSupportFragmentManager().getBackStackEntryCount();
        if(vRace.isNotify())
        {
            saveNotification();
        }
        else {
            removeNotification();
        }

        if (isTaskRoot()) {
           Intent vIntent = new Intent(this, MainActivity.class);   // write your code to switch between fragments.
            startActivity(vIntent);
        } else {
            super.onBackPressed();
        }
    }


    private boolean getNotification() {
        SharedPreferences sp = getSharedPreferences(SHARED_PREF_NOTIFICATION, MODE_PRIVATE);
        boolean notify = sp.getBoolean(KEY_NOTIFICATION, false);

        if (notify) {
            return true;
        }

        return false;
    }

    private void saveNotification() {

        SharedPreferences sp = getSharedPreferences(SHARED_PREF_NOTIFICATION, MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        editor.putBoolean(KEY_NOTIFICATION, true);

        editor.apply();

    }

    private void removeNotification()
    {
        SharedPreferences sp = getSharedPreferences(SHARED_PREF_NOTIFICATION, MODE_PRIVATE);
        sp.edit().remove(KEY_NOTIFICATION).commit();

    }


}

