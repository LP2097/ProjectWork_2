package thunderbytes.com.formulanews.Adapter;

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

    public AdapterQualifying(ArrayList<Qualifying> data){
        this.dataList = data;
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
            viewHolder.txt_Q2 = cellView.findViewById(R.id.labelQ2);
            viewHolder.txt_Q3 = cellView.findViewById(R.id.labelQ3);

            cellView.setTag(viewHolder);

        }else{
            cellView = convertView;
        }

        ViewHolder vHolder = (ViewHolder) cellView.getTag();

        vHolder.txt_id.setText(""+(position+1));
        vHolder.txt_name.setText(getItem(position).Driver.familyName);
        vHolder.txt_Q1.setText(getItem(position).Q1);
        vHolder.txt_Q2.setText(getItem(position).Q2);
        vHolder.txt_Q3.setText(getItem(position).Q3);


        return cellView;
    }

    private class ViewHolder{

        public TextView txt_id;
        public TextView txt_name;
        public TextView txt_Q1;
        public TextView txt_Q2;
        public TextView txt_Q3;

    }
}
