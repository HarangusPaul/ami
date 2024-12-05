package com.example.test.service;

import com.example.test.domain.BaseEntity;
import com.example.test.domain.Question;
import com.example.test.repo.IRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class IService<T extends BaseEntity> {
    private final IRepository<T> repository;

    public IService(IRepository<T> repository) {
        this.repository = repository;
    }

    public void add(T entity) throws Exception {
        repository.add(entity);
    }

    public List<T> returnAll() {
        ArrayList<T> arrayList = new ArrayList<>();

        repository.getData().forEach(n -> arrayList.add(n));
        return arrayList;
    }

    public void update(T entity) throws Exception {
        repository.update(entity);
    }

    public void delete(T entity) throws Exception {
        repository.delete(entity);
    }

    public int getId() {
        if (returnAll().size() == 0) {
            return 1000; //Primul id din lista
        }
        return returnAll().stream().sorted(Comparator.comparing(BaseEntity::getId).reversed()).toList().get(0).getId() + 1;
    }


    public List<T> returnOrderDataByID() {
        return returnAll().stream().sorted(Comparator.comparing(T::getId)).collect(Collectors.toList());
    }

    private int getRandomNumberUsingNextInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    public List<Question> questionListF(List<Question> questions, int nr, String dific) throws Exception {
        List<Question> questions1 = new ArrayList<>();
        if (questions.size() < nr) {
            throw new Exception("Nu am intrebari destule!");
        }

        List<Question> questionsCopy = questions;
        questions = questions.stream().filter(n -> n.getDificulty().equals(dific)).toList();
        if (questions.size() < nr / 2) {
            throw new Exception("Nu am intrebari destule cu acea dificultate");
        }
        while (questions1.size() < nr/2) {
            var qNumber = getRandomNumberUsingNextInt(0, questions.size());
            var question = questions.get(qNumber);
            if (questions1.contains(question)) {
                continue;
            }
            questions1.add(question);
        }

        questions = questionsCopy;
        while (questions1.size() < nr) {
            var qNumber = getRandomNumberUsingNextInt(0, questions.size());
            var question = questions.get(qNumber);
            if (questions1.contains(question)) {
                continue;
            }
            questions1.add(question);
        }

        return questions1;
    }
}
