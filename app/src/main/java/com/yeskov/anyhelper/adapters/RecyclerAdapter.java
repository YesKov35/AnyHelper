package com.yeskov.anyhelper.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yeskov.anyhelper.R;
import com.yeskov.anyhelper.dp.entity.NoteEntity;
import com.yeskov.anyhelper.dp.entity.ToolEntity;
import com.yeskov.anyhelper.ui.tools.note.NoteFragment;
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
            case Constants.NOTE_ITEM:
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
                return new NoteHolder(itemView);
            default:
                throw new IllegalStateException("Unexpected value: " + viewType);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

        switch (items.get(position).getType()) {
            case Constants.HOME_ITEM:
                HomeHolder homeHolder = (HomeHolder) viewHolder;
                ToolEntity tool = items.get(position).getToolEntity();

                homeHolder.view.setOnClickListener(view ->
                        Navigation.findNavController(view).navigate(tool.getFragmentId(), null, NavigationUtils.getNavOptions()));
                homeHolder.name.setText(tool.getName());
                break;
            case Constants.PLUS_ITEM:
                HomeHolder plusHolder = (HomeHolder) viewHolder;

                plusHolder.view.setOnClickListener(view ->
                        Navigation.findNavController(view).navigate(R.id.searchFragment, null, NavigationUtils.getNavOptions()));
                break;
            case Constants.NOTE_ITEM:
                NoteHolder noteHolder = (NoteHolder) viewHolder;
                NoteEntity note = items.get(position).getNoteEntity();

                noteHolder.view.setOnClickListener(view -> ((NoteFragment) fragment).selectNote(note));
                noteHolder.text.setText(note.getText());
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
            case 2:
                return Constants.NOTE_ITEM;
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
        private TextView name;

        HomeHolder(View itemView) {
            super(itemView);

            view = itemView.findViewById(R.id.view);
            name = itemView.findViewById(R.id.name);
        }
    }

    static class NoteHolder extends RecyclerView.ViewHolder {

        private View view;
        private TextView text;
        private TextView date;

        NoteHolder(View itemView) {
            super(itemView);

            view = itemView.findViewById(R.id.view);
            text = itemView.findViewById(R.id.text);
            date = itemView.findViewById(R.id.date);
        }
    }
}
