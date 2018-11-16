package pcs.ub.edu.ar.clinicavirtual.data.appointment.interfaces;

import java.util.List;

import pcs.ub.edu.ar.clinicavirtual.data.Appointment;

public interface IMemoryAppointmentsDataSource {
    List <Appointment> find(IAppointmentCriteria criteria);

    void save (Appointment appointment);
    void deleteAll();
    boolean mapIsNull();
}
