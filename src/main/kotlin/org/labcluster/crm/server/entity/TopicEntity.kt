package org.labcluster.crm.server.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import kotlinx.serialization.Serializable
import org.labcluster.crm.shared.model.Topic
import kotlin.uuid.Uuid

@Entity
@Table(name = "topics")
@Serializable
class TopicEntity(
    @Id
    var uuid: String = Uuid.random().toString(),
    var name: String = ""
) : AnyEntity<Topic>()