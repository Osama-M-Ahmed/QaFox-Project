package Tests;

import Base.TestBase;
import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("Shopping Cart")
@Feature("Add to Cart")
public class AddToCartTest extends TestBase {

    @Test
    @Description("Validate that user can add MacBook to cart successfully")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Add product to cart")
    public void userCanAddMacBookToCart() {
        productPage.navigateToMacBook();
        productPage.addToCart();
        productPage.verifyProductAddedSuccessfully("Success: You have added MacBook to your shopping cart!");
        productPage.verifyCartTotalItems("1 item");
    }


    @Test
    @Description("Validate that guest user can add product to cart")
    @Severity(SeverityLevel.NORMAL)
    @Story("Guest user add product to cart")
    public void guestUserCanAddToCart() {
        productPage.navigateToMacBook();
        productPage.addToCart();
        productPage.verifyProductAddedSuccessfully("Success: You have added MacBook to your shopping cart!");
    }



    @Test
    @Description("Validate that user cannot add zero quantity")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Add invalid quantity")
    public void userCannotAddZeroQuantity() {
        productPage.navigateToMacBook();
        productPage.setQuantity("0");
        productPage.addToCart();
        productPage.verifyErrorMessage("Invalid quantity");
    }

    @Test
    @Description("Validate that invalid coupon cannot be applied")
    @Severity(SeverityLevel.NORMAL)
    @Story("Apply coupon")
    public void userCannotApplyInvalidCoupon() {
        productPage.navigateToMacBook();
        productPage.addToCart();
        productPage.viewCart();
        productPage.applyCoupon("INVALID123");
        productPage.verifyErrorMessage("Warning: Coupon is either invalid, expired or reached its usage limit!");
    }

    @Test
    @Description("Validate that coupon cannot be applied to empty cart")
    @Severity(SeverityLevel.NORMAL)
    @Story("Apply coupon")
    public void userCannotApplyCouponToEmptyCart() {
        productPage.openCart();
        productPage.verifyUserCannotAddCouponWhenCartEmpty("Your shopping cart is empty!");
    }
}
