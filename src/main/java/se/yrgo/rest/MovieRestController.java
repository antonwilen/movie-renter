package se.yrgo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import se.yrgo.data.MovieRepository;
import se.yrgo.domain.Movie;

@RestController
public class MovieRestController {

    @Autowired
    private MovieRepository data;

    @RequestMapping("/movies")
    public MovieList allMovies(){
        List<Movie> all = data.findAll();
        return new MovieList(all);
    }
    
    @RequestMapping("/movies/{title}")
    public ResponseEntity<Movie> showMovieByTitle(@PathVariable("title") String title) {
        Movie movie = data.findByTitle(title);
        if (movie == null) {
            return new ResponseEntity<Movie>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Movie>(movie, HttpStatus.OK);
    }

    @RequestMapping("/movies/setrented/{title}")
    public ResponseEntity<Movie> setRented(@PathVariable("title") String title) {
        Movie movie = data.findByTitle(title);
        if (movie == null) {
            return new ResponseEntity<Movie>(HttpStatus.NOT_FOUND);
        }
        movie.setRented(true);
        data.save(movie);
        return new ResponseEntity<Movie>(movie, HttpStatus.OK);
    }

    @RequestMapping("/movies/return/{title}")
    public ResponseEntity<Movie> returnMovie(@PathVariable("title") String title) {
        Movie movie = data.findByTitle(title);
        if (movie == null) {
            return new ResponseEntity<Movie>(HttpStatus.NOT_FOUND);
        }
        movie.setRented(false);
        data.save(movie);
        return new ResponseEntity<Movie>(movie, HttpStatus.OK);
    }

    @RequestMapping(value="/movies", method=RequestMethod.POST)
    public ResponseEntity createNewMovie(@RequestBody Movie movie) {
        data.save(movie);
        return new ResponseEntity<Movie>(movie,HttpStatus.CREATED);
    }

}
