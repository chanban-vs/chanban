package vs.chanban.domain.enum.dto

import vs.chanban.domain.enum.ChanbanEnum

class ChanbanEnumDto(
    val name: String,
    val description: String
) {
    companion object {
        fun of(chanbanEnum: ChanbanEnum): ChanbanEnumDto = ChanbanEnumDto(
            name = chanbanEnum.getName(),
            description = chanbanEnum.getDescription()
        )
    }
}