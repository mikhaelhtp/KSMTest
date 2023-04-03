package com.company;

import org.graphwalker.core.condition.EdgeCoverage;
import org.graphwalker.core.generator.RandomPath;
import org.graphwalker.core.machine.ExecutionContext;
import org.graphwalker.core.model.Edge;
import org.graphwalker.java.test.TestBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import com.github.javafaker.Faker;

import java.nio.file.Path;
import java.nio.file.Paths;

public class CreateSubscriptionPlanTest extends ExecutionContext implements CreateSubscriptionPlanModel{
	public final static Path MODEL_PATH = Paths.get("com/company/CreateSubscriptionPlanModel.json");
    public WebDriver driver = new FirefoxDriver();
    
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
    public void e_Close() {
        System.out.println("e_Close");
        WebElement close = driver.findElement(By.xpath("/html/body/main/div/div/div/div/div[1]/div[1]/button"));
        close.click();
    }
    
    @Override
    public void e_OpenCreateSubscriptionPlanPage() {
        System.out.println("e_OpenCreateSubscriptionPlanPage");
        WebElement create_subscription_plan_button = driver.findElement(By.xpath("/html/body/main/div/div/div/div/div[1]/div/div/a"));
        create_subscription_plan_button.click();     
    }
    
    @Override
    public void v_SubscriptionPlanPage() {
        System.out.println("v_SubscriptionPlanPage");
        WebElement text = driver.findElement(By.tagName("h5"));
        String bodyText = text.getText();
        Assert.assertTrue(bodyText.contains("Susbcription Plans"));
    }
    
