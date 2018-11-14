package pcs.ub.edu.ar.clinicavirtual.activitys.general;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import pcs.ub.edu.ar.clinicavirtual.R;
import pcs.ub.edu.ar.clinicavirtual.data.Appointment;
import pcs.ub.edu.ar.clinicavirtual.data.AppointmentAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TurnsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TurnsFragment extends Fragment {

    private RecyclerView mAppointmentList;
    private AppointmentAdapter mAppointmentAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private View mEmptyView;
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
        mAppointmentList.setHasFixedSize(true);
    }

    //cambiar el esquema de colores del indicador
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
}
