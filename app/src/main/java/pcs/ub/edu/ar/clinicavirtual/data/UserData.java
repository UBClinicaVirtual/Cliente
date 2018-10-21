package pcs.ub.edu.ar.clinicavirtual.data;

import pcs.ub.edu.ar.clinicavirtual.interfaces.data.interfaces.IUserProfileData;

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
        this.mID = mID;
    }
    @Override
    public String getmName() {
        return mName;
    }

    private void setmName(String mName) {
        this.mName = mName;
    }
    @Override
    public String getmEmail() {
        return mEmail;
    }

    private void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

}
