package tests;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.KarieraPage;
import pages.JobPage;
import utilities.DriverManager;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class TestKariera
{
    public static WebDriver driver;

    //from Jenkins
    private String envBasePage = System.getenv("envBasePage");
    private String envJobArea = System.getenv("envJobArea");
    private String envJobLevel = System.getenv("envJobLevel");
    private int envTimeoutIsAt = Integer.parseInt(System.getenv("envTimeoutIsAt"));

    //from here
//    private String envBasePage = "https://www.future-processing.pl/";
//
//    private String envJobArea = "Quality Assurance";
////    private String envJobArea = "Developer";
//
////    private String envJobLevel = "Medium";
//    private String envJobLevel = "Senior";
////    private String envJobLevel = "Junior";
//
//    private int envTimeoutIsAt = 60;

    // Page Objects
    private BasePage basePage;
    private KarieraPage karieraPage;
    private JobPage jobPage;

    // Take screenshots
    private void takeScreenshot() {
        TakesScreenshot ts;
        ts = (TakesScreenshot) driver;

        if (ts != null) {
            File srcFile = ts.getScreenshotAs(OutputType.FILE);

            try {
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
                Date date = new Date();

                //ScreenShot
                FileUtils.copyFile(srcFile, new File(System.getProperty("user.dir") + "/screenShots/OK " + envJobArea + " " + envJobLevel + " " + dateFormat.format(date) + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No driver - no photo.");
        }

    }

    @DataProvider
    public Object[][] getData()
    {
        return new Object[][]{
                {1, envBasePage, "chrome"},
                {2, envBasePage, "firefox"},
        };
    }

    @BeforeSuite()
    public void BeforeSuite()
    {
        System.out.println("==============================================");
        System.out.println("=Test suite START                            =");
        System.out.println("==============================================");
    }

    @AfterMethod()
    public void AfterMethod()
    {
        if(driver != null)
        {
            driver.quit();
        }
        else
        {
            System.out.println("Something is wrong ---> driver = null in AfterMethod");
        }
    }

    //  , dependsOnMethods = { "loginCorrect" }
    //  , priority=1
    //  , enabled = false
    @Test(dataProvider="getData", enabled = false)
    public void launch(int p1, String p2, String p3) {

        System.out.println("==============================================");
        System.out.println("=Test suite parameters(env. variables)       =");
        System.out.println("p1=" + p1);
        System.out.println("p2=" + p2);
        System.out.println("p3=" + p3);
        System.out.println("==============================================");
        System.out.println("=envBasePage: " + envBasePage);
        System.out.println("=envJobArea: " + envJobArea);
        System.out.println("=envJobLevel: " + envJobLevel);
        System.out.println("=envTimeoutIsAt: " + envTimeoutIsAt);
        System.out.println("==============================================");

        // setup driver
        DriverManager driverManager = new DriverManager(driver);
        //usingBrowser=p3;
        driver = driverManager.getDriver(p3);

        //get to base page (from data provider)
        driver.get(p2);
        System.out.println("-------testing www no. " + p1 + " : " + p2 + " on " + p3);

        // Page Object - assign
        basePage = new BasePage(driver);
        karieraPage = new KarieraPage(driver);
        jobPage = new JobPage(driver);

        Assert.assertTrue(basePage.isAt(envTimeoutIsAt), "----------BasePage not loaded!");
    }

    @Test(dataProvider="getData")
    public void goToJobsAndSearch(int p1, String p2, String p3) {
        launch(p1, p2, p3);


        basePage.setKarieraMenuButton()
                .setOfertyPracyMenuButton()
                .setJobListFilterButton()
                .setJobListOptionButton(envJobArea);

          Assert.assertTrue((karieraPage.jobCount() > 0), "----------No job offers found. Check your job area. [NOK]");
//          Assert.fail(">>>>>>>>>>>>> fail test");

    }

    @Test(dataProvider="getData", dependsOnMethods = { "goToJobsAndSearch" })
    public void enterJobOffer(int p1, String p2, String p3) {
        goToJobsAndSearch(p1, p2, p3);

        karieraPage.setFirstJobWithLevel(envJobLevel);

        Assert.assertTrue(jobPage.isAt(envTimeoutIsAt), "----------JobPage not loaded!");

        if (p3.equalsIgnoreCase("chrome")) {
            takeScreenshot();
        }

    }




}
