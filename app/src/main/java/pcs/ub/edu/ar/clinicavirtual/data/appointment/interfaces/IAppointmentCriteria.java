package pcs.ub.edu.ar.clinicavirtual.data.appointment.interfaces;

import java.util.List;

import pcs.ub.edu.ar.clinicavirtual.data.Appointment;

public interface IAppointmentCriteria {
    List<Appointment> match(List<Appointment> appointments);
}
