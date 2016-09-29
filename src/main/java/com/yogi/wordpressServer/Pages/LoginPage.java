package com.yogi.wordpressServer.Pages;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class LoginPage {

	public WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="user_login")
	WebElement username;
	
	@FindBy(id="user_pass")
	WebElement password;
	
	@FindBy(id="wp-submit")
	WebElement submittButton;
	
	public void enterUsername(String usernameValue){
		username.sendKeys(usernameValue);
	}

	public void enterPassword(String passwordValue){
		password.sendKeys(passwordValue);
	}
	
	public HomePage loginWithValidCredentials( String username, String password) throws IOException
    {
		LoginPage lp = new LoginPage(driver);
    	lp.enterUsername(username);
    	lp.enterPassword(password);
    	HomePage hp = lp.submitLogin();
    	Assert.assertEquals("Dashboard ‹ localblog — WordPress", driver.getTitle());
		return hp;
    }
	
	public void loginWithInvalidCredentials( String username, String password) throws IOException
    {
		LoginPage lp = new LoginPage(driver);
    	lp.enterUsername(username);
    	lp.enterPassword(password);
    	lp.submitLogin();
    	Assert.assertNotEquals("Dashboard ‹ localblog — WordPress", driver.getTitle());
    }
	
	public HomePage submitLogin(){
	submittButton.click();
	return new HomePage(driver);
	}


}
