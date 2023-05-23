package com.tekarch;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SeleniumTest2 {

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


		driver.close();
	}

}
