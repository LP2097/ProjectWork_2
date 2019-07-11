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
import java.util.List;

import thunderbytes.com.formulanews.Fragments.ListFragment;
import thunderbytes.com.formulanews.Models.DriverStanding;
import thunderbytes.com.formulanews.R;

public class AdpterDriver extends BaseAdapter {

    private View cellView;

    private ArrayList<DriverStanding> vDataDrivers;
    private LinkedHashMap<String, Integer> vColorTeam;

    public AdpterDriver(ArrayList<DriverStanding> data, LinkedHashMap<String, Integer> colorTeam){
        this.vDataDrivers = data;
        this.vColorTeam = colorTeam;
    }

    @Override
    public int getCount() {
        return vDataDrivers.size();
    }

    @Override
    public DriverStanding getItem(int position) { return vDataDrivers.get(position); }

    @Override
    public long getItemId(int position) { return vDataDrivers.get(position).getPosition(); }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            cellView = inflater.inflate(R.layout.list_cell_layout_driver, parent, false);

            ViewHolder viewHolder = new ViewHolder();

            /* Layout cell_title_layout */
            viewHolder.txt_position = cellView.findViewById(R.id.position);
            viewHolder.txt_name = cellView.findViewById(R.id.upText);
            viewHolder.txt_surname = cellView.findViewById(R.id.downText);
            viewHolder.txt_point = cellView.findViewById(R.id.points);
            viewHolder.vw_color_team = cellView.findViewById(R.id.identifyConstructor);

            /* Layout cell_content_layout_driver */
            viewHolder.txt_name_driver = cellView.findViewById(R.id.txt_name_driver);
            viewHolder.txt_number_driver = cellView.findViewById(R.id.txt_number_driver);
            viewHolder.txt_nationality_driver = cellView.findViewById(R.id.txt_nationality_driver);
            viewHolder.txt_points_driver = cellView.findViewById(R.id.txt_points_driver);
            viewHolder.txt_birthday_driver = cellView.findViewById(R.id.txt_birthday_driver);
            viewHolder.img_driver = cellView.findViewById(R.id.img_driver);

            cellView.setTag(viewHolder);

        }else{
            cellView = convertView;
        }

        ViewHolder vHolder = (ViewHolder)cellView.getTag();

        /* Layout cell_title_layout */
        vHolder.txt_position.setText("" + (position+1));
        vHolder.txt_name.setText("" + vDataDrivers.get(position).Driver.givenName);
        vHolder.txt_surname.setText("" + vDataDrivers.get(position).Driver.familyName);
        vHolder.txt_point.setText("" + vDataDrivers.get(position).getPoints() + " PTS");


        vHolder.vw_color_team.setBackgroundColor(vColorTeam.get(vDataDrivers.get(position).Constructors.get(position).name));


        (cellView.findViewById(R.id.folding_cell)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /* Layout cell_content_layout_driver */
                vHolder.txt_name_driver.setText("" + vDataDrivers.get(position).Driver.givenName + " " + vDataDrivers.get(position).Driver.familyName);
                vHolder.txt_number_driver.setText("" + vDataDrivers.get(position).Driver.permanentNumber);
                vHolder.txt_nationality_driver.setText("" + vDataDrivers.get(position).Driver.nationality);
                vHolder.txt_points_driver.setText("" + vDataDrivers.get(position).getPoints());
                vHolder.txt_birthday_driver.setText("" + android.text.format.DateFormat.format("dd.MM.yyyy", vDataDrivers.get(position).Driver.dateOfBirth));

                Resources res = vHolder.img_driver.getContext().getResources();
                int id = res.getIdentifier("thunderbytes.com.formulanews:drawable/" + vDataDrivers.get(position).Driver.givenName.toString().toLowerCase(), null, null);
                vHolder.img_driver.setImageResource(id);

                ((FoldingCell) v).toggle(false);

            }
        });


        return cellView;
    }

    private class ViewHolder{

        /* Layout cell_title_layout */
        public TextView txt_position;
        public TextView txt_name;
        public TextView txt_surname;
        public TextView txt_point;
        public View vw_color_team;

        /* Layout cell_content_layout_driver */
        public TextView txt_name_driver;
        public TextView txt_number_driver;
        public TextView txt_nationality_driver;
        public TextView txt_points_driver;
        public TextView txt_birthday_driver;
        public ImageView img_driver;

    }
}
