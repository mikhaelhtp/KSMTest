package com.company;

import org.graphwalker.core.condition.EdgeCoverage;
import org.graphwalker.core.generator.RandomPath;
import org.graphwalker.core.machine.ExecutionContext;
import org.graphwalker.core.model.Edge;
import org.graphwalker.java.annotation.GraphWalker;
import org.graphwalker.java.test.TestBuilder;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@GraphWalker(value = "random(edge_coverage(100))", start = "e_OpenLoginPage")
public class CreateSubscriptionTest extends ExecutionContext implements CreateSubscriptionModel{
	public final static Path MODEL_PATH = Paths.get("com/company/CreateSubscriptionModel.json");
    public WebDriver driver = new FirefoxDriver();
    
    @BeforeTest
    public void setUp() {
    	System.out.println("setup");
    	System.setProperty("Webdriver.firefox.driver", "D:\\selenium webdriver\\geckodriver-v0.32.2-win64\\geckodriver.exe");
    }
    
    @Override
    public void v_SuccessCreateSubscription() {
        System.out.println("v_SuccessCreateSubscription");
        WebElement text_success_create_subscription = driver.findElement(By.tagName("strong"));
        String body_text_success_create_subscription = text_success_create_subscription.getText();
        Assert.assertTrue(body_text_success_create_subscription.contains("Subscriptions successfully added!"));  
    }
    
    @Override
    public void e_InputIncorrectData() {
    	System.out.println("e_InputIncorrectData");
        Faker faker = new Faker();
        // Subscription Plan tidak dipilih
        // Outlet tidak dipilih
        // Billing Date tidak dipilih     
        // Expires tidak dipilih
        // Payment Type tidak dipilih 
        // Amount lebih dari 12 digit
        WebElement incorrect_amount = driver.findElement(By.id("id_add_order_payment_form-amount"));
        incorrect_amount.sendKeys(faker.number().digits(15));
        WebElement save_button = driver.findElement(By.xpath("/html/body/main/div/div/div/div/div/div/div[1]/div/form/button"));
        save_button.click(); 
    }
    
    @Override
    public void e_OpenSubscriptionPage() {
        System.out.println("e_OpenSubscriptionPage");
        WebElement subscription_menu = driver.findElement(By.xpath("//*[@id=\"sidenav-collapse-main\"]/ul/li[4]/a"));
        subscription_menu.click(); 
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
        WebElement close = driver.findElement(By.xpath("/html/body/main/div/div/div/div/div/div/div[1]/button/span"));
        close.click();   
    }
    
    @Override
    public void e_InputCorrectCredentials() {
        System.out.println("e_InputCorrectCredentials");
        WebElement email_input = driver.findElement(By.name("login"));
        email_input.sendKeys("sales@gmail.com");

        WebElement password_input = driver.findElement(By.name("password"));
        password_input.sendKeys("asdf12345678");

        WebElement login_button = driver.findElement(By.xpath("/html/body/main/section/div/div/div/div/div/div[2]/form/input[2]"));
        login_button.click();
    }
    
    @Override
    public void v_LoginPage() {
        System.out.println("v_LoginPage");
        WebElement text_login_page = driver.findElement(By.tagName("p"));
        String body_text_login_page = text_login_page.getText();
        Assert.assertTrue(body_text_login_page.contains("Enter your credentials to login"));
    }
    
