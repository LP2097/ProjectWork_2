package thunderbytes.com.formulanews.Fragments;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.willowtreeapps.spruce.Spruce;
import com.willowtreeapps.spruce.animation.DefaultAnimations;
import com.willowtreeapps.spruce.sort.DefaultSort;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import thunderbytes.com.formulanews.Adapter.AdapterConstructor;
import thunderbytes.com.formulanews.Adapter.AdpterDriver;
import thunderbytes.com.formulanews.Adapter.AdapterRace;
import thunderbytes.com.formulanews.Dialogue.LogoutDialogue;
import thunderbytes.com.formulanews.Models.ConstructorStanding;
import thunderbytes.com.formulanews.Models.DriverStanding;
import thunderbytes.com.formulanews.Models.Race;

import thunderbytes.com.formulanews.R;

public class ListFragment extends Fragment {

    public static final String ID = "id";
    public static final String ITEM = "item";

    private int fragmentId;
    ListView listView;
    TextView title;
    View ColorTeam;
    AdpterDriver mAdapterDriver;
    ArrayList<Race> vRaces;
    AdapterConstructor mAdapterConstractor;
    public ListFragment() { }
    ImageView logoutView;
    private Animator spruceAnimator;
    private Boolean test;

    private LinkedHashMap<String, Integer> colorTeams = new LinkedHashMap<>();

    public interface OnItemClicked{
        void onItemValue(Race aRace, int position);
        void loadFragmentActivity();
    }

    private OnItemClicked mListener;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View vView = inflater.inflate(R.layout.list_layout, container, false);

        title = vView.findViewById(R.id.fragmentTitle);
        listView =  vView.findViewById(R.id.listView);
        fragmentId = getArguments().getInt(ID);
        logoutView = vView.findViewById(R.id.logoutView);

        listView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if(test == true){
                    initSpruce();
                }
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mListener.onItemValue(vRaces.get(position), position);
            }
        });


        logoutView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLogoutDialogue();
            }
        });

        setColorTeam();

        Bundle vBundle =  getArguments();

        if (vBundle != null){

            switch (fragmentId){
                case 0:
                    vRaces =(ArrayList<Race>) vBundle.getSerializable(ITEM);
                    AdapterRace adapterRace = new AdapterRace(vRaces);
                    listView.setAdapter(adapterRace);
                    break;

                case 1:
                    test = false;
                    ArrayList<DriverStanding> vDriver = (ArrayList<DriverStanding>) vBundle.getSerializable(ITEM);
                    mAdapterDriver = new AdpterDriver(vDriver, colorTeams);
                    title.setText("Classifica piloti");
                    listView.setAdapter(mAdapterDriver);
                    break;

                case 2:
                    test = false;
                    ArrayList<ConstructorStanding> vConstructors =(ArrayList<ConstructorStanding>) vBundle.getSerializable(ITEM);
                    mAdapterConstractor = new AdapterConstructor(vConstructors, colorTeams);
                    title.setText("Classifica costruttori");
                    listView.setAdapter(mAdapterConstractor);
                    break;
            }
        }

        mListener.loadFragmentActivity();

        return vView;
    }


    public void openLogoutDialogue(){
        LogoutDialogue logoutDialogue = new LogoutDialogue();
        logoutDialogue.show(getFragmentManager(), "Logout dialogue");
    }

    private void setColorTeam(){
        colorTeams.put("Mercedes", this.getResources().getColor(R.color.mercedes));
        colorTeams.put("Red Bull", this.getResources().getColor(R.color.redbull));
        colorTeams.put("Ferrari", this.getResources().getColor(R.color.ferrari));
        colorTeams.put("Haas F1 Team", this.getResources().getColor(R.color.richenergy));
        colorTeams.put("Renault", this.getResources().getColor(R.color.renault));
        colorTeams.put("Alfa Romeo", this.getResources().getColor(R.color.alfaromeo));
        colorTeams.put("Racing Point", this.getResources().getColor(R.color.racingpoints));
        colorTeams.put("Toro Rosso", this.getResources().getColor(R.color.rokit));
        colorTeams.put("McLaren", this.getResources().getColor(R.color.mclaren));
        colorTeams.put("Williams", this.getResources().getColor(R.color.williams));
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof OnItemClicked) {
            mListener = (OnItemClicked)context;
        }
    }


    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.addLogAdapter(new AndroidLogAdapter());
        Logger.d("creazione Fragment");
        test = true;
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    private void initSpruce() {
        spruceAnimator = new Spruce.SpruceBuilder(listView)
                .sortWith(new DefaultSort(100))
                .animateWith(DefaultAnimations.fadeInAnimator(listView, 800),
                        ObjectAnimator.ofFloat(listView, "translationY", +listView.getWidth(), 0f).setDuration(800))
                .start();
    }
}
