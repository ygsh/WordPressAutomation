package Wordpress.Automation.server;

import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.yogi.wordpressServer.Pages.AllPostsPage;
import com.yogi.wordpressServer.Pages.HomePage;
import com.yogi.wordpressServer.Pages.LoginPage;
import com.yogi.wordpressServer.Pages.NewPostPage;
import com.yogi.wordpressServer.setup.TestSetup;

public class NewPostTest extends TestSetup {

	@BeforeMethod
	public void setUp(){
		serverDriver = getServerDriver();
	}
	
	@Test
	@Parameters({"username","password"})
	public void createNewPost(String username, String password) throws IOException{
		LoginPage lp = new LoginPage(serverDriver);
		HomePage hp = lp.loginWithValidCredentials(username, password);
		NewPostPage np=hp.createNewPost();
		np.enterTitle("TEST POST1");
		np.enterPost("I am doing OK today");
		np.savePost();
	}
	
	@Test
	@Parameters({"username","password"})
	public void createNewPostInCaps(String username, String password) throws IOException{
		LoginPage lp = new LoginPage(serverDriver);
		HomePage hp = lp.loginWithValidCredentials(username, password);
		NewPostPage np=hp.createNewPost();
		np.enterTitleInCaps("test post2");
		np.enterPostInCaps("I am doing ok today");
		np.savePost();
	}
//	@Test
//	@Parameters({"username","password"})
//	public void deletePost(String username, String password) throws IOException{
//		LoginPage lp = new LoginPage(serverDriver);
//		HomePage hp = lp.loginWithValidCredentials(username, password);
//		AllPostsPage allp=hp.goToAllPosts();
//		allp.goToPost('"'+"TEST POST1"+'"');
//
//	}
//	
}
