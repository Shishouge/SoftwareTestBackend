package com.example.backend.Solution.Calendar;

public class returnformat {
    String test_case;
    String right_result;
    String actual_result;

    public returnformat(String test_case, String right_result, String actual_result) {
        this.test_case = test_case;
        this.right_result = right_result;
        this.actual_result = actual_result;
    }

    public String getTest_case() {
        return test_case;
    }

    public void setTest_case(String test_case) {
        this.test_case = test_case;
    }

    public String getRight_result() {
        return right_result;
    }

    public void setRight_result(String right_result) {
        this.right_result = right_result;
    }

    public String getActual_result() {
        return actual_result;
    }

    public void setActual_result(String actual_result) {
        this.actual_result = actual_result;
    }
}
