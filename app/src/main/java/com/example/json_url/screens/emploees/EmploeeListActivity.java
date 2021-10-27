package com.example.json_url.screens.emploees;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.json_url.R;
import com.example.json_url.adapter.EmployeeAdapter;
import com.example.json_url.pojo.Emploee;

import java.util.ArrayList;
import java.util.List;

public class EmploeeListActivity extends AppCompatActivity implements EmployeesListView {
    private RecyclerView recyclerViewEmploee;
    private EmployeeAdapter adapter;
    private EmploeeListPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new EmploeeListPresenter(this);
        recyclerViewEmploee = findViewById(R.id.recyclerViewEmployees);
        adapter = new EmployeeAdapter();
        adapter.setEmploees(new ArrayList<Emploee>());
        recyclerViewEmploee.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewEmploee.setAdapter(adapter);
        presenter.loadData();
    }

    @Override
    public void showData(List<Emploee> employees) {
        adapter.setEmploees(employees);
    }

    @Override
    public void showError() {
        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onDestroy() {
        presenter.disposeDispasable();
        super.onDestroy();
    }
}
