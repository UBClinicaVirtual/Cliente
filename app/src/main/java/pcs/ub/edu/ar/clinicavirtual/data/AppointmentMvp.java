package pcs.ub.edu.ar.clinicavirtual.data;

import java.util.List;

public interface AppointmentMvp {
    interface View{
        // camino feliz, mostrar lista de turnos
        void showAppointments(List<Appointment> appointments);
        // estado de carga hasta que termine
        void showLoadingState(boolean show);
        // no tengo turnos
        void showEmptyState();
        // mostrar  error
        void showAppointmentsError(String msg);
        // mostrar poner los turns de la pag.
        void showAppointmentPage(List<Appointment> appointments);
        //mostrar indicador de progreso al final de la lista
        void showLoadMoreIndicator(boolean show);
        // on/off si noo hay mas turnos
        void allowMoreData(boolean show);
    }

    // recupera los datos del rep, y los pone en l avista
    interface Presenter{
        void loadAppointment(boolean reload);
    }
}
