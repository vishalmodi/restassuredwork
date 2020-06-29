import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class TestUserAPI {
    User user;

    @BeforeClass
    void setupUserAPI(){
        user = new User("https://reqres.in/api/");
    }

    @Test(description = "Verify the User API without Page No parameter.")
    void testGetUsersCallWithoutPageParameter(){
        // Call User API to get the list of users.
        Response response = user.getUserList(0);

        // Verify Http status code
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);

        // Verify row count
        ArrayList<LinkedHashMap> userRows = response.jsonPath().get("data");
        Assert.assertEquals(userRows.size(), 6);
    }

    @Test(description = "Verify the User API with Page No parameter.")
    void testGetUsersCallWithPageParameter(){
        // Call User API with Page no parameter to get the list of users.
        Response response = user.getUserList(2);

        // Verify Http status code
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);

        // Verify the page no
        Assert.assertEquals(response.jsonPath().get("page").toString(), "2");

        // Verify row count
        ArrayList<LinkedHashMap> userRows = response.jsonPath().get("data");
        Assert.assertEquals(userRows.size(), 6);
    }
}
