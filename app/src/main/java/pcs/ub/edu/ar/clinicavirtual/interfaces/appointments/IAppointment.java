package pcs.ub.edu.ar.clinicavirtual.interfaces.appointments;

public interface IAppointment {

    public Integer getID();

    public Integer getSpecialityID();

    public String getSpecialityName();


    public String getDate();

    public Integer getState();

    public String getStateLebel();


}
