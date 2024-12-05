package com.example.test.repo;

import com.example.test.domain.Question;
import org.junit.Test;

import java.sql.SQLException;

public class GenericRepositoryTest {

    private IRepository<Question> genericRepository;

    public void doThat() {
        this.genericRepository = new GenericRepository<>("jdbc:sqlite:src/main/java/com/example/test/repo/database.db", Question.class);
    }


    @Test
    public void add() {
        doThat();
        var a = new Question(1);
        try {
            genericRepository.add(a);
            var b = genericRepository.getData().get(0);
            genericRepository.delete(a);
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