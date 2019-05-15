package thunderbytes.com.formulanews.Activities;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import thunderbytes.com.formulanews.R;

public class CalendarActivity extends AppCompatActivity {

    ListView listView;
    String fakeName[] = {"Gara uno", "Grand prix", "Grandissimo prix"};
    String fakeRaceDate[] = {"21 marzo, 6:12", "21 liglio, 6:12", "31 marzo, 6:45"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        listView = findViewById(R.id.listView);

        MyAdapter adapter = new MyAdapter(this, fakeName, fakeRaceDate);
        listView.setAdapter(adapter);

    }

    class MyAdapter extends ArrayAdapter<String> {

        Context context;
        String rName[];
        String rDate[];

        MyAdapter(Context c, String name[], String date[]) {
            super(c, R.layout.calendar_cell_layout, R.id.raceNameTxt, name);

            this.context = c;
            this.rName = name;
            this.rDate = date;

        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View cell = layoutInflater.inflate(R.layout.calendar_cell_layout, parent, false);

            TextView mName = cell.findViewById(R.id.raceNameTxt);
            TextView mDate = cell.findViewById(R.id.dateRaceTxt);

            mName.setText(rName[position]);
            mDate.setText(rDate[position]);

            return cell;
        }
    }

}
