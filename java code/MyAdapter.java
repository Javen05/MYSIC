package sg.edu.tp.mysicmysic;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import sg.edu.tp.mysicmysic.SongDatabase.Song;
import sg.edu.tp.mysicmysic.SongDatabase.SongCollection;

public class MyAdapter extends RecyclerView.Adapter <MyAdapter.ViewHolder> {

    private Context context;
    private Song[] songs = new SongCollection().getSongs();

    public MyAdapter(PlaylistActivity context) {

        this.context = context;
    }

    @Override @NonNull
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                   int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }



    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {

        Song song = songs[position];

        holder.txtSongName.setText(song.getTitle());
        holder.txtArtiste.setText(song.getArtiste());

        SongCollection songCollection = new SongCollection();

        holder.imageView.setImageResource(songCollection.getCurrentSong(position).getDrawable());
        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, PlayerActivity.class);

                intent.putExtra("index", holder.getAdapterPosition());
                context.startActivity(intent);

            }
        });
    }


    @Override
    public int getItemCount() {
        return songs.length;
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtSongName;
        TextView txtArtiste;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);

            this.txtSongName = itemView.findViewById(R.id.txtSongName);
            this.txtArtiste = itemView.findViewById(R.id.txtArtiste);
            this.imageView = itemView.findViewById(R.id.imageView);

        }
    }
}
