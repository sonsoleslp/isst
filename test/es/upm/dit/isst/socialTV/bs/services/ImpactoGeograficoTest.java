package es.upm.dit.isst.socialTV.bs.services;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

/**
 * Clase de prueba JUnit + Selenium WebDriver.
 * Prueba la user story "Impacto geogr�fico" adem�s de comprobar que le aparece la opci�n de
 * configuraci�n de monitorizaciones para un usuario administrador. Es necesario loggearse 
 * con una cuenta de administrador.
 * 
 * @author Paco
 *
 */
@SuppressWarnings({ "unused"})
public class ImpactoGeograficoTest {
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
    baseUrl = "https://1-dot-socialtv-1275.appspot.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    
    //ID de una monitorizaci�n que s� que tiene un mapa decente. En este caso es de #CarreraMujer
    idMonitorizacion = "4908916764835840";
    
    //Credenciales que no pienso subir
    gmailAddress = ""; //+ "@gmail.com";
    password = "";
  }

  @Test
  public void testImpactoGeografico() throws Exception {
	//P�gina inicial
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
    
    // Para comprobar que es usuario admin y tiene habilitada la opci�n /apitest
    assertEquals(driver.findElements(By.xpath("//a[contains(@href, '/apitest')]")).size(), 1);
   
    // Abrir directamente una monitorizaci�n que sabemos que tiene decente el mapa
    driver.get(baseUrl + "/mapa/" + idMonitorizacion);
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
