package com.company;

import org.graphwalker.core.condition.EdgeCoverage;
import org.graphwalker.core.generator.RandomPath;
import org.graphwalker.core.machine.ExecutionContext;
import org.graphwalker.core.model.Edge;
import org.graphwalker.java.test.TestBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

//
//@GraphWalker(value = "random(edge_coverage(100))", start = "e_OpenLoginPage")
public class LoginTest extends ExecutionContext implements LoginModel{
	public final static Path MODEL_PATH = Paths.get("com/company/LoginModel.json");
	public WebDriver driver = new FirefoxDriver();
    
    @BeforeTest
    public void setUp() {
    	System.out.println("setup");
    	System.setProperty("Webdriver.firefox.driver", "D:\\selenium webdriver\\geckodriver-v0.32.2-win64\\geckodriver.exe");
    }
    
    @Override
    public void e_OpenLoginPage() {
    	System.out.println("e_OpenLoginPage");
		driver.get("http://127.0.0.1:8000/");
		System.out.println(driver.getTitle());
    }

    @Override
    public void e_InputCorrectCredentials() {
        System.out.println("e_InputCorrectCredentials");
        WebElement email_input = driver.findElement(By.id("id_login"));
        email_input.sendKeys("admin");

        WebElement password_input = driver.findElement(By.id("id_password"));
        password_input.sendKeys("asdf12345678");

        WebElement login_button = driver.findElement(By.xpath("/html/body/main/section/div/div/div/div/div/div[2]/form/input[2]"));
        login_button.click();
    }

    @Override
    public void e_OpenLogoutPage() {
        System.out.println("e_OpenLogoutPage");
        WebElement logout_button = driver.findElement(By.xpath("//*[@id=\"navbar\"]/ul/li[1]/a/span"));
        logout_button.click();
    }

    @Override
    public void v_LogoutPage() {
        System.out.println("v_LogoutPage");
        WebElement text = driver.findElement(By.tagName("h5"));
        String bodyText = text.getText();
        Assert.assertTrue(bodyText.contains("Sign Out"));
    }

    @Override
    public void v_ClientPage() {
        System.out.println("v_ClientPage");
        WebElement text = driver.findElement(By.tagName("h5"));
        String bodyText = text.getText();
        Assert.assertTrue(bodyText.contains("Clients"));
    }

    @Override
    public void e_Logout() {
        System.out.println("e_Logout");
        WebElement logout_button = driver.findElement(By.xpath("/html/body/main/div[1]/div/div/div/div/div/div/div/div[1]/div/form/button"));
        logout_button.click();
    }

    @Override
    public void e_InputEmptyCredentials() {
        System.out.println("e_InputEmptyCredentials");
        WebElement email_input = driver.findElement(By.name("login"));
        email_input.sendKeys("");

        WebElement password_input = driver.findElement(By.name("password"));
        password_input.sendKeys("");

        WebElement login_button = driver.findElement(By.xpath("/html/body/main/section/div/div/div/div/div/div[2]/form/input[2]"));
        login_button.click();
    }

    @Override
    public void v_LoginPage() {
        System.out.println("v_LoginPage");
        WebElement text = driver.findElement(By.tagName("p"));
        String bodyText = text.getText();
        Assert.assertTrue(bodyText.contains("Enter your credentials to login"));
    }

    @Override
    public void e_InputIncorrectCredentials() {
        System.out.println("e_InputIncorrectCredentials");
        WebElement email_input = driver.findElement(By.name("login"));
        email_input.sendKeys("werijqwer");
        WebElement password_input = driver.findElement(By.name("password"));
        password_input.sendKeys("asdf1wqr2345qwerqw678");
        WebElement login_button = driver.findElement(By.xpath("/html/body/main/section/div/div/div/div/div/div[2]/form/input[2]"));
        login_button.click();
    }

    @Test
    public void runFunctionalTest() {
        new TestBuilder()
                .addContext(new LoginTest().setNextElement(new Edge().setName("e_OpenLoginPage").build()),
                        MODEL_PATH,
                        new RandomPath(new EdgeCoverage(100)))
                .execute();
    }
    




}
