package pcs.ub.edu.ar.clinicavirtual.data.exception.Speciality;

public class SpecialityNullIdException extends SpecialityException {
    public SpecialityNullIdException() {
        super("No puede estar vacio el Id de la especialidad");
    }
}
