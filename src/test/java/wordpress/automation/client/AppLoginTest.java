package wordpress.automation.client;

import java.net.MalformedURLException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.yogi.wordpressClient.pages.LoginPage;
import com.yogi.wordpressServer.setup.TestSetup;

import io.appium.java_client.android.AndroidDriver;

public class AppLoginTest extends TestSetup{
	
	private static AndroidDriver clientDriver=null;
	
	@BeforeClass
	public void setUp() throws MalformedURLException{
	clientDriver=getClientDriver();
	}
	
	@Test(dataProvider="getData")
	public void test(String username, String password, String url){
		LoginPage lp= new LoginPage(clientDriver);
		lp.updateSelfHosted(url);
		lp.enterUsername(username);
		lp.enterPassword(password);
		lp.signIn();
	}
	
	@DataProvider
	public Object[][] getData(){
		Object [][] data= new Object[1][3];
		data[0][0]="yogi";
		data[0][1]="welcome";
		data[0][2]="http://192.168.1.8/xmlrpc.php";	
		return data;
	}

	
	@AfterClass
	public static void tearDownClient(){
		clientDriver.quit();
	}
}
