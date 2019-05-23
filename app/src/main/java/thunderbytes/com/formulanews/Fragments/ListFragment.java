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
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import thunderbytes.com.formulanews.Models.Race;
import thunderbytes.com.formulanews.R;

public class ListFragment extends Fragment {
    public static final String ID = "id";
    private int fragmentId;
    private Race race;
    ListView listView;
    TextView title;
    ImageButton infoBtn;

    String fakePosition[] = {"1", "2" , "3", "4","5"};
    String fakeFirstName[] = {"Lewis", "Valteri", "Max", "Sebastian","Charles"};
    String fakeLastName[] = {"Hamilton", "Bottas", "Verstappen", "Vettel", "Leclerc"};
    String fakePoints[] = {"112 PTS", "105 PTS", "66 PTS", "66 PTS", "66 PTS"};

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

        MyAdapter adapter = new MyAdapter(getContext(), fakePosition, fakeFirstName, fakeLastName, fakePoints);
        listView.setAdapter(adapter);
        return vView;
    }


    class MyAdapter extends ArrayAdapter<String> {
        Context context;
        String rankPosition[];
        String upText[];
        String downText[];
        String points[];

        MyAdapter(Context c, String rankPosition[], String upName[], String downName[], String points[]) {
            super(c, R.layout.list_cell_layout, R.id.position, rankPosition);
            this.context = c;
            this.rankPosition = rankPosition;
            this.upText = upName;
            this.downText = downName;
            this.points = points;
        }

        @NonNull
        @Override
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View cell = null;
            TextView textPosition = null;
            TextView textUpText = null;
            TextView textDownText = null;
            TextView textPoints = null;

            switch (fragmentId) {
                case 0:
                    title.setText("Calendario");
                    cell = layoutInflater.inflate(R.layout.calendar_cell_layout, parent, false);
                    //infoBtn.setVisibility(View.VISIBLE);
                    break;

                case 1:
                    title.setText("Classifica piloti");
                    cell = layoutInflater.inflate(R.layout.list_cell_layout, parent, false);
                    textPosition = cell.findViewById(R.id.position);
                    textUpText = cell.findViewById(R.id.upText);
                    textDownText = cell.findViewById(R.id.downText);
                    textPoints = cell.findViewById(R.id.points);
                    textPosition.setText(rankPosition[position]);
                    textUpText.setText(upText[position]);
                    textDownText.setText(downText[position]);
                    textPoints.setText(points[position]);
                    //infoBtn.setVisibility(View.INVISIBLE);
                    break;

                case 2:
                    title.setText("Classifica costruttori");
                    cell = layoutInflater.inflate(R.layout.list_cell_layout, parent, false);
                    textPosition = cell.findViewById(R.id.position);
                    textUpText = cell.findViewById(R.id.upText);
                    textDownText = cell.findViewById(R.id.downText);
                    textPoints = cell.findViewById(R.id.points);
                    textPosition.setText(rankPosition[position]);
                    textUpText.setText(upText[position]);
                    textDownText.setText(downText[position]);
                    textPoints.setText(points[position]);

                    //infoBtn.setVisibility(View.INVISIBLE);
                    break;
            }

            //infoBtn.setOnClickListener(new View.OnClickListener() {
            //    @Override
            //    public void onClick(View v) {
            //    }
            //});

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
