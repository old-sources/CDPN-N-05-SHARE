package fr.imie.videodb;



import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import fr.imie.videodb.dto.FilmDTO;
import fr.imie.videodb.entity.Video;
import fr.imie.videodb.exception.VideoDBPersistenceException;
import fr.imie.videodb.service.IVideotequeService;
import fr.imie.videodb.service.VideotequeServiceJPA;


@RunWith(MockitoJUnitRunner.class)
public class VideotequServiceTestJPA {

	@Mock EntityManager entityManager;
	
	@InjectMocks  IVideotequeService videotequServiceTest = new VideotequeServiceJPA();
	
	@Test
	public void test() throws VideoDBPersistenceException {
		Video testFilm = new Video();
		testFilm.setLibelle("film1");
//		FilmDTO testFilm2 = new FilmDTO();
//		testFilm2.setLibelle("film1");
//		List<FilmDTO> testList= new ArrayList<FilmDTO>();
//		testList.add(testFilm);
		//when(filmDAO.findAllFilm()).thenReturn(testList);
		List<Video> result= new ArrayList<Video>();
		result.add(testFilm);
		Query mockedQuery = mock(Query.class);
        when(mockedQuery.getResultList()).thenReturn(result);
        when(entityManager.createNamedQuery("Video.findAll")).thenReturn(mockedQuery);
		List<FilmDTO> films= videotequServiceTest.listAllFilm();
		//assertThat(films, contains(equalTo(testFilm2)));
		assertThat(films, contains(hasProperty("libelle",is("film1"))));
		

	}

}
