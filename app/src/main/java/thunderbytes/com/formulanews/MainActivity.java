package thunderbytes.com.formulanews;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.design.widget.*;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.MenuItem;

import java.time.format.TextStyle;
import java.util.ArrayList;
import thunderbytes.com.formulanews.Activities.DetailRace;
import thunderbytes.com.formulanews.Fragments.ListFragment;
import thunderbytes.com.formulanews.Managers.SeasonManager;
import thunderbytes.com.formulanews.Managers.StandingManager;
import thunderbytes.com.formulanews.Managers.StandingManager.StandingType;
import thunderbytes.com.formulanews.Models.Season;
import thunderbytes.com.formulanews.Models.Standings;

public class MainActivity extends AppCompatActivity implements ListFragment.OnItemClicked, SeasonManager.OnSeasonFetched, StandingManager.OnStandingsFetched {
    Fragment fragment;
    Bundle bundle = new Bundle();
    thunderbytes.com.formulanews.Models.Fragment fragmentModel = new thunderbytes.com.formulanews.Models.Fragment();
    MenuItem lastSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new SeasonManager(2018, this);
        new StandingManager(2018, StandingType.constructorStandings, this);

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
                if(lastSelected != null){
                    setTitleBottomMenuColor(lastSelected, Color.BLACK);
                }
                lastSelected = item;
                setTitleBottomMenuColor(item, Color.RED);


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

    @Override
    public void onSeasonRetrievedSuccessfully(Season season) {
        Log.d("IHUBDWIDGHUIUGW", season.getRaces().get(0).getRaceName());
    }

    @Override
    public void onStandingsRetrievedSuccessfully(ArrayList<Standings> standings) {
        Log.d("IHUBDWIDGHUIUGW", ""+standings.get(0).getConstructorStandings().get(0).getPosition());
    }

    private void setTitleBottomMenuColor(MenuItem item, int color){
        SpannableString text = new SpannableString(item.getTitle().toString());
        text.setSpan(new ForegroundColorSpan(color), 0,text.length(),0);
        text.setSpan(new StyleSpan(Typeface.BOLD),0,text.length(),0);
        item.setTitle(text);
    }
}
