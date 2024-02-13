package testCases;
import java.io.IOException;
import org.testng.annotations.Test; 
import pageObjects.Courses;
import testBase.BaseClass1;
 
public class TC_001_SearchCourses extends BaseClass1 {
 
	Courses course;
 
	@Test(priority=1, groups= {"regression","masters"})
	public void searchCourses() throws InterruptedException, IOException {
		
		String webdev = p.getProperty("webdevelopment");
		course = new Courses(driver);
		loger.info("searching web development");
		course.setSearchBox(webdev);
		loger.info("click on search button");
        course.clickSearchBtn();	
	}
	@Test(priority =2, groups= {"regression","masters"})
	public void applyFilters()throws InterruptedException, IOException {
		loger.info("applying language filter");
		course.clickOnShowMore();
		course.selectLanguage();
		course.clickOnApplyBtn();
		loger.info("applying level filter");
		course.clickOnBeginnerLoc();
	}
	
	@Test(priority=3, groups= {"sanity","masters"})
	public void coursesDetails()throws InterruptedException, IOException {
		loger.info("extracting details of first two courses");
		course.getCourseDetails();
	}
}

