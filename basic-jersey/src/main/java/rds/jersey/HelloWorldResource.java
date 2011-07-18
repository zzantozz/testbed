package rds.jersey;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.Path;

@Path("/test")
public class HelloWorldResource {

 @GET
 @Produces("text/html") //Modified after comment from @ProduceMime
 public String getMessage( ) {
  return "hello";
 }
}