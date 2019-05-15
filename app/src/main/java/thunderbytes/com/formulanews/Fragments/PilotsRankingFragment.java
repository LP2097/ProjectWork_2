package thunderbytes.com.formulanews.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import thunderbytes.com.formulanews.R;

public class PilotsRankingFragment extends Fragment {
    public PilotsRankingFragment() {
        //Costruttore vuoto
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.calendar_layout, null);
    }
}
