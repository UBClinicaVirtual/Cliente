package pcs.ub.edu.ar.clinicavirtual.interfaces.appointments;

import pcs.ub.edu.ar.clinicavirtual.interfaces.appointments.IAppointment;

public interface IPatientAppointment extends IAppointment {

    public Integer getClinicID();

    public String getClinicName();

    public Integer getHCPID();

    public String getHCPName();

}
