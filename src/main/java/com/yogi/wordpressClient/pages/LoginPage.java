package com.yogi.wordpressClient.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class LoginPage {

	private AndroidDriver clientDriver=null;
	
	public LoginPage(AndroidDriver clientDriver){
		this.clientDriver=clientDriver;
		PageFactory.initElements(new AppiumFieldDecorator(clientDriver),this);	
	}
	
	@FindBy(id="org.wordpress.android:id/nux_username")
	MobileElement usernameField;
	
	@FindBy(id="org.wordpress.android:id/nux_password")
	MobileElement passwordField;
	
	@FindBy(id="org.wordpress.android:id/nux_sign_in_button")
	MobileElement signInButton;
	
	@FindBy(id="org.wordpress.android:id/nux_add_selfhosted_button")
	MobileElement selfHostedButton;
	
	@FindBy(id="org.wordpress.android:id/nux_url")
	WebElement selfHostedText;
	
	public void updateSelfHosted(String url){
		WebDriverWait wait=new WebDriverWait(clientDriver, 30);
		wait.until(ExpectedConditions.visibilityOf(usernameField));
		selfHostedButton.click();
		selfHostedText.sendKeys(url);
	}
	
	public void enterUsername(String username){
		usernameField.sendKeys(username);
	}
	
	public void enterPassword(String password){
		passwordField.sendKeys(password);
	}
	
	public void signIn(){
		signInButton.click();
		WebDriverWait wait=new WebDriverWait(clientDriver,60);
		wait.until(ExpectedConditions.visibilityOf(clientDriver.findElement(By.id("org.wordpress.android:id/my_site_title_label"))));
	}
}
