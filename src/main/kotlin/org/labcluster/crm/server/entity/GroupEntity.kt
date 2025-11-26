package org.labcluster.crm.server.entity

import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import jakarta.persistence.*
import org.labcluster.crm.shared.model.Group
import org.labcluster.crm.shared.model.Student
import org.labcluster.crm.shared.model.Teacher
import kotlin.uuid.Uuid

@Entity
@Table(name = "groups")
class GroupEntity : Group() {

    companion object : PanacheRepository<GroupEntity> {
        fun findByUuid(uuid: String) = find("uuid", uuid).firstResult()
    }

    @Id
    override var uuid: String = Uuid.random().toString()

    override var dayIndex = 0
    override var timeEpoch = 0L
    override var intervalDays = 0

    @ManyToOne(targetEntity = TeacherEntity::class)
    override var teacher = null as Teacher?

    @JoinTable(name = "group_student")
    @ManyToMany(targetEntity = StudentEntity::class)
    override var students = mutableListOf<Student>()
}