package pcs.ub.edu.ar.clinicavirtual.data;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import pcs.ub.edu.ar.clinicavirtual.R;

import static com.google.common.base.Preconditions.checkNotNull;

public class AppointmentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements DataLoading {


    private List<Appointment> mAppointment;
    private AppointmentItemListener mItemListener;

    private boolean mLoading = false;
    private boolean mMoreData = false;

    public AppointmentAdapter(List<Appointment> appointments, AppointmentItemListener itemListener){
        setList(appointments);
        mItemListener = itemListener;
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
            appointmentHolder.mclinic_name.setText(appointment.getClinicName());
            appointmentHolder.mdate.setText(appointment.getDate());
            appointmentHolder.mhcp.setText(appointment.getHCPName());
            appointmentHolder.mstatusAppointment.setText(appointment.getStateLebel());

        }
    }
    public void replaceData(List<Appointment> notes) {
        setList(notes);
        notifyDataSetChanged();
    }

    private void setList(List<Appointment> notes) {
        mAppointment = checkNotNull(notes);
    }

    public void addData(List<Appointment> appointments){
        mAppointment.addAll(appointments);
    }
    @Override
    public int getItemCount() {
        return getDataItemCount();
    }

    public Appointment getItem(int position){
        return mAppointment.get(position);
    }

    public int getDataItemCount(){
        return mAppointment.size();
    }

    @Override
    public boolean isLoadingData() {
        return mLoading;
    }

    @Override
    public boolean isThereMoreData() {
        return mMoreData;
    }


    class AppointmentHolder  extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView mclinic_name;
    public TextView mdate;
    public TextView mhcp;
    public TextView mstatusAppointment;
    public ImageView featuredImage;

    private AppointmentItemListener mAppointmentItemListener;

    public AppointmentHolder(View itemView, AppointmentItemListener listener) {
        super(itemView);
        mAppointmentItemListener = listener;
        mclinic_name = (TextView) itemView.findViewById(R.id.clinic_name);
        mdate = (TextView) itemView.findViewById(R.id.appointment_date);
        mhcp = (TextView) itemView.findViewById(R.id.hcp_name);
        mstatusAppointment = itemView.findViewById(R.id.appointment_status);
        featuredImage = (ImageView) itemView.findViewById(R.id.appointment_featured_image);
    }

    @Override
    public void onClick(View view) {
        int position = getAdapterPosition();
        Appointment appointment = getItem(position);
        mItemListener.onAppointmentClick(appointment);
    }
}

 public interface AppointmentItemListener {
        void onAppointmentClick(Appointment clickedNote);
 }
}