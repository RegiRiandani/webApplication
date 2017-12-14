package id.go.bpkp.dao;

import java.util.List;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.go.bpkp.entity.Actor;
import id.go.bpkp.entity.Film;
import id.go.bpkp.entity.FilmActor;
import id.go.bpkp.entity.FilmActorPK;



@Service
public class ActorDAO {

	
	@Autowired
	private EntityManagerFactory emf;
	 public List<Actor> allActors(){
	    	return emf  .createEntityManager().createQuery("from Actor").getResultList();
	    }
	 public Actor getActors(int id){
	    	return (Actor) emf  .createEntityManager().createQuery("from Actor where actorId=" + id).getSingleResult();
	    	
	    }
	 public List<FilmActor> allFilmActors(int id){
	    	return emf  .createEntityManager().createQuery("FROM FilmActor f WHERE f.filmActorPK.actorId =" + id).getResultList();
	    }
	 public Film getFilm(int id){
	    	return (Film) emf  .createEntityManager().createQuery("from Film where filmId=" + id).getSingleResult();
	    	
	    }
	 public List<FilmActor> allActorinFilm(int id){
	    	return emf  .createEntityManager().createQuery("FROM FilmActor f WHERE f.filmActorPK.filmId =" + id).getResultList();
	    }
	 
}
