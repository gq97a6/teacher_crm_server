package net.labcluster.crm.server

import io.quarkus.test.junit.QuarkusTest
import jakarta.transaction.Transactional
import org.junit.jupiter.api.Test
import org.labcluster.crm.server.Repository
import org.labcluster.crm.server.entity.*

@QuarkusTest
class SchemaTest {

    @Test
    @Transactional
    fun populateDatabase() {
        Repository.student.persist(StudentEntity())
        Repository.teacher.persist(TeacherEntity())
        Repository.topic.persist(TopicEntity())
        Repository.course.persist(CourseEntity())
        Repository.lesson.persist(LessonEntity())
        Repository.group.persist(GroupEntity())
    }
}