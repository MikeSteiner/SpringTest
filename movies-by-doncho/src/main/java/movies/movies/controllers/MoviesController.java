package movies.movies.controllers;

import movies.movies.models.Movie;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/movies")
public class MoviesController {

    private final ArrayList<Movie> movies;

    public MoviesController() {
        this.movies = new ArrayList<Movie>();
        this.movies.add(new Movie(1, "Spiderman", "no descr"));
        this.movies.add(new Movie(2, "How to train your dragon", "no descr"));
        this.movies.add(new Movie(3, "How to train your dragon - Secret world", "no descr"));
    }

    //    /api/movies
    //    GET
    //    Return all movies
    @RequestMapping(method=RequestMethod.GET)
    public List<Movie> getAll() {
        return this.movies;
    }

    //    /api/movies/MOVIE_ID
    //    GET
    //    Return movie details
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Movie getById(@PathVariable(value = "id") int id) {
        Movie movie = findFirstMovieById(id);
        if (movie != null) {
            return movie;
        }

        throw new NoSuchElementException("Movie");
    }

    //    /api/movies
    //    POST
    //    Create new movie
    @RequestMapping(method = RequestMethod.POST)
    public Movie addSingle(@RequestBody Movie newMovie) {
        this.movies.add(newMovie);
        return newMovie;
    }

    //    /api/movies/MOVIE_ID
    //    PUT
    //    Vote for movie
    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public Movie voteById(@PathVariable(value = "id") int id,
                          @RequestBody int voteValue) {
        Movie movie = findFirstMovieById(id);

        if (movie != null) {
            double oldAverageVoteValue = movie.getRating();
            int oldVotesCount = movie.getVotesCount();
            int newVotesCount = ++oldVotesCount;
            double oldVotesValueSum = oldAverageVoteValue * oldVotesCount;

            double newAverageVoteValue = (oldVotesValueSum + voteValue) / newVotesCount;

            movie.setVotesCount(newVotesCount);
            movie.setRating(newAverageVoteValue);

            return movie;
        }

        throw new NoSuchElementException("Movie");
    }

    private Movie findFirstMovieById(int id) {
        if (id <= 0) {
            return null;
        }

        Movie movieFound = this.movies
                .stream()
                .filter(movie -> movie.getId() == id)
                .findFirst()
                .get();

        return movieFound;
    }

}
