package pcs.ub.edu.ar.clinicavirtual.data;

import java.util.List;

public interface IAppointmentsRepository {
    interface GetAppointmentsCallback{
        void onAppointmentsLoaded(List<Appointment> appointments);
        void onDataNotAvailable (String error);
    }
    void getAppointments(GetAppointmentsCallback callback, IAppointmentCriteria criteria);
    void refreshAppointments();
}
