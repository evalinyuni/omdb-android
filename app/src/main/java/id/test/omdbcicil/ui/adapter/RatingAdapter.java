package id.test.omdbcicil.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.test.omdbcicil.R;
import id.test.omdbcicil.models.MovieDetails;

public class RatingAdapter extends RecyclerView.Adapter<RatingAdapter.RatingViewHolder> {

    private ArrayList<MovieDetails.Rating> ratings;

    public RatingAdapter(ArrayList<MovieDetails.Rating> ratings) {
        this.ratings = ratings;
    }

    @NonNull
    @Override
    public RatingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RatingViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.rating_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RatingViewHolder holder, int position) {
        final MovieDetails.Rating rating = ratings.get(position);
        holder.tv_source.setText(rating.getSource());
        holder.tv_rating.setText(rating.getValue());
    }

    @Override
    public int getItemCount() {
        return ratings == null ? 0 : ratings.size();
    }

    class RatingViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_source)
        TextView tv_source;
        @BindView(R.id.tv_rating)
        TextView tv_rating;

        RatingViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
