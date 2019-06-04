package thunderbytes.com.formulanews.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import thunderbytes.com.formulanews.Models.Race;
import thunderbytes.com.formulanews.R;

public class DetailAdapter extends BaseAdapter {

    private ArrayList<Race> dataList;

    public DetailAdapter(ArrayList<Race> data){
        this.dataList = data;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {

        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View cellView;

        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            cellView = inflater.inflate(R.layout.detail_cell_layout, parent, false);

            ViewHolder viewHolder = new ViewHolder();

            viewHolder.txt_id = cellView.findViewById(R.id.labelPosition);
            viewHolder.txt_name = cellView.findViewById(R.id.labelNamePilot);
            viewHolder.txt_time = cellView.findViewById(R.id.labelTime);
            viewHolder.txt_gap = cellView.findViewById(R.id.labelGap);

            cellView.setTag(viewHolder);

        }else{
            cellView = convertView;
        }

        return cellView;
    }

    private class ViewHolder{

        public TextView txt_id;
        public TextView txt_name;
        public TextView txt_time;
        public TextView txt_gap;

    }
}
