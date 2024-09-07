package com.horobar.infra.keycloak

import io.quarkus.security.identity.SecurityIdentity
import jakarta.inject.Inject
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import org.jboss.resteasy.reactive.NoCache

@Path("/api/users")
class UsersResource(
    private var identity: SecurityIdentity?
) {

    @GET
    @Path("/me")
    @NoCache
    fun me(): String {
        return User(identity).userName
    }

    class User(identity: SecurityIdentity?) {
        val userName: String = identity?.principal?.name ?: "Unknown"
    }
}