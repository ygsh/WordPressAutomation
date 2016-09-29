package Wordpress.Automation.server;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Maths {

	
	@Test
	@Parameters({"a","b"})
	public void addNumbersFromXML(int a, int b){
		int c=a+b;
		System.out.println(c);
	}
	
	@Test
	@Parameters({"a","b"})
	public void addNumbersFromXMLMethod(int a, int b){
		int c=a+b;
		System.out.println(c);
	}
	
	@Test(dataProvider="test2")
	public void addNumbersFromDp(int a, int b){
		int c=a+b;
		System.out.println(c);
	}
	
	@DataProvider(name="test2")
	public Object[][] getNumbers(){
		Object [][]data=new Object[2][2];
		data[0][0]=1;
		data[0][1]=2;
		data[1][0]=10;
		data[1][1]=20;
		return data;
	}
}
