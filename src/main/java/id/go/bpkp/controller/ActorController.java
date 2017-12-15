package id.go.bpkp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import id.go.bpkp.dao.ActorDAO;
import id.go.bpkp.dao.FilmDAO;
import id.go.bpkp.entity.FilmActor;

@Controller
@RequestMapping("/actor")
public class ActorController {
	
	@Autowired
	private ActorDAO actorDAO;
	private FilmDAO filmDAO;
	
	@GetMapping("/index")
	public String index(Model model) {
		String bahasa[]= {"Melayu", "Tagalog","Thailand"};
		model.addAttribute("negara", "Indonesia");
		model.addAttribute("bahasa", bahasa);
		model.addAttribute("actors",actorDAO.allActors());
		return "actor/index";
	}
	@GetMapping("/detail/{id}")
	public String detail(@PathVariable("id") int actorId, Model model)
	{
		FilmActor filmActor=new FilmActor();
		model.addAttribute("actor", actorDAO.getActors(actorId));
		model.addAttribute("filmActors",actorDAO.allFilmActors(actorId));
		return "actor/detail";
	}
	@GetMapping("/detailFilm/{id}")
	public String detailFilm(@PathVariable("id") int filmId, Model model)
	{
		
		model.addAttribute("film", actorDAO.getFilm(filmId));
		model.addAttribute("Actors", actorDAO.allActorinFilm(filmId));
		//model.addAttribute("film", "Indonesia");
		return "actor/detailFilm";
	}

}
