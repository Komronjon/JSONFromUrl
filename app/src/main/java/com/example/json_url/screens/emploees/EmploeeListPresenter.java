package com.example.json_url.screens.emploees;

import com.example.json_url.api.ApiFactory;
import com.example.json_url.api.ApiService;
import com.example.json_url.pojo.EmployeeResponse;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class EmploeeListPresenter {

    private CompositeDisposable compositeDisposable;
    private EmployeesListView view;

    public EmploeeListPresenter(EmployeesListView view) {
        this.view = view;
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
             view.showData(employeeResponse.getResponse());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
        compositeDisposable.add(disposable);
    }
    public void disposeDispasable(){
        if (compositeDisposable!=null) {
            compositeDisposable.dispose();
        }
    }
}
