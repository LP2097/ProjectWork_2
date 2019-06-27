package thunderbytes.com.formulanews.Adapter;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
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

public class AdapterQualifying extends RecyclerView.Adapter {
    private ArrayList<Qualifying> dataList;
    private int qualifing;

    public AdapterQualifying(ArrayList<Qualifying> data, int qualifing){
        this.dataList = data;
        this.qualifing = qualifing;

    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_qualifying_cell_layout, viewGroup, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {


        ViewHolder viewHold = (ViewHolder) viewHolder;

        //GESTIONE BACKGROUND
        if(i % 2 == 0){
            viewHold.itemView.setBackgroundColor(Color.WHITE);
        }else{
            viewHold.itemView.setBackgroundColor(Color.parseColor("#F0F3F4"));
        }

        //GESTIONE INIZIALIZZAIZONE CELLE
        viewHold.txt_id.setText(""+(i+1));

        String driver = dataList.get(i).Driver.familyName;
        String driverNameFormat = driver.substring(0, Math.min(driver.length(), 3)).toUpperCase();

        viewHold.txt_name.setText(""+driverNameFormat);

        if(qualifing == 1) {
            viewHold.txt_Q1.setText(dataList.get(i).Q1);
        }
        else if(qualifing == 2) {
            viewHold.txt_Q1.setText(dataList.get(i).Q2);
        }
        else
        {
            viewHold.txt_Q1.setText(dataList.get(i).Q3);
        }

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    @Override
    public long getItemId(int position) { return dataList.get(position).getPosition(); }


    private class ViewHolder extends RecyclerView.ViewHolder{

        public TextView txt_id;
        public TextView txt_name;
        public TextView txt_Q1;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_id = itemView.findViewById(R.id.labelPosition2);
            txt_name = itemView.findViewById(R.id.labelNamePilot2);
            txt_Q1 = itemView.findViewById(R.id.labelQ1);

        }
    }
}
