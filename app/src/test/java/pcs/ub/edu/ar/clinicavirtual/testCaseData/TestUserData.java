package pcs.ub.edu.ar.clinicavirtual.testCaseData;

import org.junit.Assert;
import org.junit.Test;

import pcs.ub.edu.ar.clinicavirtual.data.UserData;

public class TestUserData {

    @Test
    public void testIdZeroUserData(){

        Integer mID = 0;
        String 	mName = "Fer";
        String 	mEmail = "fer.romero@comunidad.com.ar";

        Assert.assertNotNull(new UserData(mID,mName, mEmail));
    }
}
