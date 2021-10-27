package com.example.json_url.screens.emploees;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.json_url.api.ApiFactory;
import com.example.json_url.api.ApiService;
import com.example.json_url.data.AppDatabase;
import com.example.json_url.pojo.Emploee;
import com.example.json_url.pojo.EmployeeResponse;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class EmploeeViewModel extends AndroidViewModel {

    private  static AppDatabase db;
    private LiveData<List<Emploee>> emploees;
    private CompositeDisposable compositeDisposable;
    private MutableLiveData<Throwable> errors;

    public EmploeeViewModel(@NonNull Application application) {
        super(application);
        db=AppDatabase.getInstance(application);
        emploees=db.emploeeDao().getAllEmploees();
        errors=new MutableLiveData<>();
    }

    public LiveData<List<Emploee>> getEmploees() {
        return emploees;
    }

    public LiveData<Throwable> getErrors() {
        return errors;
    }

    public void clearErrors(){
        errors.setValue(null);
    }

      @SuppressWarnings("unchecked")
    private void insertEmploees(List<Emploee> emploees){
     new InsertEmployeesTask().execute(emploees);
    }
    private static class InsertEmployeesTask extends AsyncTask<List<Emploee>, Void, Void> {
        @SafeVarargs
        @Override
        protected final Void doInBackground(List<Emploee>... lists) {
            if (lists != null && lists.length > 0) {
                db.emploeeDao().insertEmploees(lists[0]);
            }
            return null;
        }
    }
    private void deleteAllEmployees() {
        new DeleteAllEmployeesTask().execute();
    }
    private static class DeleteAllEmployeesTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            db.emploeeDao().deleteAllEmploeee();
            return null;
        }
    }


    public void loadData(){
        ApiFactory apiFactory = ApiFactory.getInstance();
        ApiService apiService = apiFactory.getApiService();
        compositeDisposable=new CompositeDisposable();
        Disposable disposable = apiService.getEmploees()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<EmployeeResponse>() {
                    @Override
                    public void accept(EmployeeResponse employeeResponse) throws Exception {
                          deleteAllEmployees();
                          insertEmploees(employeeResponse.getEmploees());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        errors.setValue(throwable);
                    }
                });
        compositeDisposable.add(disposable);
    }

    @Override
    protected void onCleared() {
        compositeDisposable.dispose();
        super.onCleared();
    }
}
