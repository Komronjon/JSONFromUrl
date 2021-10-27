package com.example.json_url.screens.emploees;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.json_url.R;
import com.example.json_url.adapter.EmployeeAdapter;
import com.example.json_url.pojo.Emploee;
import com.example.json_url.pojo.Speciality;

import java.util.ArrayList;
import java.util.List;

public class EmploeeListActivity extends AppCompatActivity {
    private RecyclerView recyclerViewEmploee;
    private EmployeeAdapter adapter;
    private EmploeeViewModel viewModel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewEmploee = findViewById(R.id.recyclerViewEmployees);
        adapter = new EmployeeAdapter();
        adapter.setEmploees(new ArrayList<Emploee>());
        recyclerViewEmploee.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewEmploee.setAdapter(adapter);
        viewModel= ViewModelProviders.of(this).get(EmploeeViewModel.class);
        viewModel.getEmploees().observe(this, new Observer<List<Emploee>>() {
            @Override
            public void onChanged(List<Emploee> emploees) {
                adapter.setEmploees(emploees);
                if (emploees!=null) {
                    for (Emploee emploee : emploees) {
                        List<Speciality> specialities = emploee.getSpecialty();
                        for (Speciality speciality : specialities) {
                            Log.i("Specialty", speciality.getName());
                        }
                    }
                }

            }
        });
        viewModel.getErrors().observe(this, new Observer<Throwable>() {
            @Override
            public void onChanged(Throwable throwable) {
                if (throwable!=null) {
                    Toast.makeText(EmploeeListActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    viewModel.clearErrors();
                }
            }
        });
        viewModel.loadData();

    }

}
