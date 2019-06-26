package thunderbytes.com.formulanews.Adapter;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import thunderbytes.com.formulanews.Models.Qualifying;
import thunderbytes.com.formulanews.Models.Results;
import thunderbytes.com.formulanews.R;

public class AdapterQualifying extends BaseAdapter {
    private ArrayList<Qualifying> dataList;
    private int qualifing;

    public AdapterQualifying(ArrayList<Qualifying> data, int qualifing){
        this.dataList = data;
        this.qualifing = qualifing;

    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Qualifying getItem(int position) { return dataList.get(position); }

    @Override
    public long getItemId(int position) { return dataList.get(position).getPosition(); }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View cellView;

        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            cellView = inflater.inflate(R.layout.adapter_qualifying_cell_layout, parent, false);

            ViewHolder viewHolder = new ViewHolder();

            viewHolder.txt_id = cellView.findViewById(R.id.labelPosition2);
            viewHolder.txt_name = cellView.findViewById(R.id.labelNamePilot2);
            viewHolder.txt_Q1 = cellView.findViewById(R.id.labelQ1);

            cellView.setTag(viewHolder);

        }else{
            cellView = convertView;
        }


        ViewHolder vHolder = (ViewHolder) cellView.getTag();

        vHolder.txt_id.setText(""+(position+1));

        //formattazione nome pilota
        String driver = getItem(position).Driver.familyName;
        String driverNameFormat = driver.substring(0, Math.min(driver.length(), 3)).toUpperCase();

        vHolder.txt_name.setText(driverNameFormat);

        if (getItem(position).getPosition() % 2 == 0) {
            int background = Color.parseColor("#F0F3F4");
            cellView.setBackgroundColor(background);
        }
        else
        {
            cellView.setBackgroundColor(Color.WHITE);
        }

        if(qualifing == 1) {
            vHolder.txt_Q1.setText(getItem(position).Q1);
        }
        else if(qualifing == 2) {
            vHolder.txt_Q1.setText(getItem(position).Q2);
        }
        else
        {
            vHolder.txt_Q1.setText(getItem(position).Q3);
        }


        return cellView;
    }

    private class ViewHolder{

        public TextView txt_id;
        public TextView txt_name;
        public TextView txt_Q1;


    }
}
