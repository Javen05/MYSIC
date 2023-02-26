package sg.edu.tp.mysicmysic;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import sg.edu.tp.mysicmysic.SongDatabase.Song;
import sg.edu.tp.mysicmysic.SongDatabase.SongCollection;

public class PlayerActivity extends AppCompatActivity {

    private String title = "";
    private String artiste = "";
    private String fileLink = "";
    private int drawable;
    private int currentIndex = -1;

    private MediaPlayer player = new MediaPlayer();
    private ImageButton btnPlayPause = null;
    private SongCollection songCollection = new SongCollection();

    TextView elapsedTimeLabel, remainingTimeLabel;

    List<Song> shuffleList = Arrays.asList(songCollection.getSongs());
    public static ArrayList<Song> likedSongs = new ArrayList<>();

    public static ArrayList<Song> playlistSongs = new ArrayList<>();

    SeekBar seekbar;
    Handler handler = new Handler();

    int totalTime;
    SharedPreferences sharedPreferences;

    public boolean addedFlag;
    public boolean likedFlag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        ImageButton btnAddSong = findViewById(R.id.btnAddSong);
        ImageButton btnLikeSong = findViewById(R.id.btnLikeSong);

        //refresh buttons
        likedFlag = false;
        addedFlag = true;


        //play song onCreate
        btnPlayPause = findViewById(R.id.btnPlayPause);
        Bundle songData = this.getIntent().getExtras();
        currentIndex = songData.getInt("index");
        displaySongBasedOnIndex();
        playSong(fileLink);


        //Seekbar initialisations
        totalTime = player.getDuration();
        btnLoopSong = findViewById(R.id.btnLoopSong);
        elapsedTimeLabel = findViewById(R.id.elapsedTimeLabel);
        remainingTimeLabel = findViewById(R.id.remainingTimeLabel);

        handler.removeCallbacks(p_bar);
        handler.postDelayed(p_bar,1000);


        LinearLayout durationBar = this.findViewById(R.id.durationBar);
        durationBar.setVisibility(View.VISIBLE);

