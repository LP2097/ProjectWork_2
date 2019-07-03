package thunderbytes.com.formulanews.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import thunderbytes.com.formulanews.Adapter.AdapterClock;
import thunderbytes.com.formulanews.Models.Race;
import thunderbytes.com.formulanews.R;


public class DetailFragmentDate extends Fragment {

    private static DetailFragmentDate detailFragmentDate;
    private static final String ITEM_RACE = "race";


    public static DetailFragmentDate newInstance(Race race) {

        if (detailFragmentDate == null){
            detailFragmentDate = new DetailFragmentDate();
        }

        Bundle args = new Bundle();
        args.putSerializable(ITEM_RACE, race);
        detailFragmentDate.setArguments(args);
        return detailFragmentDate;
    }

    private Race vRace;
    private ArrayList<ArrayList> date = new ArrayList<ArrayList>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View vView = inflater.inflate(R.layout.fragment_clock_layuot, container, false);

        RecyclerView listView = vView.findViewById(R.id.listview_clock);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());
        listView.setLayoutManager(linearLayoutManager);
        listView.setHasFixedSize(true);


        Bundle vBundle =  getArguments();

        if (vBundle != null) {
            vRace = (Race) vBundle.getSerializable(ITEM_RACE);

            setData();

            AdapterClock adapterRace = new AdapterClock(date);
            listView.setAdapter(adapterRace);
        }

        return vView;
    }

    private Date calculateDate(int amount){
        Calendar mCalendar = Calendar.getInstance();
        mCalendar.setTime(vRace.getDate());
        mCalendar.add(Calendar.DATE, amount);

        return mCalendar.getTime();
    }



    private String calculateTime(String timeRace) {
        SimpleDateFormat vInputDateFormat = new SimpleDateFormat("HH:mm:ss'Z'");
        vInputDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        String formattedDate = "";

        try {
            Date date = vInputDateFormat.parse(timeRace);
            vInputDateFormat.setTimeZone(TimeZone.getDefault());
            SimpleDateFormat vDateFormat = new SimpleDateFormat("HH:mm");

            if(TimeZone.getDefault().inDaylightTime(calculateDate(0))){

                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                cal.add(Calendar.HOUR_OF_DAY, 1);

                formattedDate = vDateFormat.format(cal.getTime());
            }else{

                formattedDate = vDateFormat.format(date);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return formattedDate;
    }

    private void setData(){
        ArrayList mRace = new ArrayList();
        ArrayList mQualify = new ArrayList();
        ArrayList mFP1 = new ArrayList();
        ArrayList mFP2 = new ArrayList();
        ArrayList mFP3 = new ArrayList();

        mRace.add(0, vRace.getDate());
        mRace.add(1, "Gara");
        mRace.add(2, calculateTime(vRace.getTime()));


        mQualify.add(0, calculateDate(-1));
        mQualify.add(1, "Qualifica");

        mFP1.add(0, calculateDate( -2));
        mFP1.add(1, "FP1");

        mFP2.add(0, calculateDate( -2));
        mFP2.add(1, "FP2");

        mFP3.add(0, calculateDate( -1));
        mFP3.add(1, "FP3");

        date.add(0, mRace);
        date.add(1, mQualify);
        date.add(2, mFP3);
        date.add(3, mFP2);
        date.add(4, mFP1);
    }

}
