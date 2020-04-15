package com.aca.demo.dao;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


	public class AmazonFindARegistryPage {

		private WebDriver driver;	
		
		@FindBy(id="nameOrEmail")
		public WebElement nameOrEmailInput;
		
		@FindBy(xpath="//*[@id='wr-home-search-registry-btn']/span/input")
		public WebElement registrySearchButton;
		
		@FindBy(id="numOfResultsText")
		public WebElement numOfResultsText;
		
		@FindBy(id="wr-search-result-table")
		public WebElement searchResultsForGifteeTable;	
		
		@FindBy(css="#wr-guest-grid-wrapper > div.a-text-center.a-spacing-none.wr-pagination-nav.wr-product-grid-pagination-nav > ul")
		public WebElement ulPagination;		
		
		public AmazonFindARegistryPage(WebDriver driver) {
			this.driver = driver;
		}
		
		public int determineNumberOfPages() {
			List<WebElement> listItems = new ArrayList<WebElement>();
			try {
				listItems = ulPagination.findElements(By.tagName("li"));
			}catch (Exception e) {
				
			}
			int numberOfPages = listItems.size() - 2;
			if (numberOfPages <0) {
				numberOfPages = 1;
			}
			return numberOfPages;
		}
		
		public List<WebElement> getPageButtons() {
			List<WebElement> listItems = new ArrayList<WebElement>();
			try {
				listItems = ulPagination.findElements(By.tagName("li"));
			}catch (Exception e) {
				
			}
			
			if (listItems.isEmpty()) {
				return listItems;
			}
			
			listItems.remove(0);
			//remove last li
			listItems.remove(listItems.size() - 1);
			
			//get the remaining page buttons
			List<WebElement> pageButtons = new ArrayList<WebElement>();
			
			for (WebElement liElement : listItems) {
				System.out.println("button text: " + liElement.findElement(By.tagName("a")).getText());
				pageButtons.add(liElement.findElement(By.tagName("a")));
			}	
			
			return pageButtons;
		}
		
		public void inputNameOrEmail(String nameOrEmail) {		
			nameOrEmailInput.sendKeys(nameOrEmail);
			registrySearchButton.click();		
		}
		
		public String getNumOfResultsText() {
			return numOfResultsText.getText();
		}
		
		public void clickLinkTextForGiftee(String name) {
			searchResultsForGifteeTable.findElement(By.linkText(name)).click();
		}
		
		public void clickLinkByHref(String name) {
			List<WebElement> anchors = driver.findElements(By.tagName("a"));

			for (WebElement webElement : anchors) {
				if (webElement.getText().equalsIgnoreCase(name)) {
					System.out.println("anchor text: " + webElement.getText());
					webElement.click();
					break;
				}
			}
		}
		
		public List<AmazonGift> readEachItem() {		
			
			//determine how many pages...... then loop through each page
			int numberOfPages = this.determineNumberOfPages();
			System.out.println("number of pages: " + numberOfPages);
			
			List<WebElement> pageButtons = this.getPageButtons();
			System.out.println("number of page buttons: " + pageButtons.size());
			
			List<AmazonGift> allGifts = new ArrayList<AmazonGift>();
			
			if(pageButtons.isEmpty()) {
				List<AmazonGift> gifts = new ArrayList<AmazonGift>();
				
				gifts = getCardItem(gifts);
				gifts  = getDescriptionPageOne(gifts);
				gifts = getPricePageOne(gifts);
				
				allGifts.addAll(gifts);
			} else {
			
			for (int i = 0; i < pageButtons.size(); i++) {
				pageButtons.get(i).click();
				List<AmazonGift> gifts = new ArrayList<AmazonGift>();
				
				gifts = getCardItem(gifts);
				if (i == 0) {
					gifts = getDescriptionPageOne(gifts);
					gifts = getPricePageOne(gifts);
				} else {
					gifts = getDescriptionPageX(gifts);
					gifts = getPricePageX(gifts);
				}
				
				allGifts.addAll(gifts);
			}
//				if( i== pageButtons.size()) {
//					break;
//				}
			}
			
			return allGifts;		
		}

		private List<AmazonGift> getPricePageOne(List<AmazonGift> gifts) {

			//loop through and get price
			//first one on first page is odd ball
			WebElement element = driver.findElement(By.xpath("//*[@id='wr-guest-grid-wrapper']/div[1]/div[1]/a/div[4]/span "));
//			System.out.println("price: " + element.getText());	
			AmazonGift updatedGift = gifts.get(0);
			updatedGift.setPrice(element.getText());
			gifts.set(0, updatedGift);
			
			for (int x = 1; x <= gifts.size() - 1; x++) {
				element = driver.findElement(By.xpath("//*[@id='wr-guest-grid-wrapper']/div[1]/div[" + (x + 1) + "]/span/a/div[4]/span"));
//				System.out.println("price: " + element.getText());
				updatedGift = gifts.get(x);
				updatedGift.setPrice(element.getText());
				gifts.set(x, updatedGift);
			}
			
			return gifts;
		}
		
		private List<AmazonGift> getPricePageX(List<AmazonGift> gifts) {

			for (int x = 0; x < gifts.size(); x++) {
				WebElement element = driver.findElement(By.xpath("//*[@id='wr-guest-grid-wrapper']/div[1]/div[" + (x + 1) + "]/span/a/div[4]/span"));
//				System.out.println("price: " + element.getText());
				AmazonGift updatedGift = gifts.get(x);
				updatedGift.setPrice(element.getText());
				gifts.set(x, updatedGift);
			}
			
			return gifts;
		}

		private List<AmazonGift> getDescriptionPageOne(List<AmazonGift> gifts) {
			// on first page, loop through and get description and image url

			// first one on first page is odd ball
			// *[@id="wr-guest-grid-wrapper"]/div[1]/div[1]/a/div[1]/img

			WebElement element = driver
					.findElement(By.xpath("//*[@id='wr-guest-grid-wrapper']/div[1]/div[1]/a/div[1]/img"));
//			System.out.println("desc: " + element.getAttribute("alt"));
//			System.out.println("image url: " + element.getAttribute("src"));
			
			AmazonGift updatedGift = gifts.get(0);
			updatedGift.setDescription(element.getAttribute("alt"));
			updatedGift.setPictureUrl(element.getAttribute("src"));
			gifts.set(0, updatedGift);

			for (int x = 1; x <= gifts.size() - 1; x++) {
				element = driver
						.findElement(By.xpath("//*[@id='wr-guest-grid-wrapper']/div[1]/div[" +  (x + 1) + "]/span/a/div[1]/img"));
//				System.out.println("desc: " + element.getAttribute("alt"));
//				System.out.println("image url: " + element.getAttribute("src")); 
				updatedGift = gifts.get(x);
				updatedGift.setDescription(element.getAttribute("alt"));
				updatedGift.setPictureUrl(element.getAttribute("src"));
				gifts.set(x, updatedGift);
			}

			return gifts;
		}
		
		private List<AmazonGift> getDescriptionPageX(List<AmazonGift> gifts) {		
			//*[@id="wr-guest-grid-wrapper"]/div[1]/div[20]/span/a/div[1]/img
			//*[@id="wr-guest-grid-wrapper"]/div[1]/div[1]/span/a/div[1]/img
			for (int x = 0; x < gifts.size(); x++) {
				WebElement element = driver
						.findElement(By.xpath("//*[@id='wr-guest-grid-wrapper']/div[1]/div[" +  (x + 1) + "]/span/a/div[1]/img"));
//				System.out.println("desc: " + element.getAttribute("alt"));
//				System.out.println("image url: " + element.getAttribute("src")); 
				AmazonGift updatedGift = gifts.get(x);
				updatedGift.setDescription(element.getAttribute("alt"));
				updatedGift.setPictureUrl(element.getAttribute("src"));
				gifts.set(x, updatedGift);
			}

			return gifts;
		}

		private List<AmazonGift> getCardItem(List<AmazonGift> gifts) {
			// on first page, loop through and get card item number, number needed,
			// number purchased

			List<WebElement> products = driver.findElements(By.className("wr-product-grid-card-in-registry"));		
			
			System.out.println("number of items on this page: " + products.size());
//			int i = 1;
			for (WebElement product : products) {
				
				if (product.getAttribute("class")
						.equals("wr-product-grid-card wr-guest-product-grid-card wr-product-grid-card-in-registry")) {
//					System.out.println(" +++ item: " + i++);
//					System.out.println("card item: " + product.getAttribute("data-wr-product-card-item"));
//					System.out.println("   needed: " + product.getAttribute("data-wr-product-card-qty-needed"));
//					System.out.println("purchased: " + product.getAttribute("data-wr-product-card-qty-purchased"));
					AmazonGift gift = new AmazonGift();
					gift.setSku(product.getAttribute("data-wr-product-card-item"));
					gift.setNeeded(product.getAttribute("data-wr-product-card-qty-needed"));
					gift.setPurchased(product.getAttribute("data-wr-product-card-qty-purchased"));
					gifts.add(gift);
				}
			}

			return gifts;
		}
		
		/* each product appears to start with this <div> */
		
//		<div 	data-wr-product-card-item="I114ZIYSR5P11B" 
//				data-wr-product-card-item-external-id="ASIN:B00T5M9OOM|ATVPDKIKX0DER" 
//				data-wr-product-card-asin="B00T5M9OOM" 
//				data-wr-product-card-qty-needed="2" 
//				data-wr-product-card-qty-purchased="0" 
//				data-wr-product-card-offer-id="P7Zv73x8xJYhKxeEEfeW7LmCJjG20cB0i8Ug5v1%2F0HYgWH3OqiWqPa28fS9UKEFFFzMT%2Bd%2FRkXpkj7GVB3VHt61zMjbgECZHDz2DbCdZ2dqhb%2BAW1u7v2g%3D%3D" 
//				data-wr-product-card-is-gift="true" 
//				class="wr-product-grid-card wr-guest-product-grid-card wr-product-grid-card-in-registry" 
//				style="min-height: 361px;">
		
		/* description and image url */
		//*[@id="wr-guest-grid-wrapper"]/div[1]/div[1]/a/div[1]/img      - first one is the gift card, do we care?
		//*[@id="wr-guest-grid-wrapper"]/div[1]/div[3]/span/a/div[1]/img
		//*[@id="wr-guest-grid-wrapper"]/div[1]/div[6]/span/a/div[1]/img
		//*[@id="wr-guest-grid-wrapper"]/div[1]/div[60]/span/a/div[1]/img
		
		
		
		
		/* price */
		//*[@id="wr-guest-grid-wrapper"]/div[1]/div[1]/a/div[4]/span       -first one is the gift care, price is odd to "from 25.00"
		//*[@id="wr-guest-grid-wrapper"]/div[1]/div[3]/span/a/div[4]/span
		//*[@id="wr-guest-grid-wrapper"]/div[1]/div[60]/span/a/div[4]/span	
		
	
}
