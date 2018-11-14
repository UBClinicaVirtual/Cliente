package pcs.ub.edu.ar.clinicavirtual.data;

import java.util.ArrayList;
import java.util.List;

public class PagingAppointmentCriteria implements IAppointmentCriteria {

    private final int mPage;
    private final int mLimit;

    public PagingAppointmentCriteria(int page, int limit){
        mPage = page;
        mLimit = limit;
    }
    @Override
    public List<Appointment> match(List<Appointment> appointments) {
        List<Appointment> criteriaAppointments = new ArrayList<>();

        if (mLimit <=0 || mPage <= 0){
            return criteriaAppointments;
        }
        int size = appointments.size();
        int numPages = size/mLimit;
        int a,b;

        if(mPage > numPages){
            return criteriaAppointments;
        }
        a = (mPage - 1 ) * mLimit;
        if(a == size){
            return criteriaAppointments;
        }

        b = a + mLimit;
        criteriaAppointments = appointments.subList(a,b);
        return criteriaAppointments;
    }
}
