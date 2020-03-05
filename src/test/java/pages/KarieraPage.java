package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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
        return this;
    }


    //  .o-JobsList__Elem







}
