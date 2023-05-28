package vs.chanban.domain.enum

import org.springframework.stereotype.Service

@Service
class EnumService {
    fun <T : ChanbanEnum> getAllEnumValues(enumClass: Class<T>): List<EnumResponseDto> {
        return enumClass.enumConstants.map { enumValue ->
            EnumResponseDto(enumValue.getName(), enumValue.getDescription())
        }
    }
}
