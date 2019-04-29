package id.test.omdbcicil.ui.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;

import java.util.Objects;

import id.test.omdbcicil.ext.Constants;

public class NetworkUtils {
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static int getConnectivityStatus(@NonNull Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = Objects.requireNonNull(cm).getActiveNetworkInfo();
        return activeNetwork == null || !activeNetwork.isConnected() ?
                Constants.NETWORK_DESCONNECTED : Constants.NETWORK_CONNECTED;
    }
}
