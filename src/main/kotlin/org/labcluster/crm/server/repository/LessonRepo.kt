package org.labcluster.crm.server.repository

import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import org.labcluster.crm.server.entity.LessonEntity
import kotlin.time.Clock

object LessonRepo : PanacheRepository<LessonEntity> {
    fun findByUuid(uuid: String) = find("uuid", uuid).firstResult()
    fun getTeacherTimetable(uuid: String) = find("teacher1.uuid", uuid).list()

    fun getGroupsNextLesson(uuid: String): LessonEntity? {
        val now = Clock.System.now().epochSeconds
        return getGroupTimetable(uuid).filter { it.epochStart > now }.minByOrNull { it.epochStart }
    }

    fun getGroupTimetable(uuid: String): List<LessonEntity> {
        val ids = GroupLessonRepo.find("groupUuid", uuid).list()
        return list("id in ?1", ids)
    }
}