package pcs.ub.edu.ar.clinicavirtual.data.exception.UserData;

public class UserDataNullEmailException extends UserDataException {

    public UserDataNullEmailException() {
        super("No puede estar vacio el email");
    }
}
