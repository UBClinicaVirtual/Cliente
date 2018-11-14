package pcs.ub.edu.ar.clinicavirtual.data;

import java.util.List;

public interface IMemoryAppointmentsDataSource {
    List <Appointment> find(IAppointmentCriteria criteria);

    void save (Appointment appointment);
    void deleteAll();
    boolean mapIsNull();
}
