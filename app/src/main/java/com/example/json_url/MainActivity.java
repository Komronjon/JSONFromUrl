package com.example.json_url;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.json_url.adapter.EmployeeAdapter;
import com.example.json_url.pojo.Emploee;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
private RecyclerView recyclerViewEmploee;
private EmployeeAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerViewEmploee=findViewById(R.id.recyclerViewEmployees);
        adapter=new EmployeeAdapter();
        recyclerViewEmploee.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewEmploee.setAdapter(adapter);
        List<Emploee> emploees =new ArrayList<>();
        Emploee emploee1=new Emploee();
        Emploee emploee2=new Emploee();
        emploee1.setName("Ivan");
        emploee2.setName("Igor");
        emploee1.setLName("Ivanov");
        emploee2.setLName("Ashurov");
        emploees.add(emploee1);
        emploees.add(emploee2);
        adapter.setEmploees(emploees);




    }
}
