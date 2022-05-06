package com.nopcommerce.commons;

import com.nopcommerce.pageObjects.*;
import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {
	public static HomePO getHomePage(WebDriver driver) {
		return new HomePO(driver);
	}

	public static LoginPO getLoginPage(WebDriver driver) {
		return new LoginPO(driver);
	}

	public static RegisterPO getRegisterPage(WebDriver driver) {
		return new RegisterPO(driver);
	}

	public static MyAccountPO getMyAccountPage(WebDriver driver) {
		return new MyAccountPO(driver);
	}

	public static CustomerInfoPO getCustomerInfoPage(WebDriver driver) {
		return new CustomerInfoPO(driver);
	}
	
	public static AddressPO getAddressPage(WebDriver driver) {
		return new AddressPO(driver);
	}

	public static SearchPO getSearchPage(WebDriver driver) {
		return new SearchPO(driver);
	}

	public static ChangePasswordPO getChangePasswordPage(WebDriver driver) {
		return new ChangePasswordPO(driver);
	}

	public static NotebooksPO getNotebooksPage(WebDriver driver) {
		return new NotebooksPO(driver);
	}

	public static ProductDetailPO getProductDetailPage(WebDriver driver) {
		return new ProductDetailPO(driver);
	}

	public static WishListPO getWishListPage(WebDriver driver) {
		return new WishListPO(driver);
	}

	public static ShoppingCartPO getShoppingCartPage(WebDriver driver) {
		return new ShoppingCartPO(driver);
	}

	public static WishlistComparePO getWishListComparePage(WebDriver driver) {
		return new WishlistComparePO(driver);
	}
}
