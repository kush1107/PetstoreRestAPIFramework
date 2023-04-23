package api.endpints;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import api.payloads.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPoints_Properties {

	private static Properties prop;

    static {
        prop = new Properties();
        try (InputStream input = UserEndPoints_Properties.class.getResourceAsStream("/routes.properties")) {
            prop.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load properties file", e);
        }
    }
	
	
	public static Response createUser(User payload)
	{
		Response response=given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
		.when()
			.post(prop.getProperty("post_url"));
			
		return response;
	}
	
	
	public static Response readUser(String userName)
	{
		Response response=given()
							.pathParam("username",userName)
						.when()
						.get(prop.getProperty("get_url"));
			
				return response;
	}
	
	
	public static Response updateUser(String userName, User payload)
	{
		Response response=given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.pathParam("username", userName)
			.body(payload)
		.when()
			.put(prop.getProperty("update_url"));
			
		return response;
	}
	
	
	public static Response deleteUser(String userName)
	{
		Response response=given()
							.pathParam("username",userName)
						.when()
						.delete(prop.getProperty("delete_url"));
			
		return response;
	}	
			
	
}
