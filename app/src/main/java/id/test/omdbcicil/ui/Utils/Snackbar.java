package id.test.omdbcicil.ui.Utils;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import id.test.omdbcicil.R;

public class Snackbar {
    public static void Success(Activity activity) {
        android.support.design.widget.Snackbar snackbar = android.support.design.widget.Snackbar.make(activity.findViewById(R.id.cl_movies), activity.getText(R.string.search_success), android.support.design.widget.Snackbar.LENGTH_LONG);
        View sbView = snackbar.getView();
        sbView.setBackgroundColor(Color.WHITE);
        TextView textView = sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.BLACK);
        snackbar.show();
    }

    public static void Error(Activity activity, String message) {
        android.support.design.widget.Snackbar snackbar = android.support.design.widget.Snackbar.make(activity.findViewById(R.id.cl_movies), message, android.support.design.widget.Snackbar.LENGTH_LONG);
        View sbView = snackbar.getView();
        sbView.setBackgroundColor(Color.WHITE);
        TextView textView = sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.RED);
        snackbar.show();
    }

}
