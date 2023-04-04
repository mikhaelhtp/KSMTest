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

public class CreateOutletTest extends ExecutionContext implements CreateOutletModel{
	public final static Path MODEL_PATH = Paths.get("com/company/CreateOutletModel.json");
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
    public void v_CreateOutletPage() {
        System.out.println("v_CreateOutletPage");
        WebElement text_create_outlet_page = driver.findElement(By.tagName("h5"));
        String body_text_create_outlet_page = text_create_outlet_page.getText();
        Assert.assertTrue(body_text_create_outlet_page.contains("Form Outlet"));
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
        WebElement close = driver.findElement(By.xpath("/html/body/main/div/div/div/div/div[1]/div/div[1]/button"));
        close.click();
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
        WebElement cancel_button = driver.findElement(By.xpath("//*[@id=\"outletForm\"]/a"));
        cancel_button.click(); 
    }

    @Override
    public void e_OpenOutletPage() {
        System.out.println("e_OpenOutletPage");
        WebElement outlet_menu = driver.findElement(By.xpath("//*[@id=\"sidenav-collapse-main\"]/ul/li[3]/a"));
        outlet_menu.click(); 
    }
    
    @Override
    public void e_InputEmptyData() {
        System.out.println("e_InputEmptyData");
        // Client, tidak ada memilih
        // Outlet Name
        WebElement empty_outlet_name = driver.findElement(By.id("id_name"));
        empty_outlet_name.sendKeys("");
        // Display Name
        WebElement empty_display_name = driver.findElement(By.id("id_display_name"));
        empty_display_name.sendKeys("");
        // Phone Number
        WebElement empty_phone_number = driver.findElement(By.id("id_phone"));
        empty_phone_number.sendKeys("");
        // Address
        WebElement empty_address = driver.findElement(By.id("id_address"));
        empty_address.sendKeys(""); 
        // Postal Code
        WebElement empty_postal_code = driver.findElement(By.id("id_postal_code"));
        empty_postal_code.sendKeys(""); 
        // Province tidak memilih
        // City tidak memilih
        // Outlet Code
        WebElement empty_outlet_code = driver.findElement(By.id("id_outlet_code"));
        empty_outlet_code.sendKeys("");
        // Transaction Code Prefix
        WebElement empty_transaction_code_prefix = driver.findElement(By.id("id_transaction_code_prefix"));
        empty_transaction_code_prefix.sendKeys("");
        // Taxes
        WebElement empty_taxes = driver.findElement(By.id("id_taxes"));
        empty_taxes.sendKeys("");
        // Gratuity
        WebElement empty_gratuity = driver.findElement(By.id("id_gratuity"));
        empty_gratuity.sendKeys("");
        // button save
        WebElement save_button = driver.findElement(By.xpath("//*[@id=\"outletForm\"]/button"));
        save_button.click();
    }
    
    @Override
    public void v_CreateOutletSuccess() {
        System.out.println("v_CreateOutletSuccess");
        WebElement text_create_outlet_success = driver.findElement(By.tagName("strong"));
        String body_text_create_outlet_success = text_create_outlet_success.getText();
        Assert.assertTrue(body_text_create_outlet_success.contains("Outlet successfully added"));
    }
    
    @Override
    public void v_AlertIncorrectData() {
        System.out.println("v_AlertIncorrectData");
        WebElement text_error_phone = driver.findElement(By.id("error_1_id_phone"));
        String body_text_error_phone = text_error_phone.getText();
        Assert.assertTrue(body_text_error_phone.contains("Enter a valid phone number (e.g. +12125552368)."));
        WebElement text_error_postal_code = driver.findElement(By.id("error_1_id_postal_code"));
        String body_text_error_postal_code = text_error_postal_code.getText();
        Assert.assertTrue(body_text_error_postal_code.contains("Invalid postal code"));
        WebElement text_error_taxes = driver.findElement(By.id("error_1_id_taxes"));
        String body_text_error_taxes = text_error_taxes.getText();
        Assert.assertTrue(body_text_error_taxes.contains("Ensure that there are no more than 2 decimal places."));
    }
    
    @Override
    public void e_OpenCreateOutletPage() {
        System.out.println("e_OpenCreateOutletPage");
        WebElement create_outlet_button = driver.findElement(By.xpath("/html/body/main/div/div/div/div/div[1]/div/div/div/a"));
        create_outlet_button.click(); 
    }
    
    @Override
    public void v_AlertEmptyData() {
        System.out.println("v_AlertEmptyData");
        WebElement text_alert_empty_data = driver.findElement(By.tagName("strong"));
        String body_text_alert_empty_data = text_alert_empty_data.getText();
        Assert.assertTrue(body_text_alert_empty_data.contains("This field is required."));
    }
    
    @Override
    public void v_OutletPage() {
        System.out.println("v_OutletPage");
        WebElement text_outlet_page = driver.findElement(By.tagName("h5"));
        String body_text_outlet_page = text_outlet_page.getText();
        Assert.assertTrue(body_text_outlet_page.contains("Outlets"));
    }

