package pcs.ub.edu.ar.clinicavirtual.data;

import java.util.List;

public interface IAppointmentCriteria {
    List<Appointment> match(List<Appointment> appointments);
}
