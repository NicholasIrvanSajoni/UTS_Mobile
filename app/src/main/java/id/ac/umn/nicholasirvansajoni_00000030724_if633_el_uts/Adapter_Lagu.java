package id.ac.umn.nicholasirvansajoni_00000030724_if633_el_uts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class Adapter_Lagu extends RecyclerView.Adapter<Adapter_Lagu.MyViewHolder> implements Filterable {
    private List<Lagu> mSongList;
    private List<Lagu> mSongListFiltered;
    private Context mContext;
    private SongAdapterListener listener;

    public Adapter_Lagu(Context context, List<Lagu> songList, SongAdapterListener listener){
        this.mContext = context;
        this.mSongList = songList;
        this.listener = listener;
        this.mSongListFiltered = songList;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    mSongListFiltered = mSongList;
                } else {
                    List<Lagu> filteredList = new ArrayList<>();
                    for (Lagu row : mSongList) {
                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getTitle().toLowerCase().contains(charString.toLowerCase()) || row
                                .getArtist().toLowerCase().contains(charSequence)) {
                            filteredList.add(row);
                        }
                    }
                    mSongListFiltered = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = mSongListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mSongListFiltered = (ArrayList<Lagu>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lagu, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Lagu song =mSongListFiltered.get(position);
        holder.title.setText(song.getTitle());
        holder.artist.setText(song.getArtist());
        Glide.with(mContext).load(song.getThumbnail()).placeholder(R.drawable.ikonmusik).error(R.drawable.ikonmusik)
                .crossFade().centerCrop().into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return mSongListFiltered.size();
    }

    public void removeItem(int position){
        mSongList.remove(position);
        // notify the item removed by position
        notifyItemRemoved(position);
    }

    public void restoreItem(Lagu item, int position){
        mSongList.add(position, item);
        notifyItemInserted(position);
    }

    public interface SongAdapterListener{
        void onSongSelected(Lagu song);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView title, artist;
        public GridLayout viewBackground, viewForeground;
        public ImageView thumbnail;

        public MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.song_title);
            artist = view.findViewById(R.id.song_artist);
            viewBackground = view.findViewById(R.id.view_background);
            viewForeground = view.findViewById(R.id.view_foreground);
            thumbnail = view.findViewById(R.id.thumbnail);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // send selected contact in callback
                    listener.onSongSelected(mSongListFiltered.get(getAdapterPosition()));
                }
            });
        }
    }
}