package DevOpsFinalAssignment.DevOpsFinalAssignment;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class BrokenLinksValidation {

	public static void main(String[] args) throws MalformedURLException, IOException {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\driverFiles\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://www.newtours.demoaut.com/");

		WebElement userNameInputBox = driver.findElement(By.name("userName"));
		userNameInputBox.sendKeys("naresh38");

		WebElement passwordInputBox = driver.findElement(By.name("password"));
		passwordInputBox.sendKeys("password");

		WebElement signInButton = driver.findElement(By.name("login"));
		signInButton.click();

		List<WebElement> allAnchorTags = driver.findElements(By.tagName("a"));
		List<String> allLinks = new ArrayList<String>();
		for(WebElement e:allAnchorTags){
			allLinks.add(e.getAttribute("href"));
		}
		
		for(String s:allLinks){
			HttpURLConnection connection = (HttpURLConnection) new URL(s).openConnection();
			connection.connect();
			String response = connection.getResponseMessage();    
			System.out.println(response);
		}
		
	}
}
