package pcs.ub.edu.ar.clinicavirtual.data.exception.UserData;

public class UserDataInvalidEmailException extends UserDataException {
    public UserDataInvalidEmailException() {
        super("Tiene que ingresar un mail");
    }
}
