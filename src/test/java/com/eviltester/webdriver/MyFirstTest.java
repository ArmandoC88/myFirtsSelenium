package com.eviltester.webdriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


public class MyFirstTest {
   private WebDriver driver;
   By registerLinkLocator = By.linkText("REGISTER") ; //1.-creo el localizador para ir al registro
   By registerPageLocator = By.xpath("//img[@src='/images/masts/mast_register.gif']");//2.-creo el localizador para indicar que estamos en la pagina de registro

    //localizadores del registro de usuario
    By userNameLocator= By.id("email");//localizador de llenado de los campos de usuario por ID
    By passwordLocator= By.name("password");
    By confirmPasswordLocator = By.cssSelector("input[name='confirmPassword']");
    //boton de registrar al usuario
    By registerBtnLocator = By.name("register");

    //3.-logeo del usuario creado
    By userLocator = By.name("userName");
    By passLocator = By.name("password");
    By signBtnLocator= By.name("login");

    By homePageLocator =By.xpath("//img[@src='/images/masts/mast_flightfinder.gif']");
   @Before
   public void setUp() throws Exception{
       driver = new ChromeDriver();//instancio el objeto
       driver.manage().window().maximize();//maximizo la ventana de navegador
       driver.get("http://newtours.demoaut.com/");//ir a la url
   }
    @After
    public void tearDown() throws Exception{
   //driver.quit();//para cerrar el navegador
    }
    @Test
    public void registerUser() throws InterruptedException{
//1.-ir al link de registro y dar click:1.-declaramos el registerLinkLocator asignando el texto del link: Register
//2.- notificar a Selenium que ya estamos en la pagina de registro mediante el locator xpath: RegisterPageLocator y llenaremos los campos:

//3.-pasos para realizar el test :

        //3.1.-click en boton de registro
        driver.findElement(registerLinkLocator).click();
        Thread.sleep(2000);//temporizador de espera en 2 seg.
        //desicion if para recibir un true o false si la imagen del registerPageLocator aparece
        if(driver.findElement(registerPageLocator).isDisplayed()){
            //verdadero: 3.2.-Completar campos para registrar usuario
           driver.findElement(userNameLocator).sendKeys("qualityadmin");
           driver.findElement(passwordLocator).sendKeys("pass1");
           driver.findElement(confirmPasswordLocator).sendKeys("pass1");
           driver.findElement(registerBtnLocator).click();
        }
        else{//falso
            System.out.println("Register page was not found");
        }
    //3.3.-creo una lista de webelements para buscar en todos los 6 fonts
        List<WebElement> fonts = driver.findElements(By.tagName("font"));//mensaje de confirmacion en listado font
        Assert.assertEquals("Note: Your user name is qualityadmin.", fonts.get(5).getText());
    }

    //nuevo escenario 2: Autenticarse con usuario creado
@Test
    public void signIn() throws InterruptedException {
//verificamos que el text box del usuario este presente en la pagina
    if(driver.findElement(userLocator).isDisplayed()){
//verdadero: introducimos al usuario creado
        driver.findElement(userLocator).sendKeys("qualityadmin");
        driver.findElement(passLocator).sendKeys("pass1");
        driver.findElement(signBtnLocator).click();
        Thread.sleep(2000);
        //confirmar que estamos en la pagina autenticados
        Assert.assertTrue(driver.findElement(homePageLocator).isDisplayed());
    }
    else{//falso
        System.out.println("username textbox was not present");
    }
}

    /*@Test
   public void startWebDriver(){


        //driver.navigate().to("https://testpages.herokuapp.com");

        //Assert.assertTrue("title should start with Selenium",
        //                    driver.getTitle().startsWith("Selenium"));
        WebDriver driver = new ChromeDriver();
        driver.get("http://the-internet.herokuapp.com/login");
        driver.findElement( By.name( "username" ) ).sendKeys( "tomsmith" );
        driver.findElement( By.cssSelector( ".large-6 #password" ) ).sendKeys( "SuperSecretPassword!" );
        driver.findElement( By.className( "radius" )).click();
        Assert.assertTrue( driver.findElement( By.id( "flash" ) ).isDisplayed() );


        driver.close();
        driver.quit();


    }*/

}
