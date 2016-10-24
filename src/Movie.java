import javax.json.JsonObject;
import java.util.ArrayList;

public class Movie
{
    private String releaseDate;

    private int runtime;

    private ArrayList<String> genre;

    private ArrayList<String> director;

    private ArrayList<String> writer;

    private ArrayList<String> mainActors;

    private ArrayList<String> languages;

    private ArrayList<String> countries;

    private ArrayList<String> oscars;

    private int metascore;

    private int imdbRating;

    private int imdbVotes;

    private String type;

    private String title;

    private int year;

    private String rated;

    protected String getTitle()
    {
        return title;
    }

    protected void setTitle(String title)
    {
        this.title = title;
    }

    protected int getYear()
    {
        return year;
    }

    protected void setYear(int year)
    {
        this.year = year;
    }

    protected String getRated()
    {
        return rated;
    }

    protected void setRated(String rated)
    {
        this.rated = rated;
    }

    protected String getReleaseDate()
    {
        return releaseDate;
    }

    protected void setReleaseDate(String releaseDate)
    {
        this.releaseDate = releaseDate;
    }

    protected int getRuntime()
    {
        return runtime;
    }

    protected void setRuntime(int runtime)
    {
        this.runtime = runtime;
    }

    protected int getMetascore()
    {
        return metascore;
    }

    protected void setMetascore(int metascore)
    {
        this.metascore = metascore;
    }

    protected int getImdbRating()
    {
        return imdbRating;
    }

    protected void setImdbRating(int imdbRating)
    {
        this.imdbRating = imdbRating;
    }

    protected int getImdbVotes()
    {
        return imdbVotes;
    }

    protected void setImdbVotes(int imdbVotes)
    {
        this.imdbVotes = imdbVotes;
    }

    protected String getType()
    {
        return type;
    }

    protected void setType(String type)
    {
        this.type = type;
    }

    Movie(JsonObject jsonObject)
    {
        this.metascore = Integer.getInteger(jsonObject.get("metascore").toString());

        this.imdbRating = Integer.getInteger(jsonObject.get("imdbRating").toString());

        this.imdbVotes = Integer.getInteger(jsonObject.get("imdbVotes").toString());

        this.type = jsonObject.get("type").toString();

        this.title = jsonObject.get("title").toString();

        this.year = Integer.getInteger(jsonObject.get("year").toString());;

        this.rated = jsonObject.get("rated").toString();
    }




   /* private ArrayList<String> genre;

    private ArrayList<String> director;

    private ArrayList<String> writer;

    private ArrayList<String> mainActors;

    private ArrayList<String> languages;

    private ArrayList<String> countries;

    private ArrayList<String> oscars;*/






}
