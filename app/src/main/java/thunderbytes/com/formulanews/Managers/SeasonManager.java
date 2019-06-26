package thunderbytes.com.formulanews.Managers;

import android.content.Context;
import android.util.Log;

import thunderbytes.com.formulanews.Fragments.DetailFragmentQualification;
import thunderbytes.com.formulanews.Fragments.DetailFragmentRace;
import thunderbytes.com.formulanews.Helpers.Constants;
import thunderbytes.com.formulanews.Interfaces.IDataWrapper;
import thunderbytes.com.formulanews.Models.Season;
import thunderbytes.com.formulanews.Tasks.HttpGetTask;
import thunderbytes.com.formulanews.Wrappers.RaceDataWrapper;
import thunderbytes.com.formulanews.Wrappers.RaceMRDataWrapper;


public class SeasonManager implements HttpGetTask.OnPostExecution {

    public enum RaceType{
        qualifying,
        race,
        results
    }

    public interface OnSeasonFetched{
        void onSeasonRetrievedSuccessfully(Season season);
    }

    private OnSeasonFetched listener;

    private Season season = new Season();

    public SeasonManager(int year, Context context) {
        if (context instanceof OnSeasonFetched) {
            listener = (OnSeasonFetched) context;
            season.setSeasonYear(year);
            HttpGetTask http = (HttpGetTask) new HttpGetTask(this, new RaceDataWrapper()).execute(Constants.baseUrl + year);
        }
    }

    public SeasonManager(int year, int race, RaceType type, DetailFragmentRace context){
        if (context instanceof OnSeasonFetched) {
            listener = (OnSeasonFetched) context;
            season.setSeasonYear(year);
            HttpGetTask http = (HttpGetTask) new HttpGetTask(this, new RaceDataWrapper()).execute(Constants.baseUrl + year + "/"+race+"/"+type);
            Log.d("URL-",Constants.baseUrl + year + "/"+race+"/"+type );
        }
    }


    public SeasonManager(int year, int race, RaceType type, DetailFragmentQualification context){
        if (context instanceof OnSeasonFetched) {
            listener = (OnSeasonFetched) context;
            season.setSeasonYear(year);
            HttpGetTask http = (HttpGetTask) new HttpGetTask(this, new RaceDataWrapper()).execute(Constants.baseUrl + year + "/"+race+"/"+type);
            Log.d("URL-",Constants.baseUrl + year + "/"+race+"/"+type );
        }
    }

    @Override
    public void onHttpGetFinished(IDataWrapper jsonObject) {
        if(jsonObject != null){
            RaceMRDataWrapper data = ((RaceDataWrapper)jsonObject).getMRData();
            season.setRaces(data.getRaceTable().getRaces());
        }else{
            season = new Season();
        }
        listener.onSeasonRetrievedSuccessfully(season);
        listener = null;
    }
}
