package org.imie;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By.ById;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestFonctionel {

	private static WebDriver driver;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		driver = new FirefoxDriver();
		driver.get("http://localhost:8080/TennisRulesWeb-1.0.0-SNAPSHOT/TennisScore");
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception{
		if(driver!=null){		
			driver.quit();
		}
	}
	
	@Before
	public void setUp() throws Exception {
		driver.findElement(By.name("reset")).click();
	}

	@Test
	public void testTitle() {	
		assertEquals("Score du Tennis", driver.getTitle()); 
	}
	
	@Test
	public void testInitScore() {
		WebElement webElement = driver.findElement(By.id("score"));
		assertEquals("0-0", webElement.getText()); 
	}
	
	@Test
	public void test_15_0() {
		driver.findElement(By.name("joueur1")).click();
		WebElement webElement = driver.findElement(By.id("score"));
		assertEquals("15-0", webElement.getText()); 
	}
	
	@Test
	public void test_30_0() {
		driver.findElement(By.name("joueur1")).click();
		driver.findElement(By.name("joueur1")).click();
		WebElement webElement = driver.findElement(By.id("score"));
		assertEquals("30-0", webElement.getText()); 
	}
	
	@Test
	public void test_40_0() {
		driver.findElement(By.name("joueur1")).click();
		driver.findElement(By.name("joueur1")).click();
		driver.findElement(By.name("joueur1")).click();
		WebElement webElement = driver.findElement(By.id("score"));
		assertEquals("40-0", webElement.getText()); 
	}
	
	@Test
	public void test_EverWin() {
		driver.findElement(By.name("joueur1")).click();
		driver.findElement(By.name("joueur1")).click();
		driver.findElement(By.name("joueur1")).click();
		driver.findElement(By.name("joueur1")).click();
		driver.findElement(By.name("joueur1")).click();
		WebElement webElementE = driver.findElement(By.id("error"));
		WebElement webElementS = driver.findElement(By.id("score"));
		assertEquals("jeux déjà en victoire", webElementE.getText()); 
		assertEquals("victoire J1", webElementS.getText());
	}

}
