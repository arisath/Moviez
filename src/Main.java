import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Main
{
    public static void main(String[] args) throws Exception
    {
        System.out.println(Tools.getMovieJson("The Matrix"));
        System.out.println(Tools.getMovieJson("Fightclub"));
        System.out.println(Tools.getMovieJson("Desperado"));
        System.out.println(Tools.getMovieJson("Django Unchained"));
        System.out.println(Tools.getMovieJson("Transformers"));
        System.out.println(Tools.getMovieJson("The Prestige"));
    }

}