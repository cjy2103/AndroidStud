package com.example.youtubetest2.adapter;

import android.content.Context;
import android.telecom.Call;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.youtubetest2.R;
import com.example.youtubetest2.models.VideoYT;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class AdapterHome extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<VideoYT> videoList;

    public AdapterHome(Context context, List<VideoYT> videoList) {
        this.context    = context;
        this.videoList  = videoList;
    }

    class YouTubeHolder extends RecyclerView.ViewHolder {

        ImageView thumbnail;
        TextView test, tanggal;

        public YouTubeHolder(@NonNull View itemView) {
            super(itemView);
            thumbnail   =   itemView.findViewById(R.id.iv_thumbnail);
            test        =   itemView.findViewById(R.id.tv_test);
            tanggal     =   itemView.findViewById(R.id.tv_tglUpdate);
        }

        public void setData(VideoYT data) {
            String getTest  =   data.getSnippet().getTitle();
            String getTgl   =   data.getSnippet().getPublishedAt();
            String getThumb =   data.getSnippet().getThumbnails().getMedium().getUrl();
            test.setText(getTest);
            tanggal.setText(getTgl);
            Picasso.get()
                    .load(getThumb)
                    .placeholder(R.mipmap.ic_launcher)
                    .fit()
                    .centerCrop()
                    .into(thumbnail, new Callback() {
                        @Override
                        public void onSuccess() {
                            Log.d(TAG, "Thumbnail load Sucess");
                        }

                        @Override
                        public void onError(Exception e) {
                            Log.d(TAG, "Thumbnail error : ",e);
                        }
                    });


        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view   =   inflater.inflate(R.layout.row_item_home, parent, false);
        return new YouTubeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
         VideoYT videoYT = videoList.get(position);
         YouTubeHolder yth  = (YouTubeHolder) holder;
         yth.setData(videoYT);
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
