package org.labcluster.crm.server.entity

import jakarta.persistence.*
import kotlinx.serialization.Serializable
import org.labcluster.crm.shared.model.Group
import kotlin.uuid.Uuid

@Entity
@Table(name = "groups")
@Serializable
class GroupEntity(
    @Id
    var uuid: String = Uuid.random().toString(),
    var dayIndex: Int = 0,
    var timeEpoch: Long = 0L,
    var intervalDays: Int = 0,

    @ManyToOne(fetch = FetchType.EAGER)
    var teacher: TeacherEntity? = null,

    @JoinTable(
        name = "group_student",
        joinColumns = [JoinColumn(name = "group_id")],
        inverseJoinColumns = [JoinColumn(name = "student_id")]
    )
    @ManyToMany(fetch = FetchType.EAGER)
    var students: MutableList<StudentEntity> = mutableListOf(),
) : AnyEntity<Group>()