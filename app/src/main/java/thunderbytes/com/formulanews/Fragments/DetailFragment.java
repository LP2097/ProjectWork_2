package thunderbytes.com.formulanews.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import thunderbytes.com.formulanews.Adapter.AdapterResult;
import thunderbytes.com.formulanews.Managers.SeasonManager;
import thunderbytes.com.formulanews.Models.Race;
import thunderbytes.com.formulanews.Models.Season;
import thunderbytes.com.formulanews.R;

public class DetailFragment extends Fragment implements SeasonManager.OnSeasonFetched{

    public static DetailFragment newIstance(){
        return new DetailFragment();
    }
    private Race vRace;

    private ListView listView;
    private ProgressBar pgsBar;
    private TextView loading;
    private TextView cell_three;
    private TextView cell_four;
    private TextView cell_five;
    private TextView mDate;
    private LinearLayout mSuperTable;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vView = inflater.inflate(R.layout.fragment_item_layout, container, false);

        listView = vView.findViewById(R.id.listViewResult);
        pgsBar = vView.findViewById(R.id.pBar2);
        loading = vView.findViewById(R.id.loading2);
        cell_three = vView.findViewById(R.id.txt_cell_three);
        cell_four = vView.findViewById(R.id.txt_cell_four);
        cell_five = vView.findViewById(R.id.txt_cell_five);
        mDate = vView.findViewById(R.id.txt_date);
        mSuperTable = vView.findViewById(R.id.list_table);

        mSuperTable.setVisibility(View.GONE);
        mDate.setVisibility(View.GONE);
        listView.setVisibility(View.GONE);
        loading.setVisibility(View.VISIBLE);
        pgsBar.setVisibility(View.VISIBLE);



        Bundle vBundle =  getArguments();
        if (vBundle != null){
            vRace = (Race) vBundle.getSerializable("ITEM_RACE_FRAGMENT");
            String mTypeRace = vBundle.getString("ITEM_TYPE_RACE");
            int mRaceNumber = vBundle.getInt("ITEM_RACE_NUMBER");


            if (mTypeRace == "race"){
                new SeasonManager(2018, mRaceNumber, SeasonManager.RaceType.results, DetailFragment.this);

                mDate.setText(android.text.format.DateFormat.format("dd-MM-yyyy", vRace.getDate()));

                cell_three.setText("TEMPO");
                cell_four.setText("GAP");
                cell_five.setText("PTS");
            }else {
                Calendar mCalendar = Calendar.getInstance();
                mCalendar.setTime(vRace.getDate());
                mCalendar.add(Calendar.DATE, -1);

                mDate.setText(android.text.format.DateFormat.format("dd-MM-yyyy", mCalendar.getTime()));

                cell_three.setText("Q1");
                cell_four.setText("Q2");
                cell_five.setText("Q3");
            }
            // OR

            //new SeasonManager(2018, mRaceNumber, SeasonManager.RaceType.qualifying, DetailFragment.this);
        }

        return vView;
    }

    @Override
    public void onSeasonRetrievedSuccessfully(Season season) {

        pgsBar.setVisibility(View.GONE);
        loading.setVisibility(View.GONE);
        mDate.setVisibility(View.VISIBLE);
        listView.setVisibility(View.VISIBLE);
        mSuperTable.setVisibility(View.VISIBLE);

        AdapterResult adapter = new AdapterResult(season.getRaces().get(0).Results);

        listView.setAdapter(adapter);

        // OR

        //AdapterQualifying adapter = new AdapterQualifying(season.getRaces().get(0).Results);

        //listView.setAdapter(adapter);
    }
}
