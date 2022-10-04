package sg.edu.tp.mysicmysic.SongDatabase;

public class Song {

    private String id;
    private String title;
    private String artiste;
    private String fileLink;
    private int drawable;

    public Song (String id, String title, String artiste, String fileLink, int drawable)
    {
        this.id = id;
        this.title = title;
        this.artiste = artiste;
        this.fileLink = fileLink;
        this.drawable = drawable;

    }

    public String getId()      { return this.id; }

    public String getTitle()   { return this.title; }

    public String getArtiste()
    {
        return this.artiste;
    }

    public String getFileLink()
    {
        return this.fileLink;
    }

    public int getDrawable()    { return this.drawable; }

}
