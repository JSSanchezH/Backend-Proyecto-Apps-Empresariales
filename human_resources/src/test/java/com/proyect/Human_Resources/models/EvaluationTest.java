package com.proyect.Human_Resources.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;
import org.junit.jupiter.api.Test;

public class EvaluationTest {

    @Test
    public void testEvaluationSettersAndGetters() {
        Evaluation evaluation = new Evaluation();

        Long id = 1L;
        Employee employee = new Employee();
        Date evaluationDate = new Date(System.currentTimeMillis());
        long punctuality = 8L;
        long performance = 9L;
        long courtesy = 7L;
        long precision = 10L;
        long collaboration = 9L;
        long proactivity = 8L;

        evaluation.setId(id);
        evaluation.setEmployee(employee);
        evaluation.setEvaluationDate(evaluationDate);
        evaluation.setPunctuality(punctuality);
        evaluation.setPerformance(performance);
        evaluation.setCourtesy(courtesy);
        evaluation.setPrecision(precision);
        evaluation.setCollaboration(collaboration);
        evaluation.setProactivity(proactivity);

        assertEquals(id, evaluation.getId());
        assertEquals(employee, evaluation.getEmployee());
        assertEquals(evaluationDate, evaluation.getEvaluationDate());
        assertEquals(punctuality, evaluation.getPunctuality());
        assertEquals(performance, evaluation.getPerformance());
        assertEquals(courtesy, evaluation.getCourtesy());
        assertEquals(precision, evaluation.getPrecision());
        assertEquals(collaboration, evaluation.getCollaboration());
        assertEquals(proactivity, evaluation.getProactivity());
    }
    
}
