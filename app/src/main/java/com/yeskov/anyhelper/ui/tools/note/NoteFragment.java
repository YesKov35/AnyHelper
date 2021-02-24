package com.yeskov.anyhelper.ui.tools.note;

import android.os.Bundle;

import com.yeskov.anyhelper.R;
import com.yeskov.anyhelper.adapters.RecyclerAdapter;
import com.yeskov.anyhelper.adapters.RecyclerModel;
import com.yeskov.anyhelper.dp.entity.NoteEntity;
import com.yeskov.anyhelper.ui.BaseFragment;
import com.yeskov.anyhelper.utils.Constants;
import com.yeskov.anyhelper.utils.NavigationUtils;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class NoteFragment extends BaseFragment {

    private RecyclerView recyclerView;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_tool_note;
    }

    @Override
    protected void initView(@Nullable Bundle bundle) {

        recyclerView = $(R.id.recycler_view);

        dataRepository.setNoteEntity(null);

        dataRepository.getDb().noteDao().getAll().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new SingleObserver<List<NoteEntity>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onSuccess(@NonNull List<NoteEntity> noteEntities) {
                if(noteEntities.size() > 0){
                    setRecycler(noteEntities);
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }
        });

        /*NoteEntity note = new NoteEntity();
        note.setText("TestTestTestTestTestTestTestTestTestTestTest");
        note.setDate(Calendar.getInstance().getTimeInMillis());

        Completable.fromAction(() -> dataRepository.getDb().noteDao().insert(note)).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onComplete() {
                Log.d("writeDB", "onComplete");
            }

            @Override
            public void onError(Throwable e) {
                Log.d("writeDB", "onError = " + e.getMessage());
            }
        });*/

        $(R.id.add_note).setOnClickListener(view -> Navigation.findNavController(view).navigate(R.id.noteEditFragment, null, NavigationUtils.getNavOptions()));
    }

    private void setRecycler(List<NoteEntity> noteEntities){
        List<RecyclerModel> models = new ArrayList<>();

        for (NoteEntity note : noteEntities) {
            models.add(new RecyclerModel(Constants.NOTE_ITEM, note));
        }

        RecyclerAdapter ordersAdapter = new RecyclerAdapter(this, models);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(ordersAdapter);
    }

    public void selectNote(NoteEntity note){
        dataRepository.setNoteEntity(note);
        Navigation.findNavController(requireView()).navigate(R.id.noteEditFragment, null, NavigationUtils.getNavOptions());
    }
}
