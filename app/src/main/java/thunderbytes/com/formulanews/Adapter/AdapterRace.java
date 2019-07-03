package thunderbytes.com.formulanews.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

import thunderbytes.com.formulanews.Models.Race;
import thunderbytes.com.formulanews.R;

public class AdapterRace extends BaseAdapter {

        private View cellView;

        private ArrayList<Race> vDataRaces;

    public AdapterRace(ArrayList<Race> data){
        this.vDataRaces = data;
    }

        @Override
        public int getCount() {
        return vDataRaces.size();
    }

        @Override
        public Race getItem(int position) { return vDataRaces.get(position); }

        @Override
        public long getItemId(int position) { return vDataRaces.get(position).getId(); }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {


        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            cellView = inflater.inflate(R.layout.calendar_cell_layout, parent, false);

            ViewHolder viewHolder = new ViewHolder();

            viewHolder.txt_RaceName = cellView.findViewById(R.id.textRight);
            viewHolder.txt_Date = cellView.findViewById(R.id.textDate);
            viewHolder.txt_Month = cellView.findViewById(R.id.textMonth);

            cellView.setTag(viewHolder);

        }else{
            cellView = convertView;
        }

        ViewHolder vHolder = (ViewHolder)cellView.getTag();
        Race race = vDataRaces.get(position);

        Calendar mCalendar = Calendar.getInstance();
        mCalendar.setTime(race.getDate());
        mCalendar.add(Calendar.DATE, -2);

        vHolder.txt_RaceName.setText(race.getRaceName());
        vHolder.txt_Date.setText(android.text.format.DateFormat.format("dd", race.getDate()) + " - " + android.text.format.DateFormat.format("dd", mCalendar.getTime()));
        vHolder.txt_Month.setText(android.text.format.DateFormat.format("MMM", race.getDate()));

        return cellView;
    }

    private class ViewHolder{

        public TextView txt_RaceName;
        public TextView txt_Date;
        public TextView txt_Month;

    }
}