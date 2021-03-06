package pcs.ub.edu.ar.clinicavirtual.interfaces.appointments;

import pcs.ub.edu.ar.clinicavirtual.interfaces.appointments.IAppointment;

public interface IHCPAppointment extends IAppointment {

    public Integer getClinicID();

    public String getClinicName();

    public Integer getPatientID();

    public String getPatientName();

}
