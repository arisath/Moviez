package src.main.java.application;


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

        this.writer = Tools.parseMultivaluedJsonKey(jsonObject.getString("Writer"));

        this.mainActors = Tools.parseMultivaluedJsonKey(jsonObject.getString("Actors"));

        this.languages = Tools.parseMultivaluedJsonKey(jsonObject.getString("Language"));

        this.countries = Tools.parseMultivaluedJsonKey(jsonObject.getString("Country").toString());




    }


}
