package thunderbytes.com.formulanews.Broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import thunderbytes.com.formulanews.Service.NetworkUtil;

public class InternetReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        String status = NetworkUtil.getConnectivityStatusString(context);
        if(status.isEmpty()) {
            status="Nessuna connessione a internet";
        }
        Toast.makeText(context, status, Toast.LENGTH_LONG).show();
    }
}
