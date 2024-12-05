package com.example.test.domain;

import java.util.Objects;

public class Question extends BaseEntity{
    private String text;

    private String raspuns1;
    private String raspuns2;
    private String raspuns3;
    private String raspunsCorect;

    private String dificulty;

    public Question(Integer id, String text, String raspuns1, String raspuns2, String raspuns3, String raspunsCorect,String dificulty) {
        super(id);
        this.text = text;
        this.raspuns1 = raspuns1;
        this.raspuns2 = raspuns2;
        this.raspuns3 = raspuns3;
        this.raspunsCorect = raspunsCorect;
        this.dificulty = dificulty;
    }

    public Question() {
        super(-1);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getRaspuns1() {
        return raspuns1;
    }

    public void setRaspuns1(String raspuns1) {
        this.raspuns1 = raspuns1;
    }

    public String getRaspuns2() {
        return raspuns2;
    }

    public void setRaspuns2(String raspuns2) {
        this.raspuns2 = raspuns2;
    }

    public String getRaspuns3() {
        return raspuns3;
    }

    public void setRaspuns3(String raspuns3) {
        this.raspuns3 = raspuns3;
    }

    public String getRaspunsCorect() {
        return raspunsCorect;
    }

    public void setRaspunsCorect(String raspunsCorect) {
        this.raspunsCorect = raspunsCorect;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public String getDificulty() {
        return dificulty;
    }

    public void setDificulty(String dificulty) {
        this.dificulty = dificulty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question pacient = (Question) o;
        return Objects.equals(pacient.getId(),this.getId());
    }

    @Override
    public String toString() {
        return "Question{" +
                "text='" + text + '\'' +
                "/ raspuns1='" + raspuns1 + '\'' +
                "/ raspuns2='" + raspuns2 + '\'' +
                "/ raspuns3='" + raspuns3 + '\'' +
                "/ raspunsCorect='" + raspunsCorect + '\'' +
                "/ punctaj=" + dificulty +
                "/ id=" + id +
                "} ";
    }

    @Override
    public String getForDb() {
        return super.getForDb() + "/" + this.text + "/" + this.raspuns1 + "/" + this.raspuns2 + "/" + this.raspuns3 + "/" + this.raspunsCorect;
    }
}
