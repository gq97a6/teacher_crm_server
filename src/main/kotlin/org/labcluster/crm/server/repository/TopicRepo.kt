package org.labcluster.crm.server.repository

import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import org.labcluster.crm.server.entity.TopicEntity

object TopicRepo : PanacheRepository<TopicEntity> {
    fun findByUuid(uuid: String) = find("uuid", uuid).firstResult()
}