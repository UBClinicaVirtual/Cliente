package pcs.ub.edu.ar.clinicavirtual.data.exception.PatientProfileDataException;

public class PatientProfileDataNullDniException extends PatientProfileDataException {

    public PatientProfileDataNullDniException() {
        super("El DNI no puede estar vacio");
    }
}
