package thunderbytes.com.formulanews;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import thunderbytes.com.formulanews.Activities.DetailRace;
import thunderbytes.com.formulanews.Activities.FirebaseLogin;
import thunderbytes.com.formulanews.Broadcast.InternetReceiver;
import thunderbytes.com.formulanews.CacheManager.CacheManager;
import thunderbytes.com.formulanews.Dialogue.LogoutDialogue;
import thunderbytes.com.formulanews.Fragments.ListFragment;
import thunderbytes.com.formulanews.Managers.SeasonManager;
import thunderbytes.com.formulanews.Managers.StandingManager;
import thunderbytes.com.formulanews.Models.Race;
import thunderbytes.com.formulanews.Models.Season;
import thunderbytes.com.formulanews.Models.Standings;
import thunderbytes.com.formulanews.Observable.InternetObservable;
import thunderbytes.com.formulanews.Service.NetworkUtil;

import com.bugfender.sdk.Bugfender;

public class MainActivity extends AppCompatActivity implements CacheManager.ICacheManagerStandings , CacheManager.ICacheManagerRace , Observer, ListFragment.OnItemClicked, SeasonManager.OnSeasonFetched, StandingManager.OnStandingsFetched, LogoutDialogue.OnLogoutDialogueListener {
    Fragment fragment;
    Bundle bundle = new Bundle();
    thunderbytes.com.formulanews.Models.Fragment fragmentModel = new thunderbytes.com.formulanews.Models.Fragment();
    MenuItem lastSelected;
    CacheManager mChache;
    private ProgressBar pgsBar;
    TextView textLoading, loadingView;
    BottomNavigationView bottomNavigationView;
    GoogleSignInClient mGoogleSignInClient;
    private static final String FRAGMENT = "fragment";
    private BroadcastReceiver InternetReceiver = null;
    private NetworkUtil mConnection = new NetworkUtil();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //BugFender
        Bugfender.init(this, "6C5tVgdDQp58HHDwALr6wRs1LAWth45e", BuildConfig.DEBUG);
        Bugfender.enableCrashReporting();
        Bugfender.enableUIEventLogging(this.getApplication());
        Bugfender.enableLogcatLogging();

        setContentView(R.layout.activity_main);
        Logger.addLogAdapter(new AndroidLogAdapter());

        InternetReceiver = new InternetReceiver();
        broadcastIntent();
        InternetObservable.getInstance().addObserver(this);

        pgsBar = (ProgressBar) findViewById(R.id.pBar);
        textLoading = findViewById(R.id.loading);
        loadingView = findViewById(R.id.btn_loadingView);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient= GoogleSignIn.getClient(this, gso);


        //0) istanzio la chace dove salvero le gare, i piloti e i costruttori,
        // trovandosi nel main persistera per tutta la durata del ciclo di vita dell'activity
        mChache =  CacheManager.getInstance(this);


        if (savedInstanceState == null) {
            fragment = new ListFragment();

            if(!mConnection.getConnection()) {
                mChache.setRaces(null, MainActivity.this);
            }else{
                new SeasonManager(2019, MainActivity.this);
            }

            fragmentModel.fragmentId = 0;
            setBundleId();
            bottomNavigationView.setVisibility(View.GONE);
        }
        else
        {
            loadingView.setVisibility(View.GONE);
            pgsBar.setVisibility(View.GONE);
            textLoading.setVisibility(View.GONE);
            bottomNavigationView.setVisibility(View.VISIBLE);
        }


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                fragment = new ListFragment();
                if(lastSelected != null){
                    setTitleBottomMenuColor(lastSelected, Color.BLACK);
                }
                lastSelected = item;
                setTitleBottomMenuColor(item, Color.RED);


                // FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                switch (item.getItemId())
                {
                    case R.id.action_recents:

                        //Calendario
                        fragmentModel.fragmentId = 0;

                        //1) verifico se la cache ha qualche dato salvato
                        if(!mConnection.getConnection()) {
                            mChache.setRaces(null, MainActivity.this);
                            //2.1) passo nel bundle i dati presenti gia nella cache senza dover fare altre chiamate
                            /*bundle.putSerializable(ListFragment.ITEM, mChache.setRaces(null, this));
                            setFragmentTransaction();*/

                        }
                        else {
                            //2.2) non ci sono dati presenti, faccio la chiamata asincrona per ricevere i dati
                            new SeasonManager(2019, MainActivity.this);
                        }

                        setBundleId();
                        break;

                    case R.id.action_favorites:

                        //Classifica piloti
                        if(!mConnection.getConnection()) {
                            ArrayList<Standings> x = new ArrayList<>();
                            mChache.setDrivers(x);
                            /*bundle.putSerializable(ListFragment.ITEM, mChache.setDrivers(null));
                            setFragmentTransaction();*/

                        }else{
                            loadingView.setVisibility(View.VISIBLE);
                            pgsBar.setVisibility(View.VISIBLE);
                            textLoading.setVisibility(View.VISIBLE);
                            new StandingManager(2019, StandingManager.StandingType.driverStandings, (StandingManager.OnStandingsFetched) MainActivity.this);
                        }

                        fragmentModel.fragmentId = 1;
                        setBundleId();
                        break;

                    case R.id.action_nearby:

                        //Classifica costruttori
                        if(!mConnection.getConnection()) {
                            ArrayList<Standings> x = new ArrayList<>();
                            mChache.setConstructors(x);
                           /* bundle.putSerializable(ListFragment.ITEM, mChache.setConstructors(null));
                            setFragmentTransaction();*/

                        }
                        else {
                            new StandingManager(2019, StandingManager.StandingType.constructorStandings, (StandingManager.OnStandingsFetched) MainActivity.this);
                            loadingView.setVisibility(View.VISIBLE);
                            pgsBar.setVisibility(View.VISIBLE);
                            textLoading.setVisibility(View.VISIBLE);
                        }

                        fragmentModel.fragmentId = 2;
                        setBundleId();
                        break;

                    default:
                        /*if(mChache.getRaces() != null) {
                            //2.1) passo nel bundle i dati presenti gia nella cache senza dover fare altre chiamate
                            bundle.putSerializable(ListFragment.ITEM, mChache.getRaces());
                            setFragmentTransaction();

                        }
                        else {*/
                            //2.2) non ci sono dati presenti, faccio la chiamata asincrona per ricevere i dati
                            new SeasonManager(2019, MainActivity.this);
                        //}
                        setBundleId();
                        break;
                }
                return true;
            }
        });
    }


    @Override
    protected void onPause() {
        super.onPause();
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(InternetReceiver);
    }

    //GESTIONE RISPOSTE CHIAMATE ASINCRONE
    @Override
    public void onSeasonRetrievedSuccessfully(Season season) {
        try{

            Logger.d(season.getRaces());
            for (int i = 0; i < season.getRaces().size(); i++) {
                season.getRaces().get(i).setId(i);
            }

            Log.d("ASYNC", "STEP 1");

            mChache.setRaces(season, this);

        }
        catch (Exception ex)
        {

        }
    }

    @Override
    public void onStandingsRetrievedSuccessfully(ArrayList<Standings> aStandings) {
        try{
            //1) PREMESSA, questo metodo gestisce sia il database di costruttori che di piloti

            //2) devo verificare quale elementi mi ritorna la chiamata e assegnare gli elementi nel modo giusto
            if (aStandings.get(0).ConstructorStandings != null) {

                Log.d("ASYNC - STANDINGS", "STEP 1 - CONSTRUCTOR");

                //3)  salvo nel db i costruttori
                mChache.setConstructors(aStandings);
            }
            else if (aStandings.get(0).DriverStandings != null)
            {
                Log.d("ASYNC - STANDINGS", "STEP 1 - DRIVER");

                //3)  salvo nel db i piloti
                mChache.setDrivers(aStandings);
            }
        }
        catch (Exception ex){

        }
    }


    private void setFragmentTransaction()
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //assegno al mio fragment il bundle che conterra l'id della view
        // che vorro caricare e l'array di elementi che mi interessa
        fragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.dynamicFragmentFrameLayout, fragment, FRAGMENT).commit();
    }



    @Override
    public void onItemValue(Race aRace, int position) {
        //1) richiamo l'activity detailRace
        Intent vIntent = new Intent(this, DetailRace.class);
        //2) aggiungo un bundle per passare il valore della gara selezionata
        Bundle vBundle = new Bundle();
        vBundle.putSerializable(DetailRace.ITEM_RACE, aRace);
        vBundle.putInt(DetailRace.RACE_NUMBER, position);
        vIntent.putExtras(vBundle);
        //3) faccio partire l'activity
        startActivity(vIntent);
    }

    //espongo l'intefacci del fragment che al caricamento della sua activity con rispettivo layout faccio sparire il loading
    @Override
    public void loadFragmentActivity() {
        loadingView.setVisibility(View.GONE);
        pgsBar.setVisibility(View.GONE);
        textLoading.setVisibility(View.GONE);
    }

    @Override
    public void signOut() {
        mGoogleSignInClient.signOut();
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(MainActivity.this, FirebaseLogin.class);
        startActivity(intent);
    }


    public void broadcastIntent() {
        registerReceiver(InternetReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }


    @Override
    public void update(Observable o, Object arg) {
        Toast.makeText(MainActivity.this, "" + arg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getRaceList(Season season) {
        Log.d("ASYNC - RACE", " STEP 5 ");

        bundle.putSerializable(ListFragment.ITEM, season.getRaces());

        //3) carico il fragment di mio interesse
        pgsBar.setVisibility(View.GONE);
        textLoading.setVisibility(View.GONE);
        bottomNavigationView.setVisibility(View.VISIBLE);
        setFragmentTransaction();
    }

    @Override
    public void getStandingsList(Standings standings) {
        Log.d("ASYNC - STANDINGS", " STEP 5 ");

        if(lastSelected.getItemId() == R.id.action_favorites){
            bundle.putSerializable(ListFragment.ITEM, standings.DriverStandings);
        }else{
            bundle.putSerializable(ListFragment.ITEM, standings.ConstructorStandings);
        }

        //4) carico il fragment di mio interesse
        setFragmentTransaction();
    }
}
