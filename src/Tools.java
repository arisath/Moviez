import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
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

    static Movie createMovieEntry(String movieTitle)
    {
        try
        {
            URL url = new URL(omdbapi + encodeURL(movieTitle) + "&y=&plot=short&r=json");

            URLConnection urlConnection = url.openConnection();

            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String inputLine = in.readLine();
            ;

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

        runtime = runtime.substring(0, 2);

        return runtime;
    }

    static String processYear(String year)
    {
        if (year.equalsIgnoreCase("N/A"))
        {
            return ("0");
        }

        return year.substring(0,4);
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


    static String findHighestRatedMoviePeople(List<String> movieRatingsPeople)
    {
        return Collections.max(movieRatingsPeople);
    }

    //  static String findHighestRatedMoviePeople()
    // {

    // }
}
