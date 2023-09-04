package utilities;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

public interface BasePage {
    //private static final Integer Session.getTimeOut() = 6;



    public default void waitForElementVisibility(WebElement webElement, int timeOutSeconds, WebDriver webDriver) throws Error{
        try{new WebDriverWait(webDriver, Duration.ofSeconds(timeOutSeconds)).until(ExpectedConditions.visibilityOf(webElement));}catch(StaleElementReferenceException s){
            new WebDriverWait(webDriver, Duration.ofSeconds(timeOutSeconds)).until(ExpectedConditions.visibilityOf(webElement));
        }
    }

    public default void waitForElementClicable(WebElement webElement, int timeOutSeconds, WebDriver webDriver) throws Error{
        /*webDriver.manage().timeouts().implicitlyWait(6, SECONDS);
        new WebDriverWait(webDriver, timeOutSeconds).until(ExpectedConditions.elementToBeClickable(webElement));*/

        Wait wait = new FluentWait(webDriver)
                .withTimeout(Duration.ofSeconds(Session.getTimeOut()))
                .pollingEvery(Duration.ofMillis(10))
                .ignoring(Exception.class);
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    public default void waitForElementClicable(By by, WebDriver webDriver) throws Error{
        /*webDriver.manage().timeouts().implicitlyWait(6, SECONDS);
        new WebDriverWait(webDriver, timeOutSeconds).until(ExpectedConditions.elementToBeClickable(webElement));*/

        Wait wait = new FluentWait(webDriver)
                .withTimeout(Duration.ofSeconds(Session.getTimeOut()))
                .pollingEvery(Duration.ofMillis(10))
                .ignoring(Exception.class);
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public default WebElement waitForElementVisibility(WebElement webElement, WebDriver webDriver) throws Error{
        Wait wait = new FluentWait(webDriver)
                .withTimeout(Duration.ofSeconds(Session.getTimeOut()))
                .pollingEvery(Duration.ofMillis(10))
                .ignoring(Exception.class);
        return (WebElement) wait.until(ExpectedConditions.visibilityOf(webElement));
        }

    public default WebElement waitForElementVisibility(WebElement webElement, By by, WebDriver webDriver) throws Error{
        Wait wait = new FluentWait(webDriver)
                .withTimeout(Duration.ofSeconds(Session.getTimeOut()))
                .pollingEvery(Duration.ofMillis(10))
                .ignoring(Exception.class);
        return (WebElement) wait.until(ExpectedConditions.visibilityOf(webElement).andThen(w -> w.findElement(by)));
    }


    public default void waitForElementInVisibility(WebElement webElement, WebDriver webDriver) throws Error{
        Wait wait = new FluentWait(webDriver)
                .withTimeout(Duration.ofSeconds(Session.getTimeOut()))
                .pollingEvery(Duration.ofMillis(10))
                .ignoring(Exception.class);
        wait.until(ExpectedConditions.invisibilityOf(webElement));
    }

    public default WebElement waitForElementVisibility(WebDriver webDriver, By by){
        Wait wait = new FluentWait(webDriver)
                .withTimeout(Duration.ofSeconds(Session.getTimeOut()))
                .pollingEvery(Duration.ofMillis(20))
                .ignoring(Exception.class);
        return (WebElement) wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public default WebElement waitForElementVisibilityCustomTimeOut(WebDriver webDriver, By by,long sec){
        Wait wait = new FluentWait(webDriver)
                .withTimeout(Duration.ofSeconds(sec))
                .pollingEvery(Duration.ofMillis(20))
                .ignoring(Exception.class);
        return (WebElement) wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public default List<WebElement> waitForElementsVisibility(WebDriver webDriver, By by){
        Wait wait = new FluentWait(webDriver)
                .withTimeout(Duration.ofSeconds(Session.getTimeOut()))
                .pollingEvery(Duration.ofMillis(20))
                .ignoring(Exception.class);
        return (List<WebElement>) wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
    }

    public default List<WebElement> waitForElementsPresence(WebDriver webDriver, By by){
        Wait wait = new FluentWait(webDriver)
                .withTimeout(Duration.ofSeconds(Session.getTimeOut()))
                .pollingEvery(Duration.ofMillis(10))
                .ignoring(Exception.class);
        return (List<WebElement>) wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }

    /*public void waitForElementInVisibility(WebDriver webDriver, By by){
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }*/
    public default void waitForElementInVisibility(WebDriver webDriver, By by){
        Wait wait = new FluentWait(webDriver)
                .withTimeout(Duration.ofSeconds(Session.getTimeOut()))
                .pollingEvery(Duration.ofMillis(10))
                .ignoring(Exception.class);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public default boolean isElementVis(WebDriver webDriver, WebElement webElement, By findelement){
        Wait wait = new FluentWait(webDriver)
                .withTimeout(Duration.ofMillis(100))
                .pollingEvery(Duration.ofMillis(5))
                .ignoring(Exception.class);
        try{ wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(webElement,findelement));
            return true;
        }
        catch (org.openqa.selenium.TimeoutException t){
            return false;
        }
    }

    public default boolean isElementVis(WebDriver webDriver, WebElement webElement, By findelement,int sec){
        Wait wait = new FluentWait(webDriver)
                .withTimeout(Duration.ofMillis(sec))
                .pollingEvery(Duration.ofMillis(5))
                .ignoring(Exception.class);
        try{ wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(webElement,findelement));
            return true;
        }
        catch (org.openqa.selenium.TimeoutException t){
            return false;
        }
    }

    public default void scrollUp(WebDriver webDriver){
        JavascriptExecutor js = (JavascriptExecutor)webDriver;
        js.executeScript("window.scrollTo(document.body.scrollHeight, 0)");//scroll(0,-900);
    }

    public default void scrollDown(WebDriver webDriver){
        JavascriptExecutor js = (JavascriptExecutor)webDriver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public default void moveToElementAndClick(WebDriver webDriver, WebElement webElement){
        isElementVis(webElement,webDriver);
        Actions actions = new Actions(webDriver);
        actions.moveToElement(webElement).click().perform();
        //actions.perform();
    }

    public default void moveToElement(WebDriver webDriver, WebElement webElement){
        isElementVis(webElement,webDriver);
        Actions actions = new Actions(webDriver);
        actions.moveToElement(webElement).perform();
        //actions.perform();
    }

    public default void moveToElementAndSendKeys(WebDriver webDriver, WebElement webElement, String value){
        isElementVis(webElement,webDriver);
        Actions actions = new Actions(webDriver);
        actions.moveToElement(webElement).click().sendKeys(value).perform();
        //actions.perform();
    }



    public default void scrollUpToElement(WebDriver webDriver, WebElement webElement){
        try{
            JavascriptExecutor js = (JavascriptExecutor)webDriver;
            ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(false);", webElement);}
        catch(ElementNotInteractableException e){
            JavascriptExecutor js = (JavascriptExecutor)webDriver;
            ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(false);", webElement);
        }
    }

    public default void scrollDownToElement(WebDriver webDriver, WebElement webElement){
        try{
            JavascriptExecutor js = (JavascriptExecutor)webDriver;
            ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", webElement);}
        catch(ElementNotInteractableException e){
            JavascriptExecutor js = (JavascriptExecutor)webDriver;
            ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", webElement);
        }
    }

    public default void waitForElementAttributeToBeVisible(WebDriver webDriver, WebElement webElement, String attribute, String text){
        int sec=0;
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Session.getTimeOut()));
        new WebDriverWait(webDriver, Duration.ofSeconds(Session.getTimeOut())).until(ExpectedConditions.attributeContains(webElement,attribute,text));
    }

    public default String getAttributeText(WebDriver webDriver, WebElement webElement, String attribute){
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Session.getTimeOut()));
        new WebDriverWait(webDriver, Duration.ofSeconds(Session.getTimeOut())).until(ExpectedConditions.attributeToBeNotEmpty(webElement,attribute));
        return webElement.getAttribute(attribute);
    }

    public default boolean isElementAttributeTextVisible(WebDriver webDriver, WebElement webElement, String attribute, String text){
        int sec=0;
        try{
        new WebDriverWait(webDriver, Duration.ofSeconds(Session.getTimeOut())).until(ExpectedConditions.attributeContains(webElement,attribute,text));
            webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Session.getTimeOut()));
            return true;
        }catch (TimeoutException | NoSuchElementException t){
            webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Session.getTimeOut()));
            return false;
        }
    }

    public default boolean isElementAttributeTextVisible(WebDriver webDriver, WebElement webElement, String attribute, String text, int mill){
        Wait wait = new FluentWait(webDriver)
                .withTimeout(Duration.ofMillis(mill))
                .pollingEvery(Duration.ofMillis(50))
                .ignoring(Exception.class);
        try{
            wait.until(ExpectedConditions.attributeContains(webElement,attribute,text));
            webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Session.getTimeOut()));
            return true;
        }catch (TimeoutException t){
            webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Session.getTimeOut()));
            return false;
        }
    }

    public default boolean isElementAttributeTextVisible(WebDriver webDriver, WebElement webElement, By by, String attribute, String text, int mill){
        Wait wait = new FluentWait(webDriver)
                .withTimeout(Duration.ofMillis(mill))
                .pollingEvery(Duration.ofMillis(50))
                .ignoring(Exception.class);
        try{
            webDriver.manage().timeouts().implicitlyWait(Duration.ofMillis(50));
            wait.until(ExpectedConditions.attributeContains(webElement.findElement(by),attribute,text));
            webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Session.getTimeOut()));
            return true;
        }catch (NoSuchElementException | TimeoutException n){
            webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Session.getTimeOut()));
            return false;
        }
    }

    public default boolean isElementAttributeTextVisible(WebDriver webDriver, WebElement webElement, By by, String text, int mill){
        Wait wait = new FluentWait(webDriver)
                .withTimeout(Duration.ofMillis(mill))
                .pollingEvery(Duration.ofMillis(50))
                .ignoring(Exception.class);
        try{
            wait.until(ExpectedConditions.textToBePresentInElement((WebElement) wait.until(ExpectedConditions.visibilityOf(webElement).andThen(w -> w.findElement(by))),text));
            webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Session.getTimeOut()));
            return true;
        }catch (NoSuchElementException | TimeoutException | Error e){
            webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Session.getTimeOut()));
            return false;
        }
    }

    public default boolean isElementAttributePresent(WebDriver webDriver, WebElement webElement, String attribute){
        int sec=0;
        try{
            new WebDriverWait(webDriver, Duration.ofSeconds(Session.getTimeOut())).until(ExpectedConditions.attributeToBeNotEmpty(webElement,attribute));
            webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Session.getTimeOut()));
            return true;
        }catch (TimeoutException t){
            webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Session.getTimeOut()));
            return false;
        }
    }

    public default boolean isElementAttributeTextVisible(WebDriver webDriver, WebElement webElement, By by, String attribute, String text){
        int sec=0;
        WebElement w = webElement.findElement(by);
        try{
            new WebDriverWait(webDriver, Duration.ofSeconds(Session.getTimeOut())).until(ExpectedConditions.attributeContains(w,attribute,text));
            webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Session.getTimeOut()));
            return true;
        }catch (TimeoutException t){
            webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Session.getTimeOut()));
            return false;
        }
    }

    public default void waitForElementText(WebDriver webDriver, WebElement webElement, String text){
        int sec=0;
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Session.getTimeOut()));
        new WebDriverWait(webDriver, Duration.ofSeconds(Session.getTimeOut())).until(ExpectedConditions.textToBePresentInElement(webElement,text));
    }

    public default void waitForElementTextInVis(WebDriver webDriver, By by, String text){
        int sec=0;
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Session.getTimeOut()));
        new WebDriverWait(webDriver, Duration.ofSeconds(Session.getTimeOut())).until(ExpectedConditions.invisibilityOfElementWithText(by,text));
    }

    public default void waitForElementText(WebDriver webDriver, By by, String text){
        Wait wait = new FluentWait(webDriver)
                .withTimeout(Duration.ofSeconds(Session.getTimeOut()))
                .pollingEvery(Duration.ofMillis(10))
                .ignoring(Exception.class);
        //webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Session.getTimeOut()));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(by,text));
    }

    public default boolean isElementTextVis(WebDriver webDriver, WebElement webElement, String text){
        int sec=0;
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Session.getTimeOut()));
        try {
            return new WebDriverWait(webDriver, Duration.ofSeconds(Session.getTimeOut())).until(ExpectedConditions.textToBePresentInElement(webElement, text));
        }catch(Exception e){
            return false;
        }
    }

    public default boolean isElementTextVis(WebDriver webDriver, WebElement w, String text, int timeout){
        Wait wait = new FluentWait(webDriver)
                .withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofMillis(10))
                .ignoring(Exception.class);
        //webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Session.getTimeOut()));
        try {
            return (boolean) wait.until(ExpectedConditions.textToBePresentInElement(w, text));
        }catch (TimeoutException t){
            return false;
        }
    }

    public default boolean isElementTextVis(WebDriver webDriver, By w, String text, int seconds){
        Wait wait = new FluentWait(webDriver)
                .withTimeout(Duration.ofSeconds(seconds))
                .pollingEvery(Duration.ofMillis(10))
                .ignoring(Exception.class);
        //webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Session.getTimeOut()));
        try {
            return (boolean) wait.until(ExpectedConditions.textToBePresentInElementLocated(w, text));
        }catch (TimeoutException t){
            return false;
        }
    }

    public default boolean iselementTextIsVisible(WebDriver webDriver, By by, String text){
        int sec=0;
        try {
            webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Session.getTimeOut()));
            return new WebDriverWait(webDriver, Duration.ofSeconds(Session.getTimeOut())).until(ExpectedConditions.textToBePresentInElementLocated(by, text));
        }catch (TimeoutException t){
            return false;
        }
    }

    public default boolean isElementVis(WebDriver webDriver, By by){
        /*int sec=0;
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Session.getTimeOut()));
        try{ return isElementVis(new WebDriverWait(webDriver, Duration.ofSeconds(6)).until(ExpectedConditions.presenceOfElementLocated(by)),webDriver); }
        catch (org.openqa.selenium.TimeoutException t){
            return false;
        }*/
        Wait wait = new FluentWait(webDriver)
                .withTimeout(Duration.ofSeconds(Session.getTimeOut()))
                .pollingEvery(Duration.ofMillis(50))
                .ignoring(Exception.class);
        //webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Session.getTimeOut()));
        try {
             wait.until(ExpectedConditions.presenceOfElementLocated(by));
             return true;
        }catch (TimeoutException t){
            return false;
        }

    }





    public default boolean isTopOfPage(WebDriver webDriver, WebElement webElement){
        JavascriptExecutor executor = (JavascriptExecutor) webDriver;
        Long value = (Long) executor.executeScript("return window.pageYOffset;");
        return value == 0 ? true : false;
    }


    public default boolean isElementVis(WebElement webElement, WebDriver webDriver) {
        Wait wait = new FluentWait(webDriver)
                .withTimeout(Duration.ofSeconds(Session.getTimeOut()))
                .pollingEvery(Duration.ofMillis(10))
                .ignoring(Exception.class);
        try {
            wait.until(ExpectedConditions.visibilityOf(webElement));
            return true;
        }catch (TimeoutException t){
            return false;
        }
    }



    public default WebElement getActiveElement(List<WebElement> elementList, WebDriver webDriver){
        try {
            for(WebElement w: elementList){
                if(isElementVis(w,webDriver)){
                    System.out.println("Active Element Found...");
                    return w;
                }
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            System.out.println("Retrying...");
            for(WebElement w: elementList){
                if(isElementVis(w,webDriver)){
                    System.out.println("Active Element Found...");
                    return w;
                }
            }
        }

        return null;
    }

    public default void waitForPageToLoadAllElements(WebDriver driver){
        new WebDriverWait(driver, Duration.ofSeconds(Session.getTimeOut())).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    public default void sleep(){
        try{
            Thread.sleep(3000);
        }catch (InterruptedException e){

        }
    }

    public default void sleep(long l){
        try{
            Thread.sleep(l);
        }catch (InterruptedException e){

        }
    }

    public default boolean isAscending(List<Float> list){
        List<Float> receivedList = list;
        return receivedList.stream().sorted().collect(Collectors.toList()).equals(list);
    }

    public default boolean isDescending(List<Float> list){
        List<Float> receivedList = list;
        return receivedList.stream().sorted(Collections.reverseOrder()).collect(Collectors.toList()).equals(list);
    }

}
