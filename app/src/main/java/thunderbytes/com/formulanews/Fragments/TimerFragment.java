package thunderbytes.com.formulanews.Fragments;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

import thunderbytes.com.formulanews.R;

public class TimerFragment extends Fragment {

     public interface ITimerFragment
    {
        void startTimer();
    }

    private static final String FRAGMET = "timerFrag";
    private static final String SAVE_TIME = "timerFrag";

    TimerAsync mTimer;
    TextView mSeconds, mMinutes, mHours, mDays;
    ITimerFragment listener;

    public long timeDiff;
    public long seconds, minutes, hours, days;



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof ITimerFragment) {
            listener = (ITimerFragment) context;
            if (mTimer != null)
                mTimer.setListener(this);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View vView = inflater.inflate(R.layout.timer_layout, container, false);
        mDays=vView.findViewById(R.id.daysLable);
        mHours=vView.findViewById(R.id.hoursLable);
        mMinutes=vView.findViewById(R.id.minutesLable);
        mSeconds=vView.findViewById(R.id.secondsLable);

        Bundle bundle = this.getArguments();

        //gli passo i millisecondi e li suddivido per ora, minuti ecc..
        if(bundle != null)
            timeDiff = bundle.getLong("Date");

            days = TimeUnit.MILLISECONDS
                    .toDays(timeDiff);
            timeDiff -= TimeUnit.DAYS.toMillis(days);

            hours = TimeUnit.MILLISECONDS
                    .toHours(timeDiff);
            timeDiff -= TimeUnit.HOURS.toMillis(hours);

            minutes = TimeUnit.MILLISECONDS
                    .toMinutes(timeDiff);
            timeDiff -= TimeUnit.MINUTES.toMillis(minutes);

            seconds = TimeUnit.MILLISECONDS
                    .toSeconds(timeDiff);

            mDays.setText(""+days);
            mHours.setText(""+hours);
            listener.startTimer();

        return vView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public void startTimer(){
        if (mTimer==null){
            mTimer = new TimerAsync(this);
            mTimer.execute();
        }
    }

    public void stopTimer()
    {
        if(mTimer != null)
        {
            mTimer.cancel(true);
            mTimer = null;
        }
    }

    static class TimerAsync extends AsyncTask<Void, Integer, Void> {

        int secondsCounter, minutesCounter, hourCounter;
        private static final String TAG = "timer";

        WeakReference<TimerFragment> mUpdater;

        public TimerAsync(TimerFragment aListener){
            mUpdater = new WeakReference<>(aListener);
        }

        public void setListener(TimerFragment aListener){
            mUpdater = new WeakReference<>(aListener);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            secondsCounter=(int)mUpdater.get().seconds;
            minutesCounter=(int)mUpdater.get().minutes;
            hourCounter=(int)mUpdater.get().hours;

            while (!isCancelled()){
                publishProgress(secondsCounter);
                secondsCounter--;
                Log.d("FRAGMENT", "il fragment sta andando");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (secondsCounter==0){
                    if (minutesCounter==0){
                        publishProgress(minutesCounter=60);
                        publishProgress(hourCounter--);
                    }
                    publishProgress(secondsCounter=60);
                    publishProgress(minutesCounter--);
                }
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            if (mUpdater.get()!=null){
                mUpdater.get().mSeconds.setText(""+secondsCounter);
                mUpdater.get().mMinutes.setText(""+minutesCounter);
                mUpdater.get().mHours.setText(""+hourCounter);
            }
        }

    }


}
