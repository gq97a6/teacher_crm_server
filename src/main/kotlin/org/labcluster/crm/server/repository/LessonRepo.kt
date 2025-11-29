package org.labcluster.crm.server.repository

import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import org.labcluster.crm.server.entity.LessonEntity
import kotlin.time.Clock

object LessonRepo : PanacheRepository<LessonEntity> {
    fun findByUuid(uuid: String) = find("uuid", uuid).firstResult()
    fun getTeacherTimetable(uuid: String) = find("teacher1.uuid", uuid).list()

    fun getGroupsNextLesson(uuid: String): LessonEntity? {
        return getGroupTimetable(uuid).minByOrNull { it.epochStart }
    }

    fun getGroupTimetable(uuid: String): List<LessonEntity> {
        val uuids = GroupLessonRepo.find("groupUuid", uuid).list().map { it.lessonUuid }
        val now = Clock.System.now().epochSeconds
        return list("uuid in ?1", uuids).filter { it.epochStart > now }
    }
}