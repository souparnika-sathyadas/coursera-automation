package testCases;

import java.io.IOException;

import org.testng.annotations.Test;

import pageObjects.LanguageLearning;

public class TC_002_LanguageLearning extends TC_001_SearchCourses {
	@Test(priority=4, groups= {"regression","masters"})
	public void searchLanguageLearning() throws InterruptedException {
		//initialize with page objects 
		LanguageLearning languageLearning = new LanguageLearning(driver);
		loger.info("Searching Language learning and applying filters");
		String langlearn = p.getProperty("languagelearning");
		languageLearning.basicOperations(langlearn);
		}
	@Test(priority=5, groups= {"sanity","masters"})
	public void printCoursesCount() throws InterruptedException, IOException {
		LanguageLearning languageLearning = new LanguageLearning(driver);
		loger.info("Getting course count of each language with the required filters");
		languageLearning.getCourseCount();
	}
}                   
