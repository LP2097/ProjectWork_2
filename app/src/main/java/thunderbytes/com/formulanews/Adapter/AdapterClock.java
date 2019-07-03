package thunderbytes.com.formulanews.Adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Date;

import thunderbytes.com.formulanews.R;

public class AdapterClock extends RecyclerView.Adapter  {

    private ArrayList dataList;

    public AdapterClock(ArrayList data){
        this.dataList = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_clock_layout, viewGroup, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {

        ViewHolder mViewHold = (ViewHolder) viewHolder;

        //GESTIONE BACKGROUND
        if(i % 2 == 0){
            mViewHold.itemView.setBackgroundColor(Color.WHITE);
        }else{
            mViewHold.itemView.setBackgroundColor(Color.parseColor("#F0F3F4"));
        }

        ArrayList mDate = (ArrayList) dataList.get(i);

        //GESTIONE INIZIALIZZAIZONE CELLE
        mViewHold.txt_date.setText(android.text.format.DateFormat.format("dd", (Date) mDate.get(0)));
        mViewHold.txt_month.setText(android.text.format.DateFormat.format("MMM", (Date) mDate.get(0)));
        mViewHold.txt_type.setText(""+mDate.get(1));

        //SE ESISTE L'ITEM 3 DELL'ORARIO ORARIO
        if(mDate.size() == 3){
            mViewHold.txt_hour.setText(""+mDate.get(2));
        }


    }

    @Override
    public long getItemId(int position) { return position; }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


    private class ViewHolder  extends RecyclerView.ViewHolder{

        public TextView txt_date;
        public TextView txt_month;
        public TextView txt_type;
        public TextView txt_hour;

        public ViewHolder(View itemView) {
            super(itemView);
            txt_date = itemView.findViewById(R.id.textDate);
            txt_month = itemView.findViewById(R.id.textMonth);
            txt_type = itemView.findViewById(R.id.textRight);
            txt_hour = itemView.findViewById(R.id.textHour);

        }
    }

    /*private View cellView;

    private ArrayList dataList;

    public AdapterClock(ArrayList data){
        this.dataList = data;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public String getItem(int position) { return (String) dataList.get(position); }

    @Override
    public long getItemId(int position) { return position; }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            cellView = inflater.inflate(R.layout.adapter_clock_layout, parent, false);

            ViewHolder viewHolder = new ViewHolder();

            viewHolder.txt_date = cellView.findViewById(R.id.textDate);
            /*viewHolder.txt_name = cellView.findViewById(R.id.upText);
            viewHolder.txt_nationality = cellView.findViewById(R.id.downText);
            viewHolder.txt_point = cellView.findViewById(R.id.points);
            viewHolder.vw_color_team = cellView.findViewById(R.id.identifyConstructor);*/

            /*cellView.setTag(viewHolder);

        }else{
            cellView = convertView;
        }

        ViewHolder vHolder = (ViewHolder) cellView.getTag();

        vHolder.txt_date.setText("09");
        /*vHolder.txt_name.setText(""+ vDataConstructor.get(position).getConstructor().getName());
        vHolder.txt_nationality.setText("" + vDataConstructor.get(position).getConstructor().getNationality());
        vHolder.txt_point.setText("" + vDataConstructor.get(position).getPoints()  + " PTS");

        vHolder.vw_color_team.setBackgroundColor(vColorTeam.get(vDataConstructor.get(position).Constructor.name));*/

       /* return cellView;
    }

    private class ViewHolder{

        public TextView txt_date;
        public TextView txt_month;
        public TextView txt_type;
        public TextView txt_hour;

    }*/



}
