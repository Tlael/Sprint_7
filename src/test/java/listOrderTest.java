import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;

public class listOrderTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
    }

    @Test
    public void listOrders() {
        given()
                .get("/api/v1/orders")
                .then().assertThat().body("orders.id", notNullValue())
                .and()
                .statusCode(200);
    }
}