package es.upm.dit.isst.socialTV.bs.services;

import java.util.regex.Pattern;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

/**
 * Clase de prueba JUnit + Selenium WebDriver.
 * Prueba la user story "Comparativa de gráficas".
 * 
 * @author Paco
 *
 */
@SuppressWarnings({ "unused"})
public class ComparativaGraficasTest {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  
  private String gmailAddress;
  private String password;
  private String idMonitorizacion;
  
  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "https://1-dot-socialtv-1275.appspot.com";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    
    //DATOS DE ENTRADA
     
    idMonitorizacion = "6026535974731776"; //ID de una monitorización que haya ocurrido más o menos a la vez que otras
    //Credenciales de google para loggearse
    gmailAddress = ""; //+ "@gmail.com";
    password = "";
  }

  @Test
  public void testImpactoGeografico() throws Exception {
	//Página inicial
    driver.get(baseUrl + "/");
    driver.findElement(By.linkText("Login")).click();
    driver.findElement(By.id("Email")).clear();
    driver.findElement(By.id("Email")).sendKeys(gmailAddress+ "@gmail.com");
    driver.findElement(By.id("next")).click();
    driver.findElement(By.id("PersistentCookie")).click();
    driver.findElement(By.id("Passwd")).clear();
    driver.findElement(By.id("Passwd")).sendKeys(password);
    driver.findElement(By.id("signIn")).click();
    driver.findElement(By.id("approve_button")).click();
   
    //Abrir directamente la monitorización elegida
    driver.get(baseUrl + "/compare/" + idMonitorizacion);
    
    //Obtener los "toggles" de gráficas que se pueden superponer
    List<WebElement> toggles = driver.findElements(By.xpath("//div[@id='leyenda']/ul/li"));
    
    if (toggles.size() == 0) {
    	fail("No hay gráficas para superponer");
    }
    
    //Activar todas las gráficas que se puedan superponer
    Iterator<WebElement> iterator = toggles.iterator();
	while (iterator.hasNext()) {
		iterator.next().click();
	}
  }

  @After
  public void tearDown() throws Exception {
	  
    //driver.quit(); //dejar el navegador abierto para que se vea el resultado
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
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