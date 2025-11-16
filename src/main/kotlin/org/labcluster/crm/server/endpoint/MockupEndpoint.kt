package org.labcluster.crm.server.endpoint

import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.core.Response
import org.labcluster.crm.shared.Mock

//@Path("/address/{code}")
//@PathParam("code") code: String

@Path("/mock")
class MockupEndpoint {

    @GET
    @Path("/courses")
    suspend fun courses(): Response {
        return Response.ok(Mock.courses).build()
    }

    @GET
    @Path("/lessons")
    suspend fun lessons(): Response {
        return Response.ok(Mock.lessons).build()
    }

    @GET
    @Path("/students")
    suspend fun students(): Response {
        return Response.ok(Mock.students).build()
    }

    @GET
    @Path("/teachers")
    suspend fun teachers(): Response {
        return Response.ok(Mock.teachers).build()
    }

    @GET
    @Path("/topics")
    suspend fun topics(): Response {
        return Response.ok(Mock.topics).build()
    }
}