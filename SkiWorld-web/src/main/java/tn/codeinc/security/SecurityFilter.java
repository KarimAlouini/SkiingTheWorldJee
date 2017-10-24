package tn.codeinc.security;

import java.io.IOException;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;
import tn.codeinc.client.CurrentUserLocal;
import tn.codeinc.persistance.AccessToken;
import tn.codeinc.services.TokenManagementLocal;
import tn.codeinc.util.ResponseMessage;

@Provider
public class SecurityFilter implements ContainerRequestFilter {
	@Inject
	private TokenManagementLocal tokens;
	@Inject
	CurrentUserLocal currentUser;
	private static final String AUTHORIZATION_HEADER = "Authorization";
	private static final String AUTHORIZATION_HEADER_PREFIX = "CodeInc ";
	private static final String SECURED_URI_PREFIX = "secured";

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {

		if (requestContext.getUriInfo().getPath().contains(SECURED_URI_PREFIX)) {
			List<String> authHeader = requestContext.getHeaders().get(AUTHORIZATION_HEADER);
			if (authHeader != null && authHeader.size() > 0) {
				String authToken = authHeader.get(0);
				authToken = authToken.replaceFirst(AUTHORIZATION_HEADER_PREFIX, "");

			
				AccessToken token = tokens.get(authToken);
				if (token != null) {

					if (token.isValid()){
						  currentUser.set(token.getUser());
						
						return;
					}
						
					else {
						Response unauthorizedStatus = Response.status(Status.UNAUTHORIZED)
								.entity(new ResponseMessage(2, "Your session has expired , please login again"))
								.build();
						requestContext.abortWith(unauthorizedStatus);
						return;
					}

				}

				Response unauthorizedStatus = Response.status(Status.UNAUTHORIZED)
						.entity(new ResponseMessage(1, "Invalid token")).build();
				requestContext.abortWith(unauthorizedStatus);

			} else {
				Response unauthorizedStatus = Response.status(Status.UNAUTHORIZED).entity("unauthorized").build();
				requestContext.abortWith(unauthorizedStatus);

			}
		}

	}

}
