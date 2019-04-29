package id.test.omdbcicil.ui.Utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;

import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetSequence;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.test.omdbcicil.R;

public class TapTargetViewHelper {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private Activity activity;
    private Context context;

    public TapTargetViewHelper(Activity activity) {
        this.activity = activity;
        this.context = activity.getBaseContext();
        ButterKnife.bind(this, activity);
    }

    public void showTutorial() {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int screenWidth = displaymetrics.widthPixels;
        int screenHeight = displaymetrics.heightPixels;
        final Drawable movie = ContextCompat.getDrawable(context, R.drawable.ic_view_list);
        final Rect movieTarget = new Rect(0, 0,
                (movie != null ? movie.getIntrinsicWidth() : 0 ) * 2,
                (movie != null ? movie.getIntrinsicHeight() : 0) * 2);
        movieTarget.offset(screenWidth / 2, screenHeight / 2);
        new TapTargetSequence(activity)
                .continueOnCancel(true)
                .targets(
                        TapTarget.forToolbarMenuItem(toolbar, R.id.action_search,
                                context.getText(R.string.tutorial_search_title), context.getText(R.string.tutorial_search_description))
                                .targetCircleColor(android.R.color.white)
                                .titleTextColor(android.R.color.white)
                                .descriptionTextColor(android.R.color.white)
                                .drawShadow(true)
                                .cancelable(true)
                                .tintTarget(true),
                        TapTarget.forBounds(movieTarget,
                                context.getText(R.string.tutorial_list_title), context.getText(R.string.tutorial_list_description))
                                .targetCircleColor(android.R.color.white)
                                .titleTextColor(android.R.color.white)
                                .descriptionTextColor(android.R.color.white)
                                .icon(movie)
                                .cancelable(true)
                                .tintTarget(true)
                ).start();
    }
}
