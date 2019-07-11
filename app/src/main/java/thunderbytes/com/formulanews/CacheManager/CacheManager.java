package thunderbytes.com.formulanews.CacheManager;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

import java.lang.ref.WeakReference;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import thunderbytes.com.formulanews.Daos.AverageSpeedDao;
import thunderbytes.com.formulanews.Daos.CircuitDao;
import thunderbytes.com.formulanews.Daos.ConstructorDao;
import thunderbytes.com.formulanews.Daos.ConstructorStandingDao;
import thunderbytes.com.formulanews.Daos.DriverDao;
import thunderbytes.com.formulanews.Daos.DriverStandingDao;
import thunderbytes.com.formulanews.Daos.FastestLapDao;
import thunderbytes.com.formulanews.Daos.LocationDao;
import thunderbytes.com.formulanews.Daos.QualifyingDao;
import thunderbytes.com.formulanews.Daos.RaceDao;
import thunderbytes.com.formulanews.Daos.ResultsDao;
import thunderbytes.com.formulanews.Daos.SeasonDao;
import thunderbytes.com.formulanews.Daos.StandingsDao;
import thunderbytes.com.formulanews.Daos.TimeDao;
import thunderbytes.com.formulanews.Fragments.DetailFragmentQualification;
import thunderbytes.com.formulanews.Helpers.FNdb;
import thunderbytes.com.formulanews.MainActivity;
import thunderbytes.com.formulanews.Managers.DbManager;
import thunderbytes.com.formulanews.Managers.SeasonManager;
import thunderbytes.com.formulanews.Models.Circuit;
import thunderbytes.com.formulanews.Models.Constructor;
import thunderbytes.com.formulanews.Models.ConstructorStanding;
import thunderbytes.com.formulanews.Models.Driver;
import thunderbytes.com.formulanews.Models.DriverStanding;
import thunderbytes.com.formulanews.Models.Location;
import thunderbytes.com.formulanews.Models.Qualifying;
import thunderbytes.com.formulanews.Models.Race;
import thunderbytes.com.formulanews.Models.Results;
import thunderbytes.com.formulanews.Models.Season;
import thunderbytes.com.formulanews.Models.Standings;
import thunderbytes.com.formulanews.R;
import thunderbytes.com.formulanews.Service.NetworkUtil;
import thunderbytes.com.formulanews.Wrappers.RaceDataWrapper;
import thunderbytes.com.formulanews.Wrappers.RaceMRDataWrapper;

public class CacheManager{

    public interface ICacheManagerRace { void getRaceList(Season season);}
    public interface ICacheManagerStandings { void getStandingsList(Standings standings);}

    ICacheManagerRace listenerRace;
    ICacheManagerStandings listenerStadings;

    private static final CacheManager instance = new CacheManager();
    private Context mContext;

    private ArrayList<Qualifying> mArrQualifying = new ArrayList<>();

    public static CacheManager getInstance(Context context){ return instance;  }

    public void setRaces(Season season, Context context) {
        Log.d("ASYNC - RACE", "STEP 2");

        mContext = context;

        listenerRace =(ICacheManagerRace) context;

        DbTaskRace taskRace = new DbTaskRace(listenerRace, context);
        taskRace.setRaceArrayList(season);
        taskRace.execute();

    }


    public void setConstructors(ArrayList<Standings> aStandings) {
        Log.d("ASYNC - STANDINGS", "STEP 2 - CONSTRUCTOR");

        listenerStadings =(ICacheManagerStandings) mContext;

        DbTaskStadings taskRace = new DbTaskStadings(listenerStadings, mContext);
        taskRace.setStandingsArrayList(aStandings);
        taskRace.execute();
    }

    public void setDrivers(ArrayList<Standings> aStandings) {
        Log.d("ASYNC - STANDINGS", "STEP 2 - DRIVER");

        listenerStadings =(ICacheManagerStandings) mContext;

        DbTaskStadings taskRace = new DbTaskStadings(listenerStadings, mContext);
        taskRace.setStandingsArrayList(aStandings);
        taskRace.execute();
    }

    private class DbTaskRace extends AsyncTask<Void,Void,Boolean>{

      private Season season;
      private final WeakReference<ICacheManagerRace> listenerRace;
      private FNdb db;
      private RaceDao raceDao;
      private CircuitDao circuitDao;
      private SeasonDao seasonDao;
      public LocationDao locationDao;

      public List<Season> mDbSeason;
      public List<Race> mDbRace;
      public List<Circuit> mDbCircuit;
      public List<Location> mDbLocation;


      public DbTaskRace(ICacheManagerRace listener, Context context) {
          listenerRace = new WeakReference<>(listener);
          db = DbManager.createDb(context);
      }


