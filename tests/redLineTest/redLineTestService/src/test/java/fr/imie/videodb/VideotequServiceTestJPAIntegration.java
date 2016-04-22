package fr.imie.videodb;


import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.hsqldb.HsqldbDataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import org.hamcrest.Matchers;
import org.hibernate.Session;
import org.hibernate.internal.SessionImpl;
//import org.dbunit.database.IDatabaseConnection;
//import org.dbunit.dataset.IDataSet;
//import org.dbunit.operation.DatabaseOperation;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import fr.imie.videodb.dto.FilmDTO;
import fr.imie.videodb.exception.VideoDBPersistenceException;
import fr.imie.videodb.service.IVideotequeService;
import fr.imie.videodb.service.VideotequeServiceJPA;

//http://city81.blogspot.fr/2011/03/testing-jpa-entities-using-dbunit.html
@RunWith(MockitoJUnitRunner.class)
public class VideotequServiceTestJPAIntegration {

    protected static EntityManagerFactory entityManagerFactory;
    protected static EntityManager entityManager;
    protected static IDatabaseConnection connection;
    protected static IDataSet dataset;
	
	//@Mock EntityManager entityManager;
	
	IVideotequeService videotequServiceTest;
	

    @BeforeClass
    public static void initEntityManager() throws Exception {
        entityManagerFactory = Persistence.createEntityManagerFactory("TestPersistenceUnitPostgres");
        entityManager = entityManagerFactory.createEntityManager();
        //connection = new DatabaseConnection(((SessionImpl)(entityManager.getDelegate())).connection());
        Session session = entityManager.unwrap(Session.class);
        connection = new DatabaseConnection(((SessionImpl) session).connection());
        connection.getConfig().setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new HsqldbDataTypeFactory());
 
        FlatXmlDataSetBuilder flatXmlDataSetBuilder = new FlatXmlDataSetBuilder();
        flatXmlDataSetBuilder.setColumnSensing(true);
        dataset = flatXmlDataSetBuilder.build(
        Thread.currentThread().getContextClassLoader().getResourceAsStream("test-dataset.xml"));
    }
    
    @Before
    public void cleanDB() throws Exception {
        DatabaseOperation.CLEAN_INSERT.execute(connection, dataset);
        VideotequeServiceJPA videotequService= new VideotequeServiceJPA();
        videotequService.setEntityManager(entityManager);
        videotequServiceTest=videotequService;
    }
 
    
    @AfterClass
    public static void closeEntityManager() {
        entityManager.close();
        entityManagerFactory.close();
    }
	
	@Test
	public void test() throws VideoDBPersistenceException {
//		Video testFilm = new Video();
//		testFilm.setLibelle("film1");
//		FilmDTO testFilm2 = new FilmDTO();
//		testFilm2.setLibelle("film1");
//		List<FilmDTO> testList= new ArrayList<FilmDTO>();
//		testList.add(testFilm);
		//when(filmDAO.findAllFilm()).thenReturn(testList);
//		List<Video> result= new ArrayList<Video>();
//		result.add(testFilm);
//		Query mockedQuery = mock(Query.class);
//        when(mockedQuery.getResultList()).thenReturn(result);
//        when(entityManager.createNamedQuery("Video.findAll")).thenReturn(mockedQuery);
//		List<FilmDTO> films= videotequServiceTest.listAllFilm();
//		//assertThat(films, contains(equalTo(testFilm2)));
//		assertThat(films, contains(hasProperty("libelle",is("film1"))));
		List<FilmDTO> result = videotequServiceTest.listAllFilm();
		Assert.assertThat(result, Matchers.<FilmDTO>hasItem(hasProperty("libelle",equalTo("spiderman"))));

	}

}
