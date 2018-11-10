package pcs.ub.edu.ar.clinicavirtual.data.exception.PatientProfileDataException;

public class PatientProfileDataNullSurnameException extends PatientProfileDataException{

    public PatientProfileDataNullSurnameException() {
        super("El apellido no puede estar vacio");
    }
}
