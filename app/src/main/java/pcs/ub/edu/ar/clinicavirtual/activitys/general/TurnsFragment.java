package pcs.ub.edu.ar.clinicavirtual.activitys.general;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import pcs.ub.edu.ar.clinicavirtual.InfiniteScrollListener;
import pcs.ub.edu.ar.clinicavirtual.R;
import pcs.ub.edu.ar.clinicavirtual.data.Appointment;
import pcs.ub.edu.ar.clinicavirtual.data.appointment.AppointmentAdapter;
import pcs.ub.edu.ar.clinicavirtual.data.appointment.interfaces.AppointmentMvp;
import pcs.ub.edu.ar.clinicavirtual.data.appointment.AppointmentPresenter;
import pcs.ub.edu.ar.clinicavirtual.data.appointment.DependencyProvider;


public class TurnsFragment extends Fragment implements AppointmentMvp.View {

    private RecyclerView mAppointmentList;
    private AppointmentAdapter mAppointmentAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private View mEmptyView;
    private AppointmentPresenter mAppointmentPresenter;

    private AppointmentAdapter.AppointmentItemListener mItemListener = new AppointmentAdapter.AppointmentItemListener() {
        @Override
        public void onAppointmentClick(Appointment clickedNote) {
            // Pendiente armar la pantalla detalle de los turnos
        }
    };


    public TurnsFragment() {
        // Required empty public constructor
    }

    public static TurnsFragment newInstance() {
        return new TurnsFragment();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAppointmentAdapter = new AppointmentAdapter(new ArrayList<Appointment>(0), mItemListener);
        mAppointmentPresenter = new AppointmentPresenter(
                // con el dependencyprovide le quito la responsibilidad del pres.
                // de saber como crear el repositorio
                DependencyProvider.provideAppointmentsRepository(getActivity()),
                this);

        setRetainInstance(true);
        }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //conseguir todos los elemtnos de la inte
        View root = inflater.inflate(R.layout.fragment_turns, container , false);

        //referencias ui
        mAppointmentList = (RecyclerView) root.findViewById(R.id.appointment_list);
        mEmptyView = root.findViewById(R.id.noAppointment);
        mSwipeRefreshLayout = (SwipeRefreshLayout)
                root.findViewById(R.id.refresh_layout);

        //setup
        setUpAppointmentList();
        setUpRefreshLayout();

        return root;

    }

    private void setUpAppointmentList() {
        mAppointmentList.setAdapter(mAppointmentAdapter);
        final LinearLayoutManager layoutManager = (LinearLayoutManager) mAppointmentList.getLayoutManager();
        mAppointmentList.addOnScrollListener(new InfiniteScrollListener(mAppointmentAdapter, layoutManager) {
            @Override
            public void onLoadMore() {
                mAppointmentPresenter.loadAppointment(false);
            }
        });
    }


    private void setUpRefreshLayout(){
        mSwipeRefreshLayout.setColorSchemeColors(
                ContextCompat.getColor(getActivity(),R.color.colorPrimary),
                ContextCompat.getColor(getActivity(), R.color.colorAccent),
                ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark));

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

            }
        });

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState == null) {
            mAppointmentPresenter.loadAppointment(false);
        }
    }

    @Override
    public void showAppointments(List<Appointment> appointments) {
        mAppointmentAdapter.replaceData(appointments);

        mAppointmentList.setVisibility(View.VISIBLE);
        mEmptyView.setVisibility(View.GONE);
    }

    @Override
    public void showLoadingState(final boolean show) {
        if(getView() == null){
            return;
        }

        // evitar que se sobrepongan los mesj
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(show);
            }
        });
    }

    @Override
    public void showEmptyState() {
        mAppointmentList.setVisibility(View.GONE);
        mEmptyView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showAppointmentsError(String msg) {
        Toast.makeText(getActivity(),msg, Toast.LENGTH_LONG).show();

    }

    @Override
    public void showAppointmentPage(List<Appointment> appointments) {
        //ampliar los turnos de la pagina
        mAppointmentAdapter.addData(appointments);
    }

    @Override
    public void showLoadMoreIndicator(boolean show) {
        if(!show){
            mAppointmentAdapter.dataFinishedLoading();
        }else{
            mAppointmentAdapter.dataStartedLoading();
        }
    }

    @Override
    public void allowMoreData(boolean allow) {
           mAppointmentAdapter.setmMoreData(allow);
    }
}
