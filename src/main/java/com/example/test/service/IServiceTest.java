package com.example.test.service;

import com.example.test.domain.Question;
import com.example.test.repo.GenericRepository;

import org.junit.Test;

import java.sql.SQLException;


public class IServiceTest {

    private IService<Question> service = new IService<>(new GenericRepository<>("jdbc:sqlite:src/main/java/com/example/test/repo/database.db", Question.class));

    @Test
    public void add() {
        var a = new Question(1);
        try {
            service.add(a);
            var b = service.returnAll().get(0);
            service.delete(a);
            assert a.equals(b);
        } catch (Exception e) {
            if (e instanceof SQLException || e instanceof IllegalAccessException) {
                assert true;
            } else {
                assert false;
            }

        }
    }
}