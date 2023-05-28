package vs.chanban.domain.enum

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import vs.chanban.domain.enum.poll.PollOption
import vs.chanban.domain.enum.topic.TopicSubject

@SpringBootTest
class EnumServiceTest(@Autowired val enumService: EnumService) {
    @Test
    @DisplayName("get topic subject enum values")
    fun getTopicSubjectEnumValues() {
        val enumValues = enumService.getAllEnumValues(TopicSubject::class.java)

        assertEquals(7, enumValues.size)
        assertEquals(TopicSubject.SELF_DIAGNOSIS.name, enumValues[0].name)
        assertEquals(TopicSubject.SELF_DIAGNOSIS.getDescription(), enumValues[0].description)
        assertEquals(TopicSubject.GIBBERISH.name, enumValues[6].name)
        assertEquals(TopicSubject.GIBBERISH.getDescription(), enumValues[6].description)
    }

    @Test
    @DisplayName("get poll option enum values")
    fun getPollOptionEnumValues() {
        val enumValues = enumService.getAllEnumValues(PollOption::class.java)

        assertEquals(2, enumValues.size)
        assertEquals("POSITIVE", enumValues[0].name)
        assertEquals("찬성", enumValues[0].description)
        assertEquals("NEGATIVE", enumValues[1].name)
        assertEquals("반대", enumValues[1].description)
    }
}