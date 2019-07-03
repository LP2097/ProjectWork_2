package thunderbytes.com.formulanews.Adapter;

import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.Date;

import thunderbytes.com.formulanews.Fragments.DetailFragmentDate;
import thunderbytes.com.formulanews.Fragments.DetailFragmentQualification;
import thunderbytes.com.formulanews.Fragments.DetailFragmentRace;
import thunderbytes.com.formulanews.Models.Race;

public class AdapterDetailFragmentPager extends FragmentPagerAdapter {

    private int racePosition;
    private int qualificationNubmer;
    private Race race;

    public AdapterDetailFragmentPager(FragmentManager fm, int racePosition, Race race) {
        super(fm);
        this.racePosition = racePosition;
        this.race = race;
    }


    @Override
    public Fragment getItem(int i) {

        if(i == 0)
        {
            DetailFragmentDate fragmentDate = DetailFragmentDate.newInstance(race);
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
