package io.cucumber.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.ArrayList;
import java.util.List;


public class Home extends Page {

  public Home(ChromeDriver driver) {
    super(driver);
    System.out.println("Homepage title is : " + getTitle().getText());
  }

  @FindBy(css = "h1")
  private WebElement title;

  public WebElement getTitle() {
    return title;
  }

  public void refresh() {
    driver.navigate().refresh();
    System.out.println("Refreshed page");
  }

 public class ExpectedList{
    public static String[] examples(){
      return new String[]{
              "A/B Testing",
              "Add/Remove Elements",
              "Basic Auth (user and pass: admin)",
              "Broken Images",
              "Challenging DOM",
              "Context Menu",
              "Digest Authentication (user and pass: admin)",
              "Disappearing Elements",
              "Drag and Drop",
              "Dropdown",
              "Dynamic Content",
              "Dynamic Controls",
              "Dynamic Loading",
              "Entry Ad",
              "Exit Intent",
              "File Download",
              "File Upload",
              "Floating Menu",
              "Forgot Password",
              "Form Authentication",
              "Geolocation",
              "Horizontal Slider",
              "Infinite Scroll",
              "Inputs",
              "JavaScript Alerts",
              "JavaScript onload event error",
              "Key Presses",
              "Large & Deep DOM",
              "Multiple Windows",
              "Nested Frames",
              "Notification Messages",
              "Redirect Link",
              "Secure File Download",
              "Shadow DOM",
              "Shifting Content",
              "Slow Resources",
              "Sortable Data Tables",
              "Status Codes",
              "Typos",
              "WYSIWYG Editor"};
   }

  }




}
