package com.yogi.wordpressServer.Pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NewPostPage {
	
	public WebDriver driver=null;
	public NewPostPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="title")
	WebElement titleText;
	
	@FindBy(id="tinymce")
	WebElement postContent;
	
	@FindBy(id="publish")
	WebElement publish;
	
	public void enterTitle(String title){
		titleText.sendKeys(title);
	}
	
	public void enterTitleInCaps(String title){
		Actions action = new Actions(driver);
		action.keyDown(Keys.SHIFT).moveToElement(titleText).sendKeys(title).build().perform();
	}
	
	//To post to a iFrame, first select the frame using swithTo and then identify the webelement and 
	//use normal send keys.
	
	public void enterPost(String post){
		driver.switchTo().frame("content_ifr");
		postContent.sendKeys(post);
		driver.switchTo().defaultContent();	//Go back to the main window
	
	}
	
	public void enterPostInCaps(String post){
		driver.switchTo().frame("content_ifr");
		Actions action = new Actions(driver);
		action.keyDown(Keys.SHIFT).moveToElement(postContent).sendKeys(post).build().perform();
		driver.switchTo().defaultContent();	//Go back to the main window
	
	}
	public void savePost(){
		//Adding a wait below so that publish button becomes visible after switching the frame.
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(publish));
		publish.click();	
	}
}
