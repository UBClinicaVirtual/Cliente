package pcs.ub.edu.ar.clinicavirtual.data.exception.Speciality;

public class SpecialityNullNameException extends SpecialityException {
    public SpecialityNullNameException() {
        super("No puede estar vacio el nombre de la especialidad");
    }
}
