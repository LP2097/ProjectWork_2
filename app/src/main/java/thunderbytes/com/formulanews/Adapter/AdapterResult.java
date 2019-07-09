package thunderbytes.com.formulanews.Adapter;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import thunderbytes.com.formulanews.Models.Results;
import thunderbytes.com.formulanews.R;

public class AdapterResult extends RecyclerView.Adapter {

    private ArrayList<Results> dataList;

    public AdapterResult(ArrayList<Results> data){
        this.dataList = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_result_cell_layout, viewGroup, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {

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

        if(dataList.get(i).getPositionText().equals("1")){
            viewHold.txt_time.setText(""+dataList.get(i).getTime().getTime());
            viewHold.txt_gap.setText("");
        }else
        {
            viewHold.txt_gap.setText("R");

            if (dataList.get(i).getTime() != null)
            {
                viewHold.txt_gap.setText(""+ dataList.get(i).getTime().getTime());
            }

            if(dataList.get(i).getStatus().toLowerCase().contains("lap"))
            {
                viewHold.txt_gap.setText(""+dataList.get(i).getStatus());
            }

            viewHold.txt_time.setText("");
        }

        viewHold.txt_pts.setText(dataList.get(i).getPoints()+"");

    }

    @Override
    public long getItemId(int position) { return dataList.get(position).getPosition(); }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


    private class ViewHolder  extends RecyclerView.ViewHolder{

        public TextView txt_id;
        public TextView txt_name;
        public TextView txt_time;
        public TextView txt_gap;
        public TextView txt_pts;

        public ViewHolder(View itemView) {
            super(itemView);
            txt_id = itemView.findViewById(R.id.labelPosition);
            txt_name = itemView.findViewById(R.id.labelNamePilot);
            txt_time = itemView.findViewById(R.id.labelTime);
            txt_gap = itemView.findViewById(R.id.labelGap);
            txt_pts = itemView.findViewById(R.id.labelPts);

        }
    }
}
