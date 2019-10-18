package com.codefactory.approomrx.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.codefactory.approomrx.data.AppDataBase;
import com.codefactory.approomrx.data.JudgmentCrimeAndPerson;
import com.codefactory.approomrx.utils.DataInit;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class JudgeViewModel extends AndroidViewModel {

    private AppDataBase appDataBase;
    private LiveData<String> dataResult;

    public JudgeViewModel(@NonNull Application application) {
        super(application);
    }

    public void initDataBase(){
        appDataBase =AppDataBase.getDataBase(getApplication());
        DataInit.populateAsync(appDataBase);
    }

    public LiveData<String> getCrimesResult(){
        return dataResult;
    }

    private void subscribeDataChanges(){
        LiveData<List<JudgmentCrimeAndPerson>> liveData = appDataBase.judgmentDao().findAllWithPersonAndCrime();

        // Instead of exposing the list of Judgment, apply a transformation and expose data as Strings.

        dataResult = Transformations.map(liveData,
                new Function<List<JudgmentCrimeAndPerson>, String>() {
                    @Override
                    public String apply(List<JudgmentCrimeAndPerson> input) {
                        StringBuilder sb = new StringBuilder();
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH);

                        for (JudgmentCrimeAndPerson cp: input){
                            sb.append(String.format("%s\n  convicted: %s From: %s To: %s\n",
                                    cp.getPersonName(),cp.getCrimeName(),cp.getStartTime(),cp.getEndTime()));
                        }

                        return sb.toString();
                    }
                });
    }


}
