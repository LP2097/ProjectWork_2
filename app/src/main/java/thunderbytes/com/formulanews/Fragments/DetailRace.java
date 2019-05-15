package thunderbytes.com.formulanews.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import thunderbytes.com.formulanews.R;

public class DetailRace extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_race, container, false);

        TextView vTitle = getView().findViewById(R.id.title);
        ImageView vCircuit = getView().findViewById(R.id.circuitRace);
        TextView vFP1 = getView().findViewById(R.id.labelFP1);
        TextView vFP2 = getView().findViewById(R.id.labelFP2);
        TextView vFP3 = getView().findViewById(R.id.labelFP3);
        TextView vQualification = getView().findViewById(R.id.labelQualification);
        TextView vRace = getView().findViewById(R.id.labelRace);
        Button vNotification = getView().findViewById(R.id.buttonNotification);

        vNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enableNotification();
            }
        });

        return view;
    }

    private void enableNotification(){

    }
}
