package com.yeskov.anyhelper.ui.tools.note;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.yeskov.anyhelper.R;
import com.yeskov.anyhelper.dp.entity.NoteEntity;
import com.yeskov.anyhelper.ui.BaseFragment;

import java.util.Calendar;

import androidx.annotation.Nullable;
import androidx.navigation.Navigation;
import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class NoteEditFragment extends BaseFragment {

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_tool_note_edit;
    }

    @Override
    protected void initView(@Nullable Bundle bundle) {

        EditText text = $(R.id.text);

        text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(text.getText().toString().isEmpty()){
                    $(R.id.save).setVisibility(View.GONE);
                }else{
                    $(R.id.save).setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        $(R.id.save).setOnClickListener(view -> {
            NoteEntity note = new NoteEntity();
            note.setText(text.getText().toString());
            note.setDate(Calendar.getInstance().getTimeInMillis());

            Completable.fromAction(() -> dataRepository.getDb().noteDao().insert(note)).observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
                @Override
                public void onSubscribe(Disposable d) {
                }

                @Override
                public void onComplete() {
                    Log.d("writeDB", "onComplete");
                    Navigation.findNavController(requireView()).popBackStack();
                }

                @Override
                public void onError(Throwable e) {
                    Log.d("writeDB", "onError = " + e.getMessage());
                }
            });
        });


    }
}
