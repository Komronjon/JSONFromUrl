package com.example.json_url.converter;

import android.widget.Spinner;

import androidx.room.TypeConverter;

import com.example.json_url.pojo.Speciality;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Converter {
    @TypeConverter
    public String ListScialityToString(List<Speciality> specialities) {
        return     new Gson().toJson(specialities);
//        JSONArray jsonArray = new JSONArray();
//        for (Speciality speciality : specialities) {
//            JSONObject jsonObject = new JSONObject();
//            try {
//                jsonObject.put("specialty_id", speciality.getSpecialtyId());
//                jsonObject.put("name", speciality.getName());
//                jsonArray.put(jsonObject);
//            } catch (JSONException e) {
//                e.printStackTrace();
//
//            }
//        }
//        return jsonArray.toString();
    }
    @TypeConverter
    public List<Speciality> stringToListSpecialty(String specialtyAsString){
        Gson gson=new Gson();
        ArrayList objects=gson.fromJson(specialtyAsString,ArrayList.class);
        ArrayList<Speciality> specialities=new ArrayList<>();
        for (Object o:objects){
            specialities.add(gson.fromJson(o.toString(),Speciality.class));
        }
        return specialities;
    }
}
