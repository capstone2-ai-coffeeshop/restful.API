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
import bean.BillItems;
import bo.AccountBO;
import bo.BillBO;
import library.md5;

@Path("/bills")
public class BillAPI {

	private static final String SUCCESS_RESULT = "{\"status\":\"success\"}";
	private static final String FAILURE_RESULT = "{\"status\":\"fail\"}";

	BillBO billBO = new BillBO();

	@GET
	@Path("/action-bills")
	@Produces("application/json")
	public List<BillItems> getListAccounts() {
		List<BillItems> list = billBO.getListBill();
		return list;
	}
	
	@POST
	@Path("/action-bills")
	@Produces("application/json")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String insertBill(@FormParam("staff_id") String staff_id, @FormParam("customer_id") String customer_id,
			@FormParam("table_id") String table_id, @FormParam("created_at") String created_at, @Context HttpServletResponse servletResponse) {
    
		if (billBO.insertBill(staff_id, customer_id, table_id, created_at)) {
			// return "{\"status\":\"true\"}";
			return SUCCESS_RESULT;
		} else {
			// return "{\"status\":\"false\"}";
			return FAILURE_RESULT;
		}
	}
	
	@POST
	@Path("/action-billinfo")
	@Produces("application/json")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String insertBillInfo(@FormParam("bill_id") String bill_id, @FormParam("product_id") String product_id,
			@FormParam("quantity") String quantity, @FormParam("session") String session,
			@FormParam("weather") String weather, @FormParam("discount") String discount,
			@FormParam("description") String description, @Context HttpServletResponse servletResponse) {
    
		if (billBO.insertBillInfo(bill_id, product_id, quantity, session, weather, discount, description)) {
			// return "{\"status\":\"true\"}";
			return SUCCESS_RESULT;
		} else {
			// return "{\"status\":\"false\"}";
			return FAILURE_RESULT;
		}
	}

	@OPTIONS
	@Path("/action-bills")
	@Produces(MediaType.APPLICATION_XML)
	public String getSupportedOperations() {
		return "<operations>GET, POST</operations>";
	}
}
