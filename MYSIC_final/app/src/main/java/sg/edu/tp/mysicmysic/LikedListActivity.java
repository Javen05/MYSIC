package sg.edu.tp.mysicmysic;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import sg.edu.tp.mysicmysic.SongDatabase.Song;
import sg.edu.tp.mysicmysic.SongDatabase.SongCollection;

public class LikedListActivity extends AppCompatActivity implements RecyclerViewInterface {

    RecyclerView PlaylistSong;
    PlaylistAdapter LikedListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_liked_list);
        AssigningId();

        SongCollection songCollection = new SongCollection();
        ArrayList<Song> likedSongs    = songCollection.getLikedSongs();


        LikedListAdapter = new PlaylistAdapter(this,PlayerActivity.likedSongs,this);
        PlaylistSong.setAdapter(LikedListAdapter);
        PlaylistSong.setLayoutManager(new LinearLayoutManager(this));


        //set song count
        TextView songCount = findViewById(R.id.songCount);
        songCount.setText(PlayerActivity.likedSongs.size() + " " + "Songs in List");


        //backspace
        ImageButton btnBack;

        btnBack = (ImageButton)findViewById(R.id.btnBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void AssigningId() {
        PlaylistSong = findViewById(R.id.RecyclerViewSong);
    }


    @Override
    public void onItemClick(int index) {

        Song song = LikedListAdapter.Songlist.get(index);
        SongCollection songCollection = new SongCollection();
        int songPosition = songCollection.searchSongById(song.getId());

        Intent intent = new Intent(this, PlayerActivity.class);
        intent.putExtra("index", songPosition);
        startActivity(intent);

    }

}