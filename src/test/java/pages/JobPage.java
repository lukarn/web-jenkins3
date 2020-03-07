package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class JobPage extends Page {

    @Override
    public boolean isAt(){
        return this.applyButton.isDisplayed();
    }


    @FindBy(css = ".o-Button.js-ModalOpen")
    private WebElement applyButton;


    public JobPage(WebDriver driver)
    {
        super(driver);
    }






}
