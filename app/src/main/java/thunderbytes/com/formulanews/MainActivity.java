package thunderbytes.com.formulanews;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Parcelable;
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
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;


import java.io.Serializable;
import java.security.Provider;
import java.util.ArrayList;

import thunderbytes.com.formulanews.Activities.DetailRace;
import thunderbytes.com.formulanews.CacheManager.CacheManager;
import thunderbytes.com.formulanews.Fragments.ListFragment;
import thunderbytes.com.formulanews.Managers.SeasonManager;
import thunderbytes.com.formulanews.Managers.StandingManager;
import thunderbytes.com.formulanews.Models.Race;
import thunderbytes.com.formulanews.Models.Season;
import thunderbytes.com.formulanews.Models.Standings;

public class MainActivity extends AppCompatActivity implements ListFragment.OnItemClicked, SeasonManager.OnSeasonFetched, StandingManager.OnStandingsFetched {
    Fragment fragment;
    Bundle bundle = new Bundle();
    thunderbytes.com.formulanews.Models.Fragment fragmentModel = new thunderbytes.com.formulanews.Models.Fragment();
    MenuItem lastSelected;
    CacheManager mChache;
    private ProgressBar pgsBar;
    TextView textLoading;
    Race race;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Logger.addLogAdapter(new AndroidLogAdapter());

        pgsBar = (ProgressBar) findViewById(R.id.pBar);
        textLoading = findViewById(R.id.loading);


        //0) istanzio la chace dove salvero le gare, i piloti e i costruttori,
        // trovandosi nel main persistera per tutta la durata del ciclo di vita dell'activity
        mChache = new CacheManager();


        if (savedInstanceState == null) {
            fragment = new ListFragment();
            new SeasonManager(2018, MainActivity.this);
            fragmentModel.fragmentId = 0;
            setBundleId();

        }
        else
        {
            pgsBar.setVisibility(View.GONE);
            textLoading.setVisibility(View.GONE);
        }
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                fragment = new ListFragment();
                if(lastSelected != null){
                    setTitleBottomMenuColor(lastSelected, Color.BLACK);
                }
                lastSelected = item;
                setTitleBottomMenuColor(item, Color.RED);

                //FragmentManager fragmentManager = getSupportFragmentManager();
               // FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                switch (item.getItemId())
                {
                    case R.id.action_recents:

                        //Calendario
                        fragmentModel.fragmentId = 0;

                        //1) verifico se la cache ha qualche dato salvato
                        if(mChache.getRaces() != null) {
                            //2.1) passo nel bundle i dati presenti gia nella cache senza dover fare altre chiamate
                            bundle.putSerializable(ListFragment.ITEM, mChache.getRaces());
                            setFragmentTransaction();

                        }
                        else {
                            //2.2) non ci sono dati presenti, faccio la chiamata asincrona per ricevere i dati
                            new SeasonManager(2018, MainActivity.this);
                        }

                        setBundleId();
                        break;

                    case R.id.action_favorites:

                        //Classifica piloti
                        if(mChache.getDrivers() != null) {
                            bundle.putSerializable(ListFragment.ITEM, mChache.getDrivers());
                            setFragmentTransaction();
                        }
                        else {
                            new StandingManager(2018, StandingManager.StandingType.driverStandings, (StandingManager.OnStandingsFetched) MainActivity.this);
                        }

                        fragmentModel.fragmentId = 1;
                        setBundleId();
                        break;

                    case R.id.action_nearby:

                        //Classifica costruttori
                        if(mChache.getConstructors() != null) {
                            bundle.putSerializable(ListFragment.ITEM, mChache.getConstructors());
                            setFragmentTransaction();

                        }
                        else {
                            new StandingManager(2018, StandingManager.StandingType.constructorStandings, (StandingManager.OnStandingsFetched) MainActivity.this);
                        }

                        fragmentModel.fragmentId = 2;
                        setBundleId();
                        break;

                    default:
                        if(mChache.getRaces() != null) {
                            //2.1) passo nel bundle i dati presenti gia nella cache senza dover fare altre chiamate
                            bundle.putSerializable(ListFragment.ITEM, mChache.getRaces());
                            setFragmentTransaction();

                        }
                        else {
                            //2.2) non ci sono dati presenti, faccio la chiamata asincrona per ricevere i dati
                            new SeasonManager(2018, MainActivity.this);
                        }
                        setBundleId();
                        break;
                }
                return true;
            }
        });



    }

    //INTERFACCIA PER IL CLICK DEL SINGOLO ITEM - ListFragment
    @Override
    public void onItemValue(Race aRace){
        //1) richiamo l'activity detailRace
        Intent vIntent = new Intent(this, DetailRace.class);
        //2) aggiungo un bundle per passare il valore della gara selezionata
        Bundle vBundle = new Bundle();
        vBundle.putSerializable("ITEM_RACE", aRace);
        vIntent.putExtras(vBundle);
        //3) faccio partire l'activity
        startActivity(vIntent);
    }

    public void setBundleId() {
        bundle.putInt(ListFragment.ID, fragmentModel.fragmentId);
    }


    private void setTitleBottomMenuColor(MenuItem item, int color){
        SpannableString text = new SpannableString(item.getTitle().toString());
        text.setSpan(new ForegroundColorSpan(color), 0,text.length(),0);
        text.setSpan(new StyleSpan(Typeface.BOLD),0,text.length(),0);
        item.setTitle(text);
    }


    //GESTIONE RISPOSTE CHIAMATE ASINCRONE
    @Override
    public void onSeasonRetrievedSuccessfully(Season season) {

        //1) inserisco nel bundle che passero nel listfragment l'array di corse ricevute dalla chiamata
        bundle.putSerializable(ListFragment.ITEM, season.getRaces());
        Logger.d(season.getRaces());
        for (int i = 0; i<season.getRaces().size(); i++)
        {
            season.getRaces().get(i).setId(i);
        }
        //2) salvo nella chache l'array, utile poi
        // nell onNavigationItemSelected dove controllero se la cache ha dati salvati o meno
        mChache.setRaces(season.getRaces());
        //3) carico il fragment di mio interesse
        pgsBar.setVisibility(View.GONE);
        textLoading.setVisibility(View.GONE);
        setFragmentTransaction();


    }

    @Override
    public void onStandingsRetrievedSuccessfully(ArrayList<Standings> standings) {

        //1) PREMESSA, questo metodo gestisce sia l'array di costruttori che di piloti

        //2) devo verificare quale elementi mi ritorna la chiamata e assegnare gli elementi nel modo giusto
        if (standings.get(0).ConstructorStandings != null) {

            //3)  salvo nella chache l'array
            mChache.setConstructors(standings.get(0).ConstructorStandings);
            bundle.putSerializable(ListFragment.ITEM, standings.get(0).ConstructorStandings);
        }
        else if (standings.get(0).DriverStandings != null)
        {
            //3)  salvo nella chache l'array
            mChache.setDrivers(standings.get(0).DriverStandings);
            bundle.putSerializable(ListFragment.ITEM, standings.get(0).DriverStandings);
        }
        //4) carico il fragment di mio interesse
        setFragmentTransaction();

    }


    private void setFragmentTransaction()
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //assegno al mio fragment il bundle che conterra l'id della view
        // che vorro caricare e l'array di elementi che mi interessa
        fragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.dynamicFragmentFrameLayout, fragment).commit();
    }






}