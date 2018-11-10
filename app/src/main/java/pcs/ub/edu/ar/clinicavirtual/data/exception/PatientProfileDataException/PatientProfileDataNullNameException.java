package pcs.ub.edu.ar.clinicavirtual.data.exception.PatientProfileDataException;

public class PatientProfileDataNullNameException extends PatientProfileDataException{

    public PatientProfileDataNullNameException() {
        super("El nombre del paciente no puede ser vacio");
    }
}
