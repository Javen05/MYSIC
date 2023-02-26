package sg.edu.tp.mysicmysic.navigation;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;

import sg.edu.tp.mysicmysic.OfflinePlayerActivity;
import sg.edu.tp.mysicmysic.R;

public class OfflineFragment extends Fragment  {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_offline, container, false);

        //initialize showPopup onclick
        ImageButton btnSelectSong = (ImageButton) view.findViewById(R.id.song0);
        ImageButton btnSelectSong1 = (ImageButton) view.findViewById(R.id.song1);
        ImageButton btnSelectSong2 = (ImageButton) view.findViewById(R.id.song2);
        ImageButton btnSelectSong3 = (ImageButton) view.findViewById(R.id.song3);
        ImageButton btnSelectSong4 = (ImageButton) view.findViewById(R.id.song4);
        ImageButton btnSelectSong5 = (ImageButton) view.findViewById(R.id.song5);

        btnSelectSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SelectSong(view);
            }

        });

        btnSelectSong1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SelectSong(view);
            }

        });

        btnSelectSong2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SelectSong(view);
            }

        });

        btnSelectSong3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SelectSong(view);
            }

        });

        btnSelectSong4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { SelectSong(view); }

        });

        btnSelectSong5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SelectSong(view);
            }

        });

        return view;
    }

    public static class Config {
        public static final String[] songName = {"Glimpse Of Us", "紅顏如霜", "Mundian To Bach Ke","Red Scarf", "飞鸟和蝉",
                "Those Bygone Years"};
        public static final String[] artiste = {"Joji", "Jay Chou", "Panjabi MC", "Weibird", "RenRan", "Hu Xia"};
    }


    public void SelectSong (View view) {

        // Capture Id of the selected song
        String idString = view.getResources().getResourceEntryName(view.getId());

        // Assign the selected song number to variable index
        int index = 0;
        if (idString.equals("song0")) {index = 0;}
        if (idString.equals("song1")) {index = 1;}
        if (idString.equals("song2")) {index = 2;}
        if (idString.equals("song3")) {index = 3;}
        if (idString.equals("song4")) {index = 4;}
        if (idString.equals("song5")) {index = 5;}

        Intent intent = new Intent(getActivity(), OfflinePlayerActivity.class);
        intent.putExtra("transferIndex", index);
        startActivity(intent);
    }

}