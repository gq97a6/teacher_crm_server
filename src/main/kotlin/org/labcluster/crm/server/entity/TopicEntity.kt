package org.labcluster.crm.server.entity

import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.labcluster.crm.shared.model.Topic
import kotlin.uuid.Uuid

@Entity
@Table(name = "topics")
class TopicEntity : Topic() {

    companion object : PanacheRepository<TopicEntity> {
        fun findByUuid(uuid: String) = find("uuid", uuid).firstResult()
    }

    @Id
    override var uuid: String = Uuid.random().toString()
    override var name: String = ""
}