package pcs.ub.edu.ar.clinicavirtual.data.exception.Speciality;

public class SpecialityIdIsZeroException extends SpecialityException {
    public SpecialityIdIsZeroException() {
        super("No puede 0 el id de la especialidad");
    }
}
