package sg.edu.tp.mysicmysic;

import static sg.edu.tp.mysicmysic.navigation.OfflineFragment.Config.artiste;
import static sg.edu.tp.mysicmysic.navigation.OfflineFragment.Config.songName;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class OfflinePlayerActivity extends AppCompatActivity {

    ImageButton playBtn, btnLoopSong;
    SeekBar positionBar, volumeBar;
    TextView elapsedTimeLabel, remainingTimeLabel, songNameLabel, artistLabel;
    ImageView iCoverArt;
    MediaPlayer mp;
    int totalTime;
    private int transferIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline_player);

        int savedIndex = getIntent().getIntExtra("transferIndex", 0);
        transferIndex = savedIndex;

        playBtn = findViewById(R.id.btnPlayPause);
        btnLoopSong = findViewById(R.id.btnLoopSong);
        elapsedTimeLabel = findViewById(R.id.elapsedTimeLabel);
        remainingTimeLabel = findViewById(R.id.remainingTimeLabel);
        songNameLabel = findViewById((R.id.txtSongTitle));
        artistLabel = findViewById((R.id.txtArtist));
        iCoverArt = findViewById(R.id.imgCoverArt);

        songNameLabel.setText(songName[savedIndex]);
        
        artistLabel.setText(artiste[savedIndex]);

        if (savedIndex == 0) {iCoverArt.setImageDrawable(getResources().getDrawable(R.drawable.glimpse_of_us));}
        if (savedIndex == 1) {iCoverArt.setImageDrawable(getResources().getDrawable(R.drawable.jay_chou));}
        if (savedIndex == 2) {iCoverArt.setImageDrawable(getResources().getDrawable(R.drawable.mundian_to_bach_ke));}
        if (savedIndex == 3) {iCoverArt.setImageDrawable(getResources().getDrawable(R.drawable.red_scarf));}
        if (savedIndex == 4) {iCoverArt.setImageDrawable(getResources().getDrawable(R.drawable.ren_ran));}
        if (savedIndex == 5) {iCoverArt.setImageDrawable(getResources().getDrawable(R.drawable.those_bygone_years));}

        if (savedIndex == 0) {mp = MediaPlayer.create(this, R.raw.glimpse_of_us);}
        if (savedIndex == 1) {mp = MediaPlayer.create(this, R.raw.jay_chou);}
        if (savedIndex == 2) {mp = MediaPlayer.create(this, R.raw.mundian_to_bach_ke);}
        if (savedIndex == 3) {mp = MediaPlayer.create(this, R.raw.red_scarf);}
        if (savedIndex == 4) {mp = MediaPlayer.create(this, R.raw.ren_ran);}
        if (savedIndex == 5) {mp = MediaPlayer.create(this, R.raw.those_bygone_years);}

        mp.start();
        mp.seekTo(0);
        mp.setVolume(0.5f, 0.5f);
        totalTime = mp.getDuration();

        playBtn.setBackgroundResource(R.drawable.btn_pause);
        mp.setLooping(false);

        positionBar = findViewById(R.id.positionBar);
        positionBar.setMax(totalTime);
        positionBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                        if (fromUser) {
                            mp.seekTo(progress);
                        }
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) { }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) { }

                });


        volumeBar = findViewById(R.id.volumeBar);
        volumeBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        float volumeNum = progress / 100f;
                        mp.setVolume(volumeNum, volumeNum);
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {}

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {}
                }
        );


        new Thread(new Runnable() {

            @Override
            public void run() {

                while (mp != null) {
                    try {

                        Message msg = new Message();
                        msg.what = mp.getCurrentPosition();
                        handler.sendMessage(msg);
                        Thread.sleep(1000);

                    } catch (InterruptedException e) {}
                }
            }
        }).start();


        //backspace
        ImageButton btnBack;

        btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                mp.stop();
                mp.reset();
                finish();
            }
        });

        //end of OnCreate
    }


    private Handler handler = new Handler(new Handler.Callback() {

        @Override
        public boolean handleMessage(Message msg) {
            int currentPosition = msg.what;

            positionBar.setProgress(currentPosition);

            String elapsedTime = createTimeLabel(currentPosition);
            elapsedTimeLabel.setText(elapsedTime);

            String remainingTime = createTimeLabel(totalTime - currentPosition);
            remainingTimeLabel.setText(remainingTime);

            return true;
        }
    });


    public String createTimeLabel(int time) {
        String timeLabel;
        int min = time / 1000 / 60;
        int sec = time / 1000 % 60;
        timeLabel = min + ":" + sec;
        return timeLabel;
    }


    //play/pause
    public void playBtnClick(View view) {

        LinearLayout durationBar;
       durationBar = findViewById(R.id.durationBar);

        if (!mp.isPlaying()) {

            mp.start();
            playBtn.setBackgroundResource(R.drawable.btn_pause);
            durationBar.setVisibility(View.VISIBLE);

        } else {

            mp.pause();
            playBtn.setBackgroundResource(R.drawable.btn_playsong);
            durationBar.setVisibility(View.INVISIBLE);

        }
    }

    TextView txtLoop;
    boolean loopFlag = false;

    //Loop Song
    public void playLoop(View view) {

        txtLoop = findViewById(R.id.txtLoop);

        if (loopFlag) {

            txtLoop.setTextColor(Color.WHITE);
            btnLoopSong.setColorFilter(Color.argb(255, 255, 255, 255));  //white
            mp.setLooping(false);

            } else {

            txtLoop.setTextColor(Color.BLACK);
            btnLoopSong.setColorFilter(Color.argb(255, 0, 0, 0));  //black
            mp.setLooping(true);

            }

            loopFlag = !loopFlag;

        }

        TextView txtVolume;
        boolean volumeFlag = false;

        //adjust volume manually
    public void showVolume(View view) {

        txtVolume = findViewById(R.id.txtVolume);

        ImageButton btnVolume;
        btnVolume = findViewById(R.id.btnVolume);

        LinearLayout volumePopup;
        volumePopup = findViewById(R.id.volumePopup);

        if (volumeFlag) {

            txtVolume.setTextColor(Color.WHITE);
            btnVolume.setColorFilter(Color.argb(255, 255, 255, 255));  //white
            volumePopup.setVisibility(View.INVISIBLE);

        } else {

            txtVolume.setTextColor(Color.BLACK);
            btnVolume.setColorFilter(Color.argb(255, 0, 0, 0));  //black
            volumePopup.setVisibility(View.VISIBLE);

        }
        volumeFlag = !volumeFlag;
    }

}
