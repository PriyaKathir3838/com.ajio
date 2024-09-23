package com.ajio;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.ajio.rerun.RetryAnalyzer;

import io.github.bonigarcia.wdm.WebDriverManager;

public class women {
static WebDriver driver;
@Parameters({"URL","browser"})

@BeforeClass(groups="common")
public void launch_Application(String link, String drive) {
	if(drive.equals("chrome")) {
	WebDriverManager.chromedriver().setup();
	driver = new ChromeDriver();
	}
	else
	{
    WebDriverManager.edgedriver().setup();
	driver = new EdgeDriver();
		
	}
	String url = link;
	driver.get(url);
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	String currenturl = driver.getCurrentUrl();
	Assert.assertEquals(currenturl, url);
	
}
@AfterClass(groups="common")
public void close_The_Application() {
	driver.quit();
}
@BeforeMethod(groups="common")
public void before() {
	System.out.println("BEFORE METHOD");
}
@AfterMethod(groups="common")
public void after() {
	System.out.println("AFTER METHOD");
}
@Test(priority = -1, groups = "regression")
public void Click_On_Women() {
	WebElement women = driver.findElement(By.xpath("//a[text() = 'Women' and @class = 'desktop-main']"));
	String text = women.getText();
	women.click();
	System.out.println(text);
	SoftAssert soft = new SoftAssert();
	soft.assertEquals(text, "Women","Assertion Failure");
	if(text.equals("WOMEN")) {
		System.out.println("clicked on Women"+text);
	}
	else {
		System.out.println("Not clicked on Women"+text);
	}
	soft.assertAll();
}

@Test(priority = 1, groups = "regression")

public void Validate_Women_Page() {

    List<WebElement> headers = driver.findElements(By.xpath("//h4[@class = 'text-banner-title']"));
    List<String> topic = new ArrayList<>();
    topic.add("BIGGEST DEALS ON TOP BRANDS");
    topic.add("CATEGORIES TO BAG");
    topic.add("EXPLORE TOP BRANDS");
    topic.add("TRENDING IN WESTERN WEAR");
    topic.add("TRENDING IN INDIAN WEAR");
    topic.add("TRENDING IN SPORTS WEAR");
    topic.add("TRENDING IN FOOTWEAR");
    topic.add("TRENDING IN ACCESSORIES");
    for(int i=0;i<headers.size();i++) {
    	WebElement heading = headers.get(i);
    	String Actualheading = heading.getText();
    	String Expectedheading = topic.get(i);
    	Assert.assertEquals(Actualheading,Expectedheading);
    }
    
	
}
@Test(priority = 2, groups = "sanity")

public void click_On_BIBA() {
	WebElement bibo = driver.findElement(By.xpath("//img[@src ='https://assets.myntassets.com/w_196,c_limit,fl_progressive,dpr_2.0/assets/images/2020/8/31/814b8ff4-1dec-4a6e-86b8-c26f5c40fe4c1598892518923-Biba.jpg']"));
	bibo.click();
}
@Test(priority = 3, groups = "sanity")
public void validate_BIBA_Productpage() {
	List<WebElement> products = driver.findElements(By.tagName("h3"));
	for(int i =0;i<products.size();i++) {
		String Product = products.get(i).getText();
		Assert.assertEquals(Product, "Biba");
		if(Product.equals("Biba")) {
			System.out.println("Biba Related Product Is Displayed");
			
		}
		else {
			System.out.println("Irrelevant Product Is Displayed");
		}
		
		
	}
}
@Test(priority =4, invocationCount = 3, groups = "sanity")
public void nextpage_on_BIBA() {
	WebElement next = driver.findElement(By.xpath("//li[@class = 'pagination-next']"));
	next.click();
	
	
}

}
