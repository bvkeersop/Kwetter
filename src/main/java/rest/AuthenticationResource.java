package rest;

import dto.LoginDto;
import entities.Profile;
import filters.AuthenticationFilter.IAuthenticatedUser;
import security.AuthenticatedUser;
import services.AuthenticationService;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/authentication")
public class AuthenticationResource {

	@EJB
	AuthenticationService authenticationService;

	@Inject
	@IAuthenticatedUser
	AuthenticatedUser authenticatedUser;

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/login")
	public Response Login(Profile profile) {
		try {
			LoginDto loginDto = authenticationService
					.authenticate(profile.getEmail(), profile.getPassword());

			return Response.ok(loginDto).header("Access-Control-Allow-Origin", "*").build();

		} catch (Exception e) {
			return Response.status(Response.Status.FORBIDDEN).build();
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/logout")
	public Response Logout(Profile profile) {
		try {
			authenticationService.deleteToken(
					authenticationService.getProfileByToken(profile.getToken()));
			return Response.ok().build();
		}
		catch (Exception e){
			return Response.serverError().build();
		}
	}
}
