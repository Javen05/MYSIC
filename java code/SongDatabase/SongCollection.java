package sg.edu.tp.mysicmysic.SongDatabase;

import java.util.ArrayList;

import sg.edu.tp.mysicmysic.R;

public class SongCollection {

    private Song songs[] = new Song[7];

    private ArrayList<Song> likedSongs = new ArrayList<>();
    private ArrayList<Song> playlistsSong = new ArrayList<>();


    //used discord player to optimize link runtime
    public SongCollection() {

        Song invisible = new Song
                (
                        "S1001",
                        "Invisible",
                        "Zeus X Crona",
                        "https://cdn.discordapp.com/attachments/996768406487437314/1005325303956115537/Julius_Dreisig__Zeus_X_Crona_-_Invisible_NCS_Release.mp3",
                        R.drawable.cover_art
                );

        Song symbolism = new Song
                (
                        "S1002",
                        "Symbolism",
                        "Electro-Light",
                        "https://cdn.discordapp.com/attachments/996768406487437314/1005325304211980328/Electro-Light_-_Symbolism_NCS_Release.mp3",
                        R.drawable.mundian_to_bach_ke
                );

        Song royalty = new Song
                (
                        "S1003",
                        "Royalty",
                        "Maestro Chives, Egzod, Neoni",
                        "https://cdn.discordapp.com/attachments/996768406487437314/1005325303670910986/Egzod_-_Rise_Up_ft._Veronica_Bravo__M.I.M.E_NCS_Release.mp3",
                        R.drawable.jay_chou
                );

        Song phoenix = new Song
                (
                        "S1004",
                        "Phoenix",
                        "Halvorsen, Netrum",
                        "https://cdn.discordapp.com/attachments/996768406487437314/1005325304786591824/Netrum__Halvorsen_-_Phoenix_NCS10_Release.mp3",
                        R.drawable.glimpse_of_us
                );

        Song takeItEasy = new Song
                (
                        "S1005",
                        "Take It Easy",
                        "BVRNOUT, Mia Vaile",
                        "https://cdn.discordapp.com/attachments/996768406487437314/1005325303377313822/BVRNOUT_Take_It_Easy_feat_Mia_Vaile_NCS_Release.mp3",
                        R.drawable.red_scarf
                );

        Song faster = new Song
                (
                        "S1006",
                        "Faster",
                        "Razihel",
                        "https://cdn.discordapp.com/attachments/996768406487437314/1005325303108866158/Razihel_-_Faster_NCS_Release.mp3",
                        R.drawable.ren_ran
                );

        Song riseUp = new Song
                (
                        "S1007",
                        "Rise Up",
                        "Egzod, Veronica Bravo, M.I.M.E",
                        "https://cdn.discordapp.com/attachments/996768406487437314/1005325304530743346/Egzod__Maestro_Chives_-_Royalty_ft._Neoni_NCS_Release.mp3",
                        R.drawable.those_bygone_years
                );

        songs[0] = invisible;
        songs[1] = symbolism;
        songs[2] = royalty;
        songs[3] = phoenix;
        songs[4] = takeItEasy;
        songs[5] = faster;
        songs[6] = riseUp;

        for(int i = 0; i < songs.length ; i++) {

            playlistsSong.add(songs[i]);
        }
    }

    public Song getCurrentSong(int currentSongId) {

        return songs[currentSongId];
    }

    public int searchSongById(String id) {

        for (int index = 0; index < songs.length; index++) {
            Song tempSong = songs[index];

            if(tempSong.getId().equals(id))
            {return index;}

        }

        return -1;
    }

    public int getNextSong(int currentSongIndex) {

        if (currentSongIndex >= songs.length-1) {
            return currentSongIndex;
        }

        else {
            return currentSongIndex +1;
        }
    }

    public int getPrevSong(int currentSongIndex){
        if (currentSongIndex <= 0) {
            return currentSongIndex;
        }

        else {
            return currentSongIndex -1;
        }
    }

    public Song[] getSongs() {
        return songs;
    }

    private String title;

    public String getTitle()    { return title; }
    public int getArrayLength() { return songs.length; }

    public ArrayList<Song> getLikedSongs()    { return likedSongs; }
    public ArrayList<Song> getPlaylistsSong() { return playlistsSong; }

}