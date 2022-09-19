import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;

import static io.restassured.RestAssured.given;

public class createCouriersTest {
    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
    }

    @Test
    public void createCourier() {
        Couriers Bi = new Couriers("Bubura", "1234", "Lesha");
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .body(Bi)
                        .when()
                        .post("/api/v1/courier");
        response.then().assertThat().body("ok", equalTo(true))
                .and()
                .statusCode(201);
    }

    @Test
    public void createSameCourier() {
        Couriers Bi = new Couriers("Bubura", "1234", "Lesha");
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .body(Bi)
                        .when()
                        .post("/api/v1/courier");
        response.then().assertThat().body("message", equalTo("Этот логин уже используется. Попробуйте другой."))
                .and()
                .statusCode(409);
    }

    @Test
    public void createInvalidCourier() {
        Couriers Bi = new Couriers("Bubur", "1234");
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .body(Bi)
                        .when()
                        .post("/api/v1/courier");
        response.then().assertThat().body("message", equalTo("Недостаточно данных для создания учетной записи"))
                .and()
                .statusCode(400);
    }
}