      @Override
      protected Boolean doInBackground(Void... voids) {
          Log.d("ASYNC - RACE", "STEP 3");

          raceDao = db.raceDao();
          circuitDao = db.circuitDao();
          seasonDao = db.seasonDao();
          locationDao = db.locationDao();

          // controllo se c'è connessione
          if(season != null) {

              //Elimino tutto quello salvato nel database
              seasonDao.deleteAll();
              raceDao.deleteAll();
              circuitDao.deleteAll();
              locationDao.deleteAll();

              Log.d("ASYNC - RACE", "DELETE DB LOCATION - SIZE: " + locationDao.getAll().size());
              Log.d("ASYNC - RACE", "DELETE DB SEASON - SIZE: " + seasonDao.getAll().size());
              Log.d("ASYNC - RACE", "DELETE DB CIRCUIT - SIZE: " + circuitDao.getAll().size());
              Log.d("ASYNC - RACE", "DELETE DB RACE - SIZE: " + raceDao.getAll().size());

              //Inserisco la season
              seasonDao.insert(season);

              int mSizeRace = season.getRaces().size();

              // RACE
              for (int i = 0; i <= mSizeRace - 1; i++) {
                  Log.d("ASYNC - RACE", i + " STEP 4 ");

                  season.getRaces().get(i).setId(i);
                  season.getRaces().get(i).circuit.location.locationId = i;

                  //set CircuitID
                  season.getRaces().get(i).setCircuitId(season.getRaces().get(i).circuit.getCircuitId());

                  //set LocationID
                  season.getRaces().get(i).circuit.locationId = i;

                  //Inserisco la location
                  locationDao.insert(season.getRaces().get(i).circuit.location);

                  //Inserisco il circuit
                  circuitDao.insert(season.getRaces().get(i).circuit);

                  //Inserisco la race
                  Race race = season.getRaces().get(i);
                  raceDao.insert(race);
              }
          }

          mDbSeason = db.seasonDao().getAll();
          mDbRace = db.raceDao().getAll();
          mDbCircuit = db.circuitDao().getAll();
          mDbLocation = db.locationDao().getAll();

          return true;
      }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);

            Season mSeason = new Season();
            final ICacheManagerRace listener = listenerRace.get();

            mSeason.setRaces((ArrayList<Race>) mDbRace);
            mSeason.setSeasonYear(mDbSeason.get(0).getSeasonYear());

            for(int i=0; i<=mDbRace.size()-1; i++){
                mSeason.getRaces().get(i).setCircuit(mDbCircuit.get(i));
                mSeason.getRaces().get(i).circuit.setLocation(mDbLocation.get(i));
            }

