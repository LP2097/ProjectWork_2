package thunderbytes.com.formulanews.Adapter;

import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ramotion.foldingcell.FoldingCell;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import thunderbytes.com.formulanews.Fragments.ListFragment;
import thunderbytes.com.formulanews.Models.ConstructorStanding;
import thunderbytes.com.formulanews.R;

public class AdapterConstructor extends BaseAdapter {

    private View cellView;

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

        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            cellView = inflater.inflate(R.layout.list_cell_layout_constructor, parent, false);

            ViewHolder viewHolder = new ViewHolder();

            /* Layout list_cell_layout_constructor */
            viewHolder.txt_position = cellView.findViewById(R.id.position);
            viewHolder.txt_name = cellView.findViewById(R.id.upText);
            viewHolder.txt_nationality = cellView.findViewById(R.id.downText);
            viewHolder.txt_point = cellView.findViewById(R.id.points);
            viewHolder.vw_color_team = cellView.findViewById(R.id.identifyConstructor);

            /* Layout list_content_layout_constructor */
            viewHolder.txt_name_constructor = cellView.findViewById(R.id.txt_name_constructor);
            viewHolder.txt_nationality_constructor = cellView.findViewById(R.id.txt_nationality_constructor);
            viewHolder.txt_points_constructor = cellView.findViewById(R.id.txt_points_constructor);
            viewHolder.img_constructor = cellView.findViewById(R.id.img_constructor);

            cellView.setTag(viewHolder);

        }else{
            cellView = convertView;
        }

        ViewHolder vHolder = (ViewHolder)cellView.getTag();

        /* Layout list_cell_layout_constructor */
        vHolder.txt_position.setText("" + (position+1));
        vHolder.txt_name.setText(""+ vDataConstructor.get(position).getConstructor().getName());
        vHolder.txt_nationality.setText("" + vDataConstructor.get(position).getConstructor().getNationality());
        vHolder.txt_point.setText("" + vDataConstructor.get(position).getPoints()  + " PTS");

        vHolder.vw_color_team.setBackgroundColor(vColorTeam.get(vDataConstructor.get(position).Constructor.name));


        (cellView.findViewById(R.id.folding_cell)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /* Layout cell_content_layout_driver */
                vHolder.txt_name_constructor.setText("" + vDataConstructor.get(position).Constructor.getName());
                vHolder.txt_nationality_constructor.setText("" + vDataConstructor.get(position).Constructor.getNationality());
                vHolder.txt_points_constructor.setText("" + vDataConstructor.get(position).getPoints());

                /*Resources res = vHolder.img_constructor.getContext().getResources();
                int id = res.getIdentifier("thunderbytes.com.formulanews:drawable/" + vDataConstructor.get(position).Constructor.getName().toString().toLowerCase(), null, null);
                vHolder.img_constructor.setImageResource(id);*/

                ((FoldingCell) v).toggle(false);
            }
        });

        return cellView;
    }

    private class ViewHolder{

        /* Layout list_cell_layout_constructor */
        public TextView txt_position;
        public TextView txt_name;
        public TextView txt_nationality;
        public TextView txt_point;
        public View vw_color_team;

        /* Layout list_content_layout_constructor */
        public TextView txt_name_constructor;
        public TextView txt_nationality_constructor;
        public TextView txt_points_constructor;
        public ImageView img_constructor;

    }
}
