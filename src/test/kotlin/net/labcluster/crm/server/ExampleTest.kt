package net.labcluster.crm.server

import io.quarkus.test.junit.QuarkusTest
import jakarta.transaction.Transactional
import org.junit.jupiter.api.Test
import org.labcluster.crm.server.entity.CourseEntity

@QuarkusTest
class SchemaTest {

    @Test
    @Transactional
    fun testSchema() {
        CourseEntity.persist(CourseEntity())
    }
}