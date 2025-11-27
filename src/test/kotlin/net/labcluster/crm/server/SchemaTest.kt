package net.labcluster.crm.server

import io.quarkus.test.junit.QuarkusTest
import jakarta.transaction.Transactional
import net.labcluster.crm.server.EntityMapper.toEntity
import org.junit.jupiter.api.Test
import org.labcluster.crm.server.entity.GroupLessonEntity
import org.labcluster.crm.server.repository.*
import org.labcluster.crm.shared.Mock

@QuarkusTest
class SchemaTest {

    @Test
    @Transactional
    fun populateDatabase() {
        Mock.students.forEach { StudentRepo.persistAndFlush(it.toEntity()) }
        Mock.teachers.forEach { TeacherRepo.persistAndFlush(it.toEntity()) }
        Mock.topics.forEach { TopicRepo.persistAndFlush(it.toEntity()) }
        Mock.courses.forEach { CourseRepo.persistAndFlush(it.toEntity()) }
        Mock.lessons.forEach { LessonRepo.persistAndFlush(it.toEntity()) }
        Mock.groups.forEach { GroupRepo.persistAndFlush(it.toEntity()) }
    }

    @Test
    @Transactional
    fun linkGroupsWithLessons() {
        //Map lessons to groups
        val groups = Mock.lessons.mapNotNull { lesson ->
            //Find first group
            Mock.groups.find { group ->
                //That has all students signed up for this lesson
                lesson.students.all { it in group.students }
            }
        }

        //Create and persist entities
        groups.forEachIndexed { index, group ->
            GroupLessonEntity(
                groupUuid = group.uuid,
                lessonUuid = Mock.lessons[index].uuid,
            ).let { GroupLessonRepo.persistAndFlush(it) }
        }
    }
}