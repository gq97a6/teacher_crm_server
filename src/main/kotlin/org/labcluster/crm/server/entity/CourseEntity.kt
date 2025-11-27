package org.labcluster.crm.server.entity

import jakarta.persistence.*
import kotlinx.serialization.Serializable
import org.labcluster.crm.shared.model.Course
import kotlin.uuid.Uuid

@Entity
@Table(name = "courses")
@Serializable
class CourseEntity(
    @Id
    var uuid: String = Uuid.random().toString(),
    var name: String = "",

    @JoinTable(
        name = "course_topic",
        joinColumns = [JoinColumn(name = "course_id")],
        inverseJoinColumns = [JoinColumn(name = "topic_id")]
    )
    @ManyToMany
    var topics: MutableList<TopicEntity> = mutableListOf(),
) : AnyEntity<Course>()