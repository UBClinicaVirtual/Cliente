package pcs.ub.edu.ar.clinicavirtual.data.exception.UserData;

public class UserDataNullNameException extends UserDataException {

    public UserDataNullNameException() {
        super("No puede estar vacio el nombre de user data");
    }

}
