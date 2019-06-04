package thunderbytes.com.formulanews.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import thunderbytes.com.formulanews.Adapter.DetailAdapter;
import thunderbytes.com.formulanews.R;

public class DetailFragment extends Fragment {

    public static DetailFragment newIstance(){
        return new DetailFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vView = inflater.inflate(R.layout.fragment_item_layout, container, false);

        ListView listView = vView.findViewById(R.id.listViewResult);

        //DetailAdapter adapter = new DetailAdapter(/*TODO aggiungere valori che arrivano dal listfragment*/);

        //listView.setAdapter(adapter);

        return vView;
    }
}
