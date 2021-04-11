package org.github;
import java.io.File;
	import java.io.FileInputStream;
	import java.io.FileNotFoundException;
	import java.io.FileOutputStream;
	import java.io.IOException;
	import java.text.SimpleDateFormat;
	import java.util.Date;
	import java.util.List;
	import java.util.Set;
	import java.util.concurrent.TimeUnit;
	import org.apache.commons.io.FileUtils;
	import org.apache.poi.ss.usermodel.Cell;
	import org.apache.poi.ss.usermodel.DateUtil;
	import org.apache.poi.ss.usermodel.Row;
	import org.apache.poi.ss.usermodel.Sheet;
	import org.apache.poi.ss.usermodel.Workbook;
	import org.apache.poi.xssf.usermodel.XSSFWorkbook;
	import org.openqa.selenium.Alert;
	import org.openqa.selenium.By;
	import org.openqa.selenium.JavascriptExecutor;
	import org.openqa.selenium.OutputType;
	import org.openqa.selenium.TakesScreenshot;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.firefox.FirefoxDriver;
	import org.openqa.selenium.ie.InternetExplorerDriver;
	import org.openqa.selenium.interactions.Actions;
	import org.openqa.selenium.support.ui.Select;

	public class BaseClass {
		public static WebDriver driver;

		// WEBDRIVER
		public static void launchBrowser(String browser) {
			switch (browser.toLowerCase()) {
			case "chrome":
				System.setProperty("webdriver.chrome.driver", "./Drivers\\chromedriver.exe");
				driver = new ChromeDriver();
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				break;
			case "firefox":
				System.setProperty("webdriver.gecko.driver", "./Drivers\\geckodriver.exe");
				driver = new FirefoxDriver();
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				break;
			case "ie":
				System.setProperty("webdriver.ie.driver", "./Drivers\\IEDriverServer.exe");
				driver = new InternetExplorerDriver();
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				break;
			}
		}

		// URL LAUNCH
		public static void launchUrl(String url) {
			driver.get(url);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}

		// WEB ELEMENT
		public static void findElement(String by, String path) {
			switch (by.toLowerCase()) {
			case "id":
				driver.findElement(By.id(path));
				break;
			case "name":
				driver.findElement(By.name(path));
				break;
			case "tagname":
				driver.findElement(By.tagName(path));
				break;
			case "xpath":
				driver.findElement(By.xpath(path));
				break;
			}

		}

		// SEND KEYS
		public static void sendKeys(WebElement e, String value) {
			e.sendKeys(value);
		}

		// CLICK
		public static void click(WebElement e) {
			e.click();
		}

		// CLEAR
		public static void clear(WebElement e) {
			e.clear();
		}

		// GET TEXT
		public static String getText(WebElement e) {
			return e.getText();
		}

		// GET ATTRIBUTE
		public static String getAttribute(WebElement e) {
			return e.getAttribute("value");
		}

		// QUIT
		public static void quit() {
			driver.quit();
		}

		// ACTIONS
		public static void moveToElements(WebElement e) {
			Actions a = new Actions(driver);
			a.moveToElement(e).perform();
		}

		public static void clickAction(WebElement e) {
			Actions a = new Actions(driver);
			a.click(e).perform();
		}

		public static void doubleClick(WebElement e) {
			Actions a = new Actions(driver);
			a.doubleClick(e).perform();
		}

		public static void dragAndDrop(WebElement e, WebElement f) {
			Actions a = new Actions(driver);
			a.dragAndDrop(e, f).perform();
		}

		public static void contextClick(WebElement e) {
			Actions a = new Actions(driver);
			a.contextClick(e).perform();
		}

		public static void clickAndHold(WebElement e) {
			Actions a = new Actions(driver);
			a.clickAndHold(e).perform();
		}

		public static void release(WebElement e) {
			Actions a = new Actions(driver);
			a.release(e).perform();
		}

		// JAVASCRIPT EXECUTOR
		public static void scrollUp(WebElement e) {
			JavascriptExecutor jk = (JavascriptExecutor) driver;
			jk.executeScript("arguments[0].scrollIntoView(true)", e);
		}

		public static void scrollDown(WebElement e) {
			JavascriptExecutor jk = (JavascriptExecutor) driver;
			jk.executeScript("arguments[0].scrollIntoView(false)", e);
		}

		public static void setAttribute(WebElement e, String value) {
			JavascriptExecutor jk = (JavascriptExecutor) driver;
			jk.executeScript("arguments[0].setAttribute('value','" + value + "')", e);
		}

		public static void getAttribute(String r, WebElement e) {
			JavascriptExecutor jk = (JavascriptExecutor) driver;
			Object text = jk.executeScript("return arguments[0].getAttribute('value')", e);
			System.out.println(text);
		}

		public static void clickByJavaScript(WebElement e) {
			JavascriptExecutor jk = (JavascriptExecutor) driver;
			jk.executeScript("arguments[0].click()", e);
		}

		// TAKE SCREENSHOT
		public static void screenShot(String name) throws IOException {
			TakesScreenshot t = (TakesScreenshot) driver;
			File source = t.getScreenshotAs(OutputType.FILE);
			File target = new File("./Screenshots\\" + name + ".png");
			FileUtils.copyFile(source, target);
		}

		// ALERT
		public static void accept() {
			Alert a = driver.switchTo().alert();
			a.accept();
		}

		public static void dismiss() {
			Alert a = driver.switchTo().alert();
			a.dismiss();
		}

		public static void sendKeysByAlert(String s) {
			Alert a = driver.switchTo().alert();
			a.sendKeys(s);
		}

		public static String gettTextByAlert() {
			Alert a = driver.switchTo().alert();
			return a.getText();
		}

		// SELECT
		public static void selectBy(WebElement e, String type, String value) {
			Select s = new Select(e);
			switch (type.toLowerCase()) {
			case "value":
				s.selectByValue(value);
				break;
			case "index":
				s.selectByIndex(Integer.parseInt(value));
				break;
			case "text":
				s.selectByVisibleText(value);
				break;
			}
		}

		public static void getOptions(WebElement e) {
			Select s = new Select(e);
			List<WebElement> options = s.getOptions();
			for (WebElement eachOptions : options) {
				System.out.println(eachOptions.getText());
			}
		}

		public static void getFirstSelected(WebElement e) {
			Select s = new Select(e);
			WebElement f1 = s.getFirstSelectedOption();
			System.out.println(f1.getText());
		}

		public static void getAllSelectedOptions(WebElement e) {
			Select s = new Select(e);
			List<WebElement> allSelectedOptions = s.getAllSelectedOptions();
			for (WebElement options : allSelectedOptions) {
				System.out.println(options.getText());
			}
		}

		public static void deSelectBy(WebElement e, String type, String value) {
			Select s = new Select(e);
			switch (type.toLowerCase()) {
			case "value":
				s.deselectByValue(value);
				break;
			case "index":
				s.deselectByIndex(Integer.parseInt(value));
				break;
			case "text":
				s.deselectByVisibleText(value);
				break;
			}
		}

		public static void deSelectAll(WebElement e) {
			Select s = new Select(e);
			s.deselectAll();
		}

		public static void multiple(WebElement e) {
			Select s = new Select(e);
			boolean multiple = s.isMultiple();
			System.out.println(multiple);
		}

		// FRAMES
		public static void switchToFrameBy(String type) {
			switch (type.toLowerCase()) {
			case "index":
				driver.switchTo().frame(Integer.parseInt(type));
				break;
			case "id":
				driver.switchTo().frame(type);
				break;
			case "name":
				driver.switchTo().frame(type);
			}
		}

		public static void switchToFrameByWebElement(WebElement e) {
			driver.switchTo().frame(e);
		}

		public static void defaultContent() {
			driver.switchTo().defaultContent();
		}

		public static void parentFrame() {
			driver.switchTo().parentFrame();
		}

		public static void switchWindow() {
			String parentID = driver.getWindowHandle();
			Set<String> allID = driver.getWindowHandles();

		}

		public static void selectDate(String date) {
			driver.findElement(By.xpath("//div[text()='" + date + "']"));
		}

		// WEBTABLE
		public static void enterIntoTable(int i) {
			List<WebElement> table = driver.findElements(By.tagName("table"));
			table.get(i);
		}

		public static void setExcelValueNew(String bookName, String sheetName, int row, int cell, String value)
				throws IOException {
			File f = new File("./src\\test\\resources\\" + bookName + ".xlsx");
			Workbook w = new XSSFWorkbook();
			Sheet s = w.createSheet(sheetName);
			Row r = s.createRow(row);
			Cell c = r.createCell(cell);
			c.setCellValue(value);
			FileOutputStream fo = new FileOutputStream(f);
			w.write(fo);
		}

		public static void setExcelValueExisting(String bookName, String sheetName, int row, int cell, String value)
				throws IOException {
			File f = new File("./src\\test\\resources\\" + bookName + ".xlsx");
			FileInputStream fi = new FileInputStream(f);
			Workbook w = new XSSFWorkbook(fi);
			Sheet s = w.getSheet(sheetName);
			Row r = s.getRow(row);
			Cell c = r.createCell(cell);
			c.setCellValue(value);
			FileOutputStream fo = new FileOutputStream(f);
			w.write(fo);
		}

		public static String getValue(String bookName, String sheetName, int row, int cell) throws IOException {
			File f = new File("./src\\test\\resources\\" + bookName + ".xlsx");
			FileInputStream fi = new FileInputStream(f);
			Workbook w = new XSSFWorkbook(fi);
			Sheet s = w.getSheet(sheetName);
			Row r1 = s.getRow(row);
			Cell c = r1.getCell(cell);
			int type = c.getCellType();
			String value = null;
			if (type == 1) {
				value = c.getStringCellValue();
			} else {
				if (DateUtil.isCellDateFormatted(c)) {
					Date d = c.getDateCellValue();
					SimpleDateFormat date = new SimpleDateFormat("dd-MMM-yyyy");
					value = date.format(d);
				} else {
					double db = c.getNumericCellValue();
					long l = (long) db;
					value = String.valueOf(l);
				}
			}
			return value;
		}
	

}
