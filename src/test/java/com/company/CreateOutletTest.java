package com.company;

import com.github.javafaker.Faker;
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

import java.nio.file.Path;
import java.nio.file.Paths;

public class CreateOutletTest extends ExecutionContext implements CreateOutletModel{
	public final static Path MODEL_PATH = Paths.get("com/company/CreateOutletModel.json");
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
    public void v_CreateOutletPage() {
        System.out.println("v_CreateOutletPage");
        WebElement text = driver.findElement(By.tagName("h5"));
        String bodyText = text.getText();
        Assert.assertTrue(bodyText.contains("Form Outlet"));
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
    	System.setProperty("Webdriver.firefox.driver", "D:\\selenium webdriver\\geckodriver-v0.32.2-win64\\geckodriver.exe");
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
        WebElement outlet_name = driver.findElement(By.id("id_name"));
        outlet_name.sendKeys("");
        // Display Name
        WebElement display_name = driver.findElement(By.id("id_display_name"));
        display_name.sendKeys("");
        // Phone Number
        WebElement phone_number = driver.findElement(By.id("id_phone"));
        phone_number.sendKeys("");
        // Address
        WebElement address = driver.findElement(By.id("id_address"));
        address.sendKeys(""); 
        // Postal Code
        WebElement postal_code = driver.findElement(By.id("id_postal_code"));
        postal_code.sendKeys(""); 
        // Province tidak memilih
        // City tidak memilih
        // Outlet Code
        WebElement outlet_code = driver.findElement(By.id("id_outlet_code"));
        outlet_code.sendKeys("");
        // Transaction Code Prefix
        WebElement transaction_code_prefix = driver.findElement(By.id("id_transaction_code_prefix"));
        transaction_code_prefix.sendKeys("");
        // Taxes
        WebElement taxes = driver.findElement(By.id("id_taxes"));
        taxes.sendKeys("");
        // Gratuity
        WebElement gratuity = driver.findElement(By.id("id_gratuity"));
        gratuity.sendKeys("");
        // button save
        WebElement save_button = driver.findElement(By.xpath("//*[@id=\"outletForm\"]/button"));
        save_button.click();
    }
    
    @Override
    public void v_CreateOutletSuccess() {
        System.out.println("v_CreateOutletSuccess");
        WebElement text = driver.findElement(By.tagName("strong"));
        String bodyText = text.getText();
        Assert.assertTrue(bodyText.contains("Outlet successfully added"));
    }
    
    @Override
    public void v_AlertIncorrectData() {
        System.out.println("v_AlertIncorrectData");
        WebElement text = driver.findElement(By.id("error_1_id_phone"));
        String bodyText = text.getText();
        Assert.assertTrue(bodyText.contains("Enter a valid phone number (e.g. +12125552368)."));
        WebElement text2 = driver.findElement(By.id("error_1_id_postal_code"));
        String bodyText2 = text2.getText();
        Assert.assertTrue(bodyText2.contains("Invalid postal code"));
        WebElement text3 = driver.findElement(By.id("error_1_id_taxes"));
        String bodyText3 = text3.getText();
        Assert.assertTrue(bodyText3.contains("Ensure that there are no more than 2 decimal places."));
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
        WebElement text = driver.findElement(By.tagName("strong"));
        String bodyText = text.getText();
        Assert.assertTrue(bodyText.contains("This field is required."));
    }
    
    @Override
    public void v_OutletPage() {
        System.out.println("v_OutletPage");
        WebElement text = driver.findElement(By.tagName("h5"));
        String bodyText = text.getText();
        Assert.assertTrue(bodyText.contains("Outlets"));
    }

    @Override
    public void e_InputIncorrectData() {
        System.out.println("e_InputIncorrectData");
        Faker faker = new Faker();
        // Client
        WebElement dropdown = driver.findElement(By.id("id_client"));
        Select dropdownSelect = new Select(dropdown);
        dropdownSelect.selectByValue("2");
        // Outlet Name
        WebElement outlet_name = driver.findElement(By.id("id_name"));
        outlet_name.sendKeys(faker.name().fullName());
        // Display Name
        WebElement display_name = driver.findElement(By.id("id_display_name"));
        display_name.sendKeys(faker.name().fullName());
        // Invalid Phone Number 
        WebElement phone_number = driver.findElement(By.id("id_phone"));
        phone_number.sendKeys(faker.phoneNumber().cellPhone());
        // Address
        WebElement address = driver.findElement(By.id("id_address"));
        address.sendKeys(faker.address().fullAddress()); 
        // Invalid Postal Code
        WebElement postal_code = driver.findElement(By.id("id_postal_code"));
        postal_code.sendKeys(faker.number().digits(6)); 
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
        double randomNumber = faker.random().nextDouble() * 1000.0;
        System.out.println(randomNumber);
        WebElement taxes = driver.findElement(By.id("id_taxes"));
        taxes.sendKeys(String.format("%.3f", randomNumber)); 
        // Gratuity
        WebElement gratuity = driver.findElement(By.id("id_gratuity"));
        gratuity.sendKeys(String.format("%.3f", randomNumber));
        // button save
        WebElement save_button = driver.findElement(By.xpath("//*[@id=\"outletForm\"]/button"));
        save_button.click();
    }
    
    @Override
    public void e_InputCorrectData() {
        System.out.println("e_InputCorrectData");
        Faker faker = new Faker();
        // Client
        WebElement dropdown = driver.findElement(By.id("id_client"));
        Select dropdownSelect = new Select(dropdown);
        dropdownSelect.selectByValue("2");
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
