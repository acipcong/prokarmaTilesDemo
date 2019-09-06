package tiles.prokarma.co.tiles.views.adapters;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import tiles.prokarma.co.tiles.R;
import tiles.prokarma.co.tiles.network.tos.Photo;
import tiles.prokarma.co.tiles.views.activities.HomeActivity;

/**
 * Created by USER on 9/6/2019.
 */

public class TilesAdapter extends RecyclerView.Adapter<TilesAdapter.TilesViewHolder> {

    ArrayList<Photo> matrix;
    Context mContext;
    ArrayList<String> flippedImages = new ArrayList<>();
    ArrayList<String> matchedImages = new ArrayList<>();
    RecyclerView tiles;

    int flippedImagesCount = 0;
    int attempts = 0;

    HomeActivity mainActivity;

    public TilesAdapter(Context applicationContext, ArrayList<Photo> matrix, RecyclerView tiles, HomeActivity mainActivity) {
        this.matrix = matrix;
        mContext = applicationContext;
        this.tiles = tiles;
        this.mainActivity = mainActivity;
        attempts = 0;
        flippedImagesCount = 0;
        flippedImages = new ArrayList<>();
        matchedImages = new ArrayList<>();
        mainActivity.updateAttempts(attempts);
    }

    @NonNull
    @Override
    public TilesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vh = LayoutInflater.from(parent.getContext()).inflate(R.layout.tiles_view, parent, false);
        return new TilesViewHolder(vh);
    }

    @Override
    public void onBindViewHolder(@NonNull final TilesViewHolder viewHolder, int position) {

        final Photo photo = matrix.get(position);
        viewHolder.itemView.setTag(photo.getId());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // do nothing if the clicked image is already flipped
                if(!matchedImages.contains(photo.getId())){
                    mainActivity.updateAttempts(++attempts);
                    // clicked image is first image
                    if(flippedImages.size() == 0) {
                        flippedImages.add(photo.getId());
                        rotateImage(viewHolder, viewHolder.image, false);
                        // clicked image to be compared with previously flipped image and if it matches with previous image
                    }else if(flippedImages.size() == 1 && photo.getId().equalsIgnoreCase(flippedImages.get(0))){
                        flippedImagesCount += 2;
                        if(flippedImagesCount >= tiles.getChildCount()){
                            mainActivity.updateBest(attempts);
                        }
                        matchedImages.add(photo.getId());
                        rotateImage(viewHolder, viewHolder.image, false);
                        flippedImages.clear();
                        // clicked image to compared and if it doesnt match reset to original positions
                    }else if(flippedImages.size() == 1){
                        rotateImage(viewHolder, viewHolder.image, false);

                        final Runnable isAliveTimerRunnable = new Runnable() {
                            @Override
                            public void run() {
                                resetImagesWithTags(flippedImages.get(0));
                                resetImagesWithTags(photo.getId());
                                flippedImages.clear();

                            }
                        };

                        new Handler().postDelayed(isAliveTimerRunnable, 2000);
                    }
                    // check to compare all the matches are identified
                }else if(flippedImagesCount >= tiles.getChildCount()){
                    mainActivity.updateBest(attempts);
                }
            }
        });

    }

    // if the flipped images are not matched... reset with default images
    public void resetImagesWithTags(String tag){
        int count = tiles.getChildCount();
        for(int i=0 ; i<count ; i++){
            RelativeLayout vh = (RelativeLayout)tiles.getChildAt(i);
            if(vh.getTag().equals(tag)){
                ImageView image = vh.findViewById(R.id.image);
                image.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.ic_launcher));
            }
        }
    }

    @Override
    public int getItemCount() {
        return matrix.size();
    }

    public void rotateImage(final TilesViewHolder viewHolder, final ImageView image, final boolean flagReset){
        ObjectAnimator anim = (ObjectAnimator) AnimatorInflater.loadAnimator(mContext, R.animator.flipping);
        anim.setTarget(image);
        anim.setDuration(500);
        anim.start();
        anim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                if(!flagReset)
                    Glide.with(mContext).load(matrix.get(viewHolder.getAdapterPosition()).getUrl()).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(image);
                else
                    image.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.ic_launcher));
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
    }

    class TilesViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        public TilesViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {



                }
            });
        }
    }
}

