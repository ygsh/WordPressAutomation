package Wordpress.Automation.server;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.yogi.wordpressServer.Pages.AllPostsPage;
import com.yogi.wordpressServer.Pages.HomePage;
import com.yogi.wordpressServer.Pages.LoginPage;
import com.yogi.wordpressServer.setup.TestSetup;

public class AllPostsTest extends TestSetup {

	@BeforeMethod
	public void loadHomePage() throws IOException {
		serverDriver=getServerDriver();
		
	}
	
	@Test
	@Parameters({"username","password"})
	public void selectEditFromDropDown(String username, String password) throws IOException, InterruptedException{
		LoginPage lp = new LoginPage(serverDriver);
		HomePage hp = lp.loginWithValidCredentials(username, password);
		AllPostsPage allPosts = hp.goToAllPosts();
		String selectedValue=allPosts.selectDropDownByVisibleText("Edit");
		Assert.assertEquals(selectedValue,"Edit");
	}
	
	@Test
	@Parameters({"username","password"})
	public void selectTrashFromDropDown(String username, String password) throws IOException, InterruptedException{
		LoginPage lp = new LoginPage(serverDriver);
		HomePage hp = lp.loginWithValidCredentials(username, password);
		AllPostsPage allPosts = hp.goToAllPosts();
		String selectedValue=allPosts.selectDropDownByVisibleText("Move to Trash");
		Assert.assertEquals(selectedValue,"Move to Trash");
	}
	
	@Test
	@Parameters({"username","password"})
	public void selectBulkOptionsFromDropDown(String username, String password) throws IOException, InterruptedException{
		LoginPage lp = new LoginPage(serverDriver);
		HomePage hp = lp.loginWithValidCredentials(username, password);
		AllPostsPage allPosts = hp.goToAllPosts();
		String selectedValue=allPosts.selectDropDownByVisibleText("Bulk Actions");
		Assert.assertEquals(selectedValue,"Bulk Actions");
	}
	@Test
	@Parameters({"username","password"})
	public void getAllPostDetails(String username, String password) throws IOException, InterruptedException{
		LoginPage lp = new LoginPage(serverDriver);
		HomePage hp = lp.loginWithValidCredentials(username, password);
		AllPostsPage allPosts = hp.goToAllPosts();
		//We get the complete table with below command.
		WebElement postTable=allPosts.getAllPostDetails();
		//Now we need to pick each row from table as below.
		List<WebElement> tableRows=postTable.findElements(By.tagName("tr"));
//		for (WebElement webElement : postTable) {
//			System.out.println(webElement.getText());
//		}
		for (int i = 0; i < tableRows.size(); i++) {
			if(i==3){
				List<WebElement> tableColumn=tableRows.get(i).findElements(By.tagName("td"));
				System.out.println("The data in third row is :");
				for (WebElement webElement : tableColumn) {
					System.out.println(webElement.getText());
				}
			}
		}
	}
}
