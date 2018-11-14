package pcs.ub.edu.ar.clinicavirtual;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;

import pcs.ub.edu.ar.clinicavirtual.data.DataLoading;

import static com.google.android.gms.internal.firebase_auth.zzv.checkNotNull;

public abstract class InfiniteScrollListener extends OnScrollListener {

    //cantidad requerida de items visibles antes del final, para cargar una nueva pag
    private static final int VISIBLE_THRESHOLD = 5;

    private final LinearLayoutManager mLayoutManager;
    private final DataLoading mDataLoading;


    public InfiniteScrollListener(DataLoading dataLoading, LinearLayoutManager linearLayoutManager){
        mDataLoading = checkNotNull(dataLoading);
        mLayoutManager = checkNotNull(linearLayoutManager);
    }


    // si la cnatidad total de i. menos la cant de visibles, es menor o igual que la suma
    // del primer item mas el threshold, entonces se inicia otra carga
    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        if (dy < 0 || mDataLoading.isLoadingData() || !mDataLoading.isThereMoreData())
            return;

        final int visibleItemCount = recyclerView.getChildCount();
        final int totalItemCount = mLayoutManager.getItemCount();
        final int firstVisibleItem = mLayoutManager.findFirstVisibleItemPosition();

        if ((totalItemCount - visibleItemCount) <= (firstVisibleItem + VISIBLE_THRESHOLD)){
            onLoadMore();
        }
    }

    public abstract void onLoadMore();


}

