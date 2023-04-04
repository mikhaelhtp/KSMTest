package com.company;

import com.github.javafaker.Faker;
import org.graphwalker.core.condition.EdgeCoverage;
import org.graphwalker.core.generator.RandomPath;
import org.graphwalker.core.machine.ExecutionContext;
import org.graphwalker.core.model.Edge;
import org.graphwalker.java.test.TestBuilder;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

public class CreateUserTest extends ExecutionContext implements CreateUserModel{
	public final static Path MODEL_PATH = Paths.get("com/company/CreateUserModel.json");
    public WebDriver driver = new FirefoxDriver();
    
    @BeforeTest
    public void setUp() {
    	System.out.println("setup");
    	System.setProperty("Webdriver.firefox.driver", "D:\\selenium webdriver\\geckodriver-v0.32.2-win64\\geckodriver.exe");
    }
    
    @Override
    public void e_InputCorrectCredentials() {
        System.out.println("e_InputCorrectCredentials");
        WebElement email_input = driver.findElement(By.name("login"));
        email_input.sendKeys("admin");

        WebElement password_input = driver.findElement(By.name("password"));
        password_input.sendKeys("asdf12345678");

        WebElement login_button = driver.findElement(By.xpath("/html/body/main/section/div/div/div/div/div/div[2]/form/input[2]"));
        login_button.click();
    }
    
    @Override
    public void v_ClientPage() {
        System.out.println("v_ClientPage");
        WebElement text = driver.findElement(By.tagName("h5"));
        String bodyText = text.getText();
        Assert.assertTrue(bodyText.contains("Clients"));
    }
    
    @Override
    public void e_OpenCreateUserPage() {
        System.out.println("e_OpenCreateUserPage");
        WebElement create_user_button = driver.findElement(By.xpath("/html/body/main/div/div/div/div/div[1]/div/div/div/a"));
        create_user_button.click(); 
    }
    
    @Override
    public void e_OpenUserPage() {
        System.out.println("e_OpenUserPage");
        WebElement user_menu = driver.findElement(By.xpath("//*[@id=\"sidenav-collapse-main\"]/ul/li[8]/a/span"));
        user_menu.click();  
    }
    
    @Override
    public void e_Close() {
        System.out.println("e_Close");
        WebElement close = driver.findElement(By.xpath("/html/body/main/div/div/div/div/div[1]/div/div[1]/button"));
        close.click(); 
    }
    
    @Override
    public void v_LoginPage() {
        System.out.println("v_LoginPage");
        WebElement text = driver.findElement(By.tagName("p"));
        String bodyText = text.getText();
        Assert.assertTrue(bodyText.contains("Enter your credentials to login"));
    }
    
    @Override
    public void e_OpenLoginPage() {
    	System.out.println("e_OpenLoginPage");
		driver.get("http://127.0.0.1:8000/");
		System.out.println(driver.getTitle());
    }

    @Override
    public void e_Cancel() {
        System.out.println("e_Cancel");
        WebElement cancel_button = driver.findElement(By.xpath("/html/body/main/div/div/div/div/div/div/div[1]/div/form/a"));
        cancel_button.click(); 
    }
    
    @Override
    public void v_SuccessCreateUser() {
        System.out.println("v_SuccessCreateUser");
        WebElement text = driver.findElement(By.tagName("strong"));
        String bodyText = text.getText();
        Assert.assertTrue(bodyText.contains("Account has been created successfully! Account details have been sent to the respective email."));
    }
    
    @Override
    public void v_UserPage() {
        System.out.println("v_UserPage");
        WebElement text = driver.findElement(By.tagName("h5"));
        String bodyText = text.getText();
        Assert.assertTrue(bodyText.contains("Users"));
    }
    
