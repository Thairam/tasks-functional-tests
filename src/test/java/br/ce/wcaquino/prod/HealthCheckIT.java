package br.ce.wcaquino.prod;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class HealthCheckIT {

  @Test
  public void healthCheck() throws MalformedURLException {
    System.setProperty("webdriver.chrome.driver", "C:/Users/thayr/dev/java/chromedriver-win64/chromedriver.exe");

    DesiredCapabilities cap = DesiredCapabilities.chrome();
    WebDriver driver = new RemoteWebDriver(new URL("http://127.0.0.1:4444/wd/hub"), cap);

    try {
      driver.navigate().to("http://172.26.128.1:9999/tasks");
      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

      String version = driver.findElement(By.id("version")).getText();
      Assert.assertTrue(version.startsWith("build"));
    } finally {
      driver.quit();
    }
  }
}
