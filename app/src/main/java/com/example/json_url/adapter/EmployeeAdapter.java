package com.example.json_url.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.json_url.R;
import com.example.json_url.pojo.Emploee;

import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder>{
   private List<Emploee> emploees;

    public List<Emploee> getEmploees() {
        return emploees;
    }

    public void setEmploees(List<Emploee> emploees) {
        this.emploees = emploees;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
       View view= LayoutInflater.from(viewGroup.getContext())
               .inflate(R.layout.emlpoes_item,viewGroup,false);

        return new EmployeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder emplolee_View, int i) {
Emploee emploee=emploees.get(i);
  emplolee_View.textViewName.setText(emploee.getName());
  emplolee_View.textViewLastName.setText(emploee.getLName());
    }

    @Override
    public int getItemCount() {
        return emploees.size();
    }

    class EmployeeViewHolder extends RecyclerView.ViewHolder{
        private TextView textViewName;
        private TextView textViewLastName;

        public EmployeeViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName=itemView.findViewById(R.id.textViewName);
            textViewLastName=itemView.findViewById(R.id.textViewLastName);
        }
    }
}
