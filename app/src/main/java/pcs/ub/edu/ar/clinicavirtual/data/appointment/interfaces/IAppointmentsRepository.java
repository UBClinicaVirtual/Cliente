package pcs.ub.edu.ar.clinicavirtual.data.appointment.interfaces;

import java.util.List;

import pcs.ub.edu.ar.clinicavirtual.data.Appointment;

public interface IAppointmentsRepository {
    interface GetAppointmentsCallback{
        void onAppointmentsLoaded(List<Appointment> appointments);
        void onDataNotAvailable (String error);
    }
    void getAppointments(GetAppointmentsCallback callback, IAppointmentCriteria criteria);
    void refreshAppointments();
}
