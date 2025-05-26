package com.proyect.Human_Resources.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.proyect.Human_Resources.Repositories.IScheduleReposiory;
import com.proyect.Human_Resources.models.Employee;
import com.proyect.Human_Resources.models.Schedule;

@ExtendWith(MockitoExtension.class)
public class ScheduleServiceTest {

    @Mock
    private IScheduleReposiory scheduleRepository;

    @InjectMocks
    private ScheduleService scheduleService;

    private Schedule schedule;
    private ArrayList<Schedule> scheduleList;

    @BeforeEach
    void setUp() {
        schedule = new Schedule();
        schedule.setId(1L);
        schedule.setStartTime(Time.valueOf("08:00:00"));
        schedule.setEndTime(Time.valueOf("17:00:00"));
        schedule.setBreakStart(Time.valueOf("12:00:00"));
        schedule.setBreakEnd(Time.valueOf("13:00:00"));
        schedule.setEmployee(new Employee()); 

        scheduleList = new ArrayList<>();
        scheduleList.add(schedule);
    }

    @Test
    void testGetSchedules() {
        when(scheduleRepository.findByEmployeeDepartmentHeadquarterCompanyNit(123L)).thenReturn(scheduleList);

        ArrayList<Schedule> result = scheduleService.getSchedules(123L);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(scheduleRepository).findByEmployeeDepartmentHeadquarterCompanyNit(123L);
    }

    @Test
    void testSaveSchedule() {
        when(scheduleRepository.save(schedule)).thenReturn(schedule);

        Schedule saved = scheduleService.saveSchedule(schedule);

        assertNotNull(saved);
        verify(scheduleRepository).save(schedule);
    }

    @Test
    void testSaveSchedules() {
        when(scheduleRepository.saveAll(scheduleList)).thenReturn(scheduleList);

        ArrayList<Schedule> savedList = scheduleService.saveSchedules(scheduleList);

        assertNotNull(savedList);
        assertEquals(1, savedList.size());
        verify(scheduleRepository).saveAll(scheduleList);
    }

    @Test
    void testGetScheduleById_Found() {
        when(scheduleRepository.findById(1L)).thenReturn(Optional.of(schedule));

        Optional<Schedule> found = scheduleService.getScheduleById(1L);

        assertTrue(found.isPresent());
        assertEquals(schedule, found.get());
        verify(scheduleRepository).findById(1L);
    }

    @Test
    void testGetScheduleById_NotFound() {
        when(scheduleRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Schedule> found = scheduleService.getScheduleById(1L);

        assertFalse(found.isPresent());
        verify(scheduleRepository).findById(1L);
    }

    @Test
    void testUpdateSchedule() {
        Schedule newData = new Schedule();
        newData.setStartTime(Time.valueOf("09:00:00"));
        newData.setEndTime(Time.valueOf("18:00:00"));
        newData.setBreakStart(Time.valueOf("13:00:00"));
        newData.setBreakEnd(Time.valueOf("14:00:00"));
        newData.setEmployee(new Employee());

        when(scheduleRepository.findById(1L)).thenReturn(Optional.of(schedule));
        when(scheduleRepository.save(any(Schedule.class))).thenAnswer(inv -> inv.getArgument(0));

        Schedule updated = scheduleService.updateSchedule(newData, 1L);

        assertEquals(Time.valueOf("09:00:00"), updated.getStartTime());
        assertEquals(Time.valueOf("18:00:00"), updated.getEndTime());
        assertEquals(Time.valueOf("13:00:00"), updated.getBreakStart());
        assertEquals(Time.valueOf("14:00:00"), updated.getBreakEnd());

        verify(scheduleRepository).findById(1L);
        verify(scheduleRepository).save(any(Schedule.class));
    }

    @Test
    void testDeleteSchedule_Success() {
        doNothing().when(scheduleRepository).deleteById(1L);

        boolean result = scheduleService.deleteSchedule(1L);

        assertTrue(result);
        verify(scheduleRepository).deleteById(1L);
    }

    @Test
    void testDeleteSchedule_Failure() {
        doThrow(new RuntimeException("Delete failed")).when(scheduleRepository).deleteById(1L);

        boolean result = scheduleService.deleteSchedule(1L);

        assertFalse(result);
        verify(scheduleRepository).deleteById(1L);
    }
    
}
