import java.util.List;

public class Main
{
    public static void main(String[] args) throws Exception
    {
        List<Movie> movieList = Tools.readMovieList("MoviesList.txt");


        Movie highestRatedMovieMovie = Tools.findHighestRatedMovie(movieList);
        Movie lowestRatedMovieMovie = Tools.findLowestRatedMovie(movieList);
        Movie highestMetascoreMovie = Tools.findHighestMetascore(movieList);
        Movie lowestMetascoreMovie = Tools.findLowestRatedMovie(movieList);
        Movie mostRecentMovie = Tools.findMostRecentMovie(movieList);
        Movie oldestMovie = Tools.findOldestMovie(movieList);
        Movie shortestMovie = Tools.findShortestMovie(movieList);
        Movie longestMovie = Tools.findLongestMovie(movieList);

        System.out.println("The movie with the highest rating is "+highestRatedMovieMovie.getTitle());
        System.out.println("The movie with the lowest rating is "+lowestRatedMovieMovie.getTitle());
        System.out.println("The movie with the highest metascore is "+highestMetascoreMovie.getTitle());
        System.out.println("The movie with the lowest rating is "+lowestMetascoreMovie.getTitle());
        System.out.println("The most recent movie is "+mostRecentMovie.getTitle());
        System.out.println("The oldest movie is "+oldestMovie.getTitle());
        System.out.println("The shortest movie is "+shortestMovie.getTitle());
        System.out.println("The movie with the longest duration is "+longestMovie.getTitle());



    }
}