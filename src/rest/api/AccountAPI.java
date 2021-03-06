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

import bean.Account;
import bo.AccountBO;
import library.md5;

@Path("/accounts")

public class AccountAPI {

	private static final String SUCCESS_RESULT = "{\"status\":\"success\"}";
	private static final String FAILURE_RESULT = "{\"status\":\"fail\"}";

	AccountBO accountBO = new AccountBO();

	@GET
	@Path("/action-accounts")
	@Produces("application/json")
	public List<Account> getListAccounts() {
		List<Account> list = accountBO.getListAccount();
		return list;
	}

	@GET
	@Path("/action-accounts/{id}")
	@Produces("application/json")
	public Account getAccount(@PathParam("id") String id) {
		return accountBO.getAccount(id);
	}
	
//	@GET
//	@Path("/action-accounts/{username}")
//	@Produces("application/json")
//	public Account getAccountByUsername(@PathParam("username") String username) {
//		return accountBO.getAccountByUsername(username);
//	}

	@POST
	@Path("/action-accounts")
	@Produces("application/json")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String insertAccount(@FormParam("username") String username, @FormParam("password") String password,
			@FormParam("role") String role, @Context HttpServletResponse servletResponse) {

		if (accountBO.insertAccount(username, password, role)) {
			// return "{\"status\":\"true\"}";
			return SUCCESS_RESULT;
		} else {
			// return "{\"status\":\"false\"}";
			return FAILURE_RESULT;
		}
	}

	@PUT
	@Path("/action-accounts")
	@Produces("application/json")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String updateAccount(@FormParam("username") String username, @FormParam("password") String password,
			@FormParam("newpassword") String newpassword, @FormParam("cfpassword") String cfpassword,
			@Context HttpServletResponse servletResponse) {
		if (newpassword.equals(cfpassword) && newpassword != null) {
			if (accountBO.updateAccount(username, password, cfpassword)) {
				return SUCCESS_RESULT;
			} else {
				return FAILURE_RESULT;
			}
		} else {
			return FAILURE_RESULT;
		}
	}

	@DELETE
	@Path("/action-accounts/{id}")
	@Produces("application/json")
	public String deleteAccount(@PathParam("id") String id) {
		if (accountBO.deleteAccount(id)) {
			return SUCCESS_RESULT;
		} else {
			return FAILURE_RESULT;
		}
	}

	@OPTIONS
	@Path("/action-accounts")
	@Produces(MediaType.APPLICATION_XML)
	public String getSupportedOperations() {
		return "<operations>GET, PUT, POST, DELETE</operations>";
	}
	
	@POST
	@Path("/login")
	@Produces("application/json")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String checkLogin(@FormParam("username") String username, @FormParam("password") String password,
			@Context HttpServletResponse servletResponse) {

		if (accountBO.checkLogin(username, password)) {
			// return "{\"status\":\"true\"}";
			return SUCCESS_RESULT;
		} else {
			// return "{\"status\":\"false\"}";
			return FAILURE_RESULT;
		}
	}
	
}
