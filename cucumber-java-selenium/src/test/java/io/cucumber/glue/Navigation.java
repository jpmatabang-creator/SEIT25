package io.cucumber.glue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import io.cucumber.core.Context;
import io.cucumber.core.Manager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import java.time.Duration;

public class Navigation extends Context {

  public Navigation(Manager manager) {
    super(manager);
  }

  @Given("^the page under test is '(.+)'$")
  public void navToPage(String url) {
    manager.getDriver().get(url);
  }

  @When("the 'Basic Auth' example is opened")
  public void basicAuthURL(){
    manager.getDriver().get("https://the-internet.herokuapp.com/basic_auth");
    stash("userid", "admin");
    stash("password", "admin");
  }

  @And("valid credentials are supplied")
  public void valid_credentials_are_supplied() {
    String userid = (String) getTestStash().get("userid");
    String password = (String) getTestStash().get("password");
    //I needed to bypass the basic auth page because I'm unable to sendkeys to the textfield popup of username and password
    manager.getDriver().get("https://" + userid + ":" + password + "@the-internet.herokuapp.com/basic_auth");
  }

  @Then("Congratulations should be displayed")
  public void congratulations_should_be_displayed() {
    try {
      Thread.sleep(Duration.ofSeconds(5));
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }

    WebElement displayparagraph = manager.getDriver().findElement(By.xpath("//*[@id=\"content\"]/div/p"));
    String text = displayparagraph.getText();
    System.out.println(text);

  }

}