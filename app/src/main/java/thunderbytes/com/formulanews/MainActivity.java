package thunderbytes.com.formulanews;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
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

    Fragment fragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if (savedInstanceState == null) {
            fragmentTransaction.replace(R.id.dynamicFragmentFrameLayout, new CalendarFragment());
            fragmentTransaction.addToBackStack(null);

            fragmentTransaction.commit();
        }

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                switch (item.getItemId()) {
                    case R.id.action_recents:
                        fragment = new CalendarFragment();
                        break;

                    case R.id.action_favorites:
                        //Classifica piloti
                        fragment = new PilotsRanking();
                        break;

                    case R.id.action_nearby:
                        //mettere collegamento fragment Costruttori
                        //fragmentTransaction.replace(R.id.dynamicFragmentFrameLayout, new BuildersRankingFragment());
                        //fragmentTransaction.commit();
                        break;
                }
                fragmentTransaction.replace(R.id.dynamicFragmentFrameLayout, fragment).commit();
                return true;
            }

        });

        //HttpGetTask httpTask = (HttpGetTask) new HttpGetTask().execute();
        //String response = httpTask.getResponse();
    }
}
