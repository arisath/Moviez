import javax.json.*;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.StringReader;



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
        System.out.println(Tools.getMovieJson("Game of Thrones"));


        JsonReader jsonReader = Json.createReader(new StringReader(Tools.getMovieJson("Desperado")));
        JsonObject jsonObject = jsonReader.readObject();

        Movie desperado =  new  Movie(jsonObject);
        String title = jsonObject.getString("Metascore");

       System.out.println(title);

    }
}