    @Override
    public void e_InputIncorrectData() {
        System.out.println("e_InputIncorrectData");
        Faker faker = new Faker();
        // Client
        WebElement client_dropdown = driver.findElement(By.id("id_client"));
        Select client_dropdown_select = new Select(client_dropdown);
        client_dropdown_select.selectByValue("2");
        // Outlet Name
        WebElement incorrect_outlet_name = driver.findElement(By.id("id_name"));
        incorrect_outlet_name.sendKeys(faker.name().fullName());
        // Display Name
        WebElement incorrect_display_name = driver.findElement(By.id("id_display_name"));
        incorrect_display_name.sendKeys(faker.name().fullName());
        // Invalid Phone Number 
        WebElement incorrect_phone_number = driver.findElement(By.id("id_phone"));
        incorrect_phone_number.sendKeys(faker.phoneNumber().cellPhone());
        // Address
        WebElement incorrect_address = driver.findElement(By.id("id_address"));
        incorrect_address.sendKeys(faker.address().fullAddress()); 
        // Invalid Postal Code
        WebElement incorrect_postal_code = driver.findElement(By.id("id_postal_code"));
        incorrect_postal_code.sendKeys(faker.number().digits(6)); 
        // Province
        WebElement province_dropdown = driver.findElement(By.id("id_province"));
        Select province_dropdown_select = new Select(province_dropdown);
        province_dropdown_select.selectByValue("2");
        // City
        WebElement city_dropdown = driver.findElement(By.id("id_city"));
        Select city_dropdown_select = new Select(city_dropdown);
        city_dropdown_select.selectByValue("48");
        // Outlet Code
        WebElement incorrect_outlet_code = driver.findElement(By.id("id_outlet_code"));
        incorrect_outlet_code.sendKeys(String.valueOf(new Faker().random().hex(5)));
        // Transaction Code Prefix
        WebElement incorrect_transaction_code_prefix = driver.findElement(By.id("id_transaction_code_prefix"));
        incorrect_transaction_code_prefix.sendKeys(String.valueOf(new Faker().random().hex(5)));
        // Taxes
        double randomNumber = faker.random().nextDouble() * 1000.0;
        WebElement incorrect_taxes = driver.findElement(By.id("id_taxes"));
        incorrect_taxes.sendKeys(String.format("%.3f", randomNumber)); 
        // Gratuity
        WebElement incorrect_gratuity = driver.findElement(By.id("id_gratuity"));
        incorrect_gratuity.sendKeys(String.format("%.3f", randomNumber));
        // button save
        WebElement save_button = driver.findElement(By.xpath("//*[@id=\"outletForm\"]/button"));
        save_button.click();
    }
    
    @Override
    public void e_InputCorrectData() {
        System.out.println("e_InputCorrectData");
        Faker faker = new Faker();
        // Client
        WebElement client_dropdown = driver.findElement(By.id("id_client"));
        Select client_dropdown_select = new Select(client_dropdown);
        client_dropdown_select.selectByValue("2");
        // Outlet Name
        WebElement outlet_name = driver.findElement(By.id("id_name"));
        outlet_name.sendKeys(faker.name().fullName());
        // Display Name
        WebElement display_name = driver.findElement(By.id("id_display_name"));
        display_name.sendKeys(faker.name().fullName());
        // Phone Number
        WebElement phone_number = driver.findElement(By.id("id_phone"));
//        phone_number.sendKeys(faker.numerify("+62###########"));
        phone_number.sendKeys("+6282243671234");
        // Address
        WebElement address = driver.findElement(By.id("id_address"));
        address.sendKeys(faker.address().fullAddress()); 
        // Postal Code
        WebElement postal_code = driver.findElement(By.id("id_postal_code"));
        postal_code.sendKeys(faker.regexify("[0-9]{5}")); 
        // Province
        WebElement province_dropdown = driver.findElement(By.id("id_province"));
        Select dropdownSelect2 = new Select(province_dropdown);
        dropdownSelect2.selectByValue("2");
        // City
        WebElement city_dropdown = driver.findElement(By.id("id_city"));
        Select dropdownSelect3 = new Select(city_dropdown);
        dropdownSelect3.selectByValue("48");
        // Outlet Code
        WebElement outlet_code = driver.findElement(By.id("id_outlet_code"));
        outlet_code.sendKeys(String.valueOf(new Faker().random().hex(5)));
        // Transaction Code Prefix
        WebElement transaction_code_prefix = driver.findElement(By.id("id_transaction_code_prefix"));
        transaction_code_prefix.sendKeys(String.valueOf(new Faker().random().hex(5)));
        // Taxes
        WebElement taxes = driver.findElement(By.id("id_taxes"));
        taxes.sendKeys(faker.number().digits(1));
        // Gratuity
        WebElement gratuity = driver.findElement(By.id("id_gratuity"));
        gratuity.sendKeys(faker.number().digits(1));
        // button save
        WebElement save_button = driver.findElement(By.xpath("//*[@id=\"outletForm\"]/button"));
        save_button.click();
    }

    @Test
    public void runFunctionalTest() {
        new TestBuilder()
                .addContext(new CreateOutletTest().setNextElement(new Edge().setName("e_OpenLoginPage").build()),
                        MODEL_PATH,
                        new RandomPath(new EdgeCoverage(100)))
                .execute();
    }
    
}
