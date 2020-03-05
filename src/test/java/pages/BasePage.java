package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BasePage extends Page {

    @Override
    public boolean isAt(){
        return this.oNasMenuButton.isDisplayed() && this.karieraMenuButton.isDisplayed();
    }


    @FindBy(css = "ul[id*='menu-main-menu-pl']>li[id*='menu-item']>a[href*='o-nas']")
    private WebElement oNasMenuButton;

    @FindBy(css = "ul[id*='menu-main-menu-pl']>li[id*='menu-item']>a[href*='kariera']")
    private WebElement karieraMenuButton;

    public BasePage(WebDriver driver)
    {
        super(driver);
    }

    public KarieraPage setKarieraMenuButton()
    {
        clickElement(this.karieraMenuButton);
        return new KarieraPage(driver);
    }





}
