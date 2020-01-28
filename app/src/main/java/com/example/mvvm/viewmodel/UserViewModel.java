package com.example.mvvm.viewmodel;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.databinding.library.baseAdapters.BR;
import com.example.mvvm.model.UserModel;
import com.example.mvvm.view.adapter.UserAdapter;

import java.util.ArrayList;

public class UserViewModel extends BaseObservable
{
    private String name;
    private String phone;
    Context context;

    ArrayList<UserViewModel> userViewModels=new ArrayList<>();
    //private MutableLiveData<ArrayList<UserViewModel>> userMutableLiveData=new MutableLiveData<>();
    private MutableLiveData<ArrayList<UserViewModel>> userMutableLiveData=new MutableLiveData<>();



    public UserViewModel(UserModel userModel) {
        this.name = userModel.getName();
        this.phone = userModel.getPhone();
    }

    public UserViewModel(Context context) {
        this.context = context;
        for (int i = 0; i < 5; i++) {
            UserModel userModel=new UserModel();
            userModel.setName("Name: "+i);
            userModel.setPhone("Phone: "+i);
            UserViewModel userViewModel=new UserViewModel(userModel);
            userViewModels.add(userViewModel);
        }
        userMutableLiveData.setValue(userViewModels);
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
        notifyPropertyChanged(BR.phone);
    }

    @BindingAdapter("bind:rvBind")
    public static void rvBind(final RecyclerView recyclerView, MutableLiveData<ArrayList<UserViewModel>> userMutableLiveData)
    {
        userMutableLiveData.observe((LifecycleOwner) recyclerView.getContext(), new Observer<ArrayList<UserViewModel>>() {
            @Override
            public void onChanged(@Nullable ArrayList<UserViewModel> userViewModels) {
                UserAdapter userAdapter=new UserAdapter(userViewModels);
                recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(),LinearLayoutManager.VERTICAL,false));
                recyclerView.setAdapter(userAdapter);
            }
        });

    }

    @Bindable
    public MutableLiveData<ArrayList<UserViewModel>> getUserViewModels() {
        return userMutableLiveData;
    }

    public void setUserViewModels(MutableLiveData<ArrayList<UserViewModel>> userMutableLiveData) {
        this.userMutableLiveData = userMutableLiveData;
        notifyPropertyChanged(BR.userViewModels);
    }
}
