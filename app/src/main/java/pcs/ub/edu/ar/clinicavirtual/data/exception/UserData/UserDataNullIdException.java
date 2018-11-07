package pcs.ub.edu.ar.clinicavirtual.data.exception.UserData;

public class UserDataNullIdException extends UserDataException {

    public UserDataNullIdException() {
        super("El id User data no puede ser null");
    }
}
