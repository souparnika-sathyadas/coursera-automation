package pageObjects;


import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utilities.ExcelManager;

public class Courses extends BasePage{

	Pattern pattern;
	Matcher matcher;
	String[] matches;
	
	ExcelManager et = new ExcelManager();
	// Constructor
	public Courses(WebDriver driver) {
		super(driver);
	}
	
	// Locators
	@FindBy(xpath="//input[starts-with(@placeholder,'What do you')]")
	WebElement inputBox_loc;
	
	@FindBy(xpath="//button[@class='nostyle search-button']")
	WebElement searchBtn_loc;
	
	@FindBy(xpath="//button[@aria-label='Show more Language options']")
	WebElement showMore_loc;
	
	@FindBy(xpath="//div[@id='checkbox-group']//div[@class='cds-checkboxAndRadio-labelText']//span")
	List<WebElement> allLang_loc;
	
	@FindBy(xpath="(//button[@class='cds-149 cds-button-disableElevation cds-button-primary css-1wio7h1'])[1]")
	WebElement apply_loc;
	
	@FindBy(xpath="//div[@data-testid=\"search-filter-group-Level\"]//label[@class=\"cds-checkboxAndRadio-label\"]/div[@class=\"cds-checkboxAndRadio-labelText\"][1]")
	WebElement beginner_loc;
	
	@FindBy(xpath="//div[@class='cds-ProductCard-content']//h3")
	List<WebElement> courseName_loc;
 	
	@FindBy(xpath="//p[@class=\"cds-119 css-11uuo4b cds-121\"][1]")
	List<WebElement> courseRating_loc;
	
	@FindBy(xpath="//div[@class=\"cds-CommonCard-metadata\"]")
	List<WebElement> courseDuration_loc;
	
	// Actions
	//Search For Courses
	JavascriptExecutor js = (JavascriptExecutor)driver;	
	public void setSearchBox(String webdev) {
		inputBox_loc.sendKeys(webdev);
	}
	
	public void clickSearchBtn() {
		js.executeScript("arguments[0].click()", searchBtn_loc);
	}
	
	public void clickOnShowMore()
	{
		js.executeScript("arguments[0].click()", showMore_loc);		
		// showMore_loc.click();
	}
	
	public void selectLanguage() {
		for(WebElement language : allLang_loc) {
			String text = language.getText();
			pattern = Pattern.compile("English");
			matcher = pattern.matcher(text);
			if(matcher.find()){
				js.executeScript("arguments[0].click()", language);				
			}
		}
	}
	
	public void clickOnApplyBtn()
	{
		js.executeScript("arguments[0].click()", apply_loc);
	}
	
	public void clickOnBeginnerLoc()
	{
		js.executeScript("arguments[0].click()", beginner_loc);
	}
	
	public void getCourseDetails() throws IOException
	{	//setting headings 
		et.setCellData("test_case_1", 0, 0, "Course Name");
		et.setCellData("test_case_1", 0, 1, "Rating");
		et.setCellData("test_case_1", 0, 2, "Learning Hours");

		int row=1;
		for(int i=0; i<2; i++)
		{
			//get Course Name
			String courseName = courseName_loc.get(i).getText();
			et.setCellData("test_case_1", row, 0, courseName);
			//get rating 
			String ratingString = courseRating_loc.get(i).getText();
			Double ratingInt = Double.parseDouble(ratingString);
			et.setCellData("test_case_1", row, 1, ratingString);

			System.out.println("Course name : "+courseName);
			System.out.println("Rating : "+ ratingInt);
			//Get Duration of Course
			String duration = courseDuration_loc.get(i).getText();
			pattern = Pattern.compile(".·.");
			matches = pattern.split(duration);
			System.out.println("Duration: " + matches[2].trim());
			et.setCellData("test_case_1", row, 2, matches[2].trim());
			row++;
			System.out.println("------------------------------------");
		}	
	}
}
