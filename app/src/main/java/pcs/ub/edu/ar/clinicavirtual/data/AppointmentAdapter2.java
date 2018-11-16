package pcs.ub.edu.ar.clinicavirtual.data;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

import pcs.ub.edu.ar.clinicavirtual.R;
import pcs.ub.edu.ar.clinicavirtual.activitys.general.DetailsAppointmentActivity;

public class AppointmentAdapter2 extends RecyclerView.Adapter<AppointmentAdapter2.AppointmentViewHolder> {
    private List<Appointment> mitems;
    private Context mContext;


    public AppointmentAdapter2(Context mContext,List<Appointment> mitems){
        this.mitems = mitems;
        this.mContext = mContext;
    }

    @Override
    public AppointmentViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_appointment, viewGroup, false);
        return new AppointmentViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final AppointmentViewHolder appointmentViewHolder, int i) {
        appointmentViewHolder.mclinic_name.setText(mitems.get(i).getmClinic().getmFirstName());
        appointmentViewHolder.mdate.setText(mitems.get(i).getDate());
        appointmentViewHolder.mhcp.setText(mitems.get(i).getmHcp().completeName());
        appointmentViewHolder.mstatusAppointment.setText(mitems.get(i).getStateLebel());
        appointmentViewHolder.mSpeciality.setText(mitems.get(i).getmSpeciality().getmName());

        appointmentViewHolder.mLayout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(mContext, DetailsAppointmentActivity.class);
                mIntent.putExtra("clinicname", appointmentViewHolder.mclinic_name.getText().toString());
                mIntent.putExtra("SpecialityName", appointmentViewHolder.mSpeciality.getText().toString());
                mIntent.putExtra("date", appointmentViewHolder.mdate.getText().toString());
                mIntent.putExtra("hcp", appointmentViewHolder.mhcp.getText().toString());
                mIntent.putExtra("appointment_status", appointmentViewHolder.mstatusAppointment.getText().toString());

                mContext.startActivity(mIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mitems.size();
    }


    class AppointmentViewHolder extends RecyclerView.ViewHolder{
        public TextView mclinic_name;
        public TextView mdate;
        public TextView mhcp;
        public TextView mstatusAppointment;
        public TextView mSpeciality;
        CardView mLayout;
        public ImageView featuredImage;

        AppointmentViewHolder(View v){
            super(v);


            mclinic_name = (TextView) itemView.findViewById(R.id.clinic_name);
            mdate = (TextView) itemView.findViewById(R.id.appointment_date);
            mhcp = (TextView) itemView.findViewById(R.id.hcp_name);
            mstatusAppointment = itemView.findViewById(R.id.appointment_status);
            mLayout = itemView.findViewById(R.id.layoutitem);
            mSpeciality = itemView.findViewById(R.id.speciality_name);
        }

    }







}