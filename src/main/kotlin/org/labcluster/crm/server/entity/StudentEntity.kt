package org.labcluster.crm.server.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import kotlinx.serialization.Serializable
import org.labcluster.crm.shared.model.Student
import kotlin.uuid.Uuid

@Entity
@Table(name = "students")
@Serializable
class StudentEntity(
    @Id
    var uuid: String = Uuid.random().toString(),
    var name: String = "",
    var surname: String = "",
) : AnyEntity<Student>()