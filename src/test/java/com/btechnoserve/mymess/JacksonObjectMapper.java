package com.btechnoserve.mymess;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;

import com.btechnoserve.mymess.model.CreatedUpdated;
import com.btechnoserve.mymess.model.Mess;
import com.btechnoserve.mymess.model.User;
import com.btechnoserve.mymess.model.UserProfile;
import com.btechnoserve.mymess.model.UserRole;

public class JacksonObjectMapper {

	public static void main(String[] args) {

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);

		User user = new User();
		UserProfile profile = new UserProfile();
		user.setUserProfile(profile);
		UserRole role = new UserRole();
		user.setUserRole(role);
		Mess mess = new Mess();
		user.setMess(mess);
		CreatedUpdated createdUpdated = new CreatedUpdated();
		user.setCreatedUpdated(createdUpdated);

		try {
			System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(user));
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
