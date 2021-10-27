package com.example.json_url.screens.emploees;

import com.example.json_url.pojo.Emploee;


import java.util.List;

public interface EmployeesListView {
    void showData(List<Emploee> employees);
    void showError();
}
