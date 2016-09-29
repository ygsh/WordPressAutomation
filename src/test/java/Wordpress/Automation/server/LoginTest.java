package Wordpress.Automation.server;

import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.yogi.wordpressServer.Pages.LoginPage;
import com.yogi.wordpressServer.setup.TestSetup;


public class LoginTest extends TestSetup
{

	@Test
	public void loadHomePage() throws IOException {
		serverDriver=getServerDriver();
		Assert.assertEquals("localblog › Log In", serverDriver.getTitle());
		
	}
	
	@Test
	@Parameters({"username","password"})
	public void loginWithValidCredentials( String username, String password) throws IOException
    {
		LoginPage lp = new LoginPage(serverDriver);
    	lp.loginWithValidCredentials(username, password);
    	Assert.assertEquals("Dashboard ‹ localblog — WordPress", serverDriver.getTitle());
    }

	@Test
	@Parameters({"invalid_username","invalid_password"})
	public void loginWithInvalidCredentials( String username, String password) throws IOException
    {
		LoginPage lp = new LoginPage(serverDriver);
    	lp.loginWithInvalidCredentials(username, password);
    	Assert.assertNotEquals("Dashboard ‹ localblog — WordPress", serverDriver.getTitle());
    }

}
