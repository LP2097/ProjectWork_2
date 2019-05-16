package thunderbytes.com.formulanews;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import thunderbytes.com.formulanews.Activities.DetailRace;
import thunderbytes.com.formulanews.Fragments.ListFragment;
import android.util.Log;
import java.io.IOException;
import thunderbytes.com.formulanews.Managers.HttpManager;
import thunderbytes.com.formulanews.Tasks.HttpGetTask;


public class MainActivity extends AppCompatActivity implements ListFragment.OnItemClicked{

    Fragment fragment;
    Bundle bundle = new Bundle();
    thunderbytes.com.formulanews.Models.Fragment fragmentModel = new thunderbytes.com.formulanews.Models.Fragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (savedInstanceState == null) {

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragment = new ListFragment();

            fragmentModel.fragmentId = 0;
            setBundleId();

            fragmentTransaction.replace(R.id.dynamicFragmentFrameLayout, fragment).commit();

        }

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragment = new ListFragment();


                switch (item.getItemId()) {
                    case R.id.action_recents:
                        //Calendario
                        fragmentModel.fragmentId = 0;
                        setBundleId();
                        break;

                    case R.id.action_favorites:
                        //Classifica piloti
                        fragmentModel.fragmentId = 1;
                        setBundleId();
                        break;

                    case R.id.action_nearby:
                        //Classifica costruttori
                        fragmentModel.fragmentId = 2;
                        setBundleId();
                        break;

                    default:
                        fragment = new ListFragment();
                        fragmentModel.fragmentId = 0;
                        break;
                }
                fragmentTransaction.replace(R.id.dynamicFragmentFrameLayout, fragment).commit();
                return true;
            }

        });

        //HttpGetTask httpTask = (HttpGetTask) new HttpGetTask().execute();
        //String response = httpTask.getResponse();
    }

    @Override
    public void onItemValue(String aCircuit) {
        Intent vIntent = new Intent(this, DetailRace.class);
        Bundle vBundle = new Bundle();
        vBundle.putString("ITEM_NAME_CIRCUIT", aCircuit);
        vIntent.putExtras(vBundle);
        startActivity(vIntent);
    }

    public void setBundleId() {

        bundle.putInt(ListFragment.ID, fragmentModel.fragmentId);
        fragment.setArguments(bundle);

    }

}
