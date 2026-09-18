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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Navigation extends Context {

  public Navigation(Manager manager) {
    super(manager);
  }

  @Given("^the page under test is '(.+)'$")
  public void navToPage(String url) {
    manager.getDriver().get(url);
  }

  @When("the 'Basic Auth' example is opened")
  public void basicauthURL() {
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
  public void congratulationsdisplayed() {
    try {
      Thread.sleep(Duration.ofSeconds(5));
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    WebElement displayparagraph = manager.getDriver().findElement(By.xpath("//*[@id=\"content\"]/div/p"));
    String text = displayparagraph.getText();
    System.out.println(text);
  }

  @Then("Example 1 displays the expected 4 results")
  public void example1_displays_expected_results(){
    List<String[]> expectedRows = new ArrayList<>();
    expectedRows.add(new String[]{"Smith", "John", "jsmith@gmail.com", "$50.00", "http://www.jsmith.com"});
    expectedRows.add(new String[]{"Bach", "Frank", "fbach@yahoo.com", "$51.00", "http://www.frank.com"});
    expectedRows.add(new String[]{"Doe", "Jason", "jdoe@hotmail.com", "$100.00", "http://www.jdoe.com"});
    expectedRows.add(new String[]{"Conway", "Tim", "tconway@earthlink.net", "$50.00", "http://www.timconway.com"});

    WebElement table = manager.getDriver().findElement(By.xpath("//*[@id=\"table1\"]"));
    List<WebElement> rows = table.findElements(By.tagName("tr"));
    List<String[]> actualRows = new ArrayList<>();

    for (WebElement row : rows){
      List<WebElement> cells = row.findElements(By.tagName("td"));
      String[] actualRow = new String[5];
      if (cells.isEmpty()) {
        continue;
      }

      //cells.size() - 5 excluded action column
      for (int i = 0; i < 5; i++) {
        actualRow[i] = cells.get(i).getText();
      }

    actualRows.add(actualRow);
  }
    for (String[] actual : actualRows) {
      System.out.println(Arrays.toString(actual));
    }

    for (String[] expected : expectedRows) {
      boolean found = actualRows.stream().anyMatch(actual -> Arrays.equals(actual, expected));
      if (!found) {
        throw new RuntimeException("Expected row not found: " + Arrays.toString(expected));
      }
    }

  }


}
