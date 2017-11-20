package com.flowDrive.scripts;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import com.flowDrive.initialize.AndroidDriverr;
import com.flowDrive.pages.HomePage;
import com.flowDrive.pages.LoginPage;
import com.flowDrive.pages.RegisterPage;
import com.helpers.Helpers;

public class WorkFlow1_Test {
	LoginPage loginPage;
	RegisterPage registerPage;
	HomePage homePage;
	WebDriverWait wait;

	@BeforeClass
	public void beforeClass() {
		loginPage = new LoginPage();
		registerPage = new RegisterPage();
		homePage = new HomePage();
		wait = new WebDriverWait(AndroidDriverr.driver, 10);
	}

	@Test
	public void loginTest() {
		try {
			loginPage.agreeTerms.click();
			loginPage.userName.sendKeys("aaa@gmail.com");
			loginPage.password.sendKeys("abcd1234");
			loginPage.loginButton.click();
			wait.until(ExpectedConditions.visibilityOf(registerPage.welcomeMessageClose));
			registerPage.welcomeMessageClose.click();
		} catch (Exception e) {
			Assert.fail("Error in login testcase");
		}
	}

	@Test
	public void registerTest() {
		try {
			loginPage.agreeTerms.click();
			registerPage.createAccount.click();
			registerPage.firstName.sendKeys(Helpers.randomString(6));
			registerPage.surName.sendKeys(Helpers.randomString(5));
			registerPage.dateOfBirth.sendKeys("11/11/1990");
			Thread.sleep(2000);
			AndroidDriverr.driver.findElement(By.xpath("//android.widget.Button[@index='0']")).click();
			Thread.sleep(2000);
			registerPage.dateOfBirthOkBtn.click();
			registerPage.emailAdress.sendKeys("teju4p@gmail.com");
			registerPage.postCode.sendKeys("12345");
			registerPage.companyName.sendKeys(Helpers.randomString(3));
			registerPage.password.sendKeys("abcd1234");
			registerPage.repeatPassword.sendKeys("abcd1234");
			Assert.assertTrue(
					registerPage.registerSucess.getText().trim().equals("Registration successful, please login."));
		} catch (Exception e) {
			Assert.fail("Error in register testcase");
		}

	}

	@Test
	public void startJourney() {
		try {
			loginTest();
			homePage.homeButton.click();
			homePage.startJourney.click();
			wait.until(ExpectedConditions.visibilityOf(homePage.cancelJourney));
			Thread.sleep(3000);
			homePage.cancelJourney.click();
		} catch (Exception e) {
			Assert.fail("Error in login testcase");
		}
	}

	@AfterClass
	public void afterMethod() {
		AndroidDriverr.driver.closeApp();
	}

}
