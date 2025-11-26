package org.labcluster.crm.server.entity

import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import jakarta.persistence.*
import org.labcluster.crm.shared.model.*
import kotlin.uuid.Uuid

@Entity
@Table(name = "lessons")
class LessonEntity : Lesson() {

    companion object : PanacheRepository<LessonEntity> {
        fun findByUuid(uuid: String) = find("uuid", uuid).firstResult()
    }

    @Id
    override var uuid: String = Uuid.random().toString()
    override var epochStart = 0L
    override var epochBegin = null as Long?
    override var duration = 0

    @ManyToOne(targetEntity = TopicEntity::class)
    override var topic = null as Topic?

    @ManyToOne(targetEntity = CourseEntity::class)
    override var course = null as Course?

    @ManyToOne(targetEntity = TeacherEntity::class)
    override var teacher1 = null as Teacher?

    @ManyToOne(targetEntity = TeacherEntity::class)
    override var teacher2 = null as Teacher?

    @JoinTable(name = "lesson_student")
    @ManyToMany(targetEntity = StudentEntity::class)
    override var students = mutableListOf<Student>()

    @ElementCollection
    @CollectionTable(name = "lesson_attendance")
    override var attendance = mutableListOf<String>()
}