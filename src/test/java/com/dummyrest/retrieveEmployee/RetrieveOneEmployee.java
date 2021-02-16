package com.dummyrest.retrieveEmployee;
import com.dummyrest.pojo.EmployeeData;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import static io.restassured.RestAssured.* ;

public class RetrieveOneEmployee {

    static int id = 5;

    @BeforeAll
    public static void setUp() {
        baseURI = "http://dummy.restapiexample.com";
        basePath = "/api/v1/";

    }

    @DisplayName("Get One Employee Test")
    @Test
    public void getOneEmployee() {

        Response response = given().
                pathParam("id", id).
                log().all().
                when().
                get("employee/{id}").
                then().
                extract().response();
       int statusCode =  response.statusCode();
        if(statusCode == 200){
            System.out.println("Succesfull response");
        }else{
            System.out.println("Error!");
        }
    }

    @DisplayName("Get One Employee Information")
    @Test
    public void getOneEmployeeInfo() {

        JsonPath jp =  given().
                pathParam("id", id).
                when().
                get("employee/{id}").
                then().
                log().body().
                statusCode(200).
                extract().jsonPath();

         String employee_name = jp.getString("data.employee_name");
         String employee_age= jp.getString("data.employee_age");
         String employee_salary = jp.getString("data.employee_salary");

        EmployeeData ed = new EmployeeData(employee_name,employee_salary,employee_age);

        System.out.println("Employe Relevant Information: "+"\n" + ed);


    }


}
