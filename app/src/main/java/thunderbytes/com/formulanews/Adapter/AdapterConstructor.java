package thunderbytes.com.formulanews.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import thunderbytes.com.formulanews.Models.ConstructorStanding;
import thunderbytes.com.formulanews.R;

public class AdapterConstructor extends BaseAdapter {

    private ArrayList<ConstructorStanding> vDataConstructor;
    private LinkedHashMap<String, Integer> vColorTeam;

    public AdapterConstructor(ArrayList<ConstructorStanding> data, LinkedHashMap<String, Integer> colorTeam){
        this.vDataConstructor = data;
        this.vColorTeam = colorTeam;
    }

    @Override
    public int getCount() {
        return vDataConstructor.size();
    }

    @Override
    public ConstructorStanding getItem(int position) { return vDataConstructor.get(position); }

    @Override
    public long getItemId(int position) { return vDataConstructor.get(position).getPosition(); }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View cellView;

        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            cellView = inflater.inflate(R.layout.list_cell_layout_constructor, parent, false);

            ViewHolder viewHolder = new ViewHolder();

            viewHolder.txt_position = cellView.findViewById(R.id.position);
            viewHolder.txt_name = cellView.findViewById(R.id.upText);
            viewHolder.txt_nationality = cellView.findViewById(R.id.downText);
            viewHolder.txt_point = cellView.findViewById(R.id.points);
            viewHolder.vw_color_team = cellView.findViewById(R.id.identifyConstructor);

            cellView.setTag(viewHolder);

        }else{
            cellView = convertView;
        }

        ViewHolder vHolder = (ViewHolder)cellView.getTag();

        vHolder.txt_position.setText("" + (position+1));
        vHolder.txt_name.setText(""+ vDataConstructor.get(position).getConstructor().getName());
        vHolder.txt_nationality.setText("" + vDataConstructor.get(position).getConstructor().getNationality());
        vHolder.txt_point.setText("" + vDataConstructor.get(position).getPoints()  + " PTS");

        vHolder.vw_color_team.setBackgroundColor(vColorTeam.get(vDataConstructor.get(position).Constructor.name));

        return cellView;
    }

    private class ViewHolder{

        public TextView txt_position;
        public TextView txt_name;
        public TextView txt_nationality;
        public TextView txt_point;
        public View vw_color_team;

    }
}
