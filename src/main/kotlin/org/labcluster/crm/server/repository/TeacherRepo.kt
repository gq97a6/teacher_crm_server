package org.labcluster.crm.server.repository

import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import org.labcluster.crm.server.entity.TeacherEntity

object TeacherRepo : PanacheRepository<TeacherEntity> {
    fun findByUuid(uuid: String) = find("uuid", uuid).firstResult()
}