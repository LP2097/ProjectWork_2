package thunderbytes.com.formulanews.Managers;

import android.content.Context;

import thunderbytes.com.formulanews.Helpers.Constants;
import thunderbytes.com.formulanews.Interfaces.IDataWrapper;
import thunderbytes.com.formulanews.Models.Season;
import thunderbytes.com.formulanews.Tasks.HttpGetTask;
import thunderbytes.com.formulanews.Wrappers.RaceDataWrapper;
import thunderbytes.com.formulanews.Wrappers.RaceMRDataWrapper;


public class SeasonManager implements HttpGetTask.OnPostExecution {

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

    @Override
    public void onHttpGetFinished(IDataWrapper jsonObject) {
        RaceMRDataWrapper data = ((RaceDataWrapper)jsonObject).getMRData();
        season.setRaces(data.getRaceTable().getRaces());
        listener.onSeasonRetrievedSuccessfully(season);
        listener = null;
    }
}
