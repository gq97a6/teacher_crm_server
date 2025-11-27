package net.labcluster.crm.server

import org.labcluster.crm.server.entity.*
import org.labcluster.crm.shared.model.*

object EntityMapper {

    fun Topic.toEntity() = TopicEntity(uuid, name)
    fun Teacher.toEntity() = TeacherEntity(uuid, name, surname)
    fun Student.toEntity() = StudentEntity(uuid, name, surname)
    fun Course.toEntity() = CourseEntity(uuid, name, topics.map { it.toEntity() }.toMutableList())

    fun Group.toEntity() = GroupEntity(
        uuid,
        dayIndex,
        timeEpoch,
        intervalDays,
        teacher?.toEntity(),
        students.map { it.toEntity() }.toMutableList()
    )

    fun Lesson.toEntity() = LessonEntity(
        uuid,
        epochStart,
        epochBegin,
        duration,
        topic?.toEntity(),
        course?.toEntity(),
        teacher1?.toEntity(),
        teacher2?.toEntity(),
        students.map { it.toEntity() }.toMutableList(),
        attendance,
    )
}