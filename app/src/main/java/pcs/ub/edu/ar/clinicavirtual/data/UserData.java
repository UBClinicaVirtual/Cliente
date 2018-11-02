package pcs.ub.edu.ar.clinicavirtual.data;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pcs.ub.edu.ar.clinicavirtual.data.exception.UserData.UserDataIdIsZeroException;
import pcs.ub.edu.ar.clinicavirtual.data.exception.UserData.UserDataInvalidEmailException;
import pcs.ub.edu.ar.clinicavirtual.data.exception.UserData.UserDataNullEmailException;
import pcs.ub.edu.ar.clinicavirtual.data.exception.UserData.UserDataNullIdException;
import pcs.ub.edu.ar.clinicavirtual.data.exception.UserData.UserDataNullNameException;
import pcs.ub.edu.ar.clinicavirtual.interfaces.IUserProfileData;

public class UserData implements IUserProfileData {

    private Integer mID;
    private String 	mName;
    private String 	mEmail;

    public UserData(Integer id, String name, String email) {
            setmEmail(email);
            setmID(id);
            setmName(name);
    }
    @Override
    public Integer getmID() {
        return mID;
    }

    private void setmID(Integer mID) {
        if (mID == null)
            throw new UserDataNullIdException();
        if (mID == 0)
            throw new UserDataIdIsZeroException();
        this.mID = mID;
    }
    @Override
    public String getmName() {
        return mName;
    }

    private void setmName(String mName) {
        if ((mName == null) || (mName.trim().isEmpty()))
            throw new UserDataNullNameException();
        this.mName = mName;
    }
    @Override
    public String getmEmail() {
        return mEmail;
    }

    private void setmEmail(String mEmail) {
        if((mEmail == null) || (mEmail.trim().isEmpty()))
            throw new UserDataNullEmailException();
        if(!validarmail(mEmail))
            throw new UserDataInvalidEmailException();

        this.mEmail = mEmail;
    }

    private boolean validarmail(String mEmail) {

        //Pattern to validate the email
        Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher mather = pattern.matcher(mEmail);

        if (mather.find() == true){
            return true;
        }else{
            return false;
        }
    }

}
