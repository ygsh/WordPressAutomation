package com.yogi.wordpressServer.Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class HomePage {

	private WebDriver driver=null;
	
	public HomePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//*[@id='menu-posts']/a/div[3]")
	WebElement postLink;
//Using List of elements
//	public AllPostsPage goToAllPosts(){
//		List<WebElement> menuItems = serverDriver.findElements(By.className("wp-menu-name"));
//		menuItems.get(1).click();
//		Assert.assertEquals("Posts ‹ localblog — WordPress", serverDriver.getTitle());
//		return new AllPostsPage(serverDriver);  
//	}
//	
	public AllPostsPage goToAllPosts(){
		postLink.click();
		Assert.assertEquals("Posts ‹ localblog — WordPress", driver.getTitle());
		return new AllPostsPage(driver);  
	}
	
	
	public NewPostPage createNewPost(){
		List<WebElement> menuItems = driver.findElements(By.className("wp-menu-name"));
		menuItems.get(1).click();
		//WebDriverWait wait = new WebDriverWait(serverDriver,10);
		//wait.until(ExpectedConditions.visibilityOf(serverDriver.findElement(By.id("search-submit"))));
		driver.findElement(By.linkText("Add New")).click();
		//wait.until(ExpectedConditions.visibilityOf(serverDriver.findElement(By.id("title"))));
		return new NewPostPage(driver);
	}
	
}
