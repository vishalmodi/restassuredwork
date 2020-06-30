import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class User {
    // https://reqres.in/
    String baseURI = null;

    User(String baseURI){
        this.baseURI = baseURI;
        RestAssured.baseURI = this.baseURI;
    }

    public Response getUserList(int pageNo)
    {
        String userURI = pageNo <= 0 ? "/users" : "/users?page=" + Integer.toString(pageNo);

        //Request object
        RequestSpecification request=RestAssured.given();

        //Response object
        Response response=request.request(Method.GET,userURI);

        return response;
    }

    public Response getUser(int userID)
    {
        if (userID == 0){
            throw new IllegalArgumentException("userID parameter can not be zero.");
        }

        String userURI = "/users/" + Integer.toString(userID);

        //Request object
        RequestSpecification request=RestAssured.given();

        //Response object
        Response response=request.request(Method.GET,userURI);

        return response;
    }

}
