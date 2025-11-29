package org.labcluster.crm.server.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import kotlinx.serialization.Serializable
import kotlin.uuid.Uuid

@Entity
@Table(name = "group_lesson")
@Serializable
class GroupLessonEntity(
    @Id
    var uuid: String = Uuid.random().toString(),
    var groupUuid: String = "",
    var lessonUuid: String = ""
)