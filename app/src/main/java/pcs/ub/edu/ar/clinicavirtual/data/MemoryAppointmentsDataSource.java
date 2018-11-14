package pcs.ub.edu.ar.clinicavirtual.data;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class MemoryAppointmentsDataSource implements IMemoryAppointmentsDataSource {

    private static HashMap<String, Appointment> mCachedAppointments;

    @Override
    public List<Appointment> find(IAppointmentCriteria criteria) {
        ArrayList<Appointment> appointments = Lists.newArrayList(mCachedAppointments.values());
        return appointments;
    }

    @Override
    public void save(Appointment appointment) {
        if(mCachedAppointments == null){
            mCachedAppointments = new LinkedHashMap<>();
        }
        mCachedAppointments.put(appointment.getID(), appointment);

    }

    @Override
    public void deleteAll() {
        if(mCachedAppointments == null){
            mCachedAppointments = new LinkedHashMap<>();
        }
        mCachedAppointments.clear();
    }

    @Override
    public boolean mapIsNull() {
        return mCachedAppointments == null;
    }
}
