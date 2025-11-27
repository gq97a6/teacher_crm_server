package org.labcluster.crm.server

import jakarta.transaction.Transactional
import jakarta.ws.rs.GET
import jakarta.ws.rs.PUT
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import jakarta.ws.rs.core.Response
import org.labcluster.crm.server.entity.LessonEntity
import org.labcluster.crm.server.repository.GroupRepo
import org.labcluster.crm.server.repository.LessonRepo

@Path("/api")
class EntityEndpoint {

    @GET
    @Path("/lesson/teacherTimetable/{uuid}")
    @Transactional
    suspend fun getTeacherTimetable(@PathParam("uuid") uuid: String): Response {
        return Response.ok(LessonRepo.getTeacherTimetable(uuid)).build()
    }

    @GET
    @Path("/lesson/groupTimetable/{uuid}")
    @Transactional
    suspend fun getGroupTimetable(@PathParam("uuid") uuid: String): Response {
        return Response.ok(LessonRepo.getGroupTimetable(uuid)).build()
    }

    @GET
    @Path("/lesson/groupNextLesson/{uuid}")
    @Transactional
    suspend fun getGroupNextLesson(@PathParam("uuid") uuid: String): Response {
        return Response.ok(LessonRepo.getGroupsNextLesson(uuid)).build()
    }

    @GET
    @Path("/group/taughtBy/{uuid}")
    @Transactional
    suspend fun getTaughtBy(@PathParam("uuid") uuid: String): Response {
        return Response.ok(GroupRepo.getTaughtBy(uuid)).build()
    }

    @PUT
    @Path("/lesson/{uuid}")
    @Transactional
    suspend fun putLesson(lesson: LessonEntity, @PathParam("uuid") uuid: String): Response {
        //Validate if uuids match
        if (lesson.uuid != uuid) return Response.status(400).build()
        LessonRepo.persist(lesson)
        return Response.ok().build()
    }

    @GET
    @Path("/health")
    suspend fun getHealth(): Response {
        return Response.ok().build()
    }
}