package fr.imie.videodb;



import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Assert;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import fr.imie.videodb.dao.IFilmDAO;
import fr.imie.videodb.dto.FilmDTO;
import fr.imie.videodb.exception.VideoDBPersistenceException;
import fr.imie.videodb.service.IVideotequeService;
import fr.imie.videodb.service.VideotequeService;


@RunWith(MockitoJUnitRunner.class)
public class VideotequServiceTest {

	@Mock IFilmDAO filmDAO;
	
	@InjectMocks  IVideotequeService videotequServiceTest = new VideotequeService();
	
	@Test
	public void test() throws VideoDBPersistenceException {
		FilmDTO testFilm = new FilmDTO();
		testFilm.setLibelle("film1");
//		FilmDTO testFilm2 = new FilmDTO();
//		testFilm2.setLibelle("film1");
		List<FilmDTO> testList= new ArrayList<FilmDTO>();
		testList.add(testFilm);
		when(filmDAO.findAllFilm()).thenReturn(testList);
		List<FilmDTO> films= videotequServiceTest.listAllFilm();
		//assertThat(films, contains(equalTo(testFilm2)));
		assertThat(films, contains(Matchers.<FilmDTO>hasProperty("libelle",is("film1"))));
		

	}

}