    @Override
    public void e_InputEmptyData() {
        System.out.println("e_InputEmptyData");
        // username
        WebElement username= driver.findElement(By.id("id_username"));
        username.sendKeys("");
        // name
        WebElement name_of_user= driver.findElement(By.id("id_name"));
        name_of_user.sendKeys("");
        // email
        WebElement email= driver.findElement(By.id("id_email"));
        email.sendKeys("");
        // type
        WebElement dropdown = driver.findElement(By.id("id_type"));
        Select dropdownSelect = new Select(dropdown);
        dropdownSelect.selectByValue("ADMIN");
        //save
        WebElement save_button = driver.findElement(By.xpath("/html/body/main/div/div/div/div/div/div/div[1]/div/form/button"));
        save_button.click();
    }
    
    @Override
    public void v_CreateUserPage() {
        System.out.println("v_CreateUserPage");
        WebElement text = driver.findElement(By.tagName("h4"));
        String bodyText = text.getText();
        Assert.assertTrue(bodyText.contains("Add User"));
    }
    
    @Override
    public void v_AlertIncorrectData() {
        System.out.println("v_AlertIncorrectData");
        WebElement text = driver.findElement(By.id("error_1_id_username"));
        String bodyText = text.getText();
        Assert.assertTrue(bodyText.contains("Enter a valid username. This value may contain only letters, numbers, and @/./+/-/_ characters."));
        WebElement text2 = driver.findElement(By.id("error_1_id_email"));
        String bodyText2 = text2.getText();
        Assert.assertTrue(bodyText2.contains("Enter a valid email address."));
 
    }
    
    @Override
    public void v_AlertEmptyData() {
        System.out.println("v_AlertEmptyData");
        WebElement text = driver.findElement(By.tagName("strong"));
        String bodyText = text.getText();
        Assert.assertTrue(bodyText.contains("This field is required."));
    }

    @Override
    public void e_InputIncorrectData() {
        System.out.println("e_InputIncorrectData");
        Faker faker = new Faker();
        // invalid username (more than 150 characters)
        int username_length = 151;
        WebElement username = driver.findElement(By.id("id_username"));
        username.sendKeys(faker.regexify("[A-Za-z0-9!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]{" + username_length + "}"));
        // name
        WebElement name_of_user = driver.findElement(By.id("id_name"));
        name_of_user.sendKeys(faker.name().fullName());
        // email
        WebElement email = driver.findElement(By.id("id_email"));
        // invalid email 
        email.sendKeys(String.valueOf(new Faker().random().hex(10)));
        // type
        WebElement dropdown = driver.findElement(By.id("id_type"));
        Select dropdownSelect = new Select(dropdown);
        dropdownSelect.selectByValue("SALES");
        //save
        WebElement save_button = driver.findElement(By.xpath("/html/body/main/div/div/div/div/div/div/div[1]/div/form/button"));
        save_button.click();
    }
    
    @Override
    public void e_InputCorrectData() {
        System.out.println("e_InputCorrectData");
        Faker faker = new Faker();
        // username
        WebElement username= driver.findElement(By.id("id_username"));
        int username_length = 150;
        username.sendKeys(faker.regexify("[A-Za-z0-9]{" + username_length + "}"));
        // name
        WebElement name_of_user= driver.findElement(By.id("id_name"));
        name_of_user.sendKeys(faker.name().fullName());
        // email
        WebElement email= driver.findElement(By.id("id_email"));
        email.sendKeys(faker.internet().emailAddress());
        // type
        WebElement dropdown = driver.findElement(By.id("id_type"));
        Select dropdownSelect = new Select(dropdown);
        dropdownSelect.selectByValue("SALES");
        //save
        WebElement save_button = driver.findElement(By.xpath("/html/body/main/div/div/div/div/div/div/div[1]/div/form/button"));
        save_button.click(); 
    }

    @Test
    public void runFunctionalTest() {
        new TestBuilder()
                .addContext(new CreateUserTest().setNextElement(new Edge().setName("e_OpenLoginPage").build()),
                        MODEL_PATH,
                        new RandomPath(new EdgeCoverage(100)))
                .execute();
    }
    
}
