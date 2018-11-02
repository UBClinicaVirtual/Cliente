package pcs.ub.edu.ar.clinicavirtual.data.exception.ClinicProfileDataException;

public class ClinicProfileDataNullIdException extends ClinicProfileDataException{
    public ClinicProfileDataNullIdException() {
        super("El id de la clinica no puede ser vacio");
    }
}
