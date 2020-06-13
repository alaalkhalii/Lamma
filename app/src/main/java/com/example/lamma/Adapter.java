package com.example.lamma;

        import android.content.Context;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.TextView;
        import android.view.LayoutInflater;
        import java.util.List;
        import androidx.recyclerview.widget.RecyclerView;
        import androidx.recyclerview.*;


        import com.squareup.picasso.Picasso;

public class Adapter
        extends RecyclerView.Adapter<Adapter.MyView> {

    // List with String type
    private List<String> list;
    private List<Integer> list2;
    private List<Upload> mUploadList;
    private Context mContext;

    public class MyView extends RecyclerView.ViewHolder {

        // Text View
        TextView textView;
        ImageView mImageView;

        // parameterised constructor for View Holder class which takes the view as a parameter

        public MyView(View view)
        {
            super(view);

            // initialise TextView with id
            textView = view.findViewById(R.id.title);
            mImageView = view.findViewById(R.id.thumbnail);
        }
    }

    public Adapter(Context context, List<Upload> uploads )
    {
        this.mUploadList = uploads;
        this.mContext = context;
    }

    // Override onCreateViewHolder which deals with the inflation of the card layout as an item for the RecyclerView.
    @Override
    public MyView onCreateViewHolder(ViewGroup parent, int viewType)
    {
        // Inflate item.xml using LayoutInflater
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_card, parent, false);
        // return itemView
        return new MyView(itemView);
    }

    // Override onBindViewHolder which deals with the setting of different data and methods related to clicks on particular items of the RecyclerView
    @Override
    public void onBindViewHolder(final MyView holder, final int position)
    {

        holder.textView.setText(mUploadList.get(position).getmName());
        Picasso.with(mContext).load(mUploadList.get(position).getmImageUrl()).fit().centerCrop().into(holder.mImageView);
    }

    // Override getItemCount which Returns the length of the RecyclerView.
    @Override
    public int getItemCount()
    {
        return mUploadList.size();
    }
}
