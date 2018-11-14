package pcs.ub.edu.ar.clinicavirtual.data;

import java.util.List;

public interface ICloudAppointmentsDataSource {
    interface AppointmentServiceCallback{
        void onLoaded(List<Appointment> appointments);
        void onError(String error);
    }
    void getAppointments(AppointmentServiceCallback callback);
}
