import javax.json.JsonObject;
import java.util.ArrayList;
import java.util.List;

public class Movie
{
    private String releaseDate;

    private int runtime;

    private List<String> genre;

    private List<String> director;

    private List<String> writers;

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

    public String getPosterURL()
    {
        return posterURL;
    }

    public void setPosterURL(String posterURL)
    {
        this.posterURL = posterURL;
    }

    private String posterURL;

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

    protected List<String> getMainActors(){return this.mainActors;}

    protected List<String> getDirectors(){return this.director;}

    protected List<String> getGenre(){return this.genre;}

    protected List<String> getCountries(){return this.countries;}

    protected List<String> getWriters(){return this.writers;}

    protected List<String> getLanguages(){return this.languages;}


    protected Movie()
    {
        this.title = "";

        this.year = 0;

        this.rated = "";

        this.releaseDate = "";

        this.runtime = 0;

        this.metascore = 0;

        this.imdbRating = 0;

        this.imdbVotes = 0;

        this.type = "";

        this.rated = "";

        this.genre = new ArrayList<String>();

        this.director = new ArrayList<String>();

        this.writers = new ArrayList<String>();

        this.mainActors = new ArrayList<String>();

        this.languages = new ArrayList<String>();

        this.countries = new ArrayList<String>();
    }

    protected Movie(JsonObject jsonObject)
    {
        this.title = jsonObject.getString("Title");

        this.year = Integer.valueOf(Tools.processYear(jsonObject.getString("Year")));

        this.rated = Tools.checkNA(jsonObject.getString("Rated"));

        this.releaseDate = Tools.checkNA(jsonObject.getString("Released"));

        this.runtime = Integer.valueOf(Tools.processRuntime(jsonObject.getString("Runtime")));

        this.metascore = Integer.valueOf(Tools.checkNA(jsonObject.getString("Metascore")));

        this.imdbRating = Integer.valueOf(Tools.removeDot(Tools.checkNA(jsonObject.getString("imdbRating"))));

        this.imdbVotes = Integer.valueOf(Tools.removeComma(Tools.checkNA(jsonObject.getString("imdbVotes"))));

        this.type = Tools.checkNA(jsonObject.getString("Type"));

        this.rated = Tools.checkNA(jsonObject.getString("Rated"));

        this.genre = Tools.parseMultivaluedJsonKey(jsonObject.getString("Genre"));

        this.director = Tools.parseMultivaluedJsonKey(jsonObject.getString("Director"));

        this.writers = Tools.parseMultivaluedJsonKey(jsonObject.getString("Writer"));

        this.mainActors = Tools.parseMultivaluedJsonKey(jsonObject.getString("Actors"));

        this.languages = Tools.parseMultivaluedJsonKey(jsonObject.getString("Language"));

        this.countries = Tools.parseMultivaluedJsonKey(jsonObject.getString("Country").toString());

        this.posterURL = Tools.checkNA(jsonObject.getString("Poster"));

    }


}
