package com.example.test.domain;


public class QDTO extends BaseEntity {
    private String text;

    private String dificulty;

    public QDTO(Integer id, String text, String dificulty) {
        super(id);
        this.text = text;
        this.dificulty = dificulty;
    }

    public QDTO(Question question) {
        super(question.getId());
        this.text = question.getText();
        this.dificulty = question.getDificulty();
    }


    @Override
    public String toString() {
        return "QDTO{" +
                "text='" + text + '\'' +
                ", punctaj=" + dificulty +
                ", id=" + id +
                "} ";
    }
}
