package pcs.ub.edu.ar.clinicavirtual.interfaces.appointments.interfaces;

public interface IClinicAppointment extends IAppointment {

    public Integer getPatientID();

    public String getPatientName();

    public Integer getHCPID();

    public String getHCPName();
}
