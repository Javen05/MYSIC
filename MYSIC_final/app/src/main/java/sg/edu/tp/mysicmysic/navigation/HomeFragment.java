package sg.edu.tp.mysicmysic.navigation;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import sg.edu.tp.mysicmysic.CreatedPlaylistActivity;
import sg.edu.tp.mysicmysic.LibraryActivity;
import sg.edu.tp.mysicmysic.LikedListActivity;
import sg.edu.tp.mysicmysic.PlaylistActivity;
import sg.edu.tp.mysicmysic.R;
import sg.edu.tp.mysicmysic.SongDatabase.SongCollection;

public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        //initialize, to take drawable from SongCollection
        SongCollection songCollection = new SongCollection();


        //Radio Player unable to be made: no viable way to obtain radio stream link or API
        ImageButton btnToRadioPlayer = (ImageButton) view.findViewById(R.id.btnToRadioPlayer);
        btnToRadioPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                btnToRadioPlayer.setImageResource(R.drawable.unavailable);

            }
        });


        //button to Library
        ImageButton btnToLibrary = (ImageButton) view.findViewById(R.id.btnToLibrary);
        btnToLibrary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LibraryActivity.class);
                startActivity(intent);
            }
        });


        //button to Playlist
        ImageButton btnToPlaylist = (ImageButton) view.findViewById(R.id.btnToPlaylist);

        LinearLayout playlist = view.findViewById(R.id.editablePlaylist);
        ArrayList playlistsSong = songCollection.getPlaylistsSong();


        if(!playlistsSong.isEmpty()) {

            playlist.setVisibility(View.VISIBLE);

        }

        btnToPlaylist.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CreatedPlaylistActivity.class);

                startActivity(intent);
            }
        });

        //button to default playlist1
        ImageButton btnToPlaylist2 = (ImageButton) view.findViewById(R.id.btnToPlaylist2);

        //algorithm to randomize playlist cover
        int randomNumberGenerator0 = (int)(Math.random() * 10);
        int index0;

        if(randomNumberGenerator0 > 6) {
            //if randomNumber is out of bound
            index0 = randomNumberGenerator0 - 3;  //randomNumber could be 7 - 9

        } else {
            //randomNumber corresponds with array
            index0 = randomNumberGenerator0;  //hence the value is taken
        }

        btnToPlaylist2.setImageResource(songCollection.getCurrentSong(index0).getDrawable());

        btnToPlaylist2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PlaylistActivity.class);

                startActivity(intent);
            }
        });


        //button to default playlist2
        ImageButton btnToPlaylist3 = (ImageButton) view.findViewById(R.id.btnToPlaylist3);

        //algorithm to randomize playlist cover
        int randomNumberGenerator = (int)(Math.random() * 10);
        int index;

        if(randomNumberGenerator > 6) {
            //if randomNumber is out of bound
            index = randomNumberGenerator - 6;  //randomNumber could be 7 - 9

        } else {
            //randomNumber corresponds with array
            index = randomNumberGenerator;  //hence the value is taken
        }

        btnToPlaylist3.setImageResource(songCollection.getCurrentSong(index).getDrawable());

        btnToPlaylist3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), PlaylistActivity.class);

                startActivity(intent);
            }
        });

        //button to Liked List
        ImageButton btnToLikedList = (ImageButton) view.findViewById(R.id.btnToLikedList);

        btnToLikedList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), LikedListActivity.class);

                startActivity(intent);
            }
        });

        //goes to the clicked button
        return view;
    }
}