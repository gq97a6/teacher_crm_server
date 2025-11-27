package org.labcluster.crm.server.repository

import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import org.labcluster.crm.server.entity.GroupEntity

object GroupRepo : PanacheRepository<GroupEntity> {
    fun findByUuid(uuid: String) = find("uuid", uuid).firstResult()
    fun getTaughtBy(uuid: String) = find("teacher.uuid", uuid).list()
}