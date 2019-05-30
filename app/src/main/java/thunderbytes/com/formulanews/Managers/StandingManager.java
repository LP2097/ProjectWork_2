package thunderbytes.com.formulanews.Managers;

import android.content.Context;

import java.util.ArrayList;

import thunderbytes.com.formulanews.Helpers.Constants;
import thunderbytes.com.formulanews.Interfaces.IDataWrapper;
import thunderbytes.com.formulanews.Models.Standings;
import thunderbytes.com.formulanews.Tasks.HttpGetTask;
import thunderbytes.com.formulanews.Wrappers.StandingsDataWrapper;
import thunderbytes.com.formulanews.Wrappers.StandingsMRDataWrapper;

public class StandingManager implements HttpGetTask.OnPostExecution {

    public interface OnStandingsFetched{
        void onStandingsRetrievedSuccessfully(ArrayList<Standings> standings);
    }

    public enum StandingType{
        driverStandings,
        constructorStandings
    }


    private OnStandingsFetched listener;

    private ArrayList<Standings> standings = new ArrayList<>();

    public StandingManager(int year, StandingType type, OnStandingsFetched listener) {
        if (listener != null) {
            this.listener = listener;
            HttpGetTask http = (HttpGetTask) new HttpGetTask(this, new StandingsDataWrapper()).execute(Constants.baseUrl+year+"/"+type);
        }
    }

    @Override
    public void onHttpGetFinished(IDataWrapper jsonObject) {
        if(jsonObject != null){
            StandingsMRDataWrapper data = ((StandingsDataWrapper)jsonObject).getMRData();
            standings = data.getStandingsTable().getStandingsLists();
            //season.setRaces(data.getRaceTable().getRaces());
        }else{
            standings = new ArrayList<>();
        }
        listener.onStandingsRetrievedSuccessfully(standings);
        listener = null;
    }
}
