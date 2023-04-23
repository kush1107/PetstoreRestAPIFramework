package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpints.UserEndPoints_Properties;
import api.payloads.User;
import io.restassured.response.Response;

public class UserTests_Properties {

	Faker faker;
	User userPayload;
	
	public Logger logger; // for logs
	
	@BeforeClass
	public void setup()
	{
		faker=new Faker();
		userPayload=new User();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		//logs
		logger= LogManager.getLogger(this.getClass());
		
		logger.debug("debugging.....");
		
	}
	
	@Test(priority=1)
	public void testPostUser()
	{
		logger.info("********** Creating user  ***************");
		Response response=UserEndPoints_Properties.createUser(userPayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(),200);
		
		logger.info("**********User is created  ***************");
			
	}
	
	@Test(priority=2)
	public void testGetUserByName()
	{
		logger.info("********** Reading User Info ***************");
		
		Response response=UserEndPoints_Properties.readUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
		
		logger.info("**********User info  is displayed ***************");
		
	}
	
	@Test(priority=3)
	public void testUpdateUserByName()
	{
		logger.info("********** Updating User ***************");
		
		//update data using payload
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		
		Response response=UserEndPoints_Properties.updateUser(this.userPayload.getUsername(),userPayload);
		response.then().log().body();
				
		Assert.assertEquals(response.getStatusCode(),200);
		
		logger.info("********** User updated ***************");
		//Checking data after update
		Response responseAfterupdate=UserEndPoints_Properties.readUser(this.userPayload.getUsername());
		Assert.assertEquals(responseAfterupdate.getStatusCode(),200);
			
	}
	
	@Test(priority=4)
	public void testDeleteUserByName()
	{
		logger.info("**********   Deleting User  ***************");
		
		Response response=UserEndPoints_Properties.deleteUser(this.userPayload.getUsername());
		Assert.assertEquals(response.getStatusCode(),200);
		
		logger.info("********** User deleted ***************");
	}
	
	
}
