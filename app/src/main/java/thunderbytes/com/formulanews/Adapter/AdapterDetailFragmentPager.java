package thunderbytes.com.formulanews.Adapter;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;

import java.util.Date;

import thunderbytes.com.formulanews.Fragments.DetailFragmentDate;
import thunderbytes.com.formulanews.Fragments.DetailFragmentQualification;
import thunderbytes.com.formulanews.Fragments.DetailFragmentRace;
import thunderbytes.com.formulanews.Fragments.TimerFragment;
import thunderbytes.com.formulanews.Models.Race;

public class AdapterDetailFragmentPager extends FragmentPagerAdapter {

    private int racePosition;
    private int qualificationNubmer;
    private Race race;

    private DetailFragmentDate fragmentDate;

    public AdapterDetailFragmentPager(FragmentManager fm, int racePosition, Race race) {
        super(fm);
        this.racePosition = racePosition;
        this.race = race;
    }


    @Override
    public Fragment getItem(int i) {

        if(i == 0)
        {
            if(fragmentDate == null)
                fragmentDate = DetailFragmentDate.getInstance(race);

                return fragmentDate;
        }
        else if ( i == 4)
        {
            DetailFragmentRace fragmentRasult = DetailFragmentRace.newInstance(racePosition);
            return fragmentRasult;
        }
        else
        {
            Log.d("POSITION", " pagina "+ qualificationNubmer);
            DetailFragmentQualification fragmentQresult = DetailFragmentQualification.newInstance(racePosition, i);
            return fragmentQresult;
        }
    }

    @Override
    public long getItemId(int position) {
        Log.d("POSITION", " get item id "+position);

        return super.getItemId(position);

    }

    public void stopTimer()
    {
        fragmentDate = DetailFragmentDate.getInstance(race);
        fragmentDate.stopTimer();
    }

    public void startTimer()
    {
        fragmentDate = DetailFragmentDate.getInstance(race);
        fragmentDate.startTimer();
    }

    @Override
    public int getCount() {
        Date date = new Date();

        if(date.getTime() < race.getDate().getTime())
            return 1;

        return 5;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        if(position == 0)
        {
            return "ORARI";
        }
        else if ( position == 4)
        {
            return "GARA";
        }
        else
        {
            return "Q"+position;
        }
    }

    @Override
    public float getPageWidth(int position) {
        qualificationNubmer = position;
        return super.getPageWidth(position);
    }


}
