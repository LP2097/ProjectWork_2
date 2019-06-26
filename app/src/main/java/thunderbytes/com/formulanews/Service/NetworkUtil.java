package thunderbytes.com.formulanews.Service;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkUtil {
    public static String getConnectivityStatusString(Context context)
    {
        String status = null;
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null)
        {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                status = "Connesso in Wifi";
                return status;
            } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                status = "Connesso con dati mobili";
                return status;
            }
        }
        else
        {
            status = "Non connesso";
            return status;
        }
        return status;
    }
}
