package pcs.ub.edu.ar.clinicavirtual.data.exception.UserData;

public class UserDataIdIsZeroException extends UserDataException {
    public UserDataIdIsZeroException() {
        super("El id no puede ser 0");
    }
}
