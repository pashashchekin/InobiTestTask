package com.somnium.inobitesttask.api;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by erma4elo on 16.10.17.
 */

public class ObserveOnMainThread<T> implements ObservableTransformer<T,T> {
    @Override
    public ObservableSource<T> apply(@NonNull Observable<T> upstream) {
        return upstream
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
