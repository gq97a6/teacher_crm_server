package org.labcluster.crm.server

import jakarta.transaction.Transactional
import jakarta.ws.rs.GET
import jakarta.ws.rs.PUT
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import jakarta.ws.rs.core.Response
import org.labcluster.crm.server.entity.LessonEntity
import org.labcluster.crm.shared.Mock

@Path("/api")
class AnyEndpoint {

    @GET
    @Path("/lesson/teacherTimetable/{teacherUuid}")
    fun getTeacherTimetable(@PathParam("teacherUuid") teacherUuid: String): Response {
        return Response.ok(Mock.lessons.shuffled()).build()
    }

    @GET
    @Path("/lesson/groupTimetable/{teacherUuid}")
    fun getGroupTimetable(@PathParam("teacherUuid") teacherUuid: String): Response {
        return Response.ok(Mock.lessons.shuffled().take(10)).build()
    }

    @GET
    @Path("/lesson/groupNextLesson/{groupUuid}")
    fun getGroupNextLesson(@PathParam("groupUuid") groupUuid: String): Response {
        return Response.ok(Mock.lessons.random()).build()
    }

    @GET
    @Path("/group/taughtBy/{teacherUuid}")
    fun getTaughtBy(@PathParam("teacherUuid") teacherUuid: String): Response {
        return Response.ok(Mock.groups.shuffled()).build()
    }

    @PUT
    @Path("/lesson/{lessonUuid}")
    @Transactional
    suspend fun putLesson(lesson: LessonEntity, @PathParam("lessonUuid") lessonUuid: String): Response {
        //Validate if uuids match
        if (lesson.uuid != lessonUuid) return Response.status(400).build()
        Repository.lesson.persist(lesson)
        return Response.ok().build()
    }

    @GET
    @Path("/health")
    suspend fun getHealth(): Response {
        return Response.ok().build()
    }
}