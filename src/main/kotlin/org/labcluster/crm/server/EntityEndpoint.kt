package org.labcluster.crm.server

import io.smallrye.common.annotation.Blocking
import jakarta.transaction.Transactional
import jakarta.ws.rs.GET
import jakarta.ws.rs.PUT
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import jakarta.ws.rs.core.Response
import org.labcluster.crm.server.entity.LessonEntity
import org.labcluster.crm.server.repository.CourseRepo.getEntityManager
import org.labcluster.crm.server.repository.GroupRepo
import org.labcluster.crm.server.repository.LessonRepo
import org.labcluster.crm.server.repository.TeacherRepo
import org.labcluster.crm.server.repository.TopicRepo

@Blocking
@Path("/api")
class EntityEndpoint {

    @GET
    @Path("/lesson/teacherTimetable/{uuid}")
    @Transactional
    fun getTeacherTimetable(@PathParam("uuid") uuid: String): Response {
        return Response.ok(LessonRepo.getTeacherTimetable(uuid)).build()
    }

    @GET
    @Path("/lesson/groupTimetable/{uuid}")
    @Transactional
    fun getGroupTimetable(@PathParam("uuid") uuid: String): Response {
        return Response.ok(LessonRepo.getGroupTimetable(uuid).sortedBy { it.epochStart }).build()
    }

    @GET
    @Path("/lesson/groupNextLesson/{uuid}")
    @Transactional
    fun getGroupNextLesson(@PathParam("uuid") uuid: String): Response {
        return Response.ok(LessonRepo.getGroupsNextLesson(uuid)).build()
    }

    @GET
    @Path("/group/taughtBy/{uuid}")
    @Transactional
    fun getTaughtBy(@PathParam("uuid") uuid: String): Response {
        return Response.ok(GroupRepo.getTaughtBy(uuid)).build()
    }

    @PUT
    @Path("/lesson/{uuid}")
    @Transactional
    fun putLesson(lesson: LessonEntity, @PathParam("uuid") uuid: String): Response {
        //Validate if uuids match
        if (lesson.uuid != uuid) return Response.status(400).build()
        getEntityManager().merge(lesson)
        return Response.ok().build()
    }

    @GET
    @Path("/lesson/{uuid}")
    @Transactional
    fun getLesson(@PathParam("uuid") uuid: String): Response {
        return Response.ok(LessonRepo.findByUuid(uuid)).build()
    }

    @GET
    @Path("/topic/{uuid}")
    @Transactional
    fun getTopic(@PathParam("uuid") uuid: String): Response {
        return Response.ok(TopicRepo.findByUuid(uuid)).build()
    }

    @GET
    @Path("/group/{uuid}")
    @Transactional
    fun getGroup(@PathParam("uuid") uuid: String): Response {
        return Response.ok(GroupRepo.findByUuid(uuid)).build()
    }

    @GET
    @Path("/teacher/{uuid}")
    @Transactional
    fun getTeacher(@PathParam("uuid") uuid: String): Response {
        return Response.ok(TeacherRepo.findByUuid(uuid)).build()
    }


    @GET
    @Path("/health")
    fun getHealth(): Response {
        return Response.ok().build()
    }
}