            listener.getRaceList(mSeason);
        }

        public void setRaceArrayList(Season season) {
         this.season = season;
      }
    }

    private class DbTaskStadings extends AsyncTask<Void,Void,Boolean>{

        private ArrayList<Standings> standings;
        private WeakReference<ICacheManagerStandings> listenerStandings;
        private FNdb db;

        public List<Standings> mDbStandings;
        public List<DriverStanding> mDbDriverStanding;
        public List<Driver> mDbDriver;
        public List<ConstructorStanding> mDbConstructorStanding;
        public List<Constructor> mDbConstructor;

        public StandingsDao standingsDao;

        // DRIVER
        public DriverDao driverDao;
        public DriverStandingDao driverStandingDao;

        // CONSTRUCTOR
        public ConstructorStandingDao constructorStandingDao;
        public ConstructorDao constructorDao;


        public DbTaskStadings(ICacheManagerStandings listener, Context context) {
            this.listenerStandings = new WeakReference<>(listener);
            db = DbManager.createDb(context);
        }


        @Override
        protected Boolean doInBackground(Void... voids) {
            Log.d("ASYNC - STANDINGS", "STEP 3");

            standingsDao = db.standingsDao();

            // CONSTRUCTOR
            constructorDao = db.constructorDao();
            constructorStandingDao = db.constructorStandingDao();

            // DRIVER
            driverDao = db.driverDao();
            driverStandingDao = db.driverStandingDao();

            // controllo se c'è connessione
            if(!standings.isEmpty()) {

                if(standingsDao.getAll().size() == 0){
                    // assegno l'id a standingId
                    standings.get(0).standingId = 0;

                    // inserisco il db stadings
                    standingsDao.insert(standings.get(0));

                    // assegno l'id a standingId
                    standings.get(0).standingId = 1;

                    // inserisco il db stadings
                    standingsDao.insert(standings.get(0));
                }

                Log.d("ASYNC - STANDINGS", "DELETE DB STADINGS - SIZE: " + standingsDao.getAll().size());


                if (standings.get(0).ConstructorStandings != null) {

                    // elimino il db di driver
                    constructorDao.deleteAll();
                    constructorStandingDao.deleteAll();

                    Log.d("ASYNC - STANDINGS", "DELETE DB CONSTRUCTOR - SIZE: " + constructorDao.getAll().size());
                    Log.d("ASYNC - STANDINGS", "DELETE DB CONSTRUCTOR STADINGS - SIZE: " + constructorStandingDao.getAll().size());

                    int mSizeConstructors = standings.get(0).ConstructorStandings.size();

                    // CONSTRUCTORS
                    for (int i = 0; i <= mSizeConstructors - 1; i++) {
                        Log.d("ASYNC - STANDINGS", i + " STEP 4 - CONSTRUCTORS");

                        //assegno gli id a constructorStandingId
                        standings.get(0).ConstructorStandings.get(i).constructorStandingId = i;
                        //assegno gli id a constructorId
                        standings.get(0).ConstructorStandings.get(i).constructorId = standings.get(0).ConstructorStandings.get(i).getConstructor().constructorId;
                        //assegno gli id a standingId
                        standings.get(0).ConstructorStandings.get(i).standingId = 0;

                        constructorDao.insert(standings.get(0).ConstructorStandings.get(i).Constructor);
                        constructorStandingDao.insert(standings.get(0).ConstructorStandings.get(i));
                    }

                } else {

                    // elimino il db di driver
                    driverDao.deleteAll();
                    driverStandingDao.deleteAll();
                    constructorDao.deleteAll();

                    Log.d("ASYNC - STANDINGS", "DELETE DB DRIVER - SIZE: " + driverDao.getAll().size());
                    Log.d("ASYNC - STANDINGS", "DELETE DB DRIVER STADINGS - SIZE: " + driverStandingDao.getAll().size());

                    int mSizeDrivers = standings.get(0).DriverStandings.size();

                    // DRIVERS
                    for (int i = 0; i <= mSizeDrivers - 1; i++) {
                        Log.d("ASYNC - STANDINGS", i + " STEP 4 - DRIVERS");

                        //assegno gli id a driverStandingId
                        standings.get(0).DriverStandings.get(i).driverStandingId = i;
                        //assegno gli id a driverId
                        standings.get(0).DriverStandings.get(i).driverId = standings.get(0).DriverStandings.get(i).Driver.driverId;
                        //assegno gli id a standingId
                        standings.get(0).DriverStandings.get(i).standingId = 1;
                        standings.get(0).DriverStandings.get(i).Constructors.get(0).constructorId = standings.get(0).DriverStandings.get(i).Constructors.get(0).constructorId+i;
                        //assegno gli id a constructorId
                        standings.get(0).DriverStandings.get(i).constructorId = standings.get(0).DriverStandings.get(i).Constructors.get(0).constructorId;

                        constructorDao.insert(standings.get(0).DriverStandings.get(i).Constructors.get(0));
                        driverDao.insert(standings.get(0).DriverStandings.get(i).Driver);
                        driverStandingDao.insert(standings.get(0).DriverStandings.get(i));
                    }
                }
            }

            mDbStandings = db.standingsDao().getAll();
            mDbDriverStanding = db.driverStandingDao().getAll();
            mDbDriver = db.driverDao().getAll();
            mDbConstructorStanding = db.constructorStandingDao().getAll();
            mDbConstructor = db.constructorDao().getAll();

            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);

            Standings mStandings = new Standings();
            final ICacheManagerStandings listener = listenerStandings.get();

            mStandings.setConstructorStandings((ArrayList<ConstructorStanding>) mDbConstructorStanding);
            mStandings.setDriverStandings((ArrayList<DriverStanding>) mDbDriverStanding);

            mStandings.round = mDbStandings.get(0).round;
            mStandings.standingId = mDbStandings.get(0).standingId;

            if(mDbDriverStanding.size() != 0) {
                for (int i = 0; i <= mDbDriverStanding.size() - 1; i++) {
                    mStandings.DriverStandings.get(i).setDriver(mDbDriver.get(i));
                    mStandings.DriverStandings.get(i).setConstructors((ArrayList<Constructor>) mDbConstructor);
                }
            }

            if(mDbConstructorStanding.size() != 0) {
                for (int i = 0; i <= mDbConstructor.size() - 1; i++) {
                    mStandings.ConstructorStandings.get(i).setConstructor(mDbConstructor.get(i));
                }
            }

            listener.getStandingsList(mStandings);
        }

        public void setStandingsArrayList(ArrayList<Standings> aStendings) {
            this.standings = aStendings;
        }
    }
}
