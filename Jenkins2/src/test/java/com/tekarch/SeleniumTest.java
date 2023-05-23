package com.tekarch;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SeleniumTest {

	@Test
	public void script1() throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		//WebDriverManager.edgedriver().setup();
		 WebDriver driver=new ChromeDriver();
		//WebDriver driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.get("https://qa-tekarch.firebaseapp.com/");
		// Thread.sleep(10000);

		By emailField = By.id("email_field");
		WebElement email = driver.findElement(emailField);
		email.sendKeys("admin123@gmail.com");

		By passField = By.id("password_field");
		WebElement password = driver.findElement(passField);
		password.sendKeys("admin123");

		By loginField = By.xpath("//*[@id=\"login_div\"]/button");
		WebElement login = driver.findElement(loginField);
		login.click();
		// Driver.findElement(By.xpath("//a[text()='App Configuration']")).click();

		System.out.println("clicking login button");

		Thread.sleep(4000);
		System.out.println("-----------");

		By nameField = By.id("name");
		WebElement name = driver.findElement(nameField);
		name.sendKeys("Janani");

		System.out.println("8888888888888");

		By fatherNameField = By.name("lastname");
		WebElement fatherName = driver.findElement(fatherNameField);
		fatherName.sendKeys("Ram");

		By postal = By.id("postaladdress");
		WebElement postalAddr = driver.findElement(postal);
		postalAddr.sendKeys("662 crudent");

		By personal = By.id("personaladdress");
		WebElement personalAddr = driver.findElement(personal);
		personalAddr.sendKeys("672 crudent");

		WebElement radio1 = driver.findElement(By.xpath("//*[@id=\"radiobut\"]"));
		// WebElement radio2 = driver.findElement(By.xpath("//*[@id=\"radiobut\"]"));
		WebElement radio2 = driver.findElement(By.xpath("/html/body/div[2]/div[2]/form/div[5]/span[2]/input"));

		// Radio Button1 is selected
		radio1.click();
		System.out.println("Radio Button Option 1 Selected");

		// Radio Button1 is de-selected and Radio Button2 is selected
		radio2.click();
		System.out.println("Radio Button Option 2 Selected");

		System.out.println("-----------------");

		Select drpCountry = new Select(driver.findElement(By.name("city")));
		drpCountry.selectByVisibleText("MUMBAI");

		System.out.println("city selected");

		driver.close();
	}

}
