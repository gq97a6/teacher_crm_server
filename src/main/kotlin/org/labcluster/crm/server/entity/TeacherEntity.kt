package org.labcluster.crm.server.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import kotlinx.serialization.Serializable
import org.labcluster.crm.shared.model.Teacher
import kotlin.uuid.Uuid

@Entity
@Table(name = "teachers")
@Serializable
class TeacherEntity(
    @Id var uuid: String = Uuid.random().toString(),
    var name: String = "",
    var surname: String = ""
) : AnyEntity<Teacher>()