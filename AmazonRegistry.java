package com.aca.demo.dao;

	import java.util.List;
	import java.util.concurrent.TimeUnit;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.chrome.ChromeOptions;
	import org.openqa.selenium.support.PageFactory;
	import com.aca.demo.dao.AmazonFindARegistryPage;

	public class AmazonRegistry {

		private WebDriver driver;
		private AmazonFindARegistryPage amazonFindARegistryPage;

//		public static void main(String[] args) {
//			AmazonRegistry registry = new AmazonRegistry();
//			registry.setUp();
//			registry.findARegistry("Hanna Dailey", 2);
//			registry.shutDown();
//		}
		
		public List <AmazonGift> findGifts(String nameEmail) {
			setUp();
			 List<AmazonGift> gifts = findARegistry(nameEmail);
			shutDown();
			return gifts;
		}

		public List <AmazonGift> findARegistry(String nameEmail) {	
			amazonFindARegistryPage.inputNameOrEmail(nameEmail);
			amazonFindARegistryPage.clickLinkByHref(nameEmail);
			List<AmazonGift> gifts = amazonFindARegistryPage.readEachItem();
			
			System.out.println("total number of items: " + gifts.size());
			for (AmazonGift gift : gifts) {
				System.out.println(gift.toString());
			}
//			GiftDAO.insertGifts(gifts);
			return gifts;		
		}

		public void setUp() {
			System.setProperty("webdriver.chrome.driver", "C:/Program Files/chrome-2.39/chromedriver.exe");

			ChromeOptions options = new ChromeOptions();
			options.addArguments("chrome.switches", "--disable-extensions");

			this.driver = new ChromeDriver(options);

			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.get("https://www.amazon.com/wedding/home/ref=nav_wishlist_wr");

			amazonFindARegistryPage = PageFactory.initElements(driver, AmazonFindARegistryPage.class);
		}

		public void shutDown() {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.driver.quit();
		}

	}
