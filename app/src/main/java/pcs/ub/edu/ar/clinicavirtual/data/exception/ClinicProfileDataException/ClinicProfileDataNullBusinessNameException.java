package pcs.ub.edu.ar.clinicavirtual.data.exception.ClinicProfileDataException;

public class ClinicProfileDataNullBusinessNameException extends ClinicProfileDataException{
    public ClinicProfileDataNullBusinessNameException() {
        super("El nombre de la clinica no puede estar vacio");
    }
}
