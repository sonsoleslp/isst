package es.upm.dit.isst.socialTV.bs.services;

import static org.junit.Assert.fail;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

import es.upm.dit.isst.socialTV.bs.model.DatoAudienciaDAO;
import es.upm.dit.isst.socialTV.bs.model.DatoAudienciaImpl;
import es.upm.dit.isst.socialTV.bs.model.ProgramaTVDAO;
import es.upm.dit.isst.socialTV.bs.model.ProgramaTVImpl;

/**
 * Clase de prueba JUnit + Selenium WebDriver.
 * Prueba la user story "Comparativa de gráficas".
 * 
 * @author Paco
 *
 */
@SuppressWarnings({ "unused"})
public class ComparativaGraficasTest {
	//Selenium
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	
	//BBDD
	private final LocalServiceTestHelper helper =
			new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());
	private ProgramaTVDAO daoProg = ProgramaTVImpl.getInstance();
	private DatoAudienciaDAO daoDat = DatoAudienciaImpl.getInstance();
	
	//Login
	private String gmailAddress;
	private String password;
	
	//Monitorizaciones
	private String idMonitorizacion1;
	private String idMonitorizacion2;
	private String idMonitorizacion3;
	
	

	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		//EN GAE
		//baseUrl = "https://1-dot-socialtv-1275.appspot.com/";
		//EN LOCAL
		baseUrl = "http://localhost:8888/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		//ID de una monitorización que sé que tiene un mapa decente. En este caso es de #CarreraMujer
		//idMonitorizacion = "5667908084563968"; //aquí se ve que pulsa los tres que hay
		//idMonitorizacion1 = "4908916764835840"; //aquí sólo hay una gráfica para comparar pero queda bien

		//Credenciales que no pienso subir
		gmailAddress = ""; //+ "@gmail.com";
		password = "";
		
		helper.setUp();
		crearMonitorizaciones();
	}

	@Test
	public void testComparativaGraficas() throws Exception {
		//Página inicial
		driver.get(baseUrl);
		
		//PARA GAE
		driver.findElement(By.linkText("Login")).click();
		/*driver.findElement(By.id("Email")).clear();
		driver.findElement(By.id("Email")).sendKeys(gmailAddress + "@gmail.com");
		driver.findElement(By.id("next")).click();
		driver.findElement(By.id("PersistentCookie")).click();
		driver.findElement(By.id("Passwd")).clear();
		driver.findElement(By.id("Passwd")).sendKeys(password);
		driver.findElement(By.id("signIn")).click();
		driver.findElement(By.id("approve_button")).click();*/

		//EN LOCAL
		driver.findElement(By.id("email")).clear();
	    driver.findElement(By.id("email")).sendKeys(gmailAddress + "@gmail.com");
	    driver.findElement(By.id("btn-login")).click();
	    //driver.findElement(By.cssSelector("span.glyphicon.glyphicon-log-in")).click();
		
		// Abrir directamente una monitorización que sabemos que tiene decente el mapa
		/*driver.get(baseUrl + "compare/" + idMonitorizacion1);

		//Seleccionar todos las gráficas que se puedan superponer
		List<WebElement> toggles = driver.findElements(By.xpath("//div[@id='leyenda']/ul/li"));

		if (toggles.size() == 0) {
			fail("No hay gráficas para superponer");
		}

		Iterator<WebElement> iterator = toggles.iterator();
		while (iterator.hasNext()) {
			iterator.next().click();
		}*/
	}

	@After
	public void tearDown() throws Exception {

		//driver.quit(); //dejar el navegador abierto para que se vea el resultado
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
		helper.tearDown();
	}
	
	private Date addMinutes(int minutes) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MINUTE, minutes);
		return cal.getTime();
	}
	
	private void crearMonitorizaciones() {
		//Crear un par de monitorizaciones simultáneas en bases de datos
		
		
		Calendar iniCal = Calendar.getInstance();
		Date ini = new Date();
		Random r = new Random();
		
		//Monitorización 1
		daoProg.crearMonitorizacion("TITULO_PRUEBA1", "EPISODE_CODE", ini, addMinutes(45), "#HASHTAG_PRUEBA");
		long key1 = daoProg.programasPorTitulo("TITULO_PRUEBA1").get(0).getPrimaryKey();
		idMonitorizacion1 =  Long.toString(key1);
		//"Tuitear" aleatoriamente durante la monitorización
		for (int i = 0; i < 50; i+=5) {
			daoDat.apuntaDato(key1, addMinutes(i), r.nextInt(101));
		}
		
		//Monitorización 2
		daoProg.crearMonitorizacion("TITULO_PRUEBA2", "EPISODE_CODE", ini, addMinutes(45), "#HASHTAG_PRUEBA");
		long key2 = daoProg.programasPorTitulo("TITULO_PRUEBA1").get(0).getPrimaryKey();
		idMonitorizacion2 =  Long.toString(key2);
		//"Tuitear" aleatoriamente durante la monitorización
		for (int i = 0; i < 50; i+=5) {
			daoDat.apuntaDato(key2, addMinutes(i), r.nextInt(101));
		}
		
		//Monitorización 3
		daoProg.crearMonitorizacion("TITULO_PRUEBA3", "EPISODE_CODE", ini, addMinutes(45), "#HASHTAG_PRUEBA");
		long key3 = daoProg.programasPorTitulo("TITULO_PRUEBA1").get(0).getPrimaryKey();
		idMonitorizacion3 =  Long.toString(key3);
		//"Tuitear" aleatoriamente durante la monitorización
		for (int i = 0; i < 50; i+=5) {
			daoDat.apuntaDato(key3, addMinutes(i), r.nextInt(101));
		}
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}
}
