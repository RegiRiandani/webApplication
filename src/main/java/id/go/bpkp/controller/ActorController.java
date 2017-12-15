package id.go.bpkp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import id.go.bpkp.dao.ActorDAO;
import id.go.bpkp.dao.FilmDAO;
import id.go.bpkp.entity.Actor;
import id.go.bpkp.entity.Film;
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
	@GetMapping("/indexFilm")
	public String indexFilm(Model model) {
		String bahasa[]= {"Melayu", "Tagalog","Thailand"};
		model.addAttribute("negara", "Indonesia");
		model.addAttribute("bahasa", bahasa);
		model.addAttribute("films",actorDAO.allFilms());
		return "actor/indexFilm";
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
	
	@GetMapping("/add")
	public String add(Model model)
	{
		model.addAttribute("actor",new Actor());
		return "actor/add";
	}

	@PostMapping("/add")
	public String add(@Valid Actor actor,BindingResult result)
	{
		if(!result.hasErrors())
		{
				actorDAO.addActor(actor);
		return "redirect:/actor/index";
		}
		else
		{
			return "actor/add";
		}
	}

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") short id,Model model)
	{
		model.addAttribute("actor",actorDAO.getActors(id));
		return "actor/edit";
	}

	@PostMapping("/edit")
	public String edit(Actor actor)
	{
		actorDAO.editActor(actor);
		return "redirect:/actor/index";
	}

	@GetMapping("/addFilm")
	public String addFilm(Model model)
	{
		model.addAttribute("film",new Film());
		return "actor/addFilm";
	}

	@PostMapping("/addFilm")
	public String addFilm(Film film)
	{
		actorDAO.addFilm(film);
		return "redirect:/actor/indexFilm";
	}

}
