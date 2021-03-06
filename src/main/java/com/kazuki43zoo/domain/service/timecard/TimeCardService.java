package com.kazuki43zoo.domain.service.timecard;

import com.kazuki43zoo.domain.model.timecard.DailyAttendance;
import com.kazuki43zoo.domain.model.timecard.TimeCard;
import org.joda.time.LocalDate;

public interface TimeCardService {

    TimeCard getTimeCard(String accountUuid, LocalDate targetMonth);

    TimeCard getDefaultTimeCard(String accountUuid, LocalDate targetMonth);

    void saveTimeCard(String accountUuid, LocalDate targetMonth, TimeCard timeCard);

    DailyAttendance getDailyAttendance(String accountUuid, LocalDate targetDate);

    void saveDailyAttendance(String accountUuid, LocalDate targetDate, DailyAttendance attendance);

}
