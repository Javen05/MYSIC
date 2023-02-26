package sg.edu.tp.mysicmysic.navigation;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import sg.edu.tp.mysicmysic.PlayerActivity;
import sg.edu.tp.mysicmysic.R;
import sg.edu.tp.mysicmysic.RecyclerViewInterface;
import sg.edu.tp.mysicmysic.SearchAdapter;
import sg.edu.tp.mysicmysic.SongDatabase.Song;
import sg.edu.tp.mysicmysic.SongDatabase.SongCollection;

public class SearchFragment extends Fragment implements RecyclerViewInterface {

    SearchAdapter searchAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);


        SongCollection songCollection = new SongCollection();
        ArrayList<Song> songs         = songCollection.getPlaylistsSong();


        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        searchAdapter             = new SearchAdapter(view.getContext(), songs,this);

        recyclerView.setAdapter(searchAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        SearchView searchSong = view.findViewById(R.id.etSearch);

        //while searching
        searchSong.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String search) { return false; }

            @Override
            public boolean onQueryTextChange(String search) {
                searchAdapter.getFilter().filter(search);
                return false;
            }

        });

        //song count
        TextView songCount = view.findViewById(R.id.songCount);
        songCount.setText(recyclerView.getAdapter().getItemCount() + " " + "results");

        return view;

    }


    @Override
    public void onItemClick(int index) {

        Intent intent = new Intent(getContext(), PlayerActivity.class);
        intent.putExtra("index", index);
        startActivity(intent);
    }
}