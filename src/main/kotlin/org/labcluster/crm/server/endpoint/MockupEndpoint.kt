package org.labcluster.crm.server.endpoint

import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import jakarta.ws.rs.core.Response
import org.labcluster.crm.shared.Mock

@Path("/api")
class MockupEndpoint {

    @GET
    @Path("/lesson/teacherTimetable/{teacherUuid}")
    fun teacherTimetable(@PathParam("teacherUuid") teacherUuid: String): Response {
        return Response.ok(Mock.lessons.shuffled()).build()
    }

    @GET
    @Path("/lesson/groupTimetable/{teacherUuid}")
    fun groupTimetable(@PathParam("teacherUuid") teacherUuid: String): Response {
        return Response.ok(Mock.lessons.shuffled().take(10)).build()
    }

    @GET
    @Path("/lesson/groupNextLesson/{groupUuid}")
    fun groupNextLesson(@PathParam("groupUuid") groupUuid: String): Response {
        return Response.ok(Mock.topics.random().apply { this.uuid = uuid }).build()
    }

    @GET
    @Path("/group/taughtBy/{teacherUuid}")
    fun taughtBy(@PathParam("teacherUuid") teacherUuid: String): Response {
        return Response.ok(Mock.groups.shuffled()).build()
    }

    @GET
    @Path("/health")
    suspend fun health(): Response {
        return Response.ok().build()
    }
}