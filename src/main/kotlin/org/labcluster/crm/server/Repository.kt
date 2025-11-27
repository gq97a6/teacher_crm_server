package org.labcluster.crm.server

import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import org.labcluster.crm.server.entity.*

object Repository {
    val teacher = object : PanacheRepository<TeacherEntity> {
        fun findByUuid(uuid: String) = find("uuid", uuid).firstResult()
    }

    val course = object : PanacheRepository<CourseEntity> {
        fun findByUuid(uuid: String) = find("uuid", uuid).firstResult()
    }

    val group = object : PanacheRepository<GroupEntity> {
        fun findByUuid(uuid: String) = find("uuid", uuid).firstResult()
    }

    val lesson = object : PanacheRepository<LessonEntity> {
        fun findByUuid(uuid: String) = find("uuid", uuid).firstResult()
    }

    val student = object : PanacheRepository<StudentEntity> {
        fun findByUuid(uuid: String) = find("uuid", uuid).firstResult()
    }

    val topic = object : PanacheRepository<TopicEntity> {
        fun findByUuid(uuid: String) = find("uuid", uuid).firstResult()
    }
}