package com.company.app.models;

import java.io.Serializable;

public class Salary extends AbstractUserEntity implements Serializable {
    private String salary;
    private String pension;

    public Salary() {
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getPension() {
        return pension;
    }

    public void setPension(String pension) {
        this.pension = pension;
    }
}
