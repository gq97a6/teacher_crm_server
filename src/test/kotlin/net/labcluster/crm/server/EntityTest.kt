package net.labcluster.crm.server

import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import org.labcluster.crm.server.entity.AnyEntity
import kotlin.random.Random
import kotlin.reflect.KClass
import kotlin.reflect.KType
import kotlin.reflect.full.primaryConstructor

// This test checks if model and entity classes represent the same data

// Will fail if:
// - there is missing field compared to model class
// - there is extra field compared to model class
// - there is field type miss match

// Will NOT fail if there is field type miss match BUT serialized
// form of these fields are the same (e.g. both objects serialize to {})

@Suppress("UNCHECKED_CAST")
@OptIn(InternalSerializationApi::class)
class EntityTest {

    val json = Json {
        ignoreUnknownKeys = false
        explicitNulls = true
        encodeDefaults = true
    }

    val randomInt = Random.nextInt()
    val randomLong = Random.nextLong()
    val randomDouble = Random.nextDouble()

    @TestFactory
    fun testEntity(): List<DynamicTest> {
        return AnyEntity::class.sealedSubclasses.map { entityClass ->
            DynamicTest.dynamicTest(entityClass.simpleName) {
                val modelClass = entityClass.supertypes
                    .first { it.classifier == AnyEntity::class }
                    .arguments.first().type!!.classifier as KClass<*>

                val entityInstance = createInstance(entityClass)
                val modelInstance = createInstance(modelClass)

                val entityString = serializeInstance(entityClass, entityInstance)
                val modelString = serializeInstance(modelClass, modelInstance)

                println("${entityClass.simpleName}: $entityString")
                println("Model: $modelString")
                println("")
                assert(Json.parseToJsonElement(entityString) == Json.parseToJsonElement(modelString))
            }
        }
    }

    private fun serializeInstance(klass: KClass<*>, instance: Any): String {
        val serializer = klass.serializer() as KSerializer<Any>
        return json.encodeToString(serializer, instance)
    }

    private fun createInstance(klass: KClass<*>): Any {
        val kpc = klass.primaryConstructor ?: error("No primary constructor")
        val args = kpc.parameters.associateWith { param ->
            createDefaultValue(param.type)
        }
        return kpc.callBy(args)
    }

    private fun createDefaultValue(type: KType): Any {
        return when (val klass = type.classifier as KClass<*>) {
            String::class -> randomLong.toString()
            Char::class -> randomLong.toString().first()
            Int::class -> randomInt
            Long::class -> randomLong
            Boolean::class -> false
            Double::class -> randomDouble
            List::class -> {
                val elementType = type.arguments.first().type!!
                val elementClass = elementType.classifier as KClass<*>
                listOf(createInstance(elementClass))
            }

            else -> createInstance(klass)
        }
    }
}