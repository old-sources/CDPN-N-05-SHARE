package fr.imie.videodb;

//import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import fr.imie.videodb.dao.FilmDAO;
import fr.imie.videodb.dao.IFilmDAO;
import fr.imie.videodb.dto.FilmDTO;
import fr.imie.videodb.exception.VideoDBPersistenceException;
import fr.imie.videodb.service.IVideotequeService;
import fr.imie.videodb.service.VideotequeService;


public class VideotequeServiceSequelTestIntegration {

	private IFilmDAO filmDAO=new FilmDAO();
	private IVideotequeService videotequeService;
	
	
	
	@Before
	public void initMocks() {
		//MockitoAnnotations.initMocks(this);
		VideotequeService videotequeServiceImpl = new VideotequeService();
		videotequeServiceImpl.setFilmDAO(filmDAO);
		videotequeService = videotequeServiceImpl;
		
	}

	
	@Test
	public void test() throws VideoDBPersistenceException {
		
		
		FilmDTO filmDTO1 = new FilmDTO();
		filmDTO1.setLibelle("Spiderman");	
		
		List<FilmDTO> result = videotequeService.createSequel(filmDTO1);
		
		Assert.assertThat(result, Matchers.<FilmDTO>hasItem(hasProperty("libelle",equalTo("Spiderman II"))));
		//assertThat(result, contains(hasProperty("libelle",is("Spiderman II"))));
		
	}

}
