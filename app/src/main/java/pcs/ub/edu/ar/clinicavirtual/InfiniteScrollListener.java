package pcs.ub.edu.ar.clinicavirtual;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;

import pcs.ub.edu.ar.clinicavirtual.data.DataLoading;

public abstract class InfiniteScrollListener extends OnScrollListener {

    //cantidad requerida de items visibles antes del final, para cargar una nueva pag
    private static final int VISIBLE_THRESHOLD = 5;

    private final LinearLayoutManager mLayoutManager;
    private final DataLoading mDataLoading;


    public InfiniteScrollListener(DataLoading dataLoading, LinearLayoutManager linearLayoutManager){
        
    }
    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

    }


}

