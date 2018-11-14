package pcs.ub.edu.ar.clinicavirtual.data;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

class AppointmentHolder  extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView mclinic_name;
    public TextView mdate;
    public TextView mhcp;
    public TextView mstatusAppointment;

    private AppointmentItemListener mAppointmentItemListener;

    public AppointmentHolder(View itemView, AppointmentItemListener listener) {
        super(itemView);
        mAppointmentItemListener = listener;
        mclinic_name = (TextView) itemView.findViewById(R.id.)

    }

    @Override
    public void onClick(View view) {

    }
}
