package sg.edu.tp.mysicmysic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import sg.edu.tp.mysicmysic.SongDatabase.Song;

public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.MyViewHolder> {
    private final RecyclerViewInterface recyclerViewInterface;

    Context context;
    ArrayList<Song> Songlist;
    ArrayList<Song> originalSonglist;


    public PlaylistAdapter(Context context, ArrayList<Song> Songlist,
                                    RecyclerViewInterface recyclerViewInterface) {

        this.context               = context;
        this.Songlist              = Songlist;
        this.recyclerViewInterface = recyclerViewInterface;
        this.originalSonglist      = Songlist;
    }


    @NonNull
    @Override
    public PlaylistAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.activity_view_playlist_recyclerfile, parent,false);

        return new PlaylistAdapter.MyViewHolder(view,recyclerViewInterface);
    }


    @Override
    public void onBindViewHolder(@NonNull PlaylistAdapter.MyViewHolder holder, int position) {

        holder.txtSongName.setText(Songlist.get(position).getTitle());
        holder.txtArtiste.setText(Songlist.get(position).getArtiste());
        holder.imageSongs.setImageResource(Songlist.get(position).getDrawable());


        holder.btnRemove.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Songlist.remove(holder.getAdapterPosition());
                notifyDataSetChanged();

            }

        });

    }


    @Override
    public int getItemCount() {
        return Songlist.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageSongs;
        TextView  txtSongName;
        TextView  txtArtiste;
        Button    btnRemove;


        public MyViewHolder(@NonNull View itemView,RecyclerViewInterface recyclerViewInterface) {
            super(itemView);

            imageSongs  = itemView.findViewById(R.id.imageView3);
            txtSongName = itemView.findViewById(R.id.textSongName);
            txtArtiste  = itemView.findViewById(R.id.textArtiste);
            btnRemove   = itemView.findViewById(R.id.btnRemoveSong);


            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    if(recyclerViewInterface != null) {

                            int pos = getAdapterPosition();

                            if(pos != RecyclerView.NO_POSITION) {
                                recyclerViewInterface.onItemClick(pos);
                            }
                    }
                }
            });
        }
    }
}