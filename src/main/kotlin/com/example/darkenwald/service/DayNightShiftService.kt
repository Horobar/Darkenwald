package com.example.darkenwald.service


import org.springframework.context.ApplicationEvent
import org.springframework.context.ApplicationEventPublisher
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.time.Instant
import java.time.LocalDateTime
import java.time.OffsetDateTime


@Service
class DayNightShiftService(
    private val eventPublisher: ApplicationEventPublisher
) {
        @Scheduled(cron = "0 0 8 * * ?")
        fun morningCall(){
            eventPublisher.publishEvent(DayBreakEvent(
                this,
                LocalDateTime.now(),
                DayTime.DAY))
        }

        @Scheduled(cron = "0 0 20 * * ?")
        fun eveningCall(){
            eventPublisher.publishEvent(DayBreakEvent(
                this,
                LocalDateTime.now(),
                DayTime.NIGHT))
        }
}

class DayBreakEvent(
    private val mySource: Any,
    val dateTime: LocalDateTime,
    val dayBreak: Enum<DayTime>
    ) : ApplicationEvent(mySource)

enum class DayTime {
    NIGHT, DAY
}
