package org.labcluster.crm.server.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import kotlin.uuid.Uuid

@Entity
@Table(name = "group_lesson")
class GroupLessonEntity(
    @Id
    var uuid: String = Uuid.random().toString(),
    var groupUuid: String,
    var lessonUuid: String
)