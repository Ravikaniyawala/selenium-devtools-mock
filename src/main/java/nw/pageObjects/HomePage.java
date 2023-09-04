package nw.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Session;
import utilities.UIAbstractTest;

import java.util.List;

public class HomePage extends UIAbstractTest {
    public HomePage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(css = "div.l-container.u-flex-justify-space-between")
    private WebElement mRibbon;

    @FindBy(css = "button.fs-tooltip__close-btn")
    public WebElement closeNotification;



    @FindBy(css = "a.btn.btn--primary.btn--large.fs-product-carousel__btn")
    private WebElement showAll;

    @FindBy(css= ".fs-home-product-carousel.fs-product-carousel .fs-carousel__btn-container.fs-carousel__btn-container--right > button")
    private WebElement slideNext;

    @FindBy(css=".fs-home-product-carousel.fs-product-carousel .fs-carousel__btn-container.fs-carousel__btn-container--left > button")
    private WebElement slidePrev;

    @FindBy(xpath = "//div[@class='fs-trolley-lockup__total-items']/span")
    private WebElement noOfItemsInCart;

    @FindBy(xpath = "//div[@class='fs-product-card__footer']")
    private List<WebElement> productFooter;

    @FindBy(css = "button.js-add-to-cart")
    private List<WebElement> addToTrolleyProducts;

    @FindBy(css = "div.fs-product-card")
    private List<WebElement> allproducts;

    @FindBy(css = ".fs-notification-bar__close-btn.js-notification-close-btn")
    private WebElement notificationbar;

    @FindBy(css = "a[aria-label='Specials']")
    private WebElement specialspage;

    @FindBy(css = ".fs-category-one-listing__heading")
    private WebElement catonelistingheading;

    @FindBy(css = ".m-sticky-footer__close.js-sticky-footer-close")
    private WebElement closelittlegarden;

    By productnameby = new By.ByCssSelector("div.fs-product-card__description > h3");

    By productq = new By.ByCssSelector("input.fs-add-to-trolley__quantity-edit");

    By homepagecontentignore1 = new By.ByXPath("//section[@class='fs-home-content-cards'][1]");

    By homepagecontentignore2 = new By.ByXPath("//section[@class='fs-home-content-cards'][2]");

    By homepagecontentcardbutton1 = new By.ByXPath("//div[contains(text(),'Shop Essential Boxes')]");


    By homepageheroonboardingignore = new By.ByCssSelector(".fs-home-onboarding.fs-hero-banner-panel.u-bg-white");

    By homepagespecialignore = new By.ByCssSelector(".fs-home-specials");

    @FindBy(xpath = "//span[contains(text(),\"$15 Delivery*\")]")//
    private WebElement deliveryonboardingtile;

    @FindBy(css = ".fs-cta-icon__icon.fs-cta-icon__icon--icon-collect")
    private WebElement cnconboardingtile;

    @FindBy(css = ".fs-cta-icon__icon.fs-cta-icon__icon--icon-user")
    private WebElement loginonboardingtile;

    @FindBy(css = ".fs-cta-icon__icon.fs-cta-icon__icon--icon-new-user")
    private WebElement registrationonboardingtile;

    @FindBy(css = ".fs-cta-icon__icon.fs-cta-icon__icon--icon-pig")
    private WebElement specialspageonboardingtile;

    @FindBy(css = ".fs-cta-icon__icon.fs-cta-icon__icon--icon-list")
    private WebElement shoppinglistsonboardingtile;

    @FindBy(css = ".fs-onboarding__tile.fs-cta-icon  .fs-cta-icon__icon.fs-cta-icon__icon--icon-calendar-tick")
    private WebElement methodandtimeonboardingtile;

    @FindBy(css = ".fs-onboarding__tile.fs-cta-icon.fs-cta-icon--primary.fs-onboarding__tile--primary")
    private WebElement shopfromordersonboardingtile;




    public boolean isDeliveryOnBoardingTileVisible(WebDriver webDriver){
        return isElementVis(deliveryonboardingtile,webDriver);
    }

    public boolean isCncOnBoardingTileVisible(WebDriver webDriver){
        return isElementVis(cnconboardingtile,webDriver);
    }

    public boolean isLoginOnBoardingTileVisible(WebDriver webDriver){
        return isElementVis(loginonboardingtile,webDriver);
    }

    public boolean isRegistrationOnBoardingTileVisible(WebDriver webDriver){
        return isElementVis(registrationonboardingtile,webDriver);
    }

    public boolean isLoggodOutUserOnboardingTileVisible(WebDriver webDriver){
        return (isDeliveryOnBoardingTileVisible(webDriver) && isCncOnBoardingTileVisible(webDriver) &&
                isLoginOnBoardingTileVisible(webDriver) && isRegistrationOnBoardingTileVisible(webDriver));
    }

    public boolean isSpecialsPageOnboardingTileVisible(WebDriver webDriver){
        return isElementVis(specialspageonboardingtile,webDriver);
    }

    public boolean isShoppingListsOnboardingTileVisible(WebDriver webDriver){
        return isElementVis(shoppinglistsonboardingtile,webDriver);
    }

    public boolean isMethodAndTimeOnboardingTileVisible(WebDriver webDriver){
        return isElementVis(methodandtimeonboardingtile,webDriver);
    }

    public boolean isShopFromOrdersOnboardingTileVisible(WebDriver webDriver){
        return isElementVis(shopfromordersonboardingtile,webDriver);
    }


    public boolean setUpHomePage(WebDriver webDriver) throws InterruptedException {
        webDriver.get(Session.BasePageURL);
        return isElementVis(mRibbon,webDriver);
    }

}



