package com.yeskov.anyhelper.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yeskov.anyhelper.R;
import com.yeskov.anyhelper.utils.Constants;
import com.yeskov.anyhelper.utils.NavigationUtils;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    private List<RecyclerModel> items;
    private Fragment fragment;

    public RecyclerAdapter(Fragment fragment, List<RecyclerModel> items) {
        this.items = items;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;

        switch (viewType){
            case Constants.HOME_ITEM:
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home, parent, false);
                return new HomeHolder(itemView);
            case Constants.PLUS_ITEM:
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_plus, parent, false);
                return new HomeHolder(itemView);
            default:
                throw new IllegalStateException("Unexpected value: " + viewType);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

        switch (items.get(position).getType()) {
            case Constants.HOME_ITEM:
                HomeHolder homeHolder = (HomeHolder) viewHolder;

                homeHolder.view.setOnClickListener(view -> {});
            case Constants.PLUS_ITEM:
                HomeHolder plusHolder = (HomeHolder) viewHolder;

                plusHolder.view.setOnClickListener(view ->
                        Navigation.findNavController(view).navigate(R.id.searchFragment, null, NavigationUtils.getNavOptions()));
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        switch (items.get(position).getType()) {
            case 0:
                return Constants.HOME_ITEM;
            case 1:
                return Constants.PLUS_ITEM;
            default:
                return -1;
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class HomeHolder extends RecyclerView.ViewHolder {

        private View view;

        HomeHolder(View itemView) {
            super(itemView);

            view = itemView.findViewById(R.id.view);
        }
    }
}
