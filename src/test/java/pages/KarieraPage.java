package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
        return this;
    }

    public KarieraPage setJobListOptionButton(String listOption)
    {
        clickElement(waitAndGetCssLocator("li[class*='o-Dropdown__Option'][data-elementname*='" + listOption + "']"));

        WebDriverWait wait = new WebDriverWait(driver, 100);
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector("li[class*='o-Dropdown__Option'][data-elementname*='Wszystkie']"))));

        return this;
    }


//  $$("li[class*='o-Dropdown__Option'][data-elementname*='Wszystkie']")
    //  .o-JobsList__Elem







}
