package pcs.ub.edu.ar.clinicavirtual.interfaces.appointments.interfaces;

public interface IPatientAppointment extends IAppointment{

    public Integer getClinicID();

    public String getClinicName();

    public Integer getHCPID();

    public String getHCPName();

}
