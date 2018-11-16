package pcs.ub.edu.ar.clinicavirtual.data;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import pcs.ub.edu.ar.clinicavirtual.R;

public class AppointmentAdapter2 extends RecyclerView.Adapter<AppointmentAdapter2.AppointmentViewHolder> {
    private List<Appointment> items;

    public static class AppointmentViewHolder extends RecyclerView.ViewHolder{
        public TextView mclinic_name;
        public TextView mdate;
        public TextView mhcp;
        public TextView mstatusAppointment;
        public ImageView featuredImage;

        public AppointmentViewHolder(View v){
            super(v);
            
            mclinic_name = (TextView) itemView.findViewById(R.id.clinic_name);
            mdate = (TextView) itemView.findViewById(R.id.appointment_date);
            mhcp = (TextView) itemView.findViewById(R.id.hcp_name);
            mstatusAppointment = itemView.findViewById(R.id.appointment_status);
        }

    }
    public AppointmentAdapter2(List<Appointment> items){
        this.items = items;
    }


    @Override
    public AppointmentViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.item_appointment, viewGroup, false);
        return new AppointmentViewHolder(v);
    }

    @Override
    public void onBindViewHolder( AppointmentViewHolder appointmentViewHolder, int i) {
        appointmentViewHolder.mclinic_name.setText(items.get(i).getClinicName());
        appointmentViewHolder.mdate.setText(items.get(i).getDate());
        appointmentViewHolder.mhcp.setText(items.get(i).getHCPName());
        appointmentViewHolder.mstatusAppointment.setText(items.get(i).getStateLebel());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


}