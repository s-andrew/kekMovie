package com.kek.kekMovie.Controllers;

import com.kek.kekMovie.Repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/movies")
@PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
public class Movies {

    @Autowired
    private MovieRepository movieRepository;
    @GetMapping("/{id}")
    public ModelAndView getMovie(@PathVariable long id){
        Map<String, Object> model = new HashMap<>();
        model.put("movie", movieRepository.findOne(id));
        return new ModelAndView("movie", model);
    }
}
