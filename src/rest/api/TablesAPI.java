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

import bean.Tables;
import bo.TablesBO;

@Path("/tables")
public class TablesAPI {
	
	private static final String SUCCESS_RESULT = "{\"status\":\"success\"}";
	private static final String FAILURE_RESULT = "{\"status\":\"fail\"}";

	TablesBO tablesBO = new TablesBO();

	@GET
	@Path("/action-tables")
	@Produces("application/json")
	public List<Tables> getListTables() {
		List<Tables> list = tablesBO.getListTables();
		return list;
	}

	@GET
	@Path("/action-tables/{id}")
	@Produces("application/json")
	public Tables getTable(@PathParam("id") String id) {
		return tablesBO.getTable(id);
	}

	@POST
	@Path("/action-tables")
	@Produces("application/json")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String insertTable(@FormParam("quantityofcustomer") String quantityOfCustomer, @FormParam("description") String description,
			@FormParam("status") String status,
			@Context HttpServletResponse servletResponse) {

		if (tablesBO.insertTable(quantityOfCustomer, description, status)) {
			// return "{\"status\":\"true\"}";
			return SUCCESS_RESULT;
		} else {
			// return "{\"status\":\"false\"}";
			return FAILURE_RESULT;
		}
	}

	@PUT
	@Path("/action-tables")
	@Produces("application/json")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String updateTable(@FormParam("id") String id, @FormParam("quantityOfCustomer") String quantityOfCustomer,
			@FormParam("description") String description, @FormParam("status") String status,
			@Context HttpServletResponse servletResponse) {
		if (tablesBO.updateTable(id, quantityOfCustomer, description, status)) {
			return SUCCESS_RESULT;
		} else {
			return FAILURE_RESULT;
		}
	}

	@DELETE
	@Path("/action-tables/{id}")
	@Produces("application/json")
	public String deleteTable(@PathParam("id") String id) {
		if (tablesBO.deleteTable(id)) {
			return SUCCESS_RESULT;
		} else {
			return FAILURE_RESULT;
		}
	}

	@OPTIONS
	@Path("/action-tables")
	@Produces(MediaType.APPLICATION_XML)
	public String getSupportedOperations() {
		return "<operations>GET, PUT, POST, DELETE</operations>";
	}
}
