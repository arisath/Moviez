import javax.json.JsonValue;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tools
{
    static final String omdbapi = "http://www.omdbapi.com/?t=";

    static String getMovieJson(String movieTitle)
    {
        try
        {
            URL url = new URL(omdbapi + encodeURL(movieTitle) + "&y=&plot=short&r=json");

            URLConnection urlConnection = url.openConnection();

            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String inputLine;

            inputLine = in.readLine();

            in.close();

            return inputLine;
        }
        catch (MalformedURLException malforedUrlException)
        {
            return ("The provided URL is malformed");
        }
        catch (IOException malforedUrlException)
        {
            return ("The provided URL is not valid");
        }
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
        runtime = runtime.substring(0, 2);

        return runtime;
    }

    static List<String> parseMultivaluedJsonKey(JsonValue jsonKey)
    {
        String jsonKeyString = jsonKey.toString();

        jsonKeyString = removeQuotes(jsonKeyString);

        List<String> items = Arrays.asList(jsonKeyString.split("\\s*,\\s*"));

        return items;
    }


}
