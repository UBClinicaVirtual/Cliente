package pcs.ub.edu.ar.clinicavirtual.data;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import pcs.ub.edu.ar.clinicavirtual.R;

import static com.google.common.base.Preconditions.checkNotNull;

public class AppointmentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private List<Appointment> mAppointment;
    private AppointmentItemListener mItemListener;

    public AppointmentAdapter(List<Appointment> appointments, AppointmentItemListener itemListener){
        setList(appointments);
        mItemListener = itemListener;
    }

    private void setList(List<Appointment> notes) {
        mAppointment = checkNotNull(notes);
    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view;

        view = inflater.inflate(R.layout.item_appointment, parent,false);
        return new AppointmentHolder(view, mItemListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if(viewHolder instanceof AppointmentHolder){
            Appointment appointment = mAppointment.get(position);
            AppointmentHolder appointmentHolder = (AppointmentHolder) viewHolder;

        }
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
