package thunderbytes.com.formulanews.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import thunderbytes.com.formulanews.Adapter.AdapterQualifying;
import thunderbytes.com.formulanews.Managers.SeasonManager;
import thunderbytes.com.formulanews.Models.Qualifying;
import thunderbytes.com.formulanews.Models.Season;
import thunderbytes.com.formulanews.R;

public class DetailFragmentQualification extends Fragment implements SeasonManager.OnSeasonFetched{

    private final static String RACE_POSITION = "position";
    private final static String QUALIFICATION = "qualification";
    private static DetailFragmentQualification detailFragmentQualification;

    public static DetailFragmentQualification newInstance(int racePosition, int qualificationNumber) {

        Bundle args = new Bundle();
        args.putInt(RACE_POSITION,racePosition);
        args.putInt(QUALIFICATION,qualificationNumber);
        DetailFragmentQualification fragment = new DetailFragmentQualification();
        fragment.setArguments(args);
        return fragment;
    }


    private String mTypeRace;
    private RecyclerView listView;
    private ProgressBar pgsBar;
    private TextView loading;
    private TextView cell_three;
    private TextView cell_four;
    private TextView cell_five;
    private LinearLayout mSuperTable;
    private int qualificationNumber;
    private ArrayList<Qualifying> qualifyingArrayList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vView = inflater.inflate(R.layout.fragment_qrace_layout, container, false);

        listView = vView.findViewById(R.id.listViewResult_qRace);
        pgsBar = vView.findViewById(R.id.pBar3);
        loading = vView.findViewById(R.id.loading3);


        loading.setVisibility(View.VISIBLE);
        pgsBar.setVisibility(View.VISIBLE);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());
        listView.setLayoutManager(linearLayoutManager);
        listView.setHasFixedSize(true);



        Bundle vBundle =  getArguments();
        if (vBundle != null){
            int mRaceNumber = vBundle.getInt(RACE_POSITION);
            qualificationNumber = vBundle.getInt(QUALIFICATION);
            new SeasonManager(2019, mRaceNumber+1, SeasonManager.RaceType.qualifying, DetailFragmentQualification.this);
        }

        return vView;
    }

    @Override
    public void onSeasonRetrievedSuccessfully(Season season) {

        try {
            pgsBar.setVisibility(View.GONE);
            loading.setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);

            if(season.races.isEmpty()){
                Toast.makeText(getActivity(), "Non ci sono dati", Toast.LENGTH_LONG).show();
            }else {
                    qualifyingArrayList = season.getRaces().get(0).QualifyingResults;
                  AdapterQualifying adapter = new AdapterQualifying(qualifyingArrayList, qualificationNumber);
                  listView.setAdapter(adapter);
            }

        } catch (Exception e) {
            Log.e("ERROR","Error " + e.getMessage());
            Toast.makeText(getActivity(), "Si è verificato un errore", Toast.LENGTH_LONG).show();
        }
    }
}


