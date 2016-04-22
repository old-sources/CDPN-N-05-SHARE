package fr.imie.videodb;

//import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fr.imie.videodb.dao.FilmDAO;
import fr.imie.videodb.dao.IFilmDAO;
import fr.imie.videodb.dto.FilmDTO;
import fr.imie.videodb.exception.VideoDBPersistenceException;
import fr.imie.videodb.service.IVideotequeService;
import fr.imie.videodb.service.VideotequeService;

public class VideotequeServiceSequelTestIntegration {

	private IFilmDAO filmDAO = new FilmDAO();
	private IVideotequeService videotequeService;

	@Before
	public void initMocks() {
		// MockitoAnnotations.initMocks(this);
		VideotequeService videotequeServiceImpl = new VideotequeService();
		videotequeServiceImpl.setFilmDAO(filmDAO);
		videotequeService = videotequeServiceImpl;
		try {
			FlatXmlDataSet flatXmlDataSet = new FlatXmlDataSetBuilder()
					.build(new FileInputStream("src/test/resources/test-dataset.xml"));
			Connection jdbcConnection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/videotheque",
					"imiedev", "imiedev");
			DatabaseOperation.CLEAN_INSERT.execute(new DatabaseConnection(jdbcConnection), flatXmlDataSet);


		} catch (DataSetException e) {
			throw new RuntimeException(e);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} catch (DatabaseUnitException e) {
			throw new RuntimeException(e);
		}

	}

	@Test
	public void testSequelForSuccess() throws VideoDBPersistenceException {

		FilmDTO filmDTO1 = new FilmDTO();
		filmDTO1.setLibelle("Spiderman");

		List<FilmDTO> result = videotequeService.createSequel(filmDTO1);

		Assert.assertThat(result, Matchers.<FilmDTO> hasItem(hasProperty("libelle", equalTo("Spiderman II"))));
		// assertThat(result, contains(hasProperty("libelle",is("Spiderman
		// II"))));

	}
	
	@Test(expected=RuntimeException.class)
	public void testSequelForException() throws VideoDBPersistenceException {

		FilmDTO filmDTO1 = new FilmDTO();
		filmDTO1.setLibelle("Batman for ever");

		List<FilmDTO> result = videotequeService.createSequel(filmDTO1);

		Assert.assertThat(result, Matchers.<FilmDTO> hasItem(hasProperty("libelle", equalTo("Batman for ever II"))));
		// assertThat(result, contains(hasProperty("libelle",is("Spiderman
		// II"))));

	}

}
