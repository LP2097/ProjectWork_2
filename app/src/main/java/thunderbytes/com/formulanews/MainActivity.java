package thunderbytes.com.formulanews;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import thunderbytes.com.formulanews.Fragments.CalendarFragment;
import thunderbytes.com.formulanews.Fragments.PilotsRanking;

import android.util.Log;
import java.io.IOException;
import thunderbytes.com.formulanews.Managers.HttpManager;
import thunderbytes.com.formulanews.Tasks.HttpGetTask;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                switch (item.getItemId()) {
                    case R.id.action_recents:
                        fragmentTransaction.replace(R.id.dynamicFragmentFrameLayout, new CalendarFragment());
                        fragmentTransaction.commit();
                        break;

                    case R.id.action_favorites:
                        //Classifica piloti
                        fragmentTransaction.replace(R.id.dynamicFragmentFrameLayout, new PilotsRanking());
                        fragmentTransaction.commit();

                        break;

                    case R.id.action_nearby:
                        //mettere collegamento fragment Costruttori
                        //fragmentTransaction.replace(R.id.dynamicFragmentFrameLayout, new BuildersRankingFragment());
                        //fragmentTransaction.commit();
                        break;
                }
                return true;
            }
        });

        HttpGetTask httpTask = (HttpGetTask) new HttpGetTask().execute();
        String response = httpTask.getResponse();

    }
}
