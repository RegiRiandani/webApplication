package id.go.bpkp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.go.bpkp.dao.FilmActorDAO;

@RestController
public class ResponsiveController {

	@Autowired
	private FilmActorDAO faDAO;
	@RequestMapping("/linkActorFilm/{filmId}/{actorId}")
	public int linkActorAndFilm(@PathVariable("filmId") short filmId, @PathVariable("actorId") short actorId)
	{
		
		return faDAO.addFilmActor(actorId,filmId);
	}
	
	
}
