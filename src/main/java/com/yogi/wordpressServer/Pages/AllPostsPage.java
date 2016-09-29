package com.yogi.wordpressServer.Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByLinkText;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import junit.framework.Assert;

public class AllPostsPage {

	public WebDriver driver=null;
	
	public AllPostsPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="bulk-action-selector-top")
	WebElement dropDownMenu;
	
	@FindBy(id="post-search-input")
	WebElement searchPosts;
	
	@FindBy(id="search-submit")
	WebElement searchPostButton;
	
	@FindBy(xpath="//*[@id='posts-filter']/table")
	WebElement allPostTable;
	
	/**
	 * For drop downs we need Select class, the reference variable in instantiated with Select object taking
	 * the WebElement drop down. This Webelement has further childs in the form of entries.
	 * @param value
	 * @return
	 */
	public String selectDropDownByVisibleText(String value){
		Select dropDown=new Select(dropDownMenu);
		dropDown.selectByVisibleText(value);
		String optionSelected = dropDown.getFirstSelectedOption().getText();
		return optionSelected;
	}
	
	public void goToPost(String title){
		searchPosts.sendKeys(title);
		searchPostButton.click();
		driver.findElement(By.linkText("http://localhost/wp-admin/post.php?post=7&action=edit"));
	}
	
	public WebElement getAllPostDetails(){
		return allPostTable;
	}
	

}
