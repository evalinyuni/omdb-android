package id.test.omdbcicil.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;

import id.test.omdbcicil.ui.Utils.NetworkUtils;

public class NetworkChangeReceiver extends BroadcastReceiver {
    private static NetworkChange networkChange;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onReceive(Context context, Intent intent) {
        networkChange.OnNetworkChanged(NetworkUtils.getConnectivityStatus(context));
    }

    public void setNetworkChange(NetworkChange networkChange) {
        NetworkChangeReceiver.networkChange = networkChange;
    }

    public interface NetworkChange {
        void OnNetworkChanged(int status);
    }
}
