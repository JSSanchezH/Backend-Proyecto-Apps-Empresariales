package com.proyect.Human_Resources.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;

import org.junit.jupiter.api.Test;

public class HistoryTest {

     @Test
    public void testHistorySettersAndGetters() {
        History history = new History();

        long id = 1L;
        Employee employee = new Employee();
        Date endDate = Date.valueOf("2025-12-31");
        String reason = "Voluntary resignation";

        history.setId(id);
        history.setEmployee(employee);
        history.setEndDate(endDate);
        history.setReason(reason);

        assertEquals(id, history.getId());
        assertEquals(employee, history.getEmployee());
        assertEquals(endDate, history.getEndDate());
        assertEquals(reason, history.getReason());
    }
    
}
