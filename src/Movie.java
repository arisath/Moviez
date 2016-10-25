import javax.json.JsonObject;
import java.util.List;

public class Movie
{
    private String releaseDate;

    private int runtime;

    private List<String> genre;

    private List<String> director;

    private List<String> writer;

    private List<String> mainActors;

    private List<String> languages;

    private List<String> countries;

    private List<String> oscars;

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

    protected Movie(JsonObject jsonObject)
    {
        this.title = jsonObject.getString("Title").toString();

        this.year = Integer.valueOf(jsonObject.getString("Year").toString());

        this.rated =  jsonObject.getString("Rated").toString();

        this.releaseDate =  jsonObject.getString("Released").toString();

        this.runtime =  Integer.valueOf(Tools.processRuntime(jsonObject.getString("Runtime").toString()));

        this.metascore = Integer.valueOf(jsonObject.getString("Metascore").toString());

        this.imdbRating =Integer.valueOf(Tools.removeDot(jsonObject.getString("imdbRating").toString()));

        this.imdbVotes = Integer.valueOf(Tools.removeComma(jsonObject.getString("imdbVotes").toString()));

        this.type = jsonObject.getString("Type").toString();

        this.rated = jsonObject.getString("Rated").toString();
    }


}
