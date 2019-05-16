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
    ListView listView;
    TextView title;
    ImageButton infoBtn;

    String fakeName[] = {"Valtteri Bottas", "Lewis Hamilton" , "Sebastian Vettel", "Max verstappen"};
    String fakeScore[] = {"87", "86", "52", "51"};

    public ListFragment() { }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View vView = inflater.inflate(R.layout.list_layout, container, false);
        title = (TextView)vView.findViewById(R.id.fragmentTitle);
        listView = (ListView)vView.findViewById(R.id.listView);

        title.setText("Classifica piloti");

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

            TextView mName = cell.findViewById(R.id.textLeft);
            TextView mDate = cell.findViewById(R.id.textRight);
            ImageButton infoBtn = cell.findViewById(R.id.infoBtn);

            infoBtn.setVisibility(View.INVISIBLE);
            mName.setText(rName[position]);
            mDate.setText(rDate[position]);

            ImageButton getRaceBtn = cell.findViewById(R.id.infoBtn);
            getRaceBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("API", rName[position]);
                }
            });

            return cell;
        }
    }
}
