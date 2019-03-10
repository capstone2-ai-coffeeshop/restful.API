package test;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

import bean.Staffs;
import bean.Status;

public class TestAPI {

	private Client client;
	private String REST_SERVICE_URL = "http://localhost:8080/Restful_API_AI_CoffeeShop/staffs/action-staffs";
	private static final String SUCCESS_RESULT = "{\"status\":\"success\"}";
	private static final String PASS = "{\"status\": \"pass\"}";
	private static final String FAIL = "{\"status\": \"fail\"}";

	private TestAPI() {
		this.client = ClientBuilder.newClient();
	}

	private String testGetAllUsers() {
		GenericType<List<Staffs>> list = new GenericType<List<Staffs>>() {
		};
		List<Staffs> staffs = client.target(REST_SERVICE_URL).request("application/json").get(list);
		String result = PASS;
		if (staffs.isEmpty()) {
			result = FAIL;
		}
		System.out.println("Test case name: testGetAllUsers, Result: " + result);
		return result;
	}

	private String testGetStaff() {
		Staffs staff = client.target(REST_SERVICE_URL).path("/{id}").resolveTemplate("id", "1")
				.request("application/json").get(Staffs.class);
		String result = FAIL;
		if (staff != null) {
			result = PASS;
		}
		System.out.println("Test case name: testGetStaff, Result: " + result);
		return result;
	}

	private String testInsertStaff() {
		Form form = new Form();
		form.param("fullname", "Lê Văn C");
		form.param("email", "clen@gmai.com");
		form.param("gender", "1");
		form.param("dateofbirth", "20/10/1997");
		form.param("phone", "0337272727");
		form.param("accountid", "1");
		String callResult = client.target(REST_SERVICE_URL).request("application/json")
				.post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE), String.class);

		String result = PASS;
		if (!SUCCESS_RESULT.equals(callResult)) {
			result = FAIL;
		}

		return result;
	}

	private String testUpdateStaff() {
		Form form = new Form();
		form.param("id", "5");
		form.param("fullname", "Nguyễn Trọng Hiếu");
		form.param("email", "tronghieuguyen1003@gmai.com");
		form.param("gender", "1");
		form.param("dateofbirth", "10/03/1997");
		form.param("phone", "0336660207");

		String callResult = client.target(REST_SERVICE_URL).request("application/json")
				.put(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE), String.class);

		String result = PASS;
		if (!SUCCESS_RESULT.equals(callResult)) {
			result = FAIL;
		}

		return result;
	}

	private String testDeleteStaff() {
		String callResult = client.target(REST_SERVICE_URL).path("/{id}").resolveTemplate("id", "5")
				.request("application/json").delete(String.class);

		String result = PASS;
		if (!SUCCESS_RESULT.equals(callResult)) {
			result = FAIL;
		}

		System.out.println("Test case name: testDeleteUser, Result: " + result);
		return result;
	}

	public static void main(String[] args) {
		TestAPI test = new TestAPI();
		Gson g = new Gson();
		Status status = g.fromJson(test.testInsertStaff(), Status.class);
		System.out.println(status.getStatus());
	}

}
