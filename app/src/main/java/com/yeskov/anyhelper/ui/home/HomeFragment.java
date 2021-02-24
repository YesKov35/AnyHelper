package com.yeskov.anyhelper.ui.home;

import android.os.Bundle;
import android.view.View;

import com.yeskov.anyhelper.R;
import com.yeskov.anyhelper.adapters.RecyclerAdapter;
import com.yeskov.anyhelper.adapters.RecyclerModel;
import com.yeskov.anyhelper.dp.entity.ToolEntity;
import com.yeskov.anyhelper.ui.BaseFragment;
import com.yeskov.anyhelper.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class HomeFragment extends BaseFragment {

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(@Nullable Bundle bundle) {

        RecyclerView recyclerView = $(R.id.recycler_view);

        List<RecyclerModel> models = new ArrayList<>();

        ToolEntity tool = new ToolEntity();
        tool.setFragmentId(R.id.noteFragment);
        tool.setName("Note");

        models.add(new RecyclerModel(Constants.HOME_ITEM, tool));
        models.add(new RecyclerModel(Constants.PLUS_ITEM));

        RecyclerAdapter ordersAdapter = new RecyclerAdapter(this, models);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 4);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(ordersAdapter);
    }
}
