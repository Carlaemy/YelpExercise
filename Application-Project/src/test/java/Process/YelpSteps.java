package Process;



import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class YelpSteps {



	WebDriver driver = null;

	@Given("User navigates to app")
	public void user_navigates_to_app() {
		System.setProperty("webdriver.chrome.driver","/Users/carlaperez/Downloads/Chrome/ChromeDriver");
		driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://www.yelp.com/");
		driver.findElement(By.id("find_desc")).click();
		driver.findElement(By.xpath("//li[@class='item suggestion suggestions-list-item '][@data-suggest-query='Restaurants']")).click();
	}

	@When("User chosses pizza")
	public void user_chosses_pizza() {
		
		WebElement search=driver.findElement(By.id("search_description"));
	
		search.sendKeys(Keys.COMMAND+"a");
		search.sendKeys(Keys.DELETE);
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		search.sendKeys("Pizza");
		search.submit();
		System.out.println(driver.findElement(By.xpath("//div[@class=' border-color--default__09f24__NPAKY text-align--center__09f24__fYBGO']/span[@class=' css-1e4fdj9']")).getText());
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		WebElement suggested = driver.findElement(By.xpath("//div[@class=' arrange-unit__09f24__rqHTg border-color--default__09f24__NPAKY']/input[@class='input__09f24__yaqh1'][@type='checkbox'][@value='italian']"));
		boolean isSelected1 = suggested.isSelected();		
		//performing click operation if element is not checked
		if(isSelected1 == false) {
			suggested.click();
		} 
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		WebElement feature = driver.findElement(By.xpath("//div[@class=' arrange-unit__09f24__rqHTg border-color--default__09f24__NPAKY']/input[@class='input__09f24__yaqh1'][@type='checkbox'][@value='GoodForKids']"));
		boolean isSelected = feature.isSelected();		
		//performing click operation if element is not checked
		if(isSelected == false) {
			feature.click();
		} 
	}

	@Then("Results are displayed")
	public void results_are_displayed() {
		System.out.println(driver.findElement(By.xpath("//div[@class=' border-color--default__09f24__NPAKY text-align--center__09f24__fYBGO']/span[@class=' css-1e4fdj9']")).getText());

		//hacer click en el primer result y mostrar el rating star the los results (pendiente)
		//List <WebElement> listofItems = driver.findElements(By.xpath("//span[@class=' css-1uq0cfn']"));
	
		//for (int i=1; i<=listofItems.size(); i++)
		//{ 
		    
		    //Clicking on the first element 
		//	listofItems.get(i-1).click();
		      
		//} 

		WebElement select=driver.findElement(By.linkText("Stone Bridge Pizza & Salad"));
		select.click();
		LogEntries entry = driver.manage().logs().get(LogType.BROWSER);
		List<LogEntry> Logs = entry.getAll();
		for(LogEntry Log : Logs) {
		System.out.println("Logging the console logs \n" +Log);
		}
		
	      
	}


		
	}






