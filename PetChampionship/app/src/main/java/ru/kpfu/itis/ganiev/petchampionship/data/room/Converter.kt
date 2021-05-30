package ru.kpfu.itis.ganiev.petchampionship.data.room

import androidx.room.TypeConverter
import org.joda.time.DateTime
import ru.kpfu.itis.ganiev.petchampionship.domain.model.VoteStatus

class Converter {

    @TypeConverter
    fun toDateTime(millis: Long): DateTime = DateTime(millis)

    @TypeConverter
    fun fromDateTime(dateTime: DateTime): Long = dateTime.millis

    @TypeConverter
    fun fromStatus(voteStatus: VoteStatus): String = voteStatus.name

    @TypeConverter
    fun toStatus(name: String): VoteStatus = VoteStatus.valueOf(name)
}
