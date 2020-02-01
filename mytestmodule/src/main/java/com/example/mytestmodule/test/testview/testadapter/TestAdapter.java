package com.example.mytestmodule.test.testview.testadapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mytestmodule.R;
import com.example.mytestmodule.databinding.TestItemRvBinding;
import com.example.mytestmodule.test.testviewmodel.TestViewModel;

import java.util.ArrayList;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.TestViewHolder> {

    ArrayList<TestViewModel> list;
    LayoutInflater inflater;

    public TestAdapter(ArrayList<TestViewModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public TestViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (inflater==null)
            inflater=LayoutInflater.from(viewGroup.getContext());
        TestItemRvBinding testItemRvBinding= DataBindingUtil.inflate(inflater, R.layout.test_item_rv,viewGroup,false);
        return new TestViewHolder(testItemRvBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull TestViewHolder testViewHolder, int i) {
        testViewHolder.bind(list.get(i));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class TestViewHolder extends RecyclerView.ViewHolder
    {

        TestItemRvBinding testItemRvBinding;

        public TestViewHolder(@NonNull TestItemRvBinding testItemRvBinding) {
            super(testItemRvBinding.getRoot());
            this.testItemRvBinding=testItemRvBinding;
        }

        public void bind(TestViewModel testViewModel)
        {
            testItemRvBinding.setTest(testViewModel);
            testItemRvBinding.executePendingBindings();
        }
    }
}
