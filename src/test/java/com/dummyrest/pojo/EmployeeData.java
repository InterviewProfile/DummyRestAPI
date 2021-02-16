package com.dummyrest.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployeeData {



    private String employee_name;
    private String employee_age;
    private String employee_salary;

    public EmployeeData (String employee_name,String employee_salary,String employee_age){
        this.employee_name = employee_name;
        this.employee_salary = employee_salary;
        this.employee_age = employee_age;
    }


    public String getEmployee_name() {
        return employee_name;
    }

    public String getEmployee_salary() {
        return employee_salary;
    }

    public String getEmployee_age() {
        return employee_age;
    }

    @Override
    public String toString() {
        return
                "Name: " + employee_name + "\n"+
                "Age: " + employee_age + "\n"+
                "Salary: $" + employee_salary ;
    }

}
