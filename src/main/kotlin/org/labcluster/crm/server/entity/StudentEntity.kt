package org.labcluster.crm.server.entity

import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.labcluster.crm.shared.model.Student
import kotlin.uuid.Uuid

@Entity
@Table(name = "students")
class StudentEntity : Student() {

    companion object : PanacheRepository<StudentEntity> {
        fun findByUuid(uuid: String) = find("uuid", uuid).firstResult()
    }

    @Id
    override var uuid: String = Uuid.random().toString()
    override var name = ""
    override var surname = ""
}