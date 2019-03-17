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

import bean.Products;
import bo.ProductsBO;

@Path("/products")
public class ProductsAPI {

	private static final String SUCCESS_RESULT = "{\"status\":\"success\"}";
	private static final String FAILURE_RESULT = "{\"status\":\"fail\"}";

	ProductsBO productsBO = new ProductsBO();

	@GET
	@Path("/action-products")
	@Produces("application/json")
	public List<Products> getListProducts() {
		List<Products> list = productsBO.getListProducts();
		return list;
	}

	@GET
	@Path("/action-products/{id}")
	@Produces("application/json")
	public Products getProduct(@PathParam("id") String id) {
		return productsBO.getProduct(id);
	}

	@POST
	@Path("/action-products")
	@Produces("application/json")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String insertProduct(@FormParam("name") String name, @FormParam("categoryid") String categoryId,
			@FormParam("unitprice") String unitPrice, @FormParam("description") String description,
			@FormParam("status") String status, @FormParam("createdat") String createdAt,
			@Context HttpServletResponse servletResponse) {

		if (productsBO.insertProduct(name, categoryId, unitPrice, description, status, createdAt)) {
			// return "{\"status\":\"true\"}";
			return SUCCESS_RESULT;
		} else {
			// return "{\"status\":\"false\"}";
			return FAILURE_RESULT;
		}
	}

	@PUT
	@Path("/action-products")
	@Produces("application/json")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String updateProduct(@FormParam("id") String id, @FormParam("name") String name,
			@FormParam("categoryid") String categoryId, @FormParam("unitprice") String unitPrice,
			@FormParam("description") String description, @FormParam("status") String status, 
			@FormParam("createdat") String createdAt,
			@Context HttpServletResponse servletResponse) {
		if (productsBO.updateProduct(id, name, categoryId, unitPrice, description, status, createdAt)) {
			return SUCCESS_RESULT;
		} else {
			return FAILURE_RESULT;
		}
	}

	@DELETE
	@Path("/action-products/{id}")
	@Produces("application/json")
	public String deleteProduct(@PathParam("id") String id) {
		if (productsBO.deleteProduct(id)) {
			return SUCCESS_RESULT;
		} else {
			return FAILURE_RESULT;
		}
	}

	@OPTIONS
	@Path("/action-products")
	@Produces(MediaType.APPLICATION_XML)
	public String getSupportedOperations() {
		return "<operations>GET, PUT, POST, DELETE</operations>";
	}
}
