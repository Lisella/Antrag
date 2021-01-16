package org.example;

import java.util.Date;
import java.util.List;

public class Customer {

    public Customer(int id, String prename, String surname, Date birthdate, int riskScore, List contracts) {
        this.id = id;
        this.prename = prename;
        this.surname = surname;
        this.birthdate = birthdate;
        this.riskScore = riskScore;
        this.contracts = contracts;
    }

    int id;
    String prename;
    String surname;
    Date birthdate;
    int riskScore;
    List contracts;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrename() {
        return prename;
    }

    public void setPrename(String prename) {
        this.prename = prename;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public int getRiskScore() {
        return riskScore;
    }

    public void setRiskScore(int riskScore) {
        this.riskScore = riskScore;
    }

    public List getContracts() {
        return contracts;
    }

    public void setContracts(List contracts) {
        this.contracts = contracts;
    }
}