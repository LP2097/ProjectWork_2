package thunderbytes.com.formulanews.Service;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkUtil {

    private static boolean mCheckConnection;

    public static String getConnectivityStatusString(Context context)
    {
        String status = null;
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null)
        {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                status = "Connesso in Wifi";
                mCheckConnection = true;
                return status;
            } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                status = "Connesso con dati mobili";
                mCheckConnection = true;
                return status;
            }
        }
        else
        {
            mCheckConnection = false;
            status = "Non connesso";
            return status;
        }
        return status;
    }

    public Boolean getConnection(){ return mCheckConnection; }
}
