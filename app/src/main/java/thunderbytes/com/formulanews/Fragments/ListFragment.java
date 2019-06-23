package thunderbytes.com.formulanews.Fragments;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.willowtreeapps.spruce.Spruce;
import com.willowtreeapps.spruce.animation.DefaultAnimations;
import com.willowtreeapps.spruce.sort.DefaultSort;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import thunderbytes.com.formulanews.Dialogue.LogoutDialogue;
import thunderbytes.com.formulanews.Managers.SeasonManager;
import thunderbytes.com.formulanews.Managers.StandingManager;
import thunderbytes.com.formulanews.Models.ConstructorStanding;
import thunderbytes.com.formulanews.Models.DriverStanding;
import thunderbytes.com.formulanews.Models.Race;
import thunderbytes.com.formulanews.Models.Season;
import thunderbytes.com.formulanews.Models.Standings;
import thunderbytes.com.formulanews.R;

public class ListFragment extends Fragment implements SeasonManager.OnSeasonFetched, StandingManager.OnStandingsFetched{
    LinearLayout detailRace;
    public static final String ID = "id";
    public static final String ITEM = "item";
    private int fragmentId;
    private Race race;
    ListView listView;
    TextView title,textPosition,textUpText,textDownText,textPoints, textRaceName, textDate, textMonth;
    View ColorTeam;
    public ListFragment() { }
    MyAdapter adapter;
    ImageView logoutView;
    private Animator spruceAnimator;

    @Override
    public void onSeasonRetrievedSuccessfully(Season season) {
        Logger.d(season.getRaces());
    }

    @Override
    public void onStandingsRetrievedSuccessfully(ArrayList<Standings> standings) {

    }

    private LinkedHashMap<String, Integer> colorTeams = new LinkedHashMap<>();


    public interface OnItemClicked{
        void onItemValue(Race aRace, int position);
        void loadFragmentActivity();
    }

    private OnItemClicked mListener;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View vView = inflater.inflate(R.layout.list_layout, container, false);
        title = (TextView)vView.findViewById(R.id.fragmentTitle);
        listView = (ListView)vView.findViewById(R.id.listView);
        fragmentId = getArguments().getInt(ID);
        logoutView = vView.findViewById(R.id.logoutView);

        listView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                initSpruce();
            }
        });

        logoutView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLogoutDialogue();

            }
        });

        setColorTeam();

        switch (fragmentId){
            case 0:
                ArrayList<Race> races = (ArrayList<Race>) getArguments().getSerializable(ITEM);
                adapter = new MyAdapter(getContext(), races, null,null);
                break;

            case 1:
                ArrayList<DriverStanding> drivers = (ArrayList<DriverStanding>) getArguments().getSerializable(ITEM);
                adapter = new MyAdapter(getContext(), null, drivers,null);
                break;

            case 2:
                ArrayList<ConstructorStanding> constructors = (ArrayList<ConstructorStanding>) getArguments().getSerializable(ITEM);
                adapter = new MyAdapter(getContext(), null,null,constructors);
                break;
        }

        listView.setAdapter(adapter);

        mListener.loadFragmentActivity();
        return vView;
    }


    public void openLogoutDialogue(){
        LogoutDialogue logoutDialogue = new LogoutDialogue();
        logoutDialogue.show(getFragmentManager(), "Logout dialogue");
    }



    class MyAdapter extends ArrayAdapter<String> {
        Context context;
        ArrayList<Race> arrayRace;
        ArrayList<DriverStanding> driverStandings;
        ArrayList<ConstructorStanding> constructorStandings;


        MyAdapter(Context c,  ArrayList<Race> races, ArrayList<DriverStanding> drivers, ArrayList<ConstructorStanding> constructors) {
            super(c, R.layout.calendar_cell_layout, R.id.position);
            this.context = c;
            arrayRace = races;
            driverStandings = drivers;
            constructorStandings = constructors;
        }


        @Override
        public int getCount() {
            switch (fragmentId) {

                case 0:
                    return arrayRace.size();

                case 1:
                    return driverStandings.size();

                case 2:
                    return constructorStandings.size();

                default:
                    return 0;
            }
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

           LayoutInflater layoutInflater = (LayoutInflater) context.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View cell = convertView;
            textPosition = null;
            textUpText = null;
            textDownText = null;
            textPoints = null;



            switch (fragmentId) {
                case 0:
                    title.setText("Calendario");
                    cell = layoutInflater.inflate(R.layout.calendar_cell_layout, parent, false);

                    textRaceName = cell.findViewById(R.id.textRight);
                    textDate = cell.findViewById(R.id.textDate);
                    textMonth = cell.findViewById(R.id.textMonth);

                    Calendar mCalendar = Calendar.getInstance();
                    mCalendar.setTime(arrayRace.get(position).getDate());
                    mCalendar.add(Calendar.DATE, -2);

                    textRaceName.setText(arrayRace.get(position).getRaceName());
                    textDate.setText(android.text.format.DateFormat.format("dd", arrayRace.get(position).getDate()) + " - " + android.text.format.DateFormat.format("dd", mCalendar.getTime()));
                    textMonth.setText(android.text.format.DateFormat.format("MMM", arrayRace.get(position).getDate()));

                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            mListener.onItemValue(arrayRace.get(position), position);
                        }
                    });
                    break;

                case 1:
                    title.setText("Classifica piloti");
                    cell = layoutInflater.inflate(R.layout.list_cell_layout, parent, false);
                    textPosition = cell.findViewById(R.id.position);
                    textUpText = cell.findViewById(R.id.upText);
                    textDownText = cell.findViewById(R.id.downText);
                    textPoints = cell.findViewById(R.id.points);
                    ColorTeam = cell.findViewById(R.id.identifyConstructor);

                    textPosition.setText("" + (position+1));
                    textUpText.setText("" + driverStandings.get(position).Driver.givenName);
                    textDownText.setText("" + driverStandings.get(position).Driver.familyName);
                    textPoints.setText("" + driverStandings.get(position).getPoints() + " PTS");

                    ColorTeam.setBackgroundColor(colorTeams.get(driverStandings.get(position).Constructors.get(0).name));

                    break;

                case 2:
                    title.setText("Classifica costruttori");
                    cell = layoutInflater.inflate(R.layout.list_cell_layout, parent, false);
                    textPosition = cell.findViewById(R.id.position);
                    textUpText = cell.findViewById(R.id.upText);
                    textDownText = cell.findViewById(R.id.downText);
                    textPoints = cell.findViewById(R.id.points);
                    ColorTeam = cell.findViewById(R.id.identifyConstructor);

                    textPosition.setText("" + (position+1));
                    textUpText.setText(""+ constructorStandings.get(position).getConstructor().getName());
                    textDownText.setText("" + constructorStandings.get(position).getConstructor().getNationality());
                    textPoints.setText("" + constructorStandings.get(position).getPoints()  + " PTS");

                    ColorTeam.setBackgroundColor(colorTeams.get(constructorStandings.get(position).getConstructor().getName()));

                    break;
            }
            return cell;
        }

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
