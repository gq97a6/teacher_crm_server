package org.labcluster.crm.server.entity

import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import jakarta.persistence.*
import org.labcluster.crm.shared.model.Course
import org.labcluster.crm.shared.model.Topic
import kotlin.uuid.Uuid

@Entity
@Table(name = "courses")
class CourseEntity : Course() {

    companion object : PanacheRepository<CourseEntity> {
        fun findByUuid(uuid: String) = find("uuid", uuid).firstResult()
    }

    @Id
    override var uuid = Uuid.random().toString()

    override var name = ""

    @JoinTable(name = "course_topic")
    @ManyToMany(targetEntity = TopicEntity::class)
    override var topics = mutableListOf<Topic>()
}