    @Override
    public void e_InputCorrectData() {
        System.out.println("e_InputCorrectData");
        WebElement text = driver.findElement(By.tagName("h5"));
        String bodyText = text.getText();
        Assert.assertTrue(bodyText.contains("Form Subscription"));

        Faker faker = new Faker();
       
        // Outlet
        WebElement outlet = driver.findElement(By.id("id_add_subscription_form-outlet"));
        Select outletSelect = new Select(outlet);
        List<WebElement> options = outletSelect.getOptions();
        if (options.size() > 0) {
            // Jika opsi outlet masih tersedia, pilih sesuai dengan value dari opsi tersebut
            String outletId = options.get(0).getAttribute("value");
            outletSelect.selectByValue(outletId);
            // Subscription Plan 
            WebElement subscription_plan = driver.findElement(By.id("id_add_subscription_form-subscriptionplan"));
            Select dropdownSelect = new Select(subscription_plan);
            dropdownSelect.selectByValue("1"); 
            // Billing Date
            WebElement billing_date = driver.findElement(By.id("id_add_subscription_form-billing_date"));
            billing_date.click();
            Actions actions = new Actions(driver);
            actions.sendKeys(Keys.ARROW_UP).sendKeys(Keys.ENTER).build().perform();        
            // Expires
            WebElement expires = driver.findElement(By.id("id_add_subscription_form-expires"));
            expires.click();
            Actions actions2 = new Actions(driver);
            actions2.sendKeys(Keys.ARROW_UP).sendKeys(Keys.ARROW_UP).sendKeys(Keys.ENTER).build().perform();
            // Payment Type
            WebElement payment_type = driver.findElement(By.id("id_add_order_payment_form-payment_type"));
            Select dropdownSelect3 = new Select(payment_type);
            dropdownSelect3.selectByValue("bank_transfer"); 
            // Amount
            WebElement amount = driver.findElement(By.id("id_add_order_payment_form-amount"));
            amount.sendKeys(faker.number().digits(10)); 
            WebElement save_button = driver.findElement(By.xpath("/html/body/main/div/div/div/div/div/div/div[1]/div/form/button"));
            save_button.click(); 
        } else {
            // Jika opsi outlet tidak tersedia, klik button cancel 
        	// panggil method e_Cancel
        	e_Cancel();
        }
    }
    
    @Override
    public void e_OpenCreateSubscriptionPage() {
    	System.out.println("e_OpenCreateSubscriptionPage");
        WebElement create_subscription_button = driver.findElement(By.xpath("/html/body/main/div/div/div/div/div/div/div[2]/div/a"));
        create_subscription_button.click();
    }
    
    @Override
    public void v_AlertIncorrectData() {
        System.out.println("v_AlertIncorrectData");
        WebElement text_alert_incorrect_data = driver.findElement(By.id("error_1_id_add_order_payment_form-amount"));
        String body_text_alert_incorrect_data = text_alert_incorrect_data.getText();
        Assert.assertTrue(body_text_alert_incorrect_data.contains("Ensure that there are no more than 12 digits in total."));
    }
    
    @Override
	public void v_CreateSubscriptionPage() {
    	System.out.println("v_CreateSubscriptionPage");
        WebElement text_create_subscription_page = driver.findElement(By.tagName("h5"));
        String body_text_create_subscription_page = text_create_subscription_page.getText();
        Assert.assertTrue(body_text_create_subscription_page.contains("Form Subscription"));
		
	}
    
    @Override
    public void v_AlertEmptyData() {
        System.out.println("v_AlertEmptyData");
        WebElement text_alert_empty_data = driver.findElement(By.tagName("strong"));
        String body_text_alert_empty_data = text_alert_empty_data.getText();
        Assert.assertTrue(body_text_alert_empty_data.contains("This field is required."));
    }

    @Override
    public void e_OpenLoginPage() {
    	System.out.println("e_OpenLoginPage");
		driver.get("http://127.0.0.1:8000/");
		System.out.println(driver.getTitle());
    }
    
    @Override
    public void v_SubscriptionPage() {
        System.out.println("v_SubscriptionPage");
        WebElement text_subscription_page = driver.findElement(By.tagName("h5"));
        String body_text_subscription_page = text_subscription_page.getText();
        Assert.assertTrue(body_text_subscription_page.contains("Subcriptions"));
    }
    
    @Override
    public void e_InputEmptyData() {
    	System.out.println("e_InputEmptyData");
        // Subscription Plan tidak dipilih
        // Outlet tidak dipillih
        // Billing Date tidak dipilih    
        // Expires tidak dipilih  
        // Payment Type tidak dipilih
        // Amount
        WebElement empty_amount = driver.findElement(By.id("id_add_order_payment_form-amount"));
        empty_amount.sendKeys("");
        WebElement save_button = driver.findElement(By.xpath("/html/body/main/div/div/div/div/div/div/div[1]/div/form/button"));
        save_button.click();
    }
    
    @Override
    public void e_Cancel() {
    	System.out.println("e_Cancel");
    	WebElement cancel_button = driver.findElement(By.xpath("/html/body/main/div/div/div/div/div/div/div[1]/div/form/a"));
        cancel_button.click(); 
    }
    
    @Test
    public void runFunctionalTest() {
        new TestBuilder()
                .addContext(new CreateSubscriptionTest().setNextElement(new Edge().setName("e_OpenLoginPage").build()),
                        MODEL_PATH,
                        new RandomPath(new EdgeCoverage(100)))
                .execute();
    }

	
    
}
