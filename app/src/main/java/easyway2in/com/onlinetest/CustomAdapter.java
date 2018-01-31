package easyway2in.com.onlinetest;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.support.v7.widget.CardView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context contex;


    private List<CurrentAffair> my_data;
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;

    public CustomAdapter(Context contex, List<CurrentAffair> my_data) {
        this.contex=contex;
        this.my_data=my_data;
    }

    @Override
    public int getItemViewType(int position) {
        Log.d("correct", "score " + my_data.get(position));
         return my_data.get(position)==null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM ;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview,parent,false);
            return new ViewHolder(itemview,contex,my_data);
        }
        else if (viewType == VIEW_TYPE_LOADING) {
            View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.footer_view, parent, false);
            return new LoadingViewHolder(itemview);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            ViewHolder viewHolder = (ViewHolder) holder;
            viewHolder.description.setText(my_data.get(position).getDescription());
            Glide.with(contex).load(my_data.get(position).getImage_link()).into(viewHolder.imgeview);
        }
        else if (holder instanceof LoadingViewHolder) {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        }

    }

    @Override
    public int getItemCount() {

        return my_data == null ? 0 : my_data.size();
    }



    public static class ViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener  {
        public TextView description;
        public ImageView imgeview;
        public ProgressBar progressBar;
        List<CurrentAffair> my_data=new ArrayList<CurrentAffair>();
        Context ctx;
        public  ViewHolder(View itemView, Context ctx, List<CurrentAffair> my_data){
            super(itemView);
            this.my_data=my_data;
            this.ctx=ctx;
            itemView.setOnClickListener(this);
            description=(TextView)itemView.findViewById(R.id.description);
            imgeview=(ImageView)itemView.findViewById(R.id.image);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progressBar1);
        }

        @Override
        public void onClick(View v) {
            int position =getAdapterPosition();
            if (my_data.size() >0){
            CurrentAffair currentAffair=this.my_data.get(position);
            Intent intent=new Intent(this.ctx,DetailActivity.class);
            intent.putExtra("img_link",currentAffair.getImage_link());
            intent.putExtra("description",currentAffair.getDescription());
            this.ctx.startActivity(intent);
            }
        }

    }
    static class LoadingViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;
        public LoadingViewHolder(View itemView) {
            super(itemView);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progressBar1);
        }
    }
}
