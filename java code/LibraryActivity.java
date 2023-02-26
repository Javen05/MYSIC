package sg.edu.tp.mysicmysic;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import sg.edu.tp.mysicmysic.SongDatabase.SongCollection;

public class LibraryActivity extends AppCompatActivity {
    SongCollection songCollection = new SongCollection();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);

        //Song Count
        TextView songCount = findViewById(R.id.songCount);
        songCount.setText(new SongCollection().getArrayLength() + " " + "Songs in List");

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


    public void sendDataToActivity(int index) {
        Intent intent = new Intent(this, PlayerActivity.class);
        intent.putExtra("index", index);
        startActivity(intent);
    }


    public void handleSelection(View myView) {

        ConnectivityManager conMgr = (ConnectivityManager) getSystemService (Context.CONNECTIVITY_SERVICE);

        if(conMgr.getActiveNetworkInfo() != null
                && conMgr.getActiveNetworkInfo().isAvailable()
                && conMgr.getActiveNetworkInfo().isConnected()) {

        String resourceId = getResources().getResourceEntryName(myView.getId());
        int currentArrayIndex = songCollection.searchSongById(resourceId);
        sendDataToActivity(currentArrayIndex);

        } else {
            Toast.makeText(this, "No Internet", Toast.LENGTH_SHORT).show();
        }
    }
}
