package com.proyect.Human_Resources.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Time;

import org.junit.jupiter.api.Test;

public class ScheduleTest {

    @Test
    public void testSettersAndGetters() {
        Schedule schedule = new Schedule();

        Long id = 1L;
        Time startTime = Time.valueOf("08:00:00");
        Time endTime = Time.valueOf("17:00:00");
        Time breakStart = Time.valueOf("12:00:00");
        Time breakEnd = Time.valueOf("13:00:00");
        Employee employee = new Employee(); // Suponiendo que tienes la clase Employee definida

        schedule.setId(id);
        schedule.setStartTime(startTime);
        schedule.setEndTime(endTime);
        schedule.setBreakStart(breakStart);
        schedule.setBreakEnd(breakEnd);
        schedule.setEmployee(employee);

        assertEquals(id, schedule.getId());
        assertEquals(startTime, schedule.getStartTime());
        assertEquals(endTime, schedule.getEndTime());
        assertEquals(breakStart, schedule.getBreakStart());
        assertEquals(breakEnd, schedule.getBreakEnd());
        assertEquals(employee, schedule.getEmployee());
    }
    
}
