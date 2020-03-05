package tests;

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


public class TestKariera
{
    public static WebDriver driver;

    //from Jenkins
//    private String envLoginPage = System.getenv("LOGIN_PAGE");
//    private String envLoginLogin = System.getenv("LOGIN_LOGIN");
//    private String envLoginPassword = System.getenv("LOGIN_PASSWORD");
//    private int envTimeoutIsAt = Integer.parseInt(System.getenv("TEST_TIMEOUT"));

//    //from here
    private String envBasePage = "https://www.future-processing.pl/";
    private String envJobArea = "Quality Assurance";
    private int envTimeoutIsAt = 60;

    // Page Objects
    private BasePage basePage;
    private KarieraPage karieraPage;
    private JobPage jobPage;


    @DataProvider
    public Object[][] getData()
    {
        return new Object[][]{
                {1, envBasePage, "chrome"},
                {2, envBasePage, "firefox"},
        };
    }

    @BeforeSuite()
    public void BeforeSuite(int p1, String p2, String p3)
    {
        System.out.println("==============================================");
        System.out.println("=Test suite parameters(env. variables)       =");
        System.out.println("p1=" + p1);
        System.out.println("p2=" + p2);
        System.out.println("p3=" + p3);
        System.out.println("==============================================");
        System.out.println("=envBasePage: " + envBasePage);
        System.out.println("=envJobArea: " + envJobArea);
        System.out.println("=envTimeoutIsAt: " + envTimeoutIsAt);
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

        //  Assert.assertTrue(karieraPage.isAt(envTimeoutIsAt), "----------Log in fail - you are not on MainPage");

    }




}
