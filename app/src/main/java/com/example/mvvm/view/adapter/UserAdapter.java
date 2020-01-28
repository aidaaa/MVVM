package com.example.mvvm.view.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mvvm.R;
import com.example.mvvm.databinding.ItemUserBinding;
import com.example.mvvm.viewmodel.UserViewModel;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private ArrayList<UserViewModel> arrayList=new ArrayList<>();
    private LayoutInflater inflater;

    public UserAdapter(ArrayList<UserViewModel> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (inflater==null)
            inflater=LayoutInflater.from(viewGroup.getContext());
        ItemUserBinding itemUserBinding= DataBindingUtil.inflate(inflater, R.layout.item_user,viewGroup,false);
        return new UserViewHolder(itemUserBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder userViewHolder, int i) {
        userViewHolder.bind(arrayList.get(i));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder
    {
        ItemUserBinding itemUserBinding;

        public UserViewHolder(@NonNull ItemUserBinding itemUserBinding) {
            super(itemUserBinding.getRoot());
            this.itemUserBinding=itemUserBinding;
        }

        public void bind(UserViewModel userViewModel)
        {
         itemUserBinding.setItem(userViewModel);
            itemUserBinding.executePendingBindings();
        }
    }
}
