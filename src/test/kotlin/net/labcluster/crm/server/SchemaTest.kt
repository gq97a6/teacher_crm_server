package net.labcluster.crm.server

import io.quarkus.test.junit.QuarkusTest
import jakarta.transaction.Transactional
import net.labcluster.crm.server.EntityMapper.toEntity
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import org.labcluster.crm.server.entity.GroupLessonEntity
import org.labcluster.crm.server.repository.*
import org.labcluster.crm.shared.SharedMock

@QuarkusTest
@Tag("manual")
class SchemaTest {

    @Test
    @Transactional
    fun populateDatabase() {
        val mock = SharedMock()

        mock.students.forEach { StudentRepo.persist(it.toEntity()) }
        StudentRepo.flush()

        mock.teachers.forEach { TeacherRepo.persist(it.toEntity()) }
        TeacherRepo.flush()

        mock.topics.forEach { TopicRepo.persist(it.toEntity()) }
        TopicRepo.flush()

        mock.courses.forEach { CourseRepo.persist(it.toEntity()) }
        CourseRepo.flush()

        mock.lessons.forEach { LessonRepo.persist(it.toEntity()) }
        LessonRepo.flush()

        mock.groups.forEach { GroupRepo.persist(it.toEntity()) }
        GroupRepo.flush()

        insertGroupLesson(mock)
    }

    @Transactional
    fun insertGroupLesson(mock: SharedMock) {
        val manager = GroupLessonRepo.getEntityManager()

        mock.groupLessons.forEach { (groupId, lessons) ->
            lessons.forEach { lesson ->
                manager.merge(GroupLessonEntity(groupId, lesson.uuid))
                manager.flush()
                manager.clear()
            }
        }
    }
}