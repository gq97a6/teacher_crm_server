package net.labcluster.crm.server

import io.quarkus.test.junit.QuarkusTest
import jakarta.transaction.Transactional
import org.labcluster.crm.server.EntityMapper.toEntity
import org.labcluster.crm.server.Repository
import org.labcluster.crm.shared.Mock

@QuarkusTest
class SchemaTest {

    //@Test
    @Transactional
    fun deleteAll() {
        Repository.lesson.deleteAll()
        Repository.student.deleteAll()
        Repository.group.deleteAll()
        Repository.topic.deleteAll()
        Repository.teacher.deleteAll()
        Repository.course.deleteAll()
    }

    //@Test
    @Transactional
    fun populateDatabase() {
        Mock.students.forEach { Repository.student.persist(it.toEntity()) }
        Repository.student.flush()

        Mock.teachers.forEach { Repository.teacher.persist(it.toEntity()) }
        Repository.teacher.flush()

        Mock.topics.forEach { Repository.topic.persist(it.toEntity()) }
        Repository.topic.flush()

        Mock.courses.forEach { Repository.course.persist(it.toEntity()) }
        Repository.course.flush()

        Mock.lessons.forEach { Repository.lesson.persist(it.toEntity()) }
        Repository.lesson.flush()

        Mock.groups.forEach { Repository.group.persist(it.toEntity()) }
        Repository.group.flush()
    }
}