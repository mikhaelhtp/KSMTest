package com.company;

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

import com.github.javafaker.Faker;

import java.nio.file.Path;
import java.nio.file.Paths;

public class CreateSubscriptionPlanTest extends ExecutionContext implements CreateSubscriptionPlanModel{
	public final static Path MODEL_PATH = Paths.get("com/company/CreateSubscriptionPlanModel.json");
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
        WebElement text_client_page = driver.findElement(By.tagName("h5"));
        String body_text_client_page = text_client_page.getText();
        Assert.assertTrue(body_text_client_page.contains("Clients"));
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
        WebElement text_subscrption_plan_page = driver.findElement(By.tagName("h5"));
        String body_text_subscrption_plan_page = text_subscrption_plan_page.getText();
        Assert.assertTrue(body_text_subscrption_plan_page.contains("Susbcription Plans"));
    }
    
    @Override
    public void v_CreateSubscriptionPlanPage() {
        System.out.println("v_CreateSubscriptionPlanPage");
        WebElement text_create_subscription_plan_page = driver.findElement(By.tagName("h5"));
        String body_text_create_subscription_plan_page = text_create_subscription_plan_page.getText();
        Assert.assertTrue(body_text_create_subscription_plan_page.contains("Form Subscription Plan"));
    }

    @Override
    public void v_LoginPage() {
        System.out.println("v_LoginPage");
        WebElement text_login_page = driver.findElement(By.tagName("p"));
        String body_text_login_page = text_login_page.getText();
        Assert.assertTrue(body_text_login_page.contains("Enter your credentials to login"));
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
    public void e_InputEmptyData() {
        System.out.println("e_InputEmptyData");
        // Subscription Plan Name
        WebElement empty_subscription_plan_name = driver.findElement(By.id("id_name"));
        empty_subscription_plan_name.sendKeys("");
        // Price
        WebElement empty_price = driver.findElement(By.id("id_price"));
        empty_price.sendKeys("");
        // Description
        WebElement empty_description = driver.findElement(By.id("id_description"));
        empty_description.sendKeys("");
        // Trial Unit Tidak Dipilih
        // Trial Period
        WebElement empty_trial_period = driver.findElement(By.id("id_trial_period"));
        empty_trial_period.sendKeys(""); 
        // Recurrence Unit Tidak Dipilih
        // Recurrence Period
        WebElement empty_recurrence_period = driver.findElement(By.id("id_recurrence_period"));
        empty_recurrence_period.sendKeys(""); 
        // button save
        WebElement save_button = driver.findElement(By.xpath("/html/body/main/div/div/div/div/div/div/div[1]/div/form/button"));
        save_button.click();
    }
    
    @Override
    public void v_AlertIncorrectData() {
        System.out.println("v_AlertIncorrectData");
        WebElement text_alert_incorrect_data = driver.findElement(By.id("error_1_id_price"));
        String body_text_alert_incorrect_data = text_alert_incorrect_data.getText();
        Assert.assertTrue(body_text_alert_incorrect_data.contains("Ensure that there are no more than 12 digits in total."));
    }
    
    @Override
    public void v_AlertEmptyData() {
        System.out.println("v_AlertEmptyData");
        WebElement text_alert_empty_data = driver.findElement(By.tagName("strong"));
        String body_text_alert_empty_data = text_alert_empty_data.getText();
        Assert.assertTrue(body_text_alert_empty_data.contains("This field is required."));
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
        WebElement incorrect_subscription_plan_name = driver.findElement(By.id("id_name"));
        incorrect_subscription_plan_name.sendKeys(faker.regexify("a-zA-Z0-9{50}"));
        // Invalid Price
        WebElement incorrect_price = driver.findElement(By.id("id_price"));
        incorrect_price.sendKeys(faker.number().digits(15));
        // Description
        WebElement incorrect_description = driver.findElement(By.id("id_description"));
        incorrect_description.sendKeys(faker.regexify("a-zA-Z0-9{50}"));
        // Trial Unit
        WebElement incorrect_trial_unit = driver.findElement(By.id("id_trial_unit"));
        Select dropdown_incorrect_trial_unit_select = new Select(incorrect_trial_unit);
        dropdown_incorrect_trial_unit_select.selectByValue("D"); 
        // Invalid Trial Period
        WebElement incorrect_trial_period = driver.findElement(By.id("id_trial_period"));
        incorrect_trial_period.sendKeys(faker.regexify("a-zA-Z0-9{10}")); 
        // Recurrence Unit
        WebElement incorrect_recurrence_unit = driver.findElement(By.id("id_recurrence_unit"));
        Select dropdown_incorrect_recurrence_unit_select = new Select(incorrect_recurrence_unit);
        dropdown_incorrect_recurrence_unit_select.selectByValue("D"); 
        // Invalid Recurrence Period
        WebElement incorrect_recurrence_period = driver.findElement(By.id("id_recurrence_period"));
        incorrect_recurrence_period.sendKeys(faker.regexify("a-zA-Z0-9{10}")); 
        // button save
        WebElement save_button = driver.findElement(By.xpath("/html/body/main/div/div/div/div/div/div/div[1]/div/form/button"));
        save_button.click();
    }

    @Override
    public void v_SuccessCreateSubscriptionPlan() {
        System.out.println("v_SuccessCreateSubscriptionPlan");
        WebElement text_success_create_subscription_plan = driver.findElement(By.tagName("strong"));
        String body_text_success_create_subscription_plan = text_success_create_subscription_plan.getText();
        Assert.assertTrue(body_text_success_create_subscription_plan.contains("Subscription plans successfully added!"));
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
