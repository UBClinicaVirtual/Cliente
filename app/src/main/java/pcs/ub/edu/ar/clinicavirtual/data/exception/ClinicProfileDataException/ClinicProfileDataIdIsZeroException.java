package pcs.ub.edu.ar.clinicavirtual.data.exception.ClinicProfileDataException;

public class ClinicProfileDataIdIsZeroException extends ClinicProfileDataException {
    public ClinicProfileDataIdIsZeroException() {
        super("El id de la clinica no puede ser 0");
    }
}
