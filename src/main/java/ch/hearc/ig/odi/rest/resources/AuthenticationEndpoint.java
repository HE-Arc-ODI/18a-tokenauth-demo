package ch.hearc.ig.odi.rest.resources;

import ch.hearc.ig.odi.exception.AuthenticationException;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/authentication")
public class AuthenticationEndpoint {

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  public Response authenticateUser(@FormParam("username") String username,
      @FormParam("password") String password) {

    try {

      // Authenticate the user using the credentials provided
      authenticate(username, password);

      // Issue a token for the user
      String token = issueToken(username);

      // Return the token on the response
      return Response.ok(token).build();

    } catch (Exception e) {
      return Response.status(Response.Status.FORBIDDEN).build();
    }
  }

  private void authenticate(String username, String password) throws Exception {
    // Authenticate against a database, LDAP, file or whatever
    // Throw an Exception if the credentials are invalid
    if (!(username.equals("bob") && password.equals("1234"))) {
      throw new AuthenticationException("invalid credentials");
    }


  }

  private String issueToken(String username) {
    // Issue a token (can be a random String persisted to a database or a JWT token)
    // The issued token must be associated to a user
    // Return the issued token
    return "123456";
  }
}