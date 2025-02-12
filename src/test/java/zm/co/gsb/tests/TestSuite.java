package zm.co.gsb.tests;

import com.example.base.Test;
import com.example.base.Then;
import com.example.base.When;
import com.example.components.NavigationBar;
import com.example.constants.Urls;
import com.example.models.Product;
import com.example.pages.CartPage;
import com.example.pages.ShopPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import java.util.Arrays;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class TestSuite extends Test {

    @BeforeMethod
    public void refresh() {
        Test.open(Urls.HOME_PAGE);
    }

    @DataProvider(name = "products")
    public Object[][] products() {
        return new Object[][]{
                {
                        new Product("Funny Cow", 2),
                        new Product("Fluffy Bunny", 1)
                },
                {
                        new Product("Stuffed Frog", 2),
                        new Product("Fluffy Bunny", 2),
                        new Product("Valentine Bear", 3)
                }
        };
    }

    /**
     *
     * @param products
     */
    @org.testng.annotations.Test(dataProvider = "products")
    public void testCase3_4(Product... products) {
        When.clickOn(NavigationBar.SHOP);

        for (Product product : products) {
            product.setPrice(Test.getTextOf(
                    ShopPage.Item.priceOf(product.getName())));

            for (int i = 0; i < product.getQuantity(); i++) {
                Test.clickOn(ShopPage.Item.buyButtonFor(product.getName()));
            }
        }

        Then.clickOn(NavigationBar.CART);

        //Verify total count of items
        assertThat(parseInt(
                        Test.getTextOf(CartPage.ITEMS_TOTAL_COUNT)),
                equalTo(
                        Arrays.stream(products)
                                .map(Product::getQuantity)
                                .mapToInt(Integer::intValue).sum()));

        //For each item verify quantity, price, subtotal
        for (Product product : products) {
            assertThat(parseInt(
                            Test.getAttributeOf(
                                    CartPage.Item
                                            .quantityOf(product.getName()), "value")),
                    equalTo(
                            product.getQuantity()));

            assertThat(
                    Test.getTextOf(
                            CartPage.Item
                                    .priceOf(product.getName())),
                    equalTo(product.getPriceString()));

            assertThat(
                    Test.getTextOf(
                            CartPage.Item
                                    .subtotalOf(product.getName())),
                    equalTo(product.getSubtotalString()));
        }

        assertThat(parseDouble(
                        Test.getTextOf(CartPage.ITEMS_TOTAL_PRICE)
                                .replaceAll("Total: ", "")),
                equalTo(
                        Arrays.stream(products)
                                .map(Product::getSubtotalValue)
                                .mapToDouble(Double::doubleValue).sum()));
    }
}