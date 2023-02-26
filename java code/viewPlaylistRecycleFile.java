package sg.edu.tp.mysicmysic;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class viewPlaylistRecycleFile extends RecyclerView.ViewHolder {

    public TextView txtSongName, txtArtiste;
    public Button btnRemoveSong;
    public ImageView imageSong;

    public viewPlaylistRecycleFile(@NonNull View itemView) {

        super(itemView);
        imageSong = itemView.findViewById(R.id.imageView3);
        btnRemoveSong = itemView.findViewById(R.id.btnRemoveSong);
        txtSongName = itemView.findViewById(R.id.textSongName);
        txtArtiste = itemView.findViewById(R.id.textArtiste);

    }
}