package pageObjects;
 
 
import java.io.IOException;

import java.util.ArrayList;

import java.util.HashSet;

import java.util.List;

import java.util.Set;

import java.util.regex.Matcher;

import java.util.regex.Pattern;
 
import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;
 
 
import utilities.ExcelManager;
 
public class LanguageLearning extends BasePage {
 
	ExcelManager excelutility = new ExcelManager();

	// Constructor

	public LanguageLearning(WebDriver driver) {

		super(driver);

	}

	// Locators

	@FindBy(xpath="//a[@aria-label='Coursera']")

	WebElement logoClick_loc;

	@FindBy(xpath="//input[starts-with(@placeholder,'What do you')]")

	WebElement inputBox_loc;

	@FindBy(xpath="//button[@class=\"nostyle search-button\"]")

	WebElement searchBtn_loc;

	@FindBy(xpath="//button[@aria-label='Show more Language options']")

	WebElement showMore_loc;

	//div[@id=\\\"checkbox-group]//input)

	@FindBy(xpath="//div[@id='checkbox-group']//div[@class='cds-checkboxAndRadio-labelText']")

	List<WebElement> allLang_loc;

	@FindBy(xpath="//div[@aria-label='Select Language options']/div/label/div/span")

	List<WebElement> AllLanguages;

	@FindBy(xpath="(//button[@class='cds-149 cds-button-disableElevation cds-button-primary css-1wio7h1'])[1]")

	WebElement apply_loc;

	@FindBy(xpath="//div[@data-testid=\"search-filter-group-Level\"]//div[@class=\"cds-checkboxAndRadio-labelText\"]")

	List<WebElement> allLevel_loc;

	@FindBy(xpath="//div[@class=\"rc-SearchResultsHeader\"]//span")

	WebElement totalCount_loc;

	@FindBy(xpath="//span[normalize-space()='Clear All']")

	WebElement clearAllBtn_loc;

	@FindBy(xpath="//span[normalize-space()='Close']//span[@class='cds-button-endIcon']")

	WebElement closeBtn_loc;


	// Actions

	JavascriptExecutor js = (JavascriptExecutor)driver;

	public void basicOperations(String langlearn)

	{

		// Click on Logo

		logoClick_loc.click();

		// Clear existing text in input Box

		inputBox_loc.click();

		js.executeScript("arguments[0].value='';",inputBox_loc);


		// Pass Language Learning in input Box

		inputBox_loc.sendKeys("Language Learning");

		// Click on Search Button

		js.executeScript("arguments[0].click()",searchBtn_loc);

	}


	public String storeonlyCharacter(String cLang) {

		String result="";

		for(int i=0;i<cLang.length();i++) {

			if(cLang.charAt(i)>='a'&&cLang.charAt(i)<='z'||cLang.charAt(i)>='A'&&cLang.charAt(i)<='Z') {

				result += cLang.charAt(i);

			}

		}

		return result;

	}

	public Set<String> ExtractFiveLanguage() {

		js.executeScript("arguments[0].click()",showMore_loc);

		Set<String> langs = new HashSet<>();

//		int i=0;

		for(int i=0;i<5;i++) {

			String value = storeonlyCharacter(AllLanguages.get(i).getText());

			langs.add(value.trim());

		}

		//System.out.println(langs);

		js.executeScript("arguments[0].click()", closeBtn_loc); //close btn

		return langs;

	}

	public void getCourseCount() throws InterruptedException, IOException

	{

		List<String> language = new ArrayList<>(ExtractFiveLanguage());

		int row=1;

		int subRow = 1;
		
		excelutility.setCellData("test_case_2", 0, 0, "Language");
		excelutility.setCellData("test_case_2", 0, 1, "Level");
		excelutility.setCellData("test_case_2", 0, 2, "Count");
 
		for(int i=0;i<5;i++)

		{

			Thread.sleep(2000);

			js.executeScript("arguments[0].click()",showMore_loc);

			Thread.sleep(2000);

			String store="";

			for(WebElement ele: AllLanguages)

			{

				store = storeonlyCharacter(ele.getText()).trim();

				if(store.equalsIgnoreCase(language.get(i)))

				{

					ele.click();

					Thread.sleep(3000);

					System.out.println("Language : "+ele.getText());

					excelutility.setCellData("test_case_2", row, 0, ele.getText());

					apply_loc.click();

					Thread.sleep(3000);

					for(WebElement level : allLevel_loc) {

						System.out.println("--------------------------------------------");

						js.executeScript("arguments[0].click()", level);  // Check the checkBox

						Thread.sleep(3000);

						System.out.println(level.getText());

						excelutility.setCellData("test_case_2", subRow, 1, level.getText());

						String count = totalCount_loc.getText();

						//Convert int-String to only int Part  //eg:  "728 results"  -> 728

						Pattern pattern = Pattern.compile("\\d+");

						Matcher matcher = pattern.matcher(count);

						String langCount = "";

						if(matcher.find())

						{

							langCount = matcher.group();

						}

						System.out.println("Total Count : " + langCount);

						excelutility.setCellData("test_case_2", subRow, 2, langCount);

						js.executeScript("arguments[0].click()", level);   // Unchecking the checkbox					

						Thread.sleep(3000);

						row++;

						subRow++;

					}

					System.out.println("********************************************");

					js.executeScript("arguments[0].click()",showMore_loc);

					Thread.sleep(2000);

					js.executeScript("arguments[0].click()",clearAllBtn_loc);

					Thread.sleep(2000);

					js.executeScript("arguments[0].click()", closeBtn_loc);

					row++;

					subRow++;

					break;

				}

			}

		}

	}

}
