package id.go.bpkp.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
	 public List<Film> allFilms(){
	    	return emf  .createEntityManager().createQuery("from Film").getResultList();
	    }
	 public Actor getActors(int id){
	    	return (Actor) emf  .createEntityManager().createQuery("from Actor where actorId=" + id).getSingleResult();
	    	
	    }
	 public List<FilmActor> allFilmActors(int id){
	    	return emf  .createEntityManager().createQuery("FROM FilmActor f WHERE f.filmActorPK.actorId =" + id).getResultList();
	    }
	 
	 public List<FilmActor> allActorinFilm(int id){
	    	return emf  .createEntityManager().createQuery("FROM FilmActor f WHERE f.filmActorPK.filmId =" + id).getResultList();
	    }
	 public Film getFilm(int id){
	    	return (Film) emf  .createEntityManager().createQuery("from Film where filmId=" + id).getSingleResult();
	    	
	    }
	 public int addActor(Actor newActor)
	 {
		 EntityManager em = emf.createEntityManager();
		 EntityTransaction trans = em.getTransaction();
		 int hasil=0;
		 try {
			 trans.begin();
			 newActor.setLastUpdate(new Date());
			 em.persist(newActor);
			 trans.commit();
		 }
		 catch(Exception ex) {
			 trans.rollback();
			 hasil=-1;
			 System.out.println(ex.getMessage());
		 }
		 
		 
		 
		 return hasil;
		 
	 }
	 
	 
	   public Actor getActors(Short id){
	   	return emf  .createEntityManager().find(Actor.class,id);
	   }
	 
	   public int editActor(Actor actor) {//@RequestParam("actor") Actor) {
		   int hasil=0;
		   try {
		   
		   EntityManager e = emf.createEntityManager();
		   e.getTransaction().begin();
		   Actor currentActor=e.find(Actor.class, actor.getActorId());
		   currentActor.setFirstName(actor.getFirstName());
		   currentActor.setLastName(actor.getLastName());
		   currentActor.setLastUpdate(new Date());
		      
		   e.getTransaction().commit();
		   
		   }
		   catch(Exception ex) {
			   System.out.println(ex.getMessage());
			   hasil=-1;
		   }
		   
		   return hasil;
	   }
	 
	 public int addFilm(Film newFilm)
	 {
		 EntityManager em = emf.createEntityManager();
		 EntityTransaction trans = em.getTransaction();
		 int hasil=0;
		 try {
			 trans.begin();
			 newFilm.setLastUpdate(new Date());
			 em.persist(newFilm);
			 trans.commit();
		 }
		 catch(Exception ex) {
			 trans.rollback();
			 hasil=-1;
			 System.out.println(ex.getMessage());
		 }
		 
		 
		 
		 return hasil;
		 
	 }
	 
}
