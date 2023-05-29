package se.yrgo.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import se.yrgo.data.MovieRepository;
import se.yrgo.domain.Movie;


@Controller
@RequestMapping("/website/movies")
public class MovieController {
	@Autowired
	private MovieRepository data;

      @RequestMapping(value="/newMovie.html",method=RequestMethod.POST)
      public String newMovie(Movie movie) {
		data.save(movie);
		return "redirect:/website/movies/list.html";
	}
	
	@RequestMapping(value="/newMovie.html",method=RequestMethod.GET)
	public ModelAndView renderNewMovieForm(){
		Movie newMovie = new Movie();
		return new ModelAndView("newMovie", "form", newMovie);
	} 
	
	@RequestMapping(value="/list.html", method=RequestMethod.GET)	
	public ModelAndView movies(){
		List<Movie> allMovies = data.findAll();
		return new ModelAndView("allMovies" , "movies",
                       allMovies);
	}
	  
	@RequestMapping(value="/movie/{name}")
	public ModelAndView showMovieByName(@PathVariable("name") String 
                                                               name) {
		Movie movie = data.findByTitle(name);
        return new ModelAndView("MovieInfo", name, movie);
	}	


}

