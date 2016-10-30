import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
        if (string.equalsIgnoreCase("N/A"))
        {
            return ("0");
        }

        return string;
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
        if (moviesList.size() == 0)
        {
            System.out.println("Given List is Empty");

            return null;
        }
        else if (moviesList.size() == 1)
        {
            return moviesList.get(1);
        }

        Movie highestRatedMovie = moviesList.get(0);

        for (int i = 1; i < moviesList.size(); i++)
        {
            if (moviesList.get(i).getImdbRating() > highestRatedMovie.getImdbRating())
            {
                highestRatedMovie = moviesList.get(i);
            }
        }

        return highestRatedMovie;
    }

    static Movie findLowestRatedMovie(List<Movie> moviesList)
    {
        if (moviesList.size() == 0)
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
        if (moviesList.size() == 0)
        {
            System.out.println("Given List is Empty");

            return null;
        }
        else if (moviesList.size() == 1)
        {
            return moviesList.get(1);
        }

        Movie highestMetascoreMovie = moviesList.get(0);

        for (int i = 1; i < moviesList.size(); i++)
        {
            if (moviesList.get(i).getMetascore() > highestMetascoreMovie.getMetascore())
            {
                highestMetascoreMovie = moviesList.get(i);
            }
        }

        return highestMetascoreMovie;
    }

    static Movie findLowestMetascore(List<Movie> moviesList)
    {
        if (moviesList.size() == 0)
        {
            System.out.println("Given List is Empty");

            return null;
        }
        else if (moviesList.size() == 1)
        {
            return moviesList.get(1);
        }

        Movie lowestMetascoreMovie = moviesList.get(0);

        for (int i = 1; i < moviesList.size(); i++)
        {
            if (moviesList.get(i).getMetascore() < lowestMetascoreMovie.getMetascore())
            {
                lowestMetascoreMovie = moviesList.get(i);
            }
        }

        return lowestMetascoreMovie;
    }

    static Movie findOldestMovie(List<Movie> moviesList)
    {
        if (moviesList.size() == 0)
        {
            System.out.println("Given List is Empty");

            return null;
        }
        else if (moviesList.size() == 1)
        {
            return moviesList.get(1);
        }

        Movie oldestMovie = moviesList.get(0);

        for (int i = 1; i < moviesList.size(); i++)
        {
            if (moviesList.get(i).getYear() < oldestMovie.getYear())
            {
                oldestMovie = moviesList.get(i);
            }
        }

        return oldestMovie;
    }

    static Movie findMostRecentMovie(List<Movie> moviesList)
    {
        if (moviesList.size() == 0)
        {
            System.out.println("Given List is Empty");

            return null;
        }
        else if (moviesList.size() == 1)
        {
            return moviesList.get(1);
        }

        Movie mostRecentMovie = moviesList.get(0);

        for (int i = 1; i < moviesList.size(); i++)
        {
            if (moviesList.get(i).getYear() > mostRecentMovie.getYear())
            {
                mostRecentMovie = moviesList.get(i);
            }
        }

        return mostRecentMovie;
    }

    static Movie findLongestMovie(List<Movie> moviesList)
    {
        if (moviesList.size() == 0)
        {
            System.out.println("Given List is Empty");

            return null;
        }
        else if (moviesList.size() == 1)
        {
            return moviesList.get(1);
        }

        Movie longestMovie = moviesList.get(0);

        for (int i = 1; i < moviesList.size(); i++)
        {
            if (moviesList.get(i).getRuntime() > longestMovie.getRuntime())
            {
                longestMovie = moviesList.get(i);
            }
        }

        return longestMovie;
    }

    static Movie findShortestMovie(List<Movie> moviesList)
    {
        if (moviesList.size() == 0)
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
            if ( (moviesList.get(i).getRuntime() == 0) || (moviesList.get(i).getRuntime()<35) )
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

}
