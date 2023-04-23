package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpints.UserEndPoints;
import api.payloads.User;
import api.utilities.CSV_DataProvider;
import io.restassured.response.Response;

public class DataDrivenTests {

	@Test(priority=1, dataProvider="UsersData", dataProviderClass= CSV_DataProvider.class )
	public void testPostuser(String userID, String userName,String fname,String lname,String useremail,String pwd,String ph)
	{
		User userPayload=new User();
		
		userPayload.setId(Integer.parseInt(userID));
		userPayload.setUsername(userName);
		userPayload.setFirstName(fname);
		userPayload.setLastName(lname);
		userPayload.setEmail(useremail);
		userPayload.setPassword(pwd);
		userPayload.setPhone(ph);
		
		Response response=UserEndPoints.createUser(userPayload);
		Assert.assertEquals(response.getStatusCode(),200);
			
	}
	
	@Test(priority=2, dataProvider="UserNames", dataProviderClass= CSV_DataProvider.class)
	public void testDeleteUserByName(String userName)   // Username should be same as Username at time of creations , Better to use fake API it will save your time
	{
			Response response=UserEndPoints.deleteUser(userName);
			Assert.assertEquals(response.getStatusCode(),200);	
	
	}
}
