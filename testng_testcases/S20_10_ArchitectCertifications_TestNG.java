package testng_testcases;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class S20_10_ArchitectCertifications_TestNG extends GeneralMethods{

	@Test
	public void architectCertification() throws InterruptedException, IOException {

		String parent = driver.getWindowHandle();

		// 3. Click on Learn More link in Mobile Publisher
		// 4. Navigate to the Salesforce Customer Service
		driver.findElement(By.xpath("//span[text()='Learn More']")).click();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		// Navigating to new window
		Set<String> windows = driver.getWindowHandles();
		Iterator<String> I1 = windows.iterator();
		while (I1.hasNext()) {
			String child_window = I1.next();
			if (!parent.equals(child_window)) {
				driver.switchTo().window(child_window);

				// implicit wait
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

				// 5. Mouse hover on Support & Services
				Actions actions = new Actions(driver);
				WebElement mousehover = driver.findElement(By.xpath("(//span[text()='Resources'])[1]"));
				actions.moveToElement(mousehover).build().perform();
				Thread.sleep(2000);

				// Click on the Certifications link
				WebElement certselect = driver
						.findElement(By.xpath("//span[contains(text(),'Salesforce Certification')]"));
				JavascriptExecutor executor = (JavascriptExecutor) driver;
				executor.executeScript("arguments[0].click();", certselect);
				Thread.sleep(2000);

				// Switching to new window
				windows = driver.getWindowHandles();
				String[] arrayOfString = Arrays.copyOf(windows.toArray(), windows.size(), String[].class);
				driver.switchTo().window(arrayOfString[2]);
				Thread.sleep(2000);

				// Selecting the role as Sales force Architect
				driver.findElement(By.xpath("//div[text()='Salesforce Architect']")).click();
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

				// Verifying the certifications displayed for sales

				List<WebElement> cert = driver.findElements(By.xpath("//div[@class='cs-card tile']/div[3]/a"));
				for (WebElement cert1 : cert)
					System.out.println(cert1.getText() + "is Displayed");
				driver.switchTo().window(arrayOfString[1]);
				driver.switchTo().window(arrayOfString[0]);
			}
		}
	}
}