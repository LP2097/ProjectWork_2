package thunderbytes.com.formulanews.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
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
            viewHolder.image_flag = cellView.findViewById(R.id.flagImage);

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
        vHolder.txt_Date.setText(android.text.format.DateFormat.format("dd", mCalendar.getTime()) + " - " + android.text.format.DateFormat.format("dd", race.getDate()));
        vHolder.txt_Month.setText(android.text.format.DateFormat.format("MMM", race.getDate()));

        String nation = race.getCircuit().location.getCountry();

        switch (nation){
            case "China":
                vHolder.image_flag.setImageResource(R.drawable.china_flag);
                break;
            case "Australia":
                vHolder.image_flag.setImageResource(R.drawable.australian_flag);
                break;
            case "Bahrain":
                vHolder.image_flag.setImageResource(R.drawable.bahrain_flag);
                break;
            case "Azerbaijan":
                vHolder.image_flag.setImageResource(R.drawable.azerbaijan_flag);
                break;
            case "Spain":
                vHolder.image_flag.setImageResource(R.drawable.spain_flag);
                break;
            case "Monaco":
                vHolder.image_flag.setImageResource(R.drawable.monaco_flag);
                break;
            case "Canada":
                vHolder.image_flag.setImageResource(R.drawable.canada_flag);
                break;
            case "France":
                vHolder.image_flag.setImageResource(R.drawable.french_flag);
                break;
            case "Austria":
                vHolder.image_flag.setImageResource(R.drawable.austria_flag);
                break;
            case "UK":
                vHolder.image_flag.setImageResource(R.drawable.uk_flag);
                break;
            case "Germany":
                vHolder.image_flag.setImageResource(R.drawable.german_flag);
                break;
            case "Hungary":
                vHolder.image_flag.setImageResource(R.drawable.hungary_flag);
                break;
            case "Belgium":
                vHolder.image_flag.setImageResource(R.drawable.belgium_flag);
                break;
            case "Italy":
                vHolder.image_flag.setImageResource(R.drawable.italy_flag);
                break;
            case "Singapore":
                vHolder.image_flag.setImageResource(R.drawable.singapore_flag);
                break;
            case "Russia":
                vHolder.image_flag.setImageResource(R.drawable.russia_flag);
                break;
            case "Japan":
                vHolder.image_flag.setImageResource(R.drawable.japan_flag);
                break;
            case "Mexico":
                vHolder.image_flag.setImageResource(R.drawable.mexico_flag);
                break;
            case "USA":
                vHolder.image_flag.setImageResource(R.drawable.usa_flag);
                break;
            case "Brazil":
                vHolder.image_flag.setImageResource(R.drawable.brazil_flag);
                break;
            case "UAE":
                vHolder.image_flag.setImageResource(R.drawable.uae_flag);
                break;
        }

        return cellView;
    }

    private class ViewHolder{

        public TextView txt_RaceName;
        public TextView txt_Date;
        public TextView txt_Month;
        public ImageView image_flag;

    }
}