package rest.api;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import bean.Customer;
import bo.CustomerBO;

@Path("/customers")
public class CustomerAPI {
	
	private static final String SUCCESS_RESULT = "{\"status\":\"success\"}";
	private static final String FAILURE_RESULT = "{\"status\":\"fail\"}";
	
	CustomerBO customerBO = new CustomerBO();

	@GET
	@Path("/action-customers")
	@Produces("application/json")
	public List<Customer> getListCustomer() {
		List<Customer> list = customerBO.getListCustomer();
		return list;
	}

	@GET
	@Path("/action-customers/{id}")
	@Produces("application/json")
	public Customer getCustomer(@PathParam("id") String id) {
		return customerBO.getCustomer(id);
	}

	@POST
	@Path("/action-customers")
	@Produces("application/json")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String insertCustomer(@FormParam("fullname") String fullname, @FormParam("email") String email,
			@FormParam("gender") String gender, @FormParam("dateofbirth") String dateOfBirth,
			@FormParam("phone") String phone,
			@Context HttpServletResponse servletResponse) {

		if (customerBO.insertCustomer(fullname, email, gender, dateOfBirth, phone)) {
			return SUCCESS_RESULT;
		} else {
			return FAILURE_RESULT;
		}
	}

	@PUT
	@Path("/action-customers")
	@Produces("application/json")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String updateCustomer(@FormParam("id") String id, @FormParam("fullname") String fullname,
			@FormParam("email") String email, @FormParam("gender") String gender,
			@FormParam("dateofbirth") String dateOfBirth, @FormParam("phone") String phone,
			@Context HttpServletResponse servletResponse) {
		if (customerBO.updateCustomer(id, fullname, email, gender, dateOfBirth, phone)) {
			return SUCCESS_RESULT;
		} else {
			return FAILURE_RESULT;
		}
	}
	
	@OPTIONS
	@Path("/action-customers")
	@Produces(MediaType.APPLICATION_XML)
	public String getSupportedOperations() {
		return "<operations>GET, PUT, POST, DELETE</operations>";
	}
}
