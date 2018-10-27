package pcs.ub.edu.ar.clinicavirtual.data.exception.PatientProfileDataException;

public class PatientProfileDataDniIsZeroException extends PatientProfileDataException {

    public PatientProfileDataDniIsZeroException() {
        super("El dni no puede ser 0");
    }
}
