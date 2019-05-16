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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                thunderbytes.com.formulanews.Models.Fragment fragmentModel = new thunderbytes.com.formulanews.Models.Fragment();
                Bundle bundle = new Bundle();

                switch (item.getItemId()) {
                    case R.id.action_recents:
                        //Calendario
                        fragment = new ListFragment();
                        fragmentModel.fragmentId = 0;
                        bundle.putInt(ListFragment.ID, fragmentModel.fragmentId);
                        fragment.setArguments(bundle);
                        break;

                    case R.id.action_favorites:
                        //Classifica piloti
                        fragment = new ListFragment();
                        fragmentModel.fragmentId = 1;
                        bundle.putInt(ListFragment.ID, fragmentModel.fragmentId);
                        fragment.setArguments(bundle);
                        break;

                    case R.id.action_nearby:
                        //Classifica costruttori
                        fragment = new ListFragment();
                        fragmentModel.fragmentId = 2;
                        bundle.putInt(ListFragment.ID, fragmentModel.fragmentId);
                        fragment.setArguments(bundle);
                        break;

                    default:
                        fragment = new ListFragment();
                        fragmentModel.fragmentId = 0;
                        bundle.putInt(ListFragment.ID, fragmentModel.fragmentId);
                        fragment.setArguments(bundle);
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
}
