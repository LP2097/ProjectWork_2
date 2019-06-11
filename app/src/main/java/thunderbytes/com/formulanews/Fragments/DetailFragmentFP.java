package thunderbytes.com.formulanews.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import thunderbytes.com.formulanews.Models.Race;
import thunderbytes.com.formulanews.R;


public class DetailFragmentFP extends Fragment {

    public static DetailFragmentFP newIstance(){
        return new DetailFragmentFP();
    }

    private Race vRace;
    private TextView mDateFP1;
    private TextView mDateFP2;
    private TextView mDateFP3;
    private TextView mDateQualifing;
    private TextView mDateRace;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View vView = inflater.inflate(R.layout.fragment_fp_layout, container, false);

        mDateFP1 = vView.findViewById(R.id.txt_FP1);
        mDateFP2 = vView.findViewById(R.id.txt_FP2);
        mDateFP3 = vView.findViewById(R.id.txt_FP3);
        mDateQualifing = vView.findViewById(R.id.txt_Qualifing);
        mDateRace = vView.findViewById(R.id.txt_Race);

        Bundle vBundle =  getArguments();
        if (vBundle != null) {
            vRace = (Race) vBundle.getSerializable("ITEM_RACE");

            mDateFP1.setText(android.text.format.DateFormat.format("dd.MM.yyyy", calculateDate(-2)) + ", --:--");
            mDateFP2.setText(android.text.format.DateFormat.format("dd.MM.yyyy", calculateDate(-2)) + ", --:--");
            mDateFP3.setText(android.text.format.DateFormat.format("dd.MM.yyyy", calculateDate(-1)) + ", --:--");
            mDateQualifing.setText(android.text.format.DateFormat.format("dd.MM.yyyy", calculateDate(-1)) + ", --:--");
            mDateRace.setText(android.text.format.DateFormat.format("dd.MM.yyyy", vRace.getDate()) + ", " + calculateTime(vRace.getTime()));
        }

        return vView;
    }

    public Date calculateDate(int amount){
        Calendar mCalendar = Calendar.getInstance();
        mCalendar.setTime(vRace.getDate());
        mCalendar.add(Calendar.DATE, amount);

        return mCalendar.getTime();
    }

    public String calculateTime(String string) {
        SimpleDateFormat df = new SimpleDateFormat("HH:mm", Locale.ITALIAN);
        df.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date = null;
        String formattedDate = "";

        try {
            date = df.parse(string);
            df.setTimeZone(TimeZone.getDefault());
            formattedDate = df.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return formattedDate;
    }


}
