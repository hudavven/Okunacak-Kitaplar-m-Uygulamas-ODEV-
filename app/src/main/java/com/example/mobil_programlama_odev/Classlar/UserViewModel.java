package com.example.mobil_programlama_odev.Classlar;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UserViewModel extends ViewModel {
    private final MutableLiveData<String> email = new MutableLiveData<>();
    private final MutableLiveData<String> uid = new MutableLiveData<>();

    public void setEmail(String email) {
        this.email.setValue(email);
    }

    public void setUid(String uid) {
        this.uid.setValue(uid);
    }

    public LiveData<String> getEmail() {
        return email;
    }

    public LiveData<String> getUid() {
        return uid;
    }
}

