package id.test.omdbcicil.ui;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;
import id.test.omdbcicil.R;
import id.test.omdbcicil.receivers.NetworkChangeReceiver;

import static android.net.ConnectivityManager.CONNECTIVITY_ACTION;

public class NetworkActivity extends AppCompatActivity implements NetworkChangeReceiver.NetworkChange {

    private IntentFilter intentFilter;
    private NetworkChangeReceiver networkChangeReceiver;
    private boolean hasNetwork;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);
        ButterKnife.bind(this);
        intentFilter = new IntentFilter();
        intentFilter.addAction(CONNECTIVITY_ACTION);
        networkChangeReceiver = new NetworkChangeReceiver();
        networkChangeReceiver.setNetworkChange(this);
    }


    @OnClick(R.id.bt_retry)
    public void retry() {
        if (hasNetwork) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }

    @Override
    protected void onResume() {
        registerReceiver(networkChangeReceiver, intentFilter);
        super.onResume();
    }

    @Override
    protected void onPause() {
        unregisterReceiver(networkChangeReceiver);
        super.onPause();
    }

    @Override
    public void OnNetworkChanged(int status) {
        hasNetwork = status != 0;
    }
}
