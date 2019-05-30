package thunderbytes.com.formulanews.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import thunderbytes.com.formulanews.Models.DriverStanding;
import thunderbytes.com.formulanews.Models.Race;
import thunderbytes.com.formulanews.Models.Standings;
import thunderbytes.com.formulanews.R;

public class ListFragment extends Fragment{
    LinearLayout detailRace;
    public static final String ID = "id";
    private int fragmentId;
    private Race race;
    ListView listView;
    TextView title,textPosition,textUpText,textDownText,textPoints;
    MyAdapter adapter;
    public ListFragment() { }


    public interface OnItemClicked{
        void onItemValue(String aCircuit);
    }
    private OnItemClicked mListener;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View vView = inflater.inflate(R.layout.list_layout, container, false);
        title = (TextView)vView.findViewById(R.id.fragmentTitle);
        listView = (ListView)vView.findViewById(R.id.listView);
        fragmentId = getArguments().getInt(ID);

        switch (fragmentId){
            case 0:
                vView.setBackgroundColor(getResources().getColor(R.color.white_smoke));
                break;

            case 1:
                vView.setBackgroundColor(getResources().getColor(R.color.grey_darker));
                break;

            case 2:
                vView.setBackgroundColor(getResources().getColor(R.color.grey_darker));
                break;
        }
        return vView;
    }


    class MyAdapter extends ArrayAdapter<String> {
        Context context;
        ArrayList<Standings> arrayRankList;

        MyAdapter(Context c, ArrayList<Standings> arrayRankList) {
            super(c, R.layout.calendar_cell_layout, R.id.position);
            this.context = c;
            this.arrayRankList = arrayRankList;
            ArrayList<DriverStanding> driverRankList = arrayRankList.get(0).getDriverStandings();
        }

        @NonNull
        @Override
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View cell = null;
            textPosition = null;
            textUpText = null;
            textDownText = null;
            textPoints = null;

            switch (fragmentId) {
                case 0:
                    title.setText("Calendario");
                    cell = layoutInflater.inflate(R.layout.calendar_cell_layout, parent, false);

                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            //mListener.onItemValue();
                            Log.d("TEST", "Click");
                        }
                    });
                    break;

                case 1:
                    title.setText("Classifica piloti");
                    cell = layoutInflater.inflate(R.layout.list_cell_layout, parent, false);
                    textPosition = cell.findViewById(R.id.position);
                    textUpText = cell.findViewById(R.id.upText);
                    textDownText = cell.findViewById(R.id.downText);
                    textPoints = cell.findViewById(R.id.points);
                    break;

                case 2:
                    title.setText("Classifica costruttori");
                    cell = layoutInflater.inflate(R.layout.list_cell_layout, parent, false);
                    textPosition = cell.findViewById(R.id.position);
                    textUpText = cell.findViewById(R.id.upText);
                    textDownText = cell.findViewById(R.id.downText);
                    textPoints = cell.findViewById(R.id.points);
                    break;
            }
            return cell;
        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnItemClicked) {
            mListener = (OnItemClicked)context;
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