        seekbar = findViewById(R.id.durationbarBar);
        seekbar.setMax(totalTime);
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {}

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                if(player != null && player.isPlaying()) {
                    player.seekTo(seekBar.getProgress());
                }
            }
        });


        //backspace
        ImageButton btnBack;

        btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                onBackPressed();
            }
        });


        //persistent list
        sharedPreferences = getSharedPreferences("likedList", MODE_PRIVATE);
        String likedList = sharedPreferences.getString("likedList","");

        if(likedList.equals("")) {
            TypeToken<ArrayList<Song>> token = new TypeToken<ArrayList<Song>>() {};
            Gson gson = new Gson();

            //revert json
            likedList = gson.fromJson(likedList, token.getType());
        }


        //add to liked list
        btnLikeSong.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if(!likedFlag) {

                    Song selectedSong = songCollection.getCurrentSong(currentIndex);
                    likedSongs.add(selectedSong);

                    Gson gson = new Gson();
                    String json = gson.toJson(likedSongs);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("likedList", json);
                    editor.apply();

                    btnLikeSong.setColorFilter(Color.RED);
                    Toast.makeText(PlayerActivity.this, "Liked", Toast.LENGTH_SHORT).show();

                    likedFlag = true;

                } else {

                    Song selectedSong = songCollection.getCurrentSong(currentIndex);
                    likedSongs.remove(selectedSong);

                    Gson gson = new Gson();
                    String json = gson.toJson(likedSongs);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("likedList", json);
                    editor.apply();

                    btnLikeSong.setColorFilter(Color.WHITE);
                    Toast.makeText(PlayerActivity.this, "Un-Liked", Toast.LENGTH_SHORT).show();

                    likedFlag = false;
                }

            }});


        //persistent list
        sharedPreferences = getSharedPreferences("playList", MODE_PRIVATE);
        String albums = sharedPreferences.getString("list","");

        if(albums.equals("")) {
            TypeToken<ArrayList<Song>> token = new TypeToken<ArrayList<Song>>() {};
            Gson gson = new Gson();

            //revert json
            playlistSongs = gson.fromJson(albums, token.getType());
        }


        //add to playlist
        btnAddSong.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if(addedFlag) {

                    Song selectedSong = songCollection.getCurrentSong(currentIndex);
                    playlistSongs.add(selectedSong);

                    Gson gson = new Gson();
                    String json = gson.toJson(playlistSongs);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("list", json);
                    editor.apply();

                    btnAddSong.setImageResource(R.drawable.added_playlist);
                    Toast.makeText(PlayerActivity.this, "Added to Playlist", Toast.LENGTH_SHORT).show();

                    addedFlag = false;

                } else {

                    Toast.makeText(PlayerActivity.this, "Already added", Toast.LENGTH_SHORT).show();
                }

            }});

        //end of OnCreate
    }


    //seekbar
    Runnable p_bar = new Runnable() {

        @Override
        public void run() {

            if(player != null && player.isPlaying()) {

                    Message msg = new Message();
                    msg.what = player.getCurrentPosition();
                    handler.sendMessage(msg);

                    int currentPosition = msg.what;

                    String elapsedTime = createTimeLabel(currentPosition);
                    elapsedTimeLabel.setText(elapsedTime);

                    String remainingTime = createTimeLabel(totalTime - currentPosition);
                    remainingTimeLabel.setText(remainingTime);

                    seekbar.setProgress(currentPosition);

            } else {

                onPause();
            }

            handler.postDelayed(this, 500);

        }

    };


    //configuring seekbar timer
    public String createTimeLabel(int time) {

        String timeLabel;
        int min = time / 1000 / 60;
        int sec = time / 1000 % 60;
        timeLabel = min + ":" + sec;
        return timeLabel;

    }


    //display selected song accordingly
    public void displaySongBasedOnIndex() {

        Song song = songCollection.getCurrentSong(currentIndex);
        title = song.getTitle();
        artiste = song.getArtiste();
        fileLink = song.getFileLink();
        drawable = song.getDrawable();

        TextView txtTitle = findViewById(R.id.txtSongTitle);
        txtTitle.setText(title);
        TextView txtTitleFront = findViewById(R.id.txtSongTitleFront);
        txtTitleFront.setText(title);

        TextView txtArtiste = findViewById(R.id.txtArtist);
        txtArtiste.setText(artiste);
        TextView txtArtisteFront = findViewById(R.id.txtArtistFront);
        txtArtisteFront.setText(artiste);

        ImageView iCoverArt = findViewById(R.id.imgCoverArt);
        iCoverArt.setImageResource(drawable);
        LinearLayout background = findViewById(R.id.backgroundCover);
        background.setBackgroundResource(drawable);

    }


    //when song is playing
    public void playSong(String songUrl) {

        try {

            player.reset();
            player.setDataSource(songUrl);
            player.prepare();
            player.start();
            totalTime = player.getDuration();

            ImageButton rotater = findViewById(R.id.rotater);
            rotater.animate()
                    .rotation(-1800)
                    .setDuration(totalTime)
                    .setInterpolator(new LinearInterpolator());

            rotater.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    LinearLayout durationBar = findViewById(R.id.durationBar);

                    if(durationBar.getVisibility() == View.INVISIBLE) {

                        durationBar.setVisibility(View.VISIBLE);
                    } else {

                        durationBar.setVisibility(View.INVISIBLE);
                    }

                }
            });

            Toast.makeText(this, "Playing: " + title +
                    "\nBy: " + artiste, Toast.LENGTH_SHORT).show();

            gracefullyStopsWhenMusicEnds();
            btnPlayPause.setImageResource(R.drawable.btn_pause);

        } catch (IOException e) {

            e.printStackTrace();

        }
    }


    //onClick for play/pause button
    public void PlayOrPauseMusic(View view) {

        if (player.isPlaying()) {

            ImageButton btnPlayPause = findViewById(R.id.btnPlayPause);
            btnPlayPause.animate()
                    .translationY(-5)
                    .setInterpolator(new LinearInterpolator());

            player.pause();
            btnPlayPause.setImageResource(R.drawable.btn_playsong);

            seekbar.setMax(player.getDuration());
            handler.removeCallbacks(p_bar);
            handler.postDelayed(p_bar,1000);

            LinearLayout durationBar = this.findViewById(R.id.durationBar);
            durationBar.setVisibility(View.INVISIBLE);

            onEnterAnimationComplete();

        }

        else {

            ImageButton btnPlayPause = findViewById(R.id.btnPlayPause);
            btnPlayPause.animate()
                    .translationY(0)
                    .setInterpolator(new LinearInterpolator());

            player.start();
            btnPlayPause.setImageResource(R.drawable.btn_pause);

            LinearLayout durationBar = this.findViewById(R.id.durationBar);
            durationBar.setVisibility(View.VISIBLE);

        }

    }


    public void playPrevious(View view) {

        currentIndex = songCollection.getPrevSong(currentIndex);

        displaySongBasedOnIndex();
        playSong(fileLink);

        ImageButton btnAddSong = findViewById(R.id.btnAddSong);
        btnAddSong.setImageResource(R.drawable.add_to_library);

        ImageButton btnLikeSong = findViewById(R.id.btnLikeSong);
        btnLikeSong.setColorFilter(Color.WHITE);

        //refresh buttons
        likedFlag = false;
        addedFlag = true;
    }


    public void playNext(View view) {

        currentIndex = songCollection.getNextSong(currentIndex);

        displaySongBasedOnIndex();
        playSong(fileLink);

        ImageButton btnAddSong = findViewById(R.id.btnAddSong);
        btnAddSong.setImageResource(R.drawable.add_to_library);

        ImageButton btnLikeSong = findViewById(R.id.btnLikeSong);
        btnLikeSong.setColorFilter(Color.WHITE);

        //refresh buttons
        likedFlag = false;
        addedFlag = true;

    }

    ImageButton btnLoopSong;
    TextView txtLoop;
    boolean loopFlag = false;


    public void playLoop(View view) {

        txtLoop = findViewById(R.id.txtLoop);
        btnLoopSong = findViewById(R.id.btnLoopSong);

        if(loopFlag) {

            txtLoop.setTextColor(Color.WHITE);
            btnLoopSong.setColorFilter(Color.argb(255, 255, 255, 255));   //white

        } else {

            txtLoop.setTextColor(Color.BLACK);
            btnLoopSong.setColorFilter(Color.argb(255, 0, 0, 0));   //black

        }

        loopFlag = !loopFlag;
    }


    ImageButton btnShuffleSong;
    TextView txtShuffle;
    boolean shuffleFlag = false;

    public void playShuffle(View view) {

        btnShuffleSong = findViewById(R.id.btnShuffleSong);
        txtShuffle = findViewById(R.id.txtShuffle);

        if(shuffleFlag) {

            txtShuffle.setTextColor(Color.WHITE);
            btnShuffleSong.setColorFilter(Color.argb(255, 255, 255, 255));  //white
            songCollection = new SongCollection();

        } else {

            txtShuffle.setTextColor(Color.BLACK);
            btnShuffleSong.setColorFilter(Color.argb(255, 0, 0, 0));  //black
            Collections.shuffle(shuffleList);
            shuffleList.toArray(songCollection.getSongs());

        }

        shuffleFlag = !shuffleFlag;

    }


    private void gracefullyStopsWhenMusicEnds() {
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mp) {

                if(loopFlag) {

                    player.seekTo(0);
                    PlayOrPauseMusic(null);

                } else {

                try {
                    currentIndex = songCollection.getNextSong(currentIndex);

                    displaySongBasedOnIndex();
                    playSong(fileLink);

                    //refresh buttons
                    ImageButton btnAddSong = findViewById(R.id.btnAddSong);
                    btnAddSong.setImageResource(R.drawable.add_to_library);

                    ImageButton btnLikeSong = findViewById(R.id.btnLikeSong);
                    btnLikeSong.setColorFilter(Color.WHITE);

                    likedFlag = false;
                    addedFlag = true;

                } finally {

                    btnPlayPause.setImageResource(R.drawable.btn_pause);
                } }

            }

        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        handler.removeCallbacks(p_bar);
        player.release();
    }

}
