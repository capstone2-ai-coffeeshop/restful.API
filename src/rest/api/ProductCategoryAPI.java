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

import bean.ProductCategory;
import bo.ProductCategoryBO;

@Path("/categorys")
public class ProductCategoryAPI {
	
	private static final String SUCCESS_RESULT = "{\"status\":\"success\"}";
	private static final String FAILURE_RESULT = "{\"status\":\"fail\"}";

	ProductCategoryBO categoryBO = new ProductCategoryBO();

	@GET
	@Path("/action-categorys")
	@Produces("application/json")
	public List<ProductCategory> getListCategory() {
		List<ProductCategory> list = categoryBO.getListCategory();
		return list;
	}

	@GET
	@Path("/action-categorys/{id}")
	@Produces("application/json")
	public ProductCategory getCategory(@PathParam("id") String id) {
		return categoryBO.getCategory(id);
	}

	@POST
	@Path("/action-categorys")
	@Produces("application/json")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String insertCategory(@FormParam("name") String name, @FormParam("description") String description,
			@FormParam("created_at") String created_at,
			@Context HttpServletResponse servletResponse) {

		if (categoryBO.insertCategory(name, description, created_at)) {
			// return "{\"status\":\"true\"}";
			return SUCCESS_RESULT;
		} else {
			// return "{\"status\":\"false\"}";
			return FAILURE_RESULT;
		}
	}

	@PUT
	@Path("/action-categorys")
	@Produces("application/json")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String updateCategory(@FormParam("id") String id, @FormParam("name") String name,
			@FormParam("description") String description, @FormParam("created_at") String created_at,
			@Context HttpServletResponse servletResponse) {
		if (categoryBO.updateCategory(id, name, description, created_at)) {
			return SUCCESS_RESULT;
		} else {
			return FAILURE_RESULT;
		}
	}

	@DELETE
	@Path("/action-categorys/{id}")
	@Produces("application/json")
	public String deleteCategory(@PathParam("id") String id) {
		if (categoryBO.deleteCategory(id)) {
			return SUCCESS_RESULT;
		} else {
			return FAILURE_RESULT;
		}
	}

	@OPTIONS
	@Path("/action-categorys")
	@Produces(MediaType.APPLICATION_XML)
	public String getSupportedOperations() {
		return "<operations>GET, PUT, POST, DELETE</operations>";
	}

}
