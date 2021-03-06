import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Tools
{
    static final String omdbapi = "http://www.omdbapi.com/?t=";

    static List<Movie> readMovieList(String filename)
    {
        try
        {
            ArrayList<Movie> moviesList = new ArrayList<Movie>();

            FileReader input = new FileReader(filename);

            BufferedReader br = new BufferedReader(input);

            String line;

            while ((line = br.readLine()) != null)
            {
                Movie movie = Tools.createMovieEntry(line.trim());

                moviesList.add(movie);
            }

            br.close();

            return moviesList;
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File " + filename + " not found");
        }
        catch (IOException e)
        {
            System.out.println("IO Exception");
        }

        return null;
    }

    static Movie createMovieEntry(String movieTitle)
    {
        try
        {
            URL url = new URL(omdbapi + encodeURL(movieTitle) + "&y=&plot=short&r=json");

            URLConnection urlConnection = url.openConnection();

            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String inputLine = in.readLine();

            in.close();

            JsonReader jsonReader = Json.createReader(new StringReader(inputLine));

            JsonObject jsonObject = jsonReader.readObject();

            Movie movie = new Movie(jsonObject);

            return movie;
        }
        catch (MalformedURLException malforedUrlException)
        {
            System.out.println("The provided URL is malformed");
        }
        catch (IOException malforedUrlException)
        {
            System.out.println("The provided URL is not valid");
        }

        return null;
    }

    static String encodeURL(String s) throws UnsupportedEncodingException
    {
        String encodedUrl = URLEncoder.encode(s, "UTF-8");

        return encodedUrl;
    }

    static String removeQuotes(String jsonValue)
    {
        jsonValue = jsonValue.replace("\"", "");

        return jsonValue;
    }

    static String removeComma(String jsonValue)
    {
        jsonValue = jsonValue.replace(",", "");

        return jsonValue;
    }

    static String removeDot(String jsonValue)
    {
        jsonValue = jsonValue.replace(".", "");

        return jsonValue;
    }

    static String processRuntime(String runtime)
    {
        if (runtime.equalsIgnoreCase("N/A"))
        {
            return ("0");
        }

        runtime = runtime.substring(0, 3).trim();

        return runtime;
    }

    static String processYear(String year)
    {
        if (year.equalsIgnoreCase("N/A"))
        {
            return ("0");
        }

        return year.substring(0, 4);
    }

    static String checkNA(String string)
    {
        return string.equalsIgnoreCase("N/A") ? "0" : string;
    }

    static List<String> parseMultivaluedJsonKey(String jsonKey)
    {
        List<String> items = new ArrayList<String>();

        if (jsonKey.equalsIgnoreCase("N/A"))
        {
            items.add("N/A");

            return items;
        }

        jsonKey = removeQuotes(jsonKey);

        items = Arrays.asList(jsonKey.split("\\s*,\\s*"));

        return items;
    }

    static Movie findHighestRatedMovie(List<Movie> moviesList)
    {
        if (moviesList == null || moviesList.isEmpty())
        {
            System.out.println("Given List is Empty");

            return null;
        }

        Movie highestRatedMovie = Collections.max(moviesList, Comparator.comparingInt(movie -> movie.getImdbRating()));

        return highestRatedMovie;

    }

    static Movie findLowestRatedMovie(List<Movie> moviesList)
    {
        if (moviesList == null || moviesList.isEmpty())
        {
            System.out.println("Given List is Empty");

            return null;
        }
        else if (moviesList.size() == 1)
        {
            return moviesList.get(1);
        }

        Movie lowestRatedMovie = moviesList.get(0);

        for (int i = 1; i < moviesList.size(); i++)
        {
            if (moviesList.get(i).getImdbRating() < lowestRatedMovie.getImdbRating())
            {
                lowestRatedMovie = moviesList.get(i);
            }
        }

        return lowestRatedMovie;
    }

    static Movie findHighestMetascore(List<Movie> moviesList)
    {
        if (moviesList.size() == 0 || moviesList.isEmpty())
        {
            System.out.println("Given List is Empty");

            return null;
        }

        Movie highestMetascoreMovie = Collections.max(moviesList, Comparator.comparingInt(movie -> movie.getMetascore()));

        return highestMetascoreMovie;
    }

    static Movie findLowestMetascore(List<Movie> moviesList)
    {
        if (moviesList.size() == 0 || moviesList.isEmpty())
        {
            System.out.println("Given List is Empty");

            return null;
        }

        Movie lowestMetascoreMovie = Collections.min(moviesList, Comparator.comparingInt(movie -> movie.getMetascore()));

        return lowestMetascoreMovie;
    }

    static Movie findOldestMovie(List<Movie> moviesList)
    {
        if (moviesList.size() == 0 || moviesList.isEmpty())
        {
            System.out.println("Given List is Empty");

            return null;
        }

        Movie oldestMovie = Collections.min(moviesList, Comparator.comparingInt(movie -> movie.getYear()));

        return oldestMovie;
    }

    static Movie findMostRecentMovie(List<Movie> moviesList)
    {
        if (moviesList.size() == 0 || moviesList.isEmpty())
        {
            System.out.println("Given List is Empty");

            return null;
        }

        Movie mostRecentMovie = Collections.max(moviesList, Comparator.comparingInt(movie -> movie.getYear()));

        return mostRecentMovie;
    }

    static Movie findLongestMovie(List<Movie> moviesList)
    {
        if (moviesList.size() == 0 || moviesList.isEmpty())
        {
            System.out.println("Given List is Empty");

            return null;
        }

        Movie longestMovie = Collections.max(moviesList, Comparator.comparingInt(movie -> movie.getRuntime()));

        return longestMovie;
    }

    static Movie findShortestMovie(List<Movie> moviesList)
    {
        if (moviesList.size() == 0 || moviesList.isEmpty())
        {
            System.out.println("Given List is Empty");

            return null;
        }
        else if (moviesList.size() == 1)
        {
            return moviesList.get(1);
        }

        Movie shortestMovie = moviesList.get(0);

        for (int i = 1; i < moviesList.size(); i++)
        {
            if ((moviesList.get(i).getRuntime() == 0) || (moviesList.get(i).getRuntime() < 35))
            {
                continue;
            }

            if (moviesList.get(i).getRuntime() < shortestMovie.getRuntime())
            {
                shortestMovie = moviesList.get(i);
            }
        }

        return shortestMovie;
    }

    static void downloadMoviePoster(Movie movie)
    {
        try
        {
            Files.createDirectories(Paths.get("posters"));

            String posterURLString = movie.getPosterURL();

            if (posterURLString.equalsIgnoreCase("0"))
            {
                throw new MalformedURLException();
            }

            URL posterURL = new URL(movie.getPosterURL());

            InputStream in = posterURL.openStream();

            Files.copy(in, Paths.get("posters/" + movie.getTitle().replaceAll("[^a-zA-Z0-9.-]", " ") + ".jpg"));

        }
        catch (MalformedURLException malformedURLException)
        {
            System.out.println("The URL of the " + movie.getTitle() + " does not exist or is malforned");
        }
        catch (IOException ioException)
        {
            System.out.println("Poster for " + movie.getTitle() + " already exists");
        }
    }

    static void downloadMoviesPosters(List<Movie> movieList)
    {
        try
        {
            Files.createDirectories(Paths.get("posters"));

            for (Movie movie : movieList)
            {
                downloadMoviePoster(movie);
            }
        }
        catch (IOException ioException)
        {
            System.out.println("Could not create directory");
        }

    }

    static TreeMap<String, Integer> findActorsWithMostAppearances(List<Movie> movieList)
    {
        TreeMap<String, Integer> actorsHashMap = new TreeMap<>();

        for (Movie movie : movieList)
        {
            addActorsToHashMap(movie, actorsHashMap);
        }

        return SortByValue(actorsHashMap);
    }

    static void addActorsToHashMap(Movie movie, TreeMap<String, Integer> actorsHashMap)
    {
        List<String> mainActors = movie.getMainActors();

        for (String actor : mainActors)
        {
            if (!actorsHashMap.containsKey(actor))
            {
                actorsHashMap.put(actor, 1);
            }
            else
            {
                int value = actorsHashMap.get(actor);

                actorsHashMap.put(actor, value + 1);
            }
        }
    }

    static TreeMap<String, Integer> findDirectorsWithMostAppearances(List<Movie> movieList)
    {
        TreeMap<String, Integer> directorsHashMap = new TreeMap<>();

        for (Movie movie : movieList)
        {
            addDirectorsToHashMap(movie, directorsHashMap);
        }

        return SortByValue(directorsHashMap);
    }

    static void addDirectorsToHashMap(Movie movie, TreeMap<String, Integer>directorsHashMap)
    {
        List<String> directors = movie.getDirectors();

        directors.removeAll(Collections.singleton("N/A"));

        for (String director : directors)
        {
            if (!directorsHashMap.containsKey(director))
            {
                directorsHashMap.put(director, 1);
            }
            else
            {
                int value = directorsHashMap.get(director);

                directorsHashMap.put(director, value + 1);
            }
        }
    }

    static TreeMap<String, Integer> findGenresWithMostAppearances(List<Movie> movieList)
    {
        TreeMap<String, Integer> genresHashMap = new TreeMap<>();

        for (Movie movie : movieList)
        {
            addGenresToHashMap(movie, genresHashMap);
        }

        return SortByValue(genresHashMap);
    }

    static void addGenresToHashMap(Movie movie, TreeMap<String, Integer>genresHashMap)
    {
        List<String> genres = movie.getGenre();

        genres.removeAll(Collections.singleton("N/A"));

        for (String genre : genres)
        {
            if (!genresHashMap.containsKey(genre))
            {
                genresHashMap.put(genre, 1);
            }
            else
            {
                int value = genresHashMap.get(genre);

                genresHashMap.put(genre, value + 1);
            }
        }
    }

    static TreeMap<String, Integer> findCountriesWithMostAppearances(List<Movie> movieList)
    {
        TreeMap<String, Integer> countriesHashMap = new TreeMap<>();

        for (Movie movie : movieList)
        {
            addCountriesToHashMap(movie, countriesHashMap);
        }

        return SortByValue(countriesHashMap);
    }

    static void addCountriesToHashMap(Movie movie, TreeMap<String, Integer>countriesHashMap)
    {
        List<String> countries = movie.getCountries();

        countries.removeAll(Collections.singleton("N/A"));

        for (String country : countries)
        {
            if (!countriesHashMap.containsKey(country))
            {
                countriesHashMap.put(country, 1);
            }
            else
            {
                int value = countriesHashMap.get(country);

                countriesHashMap.put(country, value + 1);
            }
        }
    }

    static TreeMap<String, Integer> findWritersWithMostAppearances(List<Movie> movieList)
    {
        TreeMap<String, Integer> writersHashMap = new TreeMap<>();

        for (Movie movie : movieList)
        {
            addWritersToHashMap(movie, writersHashMap);
        }

        return SortByValue(writersHashMap);
    }

    static void addWritersToHashMap(Movie movie, TreeMap<String, Integer>writersHashMap)
    {
        List<String> writers = movie.getWriters();

        writers.removeAll(Collections.singleton("N/A"));

        for (String writer : writers)
        {
            if (!writersHashMap.containsKey(writer))
            {
                writersHashMap.put(writer, 1);
            }
            else
            {
                int value = writersHashMap.get(writer);

                writersHashMap.put(writer, value + 1);
            }
        }
    }

    static TreeMap<String, Integer> findLanguagesWithMostAppearances(List<Movie> movieList)
    {
        TreeMap<String, Integer> languagesHashMap = new TreeMap<>();

        for (Movie movie : movieList)
        {
            addLanguagesToHashMap(movie, languagesHashMap);
        }

        return SortByValue(languagesHashMap);
    }

    static void addLanguagesToHashMap(Movie movie, TreeMap<String, Integer>writersHashMap)
    {
        List<String> languages = movie.getLanguages();

        languages.removeAll(Collections.singleton("N/A"));

        for (String language : languages)
        {
            if (!writersHashMap.containsKey(language))
            {
                writersHashMap.put(language, 1);
            }
            else
            {
                int value = writersHashMap.get(language);

                writersHashMap.put(language, value + 1);
            }
        }
    }

    public static TreeMap<String, Integer> SortByValue(TreeMap<String, Integer> map)
    {
        TreeMap<String, Integer> sortedMap = new TreeMap<String, Integer>(new ValueComparator(map));

        sortedMap.putAll(map);

        return sortedMap;
    }

}

    class ValueComparator implements Comparator<String>
    {
        Map<String, Integer> map;

        public ValueComparator(Map<String, Integer> base)
        {
            this.map = base;
        }

        public int compare(String a, String b)
        {

            if (map.get(a) >= map.get(b))
            {
                return -1;
            }
            else
            {
                return 1;
            }
        }
    }


