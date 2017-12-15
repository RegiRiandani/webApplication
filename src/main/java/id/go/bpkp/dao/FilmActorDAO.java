package id.go.bpkp.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.transaction.Transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.go.bpkp.entity.Actor;
import id.go.bpkp.entity.Film;
import id.go.bpkp.entity.FilmActor;
import id.go.bpkp.entity.FilmActorPK;



@Service
public class FilmActorDAO {

	
	@Autowired
	private EntityManagerFactory emf;
	@Autowired
	private FilmDAO filmDAO;
	@Autowired
	private ActorDAO actorDAO;
	 public List<FilmActor> allFilmActors(){
	    	return (List<FilmActor>)emf  .createEntityManager().createQuery("from FilmActor").getResultList();
	    }
	 public int addFilmActor(short actorId, short filmId)
	 {
		 EntityManager em = emf.createEntityManager();
		 EntityTransaction trans = em.getTransaction();
		 int hasil=0;
		 try {
			 trans.begin();
			 FilmActor filmActor= new FilmActor(actorId, filmId);
			 filmActor.setLastUpdate(new Date());
			 em.persist(filmActor);
			 trans.commit();
		 }
		 catch(Exception ex) {
			 trans.rollback();
			 hasil=-1;
			 System.out.println(ex.getMessage());
		 }
		 
		 
		 
		 return hasil;
		 
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