    @Override
    public void v_CreateSubscriptionPlanPage() {
        System.out.println("v_CreateSubscriptionPlanPage");
        WebElement text = driver.findElement(By.tagName("h5"));
        String bodyText = text.getText();
        Assert.assertTrue(bodyText.contains("Form Subscription Plan"));
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
    	System.setProperty("Webdriver.firefox.driver", "D:\\selenium webdriver\\geckodriver-v0.32.2-win64\\geckodriver.exe");	
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
    public void e_InputEmptyData() {
        System.out.println("e_InputEmptyData");
        // Subscription Plan Name
        WebElement subscription_plan_name = driver.findElement(By.id("id_name"));
        subscription_plan_name.sendKeys("");
        // Price
        WebElement price = driver.findElement(By.id("id_price"));
        price.sendKeys("");
        // Description
        WebElement description = driver.findElement(By.id("id_description"));
        description.sendKeys("");
        // Trial Unit Tidak Dipilih
        // Trial Period
        WebElement trial_period = driver.findElement(By.id("id_trial_period"));
        trial_period.sendKeys(""); 
        // Recurrence Unit Tidak Dipilih
        // Recurrence Period
        WebElement recurrence_period = driver.findElement(By.id("id_recurrence_period"));
        recurrence_period.sendKeys(""); 
        // button save
        WebElement save_button = driver.findElement(By.xpath("/html/body/main/div/div/div/div/div/div/div[1]/div/form/button"));
        save_button.click();
    }
    
    @Override
    public void v_AlertIncorrectData() {
        System.out.println("v_AlertIncorrectData");
        WebElement text = driver.findElement(By.id("error_1_id_price"));
        String bodyText = text.getText();
        Assert.assertTrue(bodyText.contains("Ensure that there are no more than 12 digits in total."));
    }
    
    @Override
    public void v_AlertEmptyData() {
        System.out.println("v_AlertEmptyData");
        WebElement text = driver.findElement(By.tagName("strong"));
        String bodyText = text.getText();
        Assert.assertTrue(bodyText.contains("This field is required."));
    }
    
    @Override
    public void e_OpenSubscriptionPlanPage() {
        System.out.println("e_OpenSubscriptionPlanPage");
        WebElement subscription_plan_menu = driver.findElement(By.xpath("//*[@id=\"sidenav-collapse-main\"]/ul/li[7]/a"));
        subscription_plan_menu.click(); 
    }
    
    @Override
    public void e_InputIncorrectData() {
        System.out.println("e_InputIncorrectData");
        Faker faker = new Faker();
        // Subscription Plan Name
        WebElement subscription_plan_name = driver.findElement(By.id("id_name"));
        subscription_plan_name.sendKeys(faker.regexify("a-zA-Z0-9{50}"));
        // Invalid Price
        WebElement price = driver.findElement(By.id("id_price"));
        price.sendKeys(faker.number().digits(15));
        // Description
        WebElement description = driver.findElement(By.id("id_description"));
        description.sendKeys(faker.regexify("a-zA-Z0-9{50}"));
        // Trial Unit
        WebElement trial_unit = driver.findElement(By.id("id_trial_unit"));
        Select dropdownSelect = new Select(trial_unit);
        dropdownSelect.selectByValue("D"); 
        // Invalid Trial Period
        WebElement trial_period = driver.findElement(By.id("id_trial_period"));
        trial_period.sendKeys(faker.regexify("a-zA-Z0-9{10}")); 
        // Recurrence Unit
        WebElement recurrence_unit = driver.findElement(By.id("id_recurrence_unit"));
        Select dropdownSelect2 = new Select(recurrence_unit);
        dropdownSelect2.selectByValue("D"); 
        // Invalid Recurrence Period
        WebElement recurrence_period = driver.findElement(By.id("id_recurrence_period"));
        recurrence_period.sendKeys(faker.regexify("a-zA-Z0-9{10}")); 
        // button save
        WebElement save_button = driver.findElement(By.xpath("/html/body/main/div/div/div/div/div/div/div[1]/div/form/button"));
        save_button.click();
    }

    @Override
    public void v_SuccessCreateSubscriptionPlan() {
        System.out.println("v_SuccessCreateSubscriptionPlan");
        WebElement text = driver.findElement(By.tagName("strong"));
        String bodyText = text.getText();
        Assert.assertTrue(bodyText.contains("Subscription plans successfully added!"));
    }
    
    @Override
    public void e_InputCorrectData() {
        System.out.println("e_InputCorrectData");
        Faker faker = new Faker();
        // Subscription Plan Name
        WebElement subscription_plan_name = driver.findElement(By.id("id_name"));
        subscription_plan_name.sendKeys(faker.lorem().characters(5));
        // Price
        WebElement price = driver.findElement(By.id("id_price"));
        price.sendKeys(faker.number().digits(10));
        // Description
        WebElement description = driver.findElement(By.id("id_description"));
        description.sendKeys(faker.lorem().characters(30));
        // Trial Unit
        WebElement trial_unit = driver.findElement(By.id("id_trial_unit"));
        Select dropdownSelect = new Select(trial_unit);
        dropdownSelect.selectByValue("D"); 
        // Trial Period
        WebElement trial_period = driver.findElement(By.id("id_trial_period"));
        trial_period.sendKeys(faker.number().digits(1)); 
        // Recurrence Unit
        WebElement recurrence_unit = driver.findElement(By.id("id_recurrence_unit"));
        Select dropdownSelect2 = new Select(recurrence_unit);
        dropdownSelect2.selectByValue("D"); 
        // Recurrence Period
        WebElement recurrence_period = driver.findElement(By.id("id_recurrence_period"));
        recurrence_period.sendKeys(faker.number().digits(1)); 
        // button save
        WebElement save_button = driver.findElement(By.xpath("/html/body/main/div/div/div/div/div/div/div[1]/div/form/button"));
        save_button.click();
    }

    @Test
    public void runFunctionalTest() {
        new TestBuilder()
                .addContext(new CreateSubscriptionPlanTest().setNextElement(new Edge().setName("e_OpenLoginPage").build()),
                        MODEL_PATH,
                        new RandomPath(new EdgeCoverage(100)))
                .execute();
    }
    
}
