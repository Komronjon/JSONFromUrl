package com.example.json_url.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.json_url.pojo.Emploee;

import java.util.List;

@Dao
public interface  EmploeeDao {
    @Query("SELECT * FROM emploee")
    LiveData<List<Emploee>> getAllEmploees();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertEmploees(List<Emploee> emploees);

    @Query("DELETE  FROM emploee")
    void deleteAllEmploeee();

}
