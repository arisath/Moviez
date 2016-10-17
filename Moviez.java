import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Main
{
    public static void main(String [ ] args)   throws Exception
    {



                URL yahoo = new URL("http://www.omdbapi.com/?t=Lock%2C+Stock+and+Two+Smoking+Barrels&y=&plot=short&r=json");
                URLConnection yc = yahoo.openConnection();
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(
                                yc.getInputStream()));
                String inputLine;

                while ((inputLine = in.readLine()) != null)
                    System.out.println(inputLine);
                in.close();


    }
}
