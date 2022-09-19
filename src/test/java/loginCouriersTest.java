import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.equalTo;

import static io.restassured.RestAssured.given;

public class loginCouriersTest {
    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
    }

    @Test
    public void loginCourier() {
        Couriers Bi = new Couriers("Bubur", "1234");
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .body(Bi)
                        .when()
                        .post("/api/v1/courier/login");
        response.then().assertThat().body("id", notNullValue())
                .and()
                .statusCode(200);
    }

    @Test
    public void noLoginCourier() {
        Couriers Bi = new Couriers("1234");
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .body(Bi)
                        .when()
                        .post("/api/v1/courier/login");
        response.then().assertThat().body("message", equalTo("Недостаточно данных для входа"))
                .and()
                .statusCode(400);
    }

    @Test
    public void invalidLoginSameCourier() {
        Couriers Bi = new Couriers("Buburhuhiij", "12345878787878878");
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .body(Bi)
                        .when()
                        .post("/api/v1/courier/login");
        response.then().assertThat().body("message", equalTo("Учетная запись не найдена"))
                .and()
                .statusCode(404);
    }
}