package ru.kpfu.itis.ganiev.petchampionship.data.room

import androidx.room.TypeConverter
import org.joda.time.DateTime

class DateTimeConverter {

    @TypeConverter
    fun toDateTime(millis: Long): DateTime = DateTime(millis)

    @TypeConverter
    fun fromDateTime(dateTime: DateTime): Long = dateTime.millis
}