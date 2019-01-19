package instaReM;
/* 
 * 
 * Author : AjitkumarSwami
 * 
 *  
 */
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
public class Ecommerce 

{
    public String baseUrl = "https://www.amazon.in/";
    String driverPath = "input/chromedriver1.exe";
    public WebDriver driver ;  

     /*
      * 
	  * openBrowser method will open browser and URL 
	  * maximize the browser.
	  */
    
    @BeforeTest
    public void openBrowser() 
    {
        System.out.println("Launching Chorme Browser"); 
        System.setProperty("webdriver.chrome.driver", driverPath);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(baseUrl);
     }
    
     /*
	  * The verifyHomepage method Test Case will check images are displayed on Home page 
	  * list of these images and prints its HREF attribute.  
	  *
	  */
    
	@Test(priority=0)
    public void verifyHomepage() 
    {    
        String expectedTitle = "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle);
        //List of Images in the Home Page using src attribute
        List<WebElement> image=driver.findElements(By.tagName("img"));
        System.out.println("Number of Images :"+image.size());
		 for(int i =0; i <image.size();i++)
		 {	  
			 if (image.get(i).getAttribute("src") != null)
			  System.out.println(image.get(i).getAttribute("src"));
         }      
    }
	
	 /*
	  * 
	  * The verifyBanknames method Test Case will print list of malaysia's bank name and swift codes 
	  * from the given page.
	  * 
	  */ 
	
	@Test(priority=1)
    public void verifyBanknames() throws AWTException 
    {  
		driver.get("https://www.theswiftcodes.com/malaysia/");  
		WebElement ele = driver.findElement(By.xpath("//table/tbody"));
		List<WebElement> column =driver.findElements(By.xpath(".//table/tbody/tr/td[2]"));
		   for (WebElement tdElement : column )
			     {	
				    System.out.println(tdElement.getText());   
			     }  
		               
		List<WebElement> column1 =driver.findElements(By.xpath(".//table/tbody/tr/td[5]"));
		   for (WebElement tdElement : column1 )
			     {  
				    System.out.println(tdElement.getText());
		         }  		               
	 } 
	
	/*
	 * 
	 * After Test will quit browser once all test cases executed in the same class
	 * 
	 */
	
	@AfterTest
    public void terminateBrowser()
    {
        driver.quit();
    } 
}
