package rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import domain.Person;
import domain.services.PersonService;

@Path("/people")
public class PeopleResources {

	private PersonService db = new PersonService();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Person> getAll(){
		return db.getAll();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response Add(Person person){
		db.add(person);
		return Response.ok(person.getId()).build();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@PathParam("id") int id){
		Person result = db.get(id);
		if(result==null){
			return Response.status(404).build();
		}
		return Response.ok(result).build();
	}
}

