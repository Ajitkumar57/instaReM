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
	 * OpenBrowser method will open browser and URL 
	 * 
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
	 * The verifyHomepage method test case will check images are displayed on Home page 
	 * list of these images and prints its HREF attribute.  
	 *
	 */
	@Test(priority=0)
    public void verifyHomepage() 
    {    
        String expectedTitle = "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle);
        //List of Images in the Home Page 
        List<WebElement> listImages=driver.findElements(By.tagName("img"));
        System.out.println("No.of Images: "+listImages.size()); 
        List<WebElement> list=driver.findElements(By.xpath("//*[@href ]"));

        for(WebElement e : list)
        {
            String link = e.getAttribute("href");
            System.out.println(link);
        }      
    }
	
	 /*
	  * 
	  * The verifyBanknames method test case will print list of malaysia's bank name and swift codes 
	  * from the given page.
	  * 
	  */ 
	@Test(priority=1)
    public void verifyBanknames() throws AWTException 
    {  
		//Robot class open new tab into the browser and getWindowHandles handles the current window 
		Robot robot = new Robot();                          
		robot.keyPress(KeyEvent.VK_CONTROL); 
		robot.keyPress(KeyEvent.VK_T); 
		robot.keyRelease(KeyEvent.VK_CONTROL); 
		robot.keyRelease(KeyEvent.VK_T);
		Set<String> handles = driver.getWindowHandles();
		String currentHandle = driver.getWindowHandle();
		    for (String handle : handles) 
		   {
		     if (!handle .equals(currentHandle))
		     {
		         driver.switchTo().window(handle);
		         driver.get("https://www.theswiftcodes.com/malaysia/");  
		         WebElement ele = driver.findElement(By.xpath("//table/tbody"));
		         List<WebElement> column =driver.findElements(By.xpath(".//table/tbody/tr/td[2]"));
		         System.out.println("----------***********-----------");
		         System.out.println("Number Of Bank Name = "+ column .size()); 
		    
		               for (WebElement tdElement : column )
			              {	
				             System.out.println(tdElement.getText());   
			              }  
		               
		          List<WebElement> column1 =driver.findElements(By.xpath(".//table/tbody/tr/td[5]"));
		          System.out.println("----------***********-----------");
		          System.out.println("Number Of Swift Code = "+ column1 .size());
		    
		               for (WebElement tdElement : column1 )
			              {  
				             System.out.println(tdElement.getText());
		                  }  		               
		      }      	  	     	       
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
