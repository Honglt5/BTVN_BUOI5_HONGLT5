import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestSuite {
    WebDriver driver;

    @Before
    public void setUp() {
//        //firefox
//        System.setProperty("webdriver.gecko.driver", "C:\\webdriver\\geckodriver.exe");
//        driver = new FirefoxDriver();

        // chrome
        System.setProperty("webdriver.chrome.driver", "C:\\webdriver\\chromedriver.exe");
        driver = new ChromeDriver();

        driver.get("https://saucedemo.com");
        driver.manage().window().maximize();
    }


    //        Dang nhap thanh cong
    @Test
    public void testcase01() {
       Login("standard_user","secret_sauce");
        WebElement shoppingCart = driver.findElement(By.id("shopping_cart_container"));
        Assert.assertTrue(shoppingCart.isDisplayed());

    }

    //        Sai username
    @Test
    public void testcase02() {
        Login("wrong_user","secret_sauce");
        WebElement errorLabel = driver.findElement(By.xpath("//div[@class='login-box']//h3"));
        //        Ctrl + F tren F12 de tim Xpath
        Assert.assertEquals("Epic sadface: Username and password do not match any user in this service",
                errorLabel.getText());
    }

    //        Sai password
    @Test
    public void testcase03() {
        Login("standard_user","wrong_pass");
        WebElement errorLabel = driver.findElement(By.xpath("//div[@class='login-box']//h3"));
        Assert.assertEquals("Epic sadface: Username and password do not match any user in this service",
                errorLabel.getText());
    }

    //        Sai username + password
    @Test
    public void testcase04() {
        Login("wrong_user","wrong_pass");
        WebElement errorLabel = driver.findElement(By.xpath("//div[@class='login-box']//h3"));
        Assert.assertEquals("Epic sadface: Username and password do not match any user in this service",
                errorLabel.getText());
    }

    //        Truyen ky tu dac biet sql html
    @Test
    public void testcase05() {
        Login("<html><b>standard_user</b></html>","secret_sauce");
        WebElement errorLabel = driver.findElement(By.xpath("//div[@class='login-box']//h3"));
        Assert.assertEquals("Epic sadface: Username and password do not match any user in this service",
                errorLabel.getText());
    }

   //    De trong username
    @Test
    public void testcase06() {
        Login("","secret_sauce");
        WebElement errorLabel = driver.findElement(By.xpath("//div[@class='login-box']//h3"));
        Assert.assertEquals("Epic sadface: Username is required",
                errorLabel.getText());
    }

    //    De trong password
    @Test
    public void testcase07() {
        Login("standard_user","");
        WebElement errorLabel = driver.findElement(By.xpath("//div[@class='login-box']//h3"));
        Assert.assertEquals("Epic sadface: Password is required",
                errorLabel.getText());
    }

    //    De trong username + password
    @Test
    public void testcase08() {
        Login("","");
        WebElement errorLabel = driver.findElement(By.xpath("//div[@class='login-box']//h3"));
        Assert.assertEquals("Epic sadface: Username is required",
                errorLabel.getText());
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    public void Login(String username, String password) {
        WebElement txtUsername = driver.findElement(By.id("user-name"));
        WebElement txtPassword = driver.findElement(By.id("password"));
        WebElement btnLogin = driver.findElement(By.id("login-button"));
        txtUsername.sendKeys(username);
        txtPassword.sendKeys(password);
        btnLogin.click();
    }

}
