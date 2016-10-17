import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

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
}
