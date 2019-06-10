package thunderbytes.com.formulanews.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import thunderbytes.com.formulanews.Models.Results;
import thunderbytes.com.formulanews.R;

public class AdapterResult extends BaseAdapter {

    private ArrayList<Results> dataList;

    public AdapterResult(ArrayList<Results> data){
        this.dataList = data;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Results getItem(int position) { return dataList.get(position); }

    @Override
    public long getItemId(int position) { return dataList.get(position).getPosition(); }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View cellView;

        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            cellView = inflater.inflate(R.layout.adapter_result_cell_layout, parent, false);

            ViewHolder viewHolder = new ViewHolder();

            viewHolder.txt_id = cellView.findViewById(R.id.labelPosition);
            viewHolder.txt_name = cellView.findViewById(R.id.labelNamePilot);
            viewHolder.txt_time = cellView.findViewById(R.id.labelTime);
            viewHolder.txt_gap = cellView.findViewById(R.id.labelGap);
            viewHolder.txt_pts = cellView.findViewById(R.id.labelPts);

            cellView.setTag(viewHolder);

        }else{
            cellView = convertView;
        }

        ViewHolder vHolder = (ViewHolder)cellView.getTag();

        vHolder.txt_id.setText(""+getItem(position).getPositionText());
        vHolder.txt_name.setText(getItem(position).Driver.givenName + " " + getItem(position).Driver.familyName);

        if(getItem(position).getPositionText().equals("R")){
            vHolder.txt_time.setText("");
            vHolder.txt_gap.setText("");
        }else if (getItem(position).getStatus().equals("Finished") && position == 0){
            vHolder.txt_time.setText(getItem(position).Time.getTime()+"");
        }else if(getItem(position).getStatus().equals("Finished") && position > 0){
            vHolder.txt_gap.setText(getItem(position).Time.getTime());
        }

        vHolder.txt_pts.setText(getItem(position).getPoints()+"");


        return cellView;
    }

    private class ViewHolder{

        public TextView txt_id;
        public TextView txt_name;
        public TextView txt_time;
        public TextView txt_gap;
        public TextView txt_pts;

    }
}
