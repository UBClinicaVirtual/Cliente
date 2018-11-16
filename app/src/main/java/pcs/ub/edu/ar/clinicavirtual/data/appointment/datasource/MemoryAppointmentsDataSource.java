package pcs.ub.edu.ar.clinicavirtual.data.appointment.datasource;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import pcs.ub.edu.ar.clinicavirtual.data.Appointment;
import pcs.ub.edu.ar.clinicavirtual.data.appointment.interfaces.IAppointmentCriteria;
import pcs.ub.edu.ar.clinicavirtual.data.appointment.interfaces.IMemoryAppointmentsDataSource;

public class MemoryAppointmentsDataSource implements IMemoryAppointmentsDataSource {

    private static HashMap<String, Appointment> mCachedAppointments;

    @Override
    public List<Appointment> find(IAppointmentCriteria criteria) {
        ArrayList<Appointment> appointments = Lists.newArrayList(mCachedAppointments.values());
        return criteria.match(appointments);
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
