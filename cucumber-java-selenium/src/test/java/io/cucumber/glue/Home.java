package io.cucumber.glue;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.core.Context;
import io.cucumber.core.Manager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.WebElement;
import io.cucumber.pages.Home.ExpectedList;
import java.time.Duration;



public class Home extends Context {

  public Home(Manager manager) {
    super(manager);
  }

  @Given("the Homepage has loaded")
  public void confirmHomePageStep() {
      try {
          Thread.sleep(Duration.ofSeconds(5));
      } catch (InterruptedException e) {
          throw new RuntimeException(e);
      }
  }

  @Then("expected Examples should be displayed in a list")
  public void homeDisplayList() {
      String[] expectedlist = ExpectedList.examples();
      WebElement ul = manager.getDriver().findElement(By.xpath("//*[@id=\"content\"]/ul"));
      List<WebElement> liElements = ul.findElements(By.tagName("li"));

      for (WebElement li : liElements) {
          String actualText = li.getText();
          if (Arrays.asList(expectedlist).contains(actualText)){
              System.out.println(actualText);
          }
      }
  }

}

