package com.ajio;

import java.time.Duration;

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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Kids {
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
		
		driver.get(link);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		String currenturl = driver.getCurrentUrl();
		Assert.assertEquals(currenturl,link);
		
		
	}
	@AfterClass(groups="common")
	public void close_The_Application()
	{
		driver.quit();
		
	}
	@BeforeMethod(groups="common")
	public void before_Method() {
		System.out.println("BEFORE METHOD");
	}
	@AfterMethod(groups="common")
	public void after_Method() {
		System.out.println("AFTER METHOD");
	}
	@Test(priority = 0, groups = "regression")
	public void click_On_Kids() {
		System.out.println("TEST");
		WebElement Kids = driver.findElement(By.xpath("//a[text() = 'Kids' and @class = 'desktop-main']"));
		String text = Kids.getText();
		Kids.click();
		System.out.println(text);
		if(text.equals("KIDS")) {
			System.out.println("Clicked on Kids" );
			
		}
		else {
			System.out.println("Not Clicked on Kids"+text);
		}
	}
	@DataProvider(name ="link")
	public Object [][] elements(){
		return new Object[][] {{"Allensolly junior"},{"Nautinati"}};
		
	}
	@Test(priority = 1, dataProvider = "link", groups = "sanity")
	public void validate_Brand_Element(String brand) throws InterruptedException {
		WebElement ele = driver.findElement(By.xpath("//img[@src = 'https://assets.myntassets.com/w_108,c_limit,fl_progressive,dpr_2.0/assets/images/2020/11/9/ab69d015-a975-4bda-990c-49e68938f6b31604926460802-19-FavBrands-AllenSollyJuniors.jpg']"));
		ele.click();
		System.out.println(brand);
		Thread.sleep(2000);
		driver.navigate().back();
		
	}
	@Test(priority = 2, dataProvider = "link", groups = "sanity")
	public void validate_Brand(String anotherBrand) {
		WebElement ele2 = driver.findElement(By.xpath("//img[@src = 'https://assets.myntassets.com/w_108,c_limit,fl_progressive,dpr_2.0/assets/images/2020/11/9/4d3f0e9d-1b7a-458e-b05b-75d1f51db0411604926460628-15-FavBrands-NautiNati.jpg']"));
		ele2.click();
		System.out.println(anotherBrand);
		
	}
	
}
