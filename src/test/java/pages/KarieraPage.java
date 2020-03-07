package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Collections;
import java.util.List;

public class KarieraPage extends Page {

    @Override
    public boolean isAt(){
        return (this.ofertyPracyMenuButton.isDisplayed() && this.jakPracujemyMenuButton.isDisplayed());
    }

    @FindBy(css = "ul[id*='menu-main-menu']>li[id*='menu-item']>a[href*='oferty-pracy']")
    private WebElement ofertyPracyMenuButton;

    @FindBy(css = "ul[id*='menu-main-menu']>li[id*='menu-item']>a[href*='jak-pracujemy']")
    private WebElement jakPracujemyMenuButton;

    @FindBy(css = "div[class*='JobListing__Filters__TypesDropdown']")
    private WebElement jobListFilterButton;

    @FindBy(css = ".o-JobsList__Elem")
    private List<WebElement> jobsFound;



    public KarieraPage(WebDriver driver)
    {
        super(driver);
    }


    public KarieraPage setOfertyPracyMenuButton()
    {
        clickElement(this.ofertyPracyMenuButton);
        return this;
    }

    public KarieraPage setJobListFilterButton()
    {
        clickElement(this.jobListFilterButton);
        WebDriverWait wait = new WebDriverWait(driver, 1);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("li[class*='o-Dropdown__Option'][data-elementname*='Wszystkie']"))));
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("li[class*='o-Dropdown__Option'][data-elementname*='Developer']"))));
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("li[class*='o-Dropdown__Option'][data-elementname*='Inne']"))));
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("li[class*='o-Dropdown__Option'][data-elementname*='Quality Assurance']"))));

        return this;
    }

    public void setJobListOptionButton(String listOption)
    {
        clickElement(waitAndGetCssLocator("li[class*='o-Dropdown__Option'][data-elementname*='" + listOption + "']"));

        WebDriverWait wait = new WebDriverWait(driver, 100);
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector("li[class*='o-Dropdown__Option'][data-elementname*='Wszystkie']"))));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".o-Dropdown.s-JobListing__Filters__TypesDropdown[aria-expanded='false']"))));
    }

    public int jobCount()
    {
        System.out.println("Jobs found: " + jobsFound.size() + " [OK]");
        return jobsFound.size();
    }

    public void setFirstJobWithLevel(String jobLevel)
    {
        try{
        List<WebElement> jobsWithLevel = driver.findElements(By.cssSelector("li.o-JobsList__Elem[data-elementname*='" + jobLevel + "']"));

        System.out.println("Jobs with level '" + jobLevel + "'found: " + jobsWithLevel.size() + " [OK]");
        System.out.println("Entering first one...");
        clickElement(jobsWithLevel.get(0));

        }catch (Exception e){
            System.out.println("No jobs with level '" + jobLevel + "'found [NOK]");
        }
    }







}
