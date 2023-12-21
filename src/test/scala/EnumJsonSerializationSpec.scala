package org.acme

import com.fasterxml.jackson.databind.ObjectMapper
import io.quarkus.test.junit.QuarkusTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

import jakarta.inject.Inject

enum AnEnum:
    case A extends AnEnum
    case B extends AnEnum

case class Other(foo: String)
case class Something(
    name:     String,
    someEnum: AnEnum,
    other:    Other,
  )

@QuarkusTest
class Scala3ObjectMapperCustomizerTest:

    @Inject
    var objectMapper: ObjectMapper = null

    @Test
    def `Jackson ObjectMapper can parse Scala 3 members`: Unit =
        val sampleSomethingJSON: String = """
            {
            "name": "My Something",
            "someEnum": "A",
            "other": {
                "foo": "bar"
              }
            }
            """
        val parsed = objectMapper.readValue[Something](sampleSomethingJSON, classOf[Something])
        assertEquals(parsed.name, "My Something")
        assertEquals(parsed.someEnum, AnEnum.A)
        assertEquals(parsed.other.foo, "bar")
    end `Jackson ObjectMapper can parse Scala 3 members`

    @Test
    def `Jackson ObjectMapper can generate Json from Scala 3 class`: Unit =
        val something = Something("My Something", AnEnum.A, Other("bar"))
        val json      = objectMapper.writeValueAsString(something)
        assertEquals(json, """{"name":"My Something","someEnum":"A","other":{"foo":"bar"}}""")

end Scala3ObjectMapperCustomizerTest
