package fr.imie.videodb.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.imie.videodb.dao.IFilmDAO;
import fr.imie.videodb.dto.FilmDTO;
import fr.imie.videodb.entity.Video;

@Stateless
public class VideotequeServiceJPA implements IVideotequeService {

	@PersistenceContext
	private EntityManager entityManager; 
	

	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<FilmDTO> listAllFilm() {
		@SuppressWarnings("unchecked")
		List<Video> videoEntities = entityManager.createNamedQuery(
				"Video.findAll").getResultList();
//		List<Video> videoEntities = entityManager.createQuery("SELECT v FROM Video v").getResultList();
		
		List<FilmDTO> out = new ArrayList<FilmDTO>();
		for (Video video : videoEntities) {
			FilmDTO dto = new FilmDTO();
			dto.setId(video.getId());
			dto.setLibelle(video.getLibelle());
			out.add(dto);
		}
		return out;
	}

	@Override
	public List<FilmDTO> createSequel(FilmDTO filmDTO) {
		// TODO Auto-generated method stub
		return null;
	}





	
}
