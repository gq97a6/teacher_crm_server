package org.labcluster.crm.server.entity

import jakarta.persistence.*
import kotlinx.serialization.Serializable
import org.labcluster.crm.shared.model.Lesson
import kotlin.uuid.Uuid

@Entity
@Table(name = "lessons")
@Serializable
class LessonEntity(
    @Id
    var uuid: String = Uuid.random().toString(),
    var epochStart: Long = 0L,
    var epochBegin: Long? = null,
    var duration: Int = 0,

    @ManyToOne
    var topic: TopicEntity? = null,

    @ManyToOne
    var course: CourseEntity? = null,

    @ManyToOne
    var teacher1: TeacherEntity? = null,

    @ManyToOne
    var teacher2: TeacherEntity? = null,

    @JoinTable(
        name = "lesson_student",
        joinColumns = [JoinColumn(name = "lesson_id")],
        inverseJoinColumns = [JoinColumn(name = "student_id")]
    )
    @ManyToMany
    var students: MutableList<StudentEntity> = mutableListOf(),

    @ElementCollection
    @CollectionTable(name = "lesson_attendance", joinColumns = [JoinColumn(name = "lesson_id")])
    @Column(name = "student_uuid")
    var attendance: MutableList<String> = mutableListOf()
) : AnyEntity<Lesson>()