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
import thunderbytes.com.formulanews.R;

public class ListFragment extends Fragment {
    public static final String ID = "id";
    private int fragmentId;
    ListView listView;
    TextView title;
    ImageButton infoBtn;

    String fakeName[] = {"Valtteri Bottas", "Lewis Hamilton" , "Sebastian Vettel", "Max verstappen"};
    String fakeScore[] = {"87", "86", "52", "51"};

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

        MyAdapter adapter = new MyAdapter(getContext(), fakeName, fakeScore);
        listView.setAdapter(adapter);
        return vView;
    }


    class MyAdapter extends ArrayAdapter<String> {
        Context context;
        String rName[];
        String rDate[];

        MyAdapter(Context c, String name[], String date[]) {
            super(c, R.layout.list_cell_layout, R.id.textLeft, name);
            this.context = c;
            this.rName = name;
            this.rDate = date;
        }

        @NonNull
        @Override
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View cell = layoutInflater.inflate(R.layout.list_cell_layout, parent, false);
            TextView textLeft = cell.findViewById(R.id.textLeft);
            TextView textRight = cell.findViewById(R.id.textRight);
            ImageButton infoBtn = cell.findViewById(R.id.infoBtn);

            switch (fragmentId) {
                case 0:
                    title.setText("Calendario");
                    infoBtn.setVisibility(View.VISIBLE);
                    break;

                case 1:
                    title.setText("Classifica piloti");
                    infoBtn.setVisibility(View.INVISIBLE);
                    break;

                case 2:
                    title.setText("Classifica costruttori");
                    infoBtn.setVisibility(View.INVISIBLE);
                    break;
            }
            textLeft.setText(rName[position]);
            textRight.setText(rDate[position]);

            infoBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("API", rName[position]);
                    mListener.onItemValue(rName[position]);
                }
            });

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
