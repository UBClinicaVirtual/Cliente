package pcs.ub.edu.ar.clinicavirtual.data.appointment.interfaces;

import java.util.List;

import pcs.ub.edu.ar.clinicavirtual.data.Appointment;

public interface ICloudAppointmentsDataSource {
    interface AppointmentServiceCallback{
        void onLoaded(List<Appointment> appointments);
        void onError(String error);
    }
    void getAppointments(AppointmentServiceCallback callback);
}
