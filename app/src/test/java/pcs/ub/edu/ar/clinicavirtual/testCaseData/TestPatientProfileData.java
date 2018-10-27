package pcs.ub.edu.ar.clinicavirtual.testCaseData;

import pcs.ub.edu.ar.clinicavirtual.data.PatientProfileData;
import pcs.ub.edu.ar.clinicavirtual.data.exception.PatientProfileDataException.PatientProfileDataDniIsZeroException;
import pcs.ub.edu.ar.clinicavirtual.data.exception.PatientProfileDataException.PatientProfileDataNullDniException;
import pcs.ub.edu.ar.clinicavirtual.data.exception.PatientProfileDataException.PatientProfileDataNullNameException;
import pcs.ub.edu.ar.clinicavirtual.data.exception.PatientProfileDataException.PatientProfileDataNullSurnameException;

import static org.junit.Assert.assertNotNull;
import org.junit.Test;

public class TestPatientProfileData {
    @Test

    public void testPatientNameNull()
    {

        try
        {
            String mName = null;
            String mSName = "Romero";
            String mEmail = "fer.romero1991@gmail.com";
            Long    mDNI = Long.valueOf(35777393);

            assertNotNull ( new PatientProfileData(mName,mSName,mEmail,mDNI));
        }
        catch (PatientProfileDataNullNameException e)
        {

        }
    }

    public void testPatientNameEmpty() {
        try {
            String mName = "";
            String mSName = "Romero";
            String mEmail = "fer.romero1991@gmail.com";
            Long mDNI = Long.valueOf(35777393);

            assertNotNull(new PatientProfileData(mName, mSName, mEmail, mDNI));
        } catch (PatientProfileDataNullNameException e) {

        }

    }



    public void testPatientSurNameNull(){
        try{
            String mName = "Fernando";
            String mSName = null;
            String mEmail = "fer.romero1991@gmail.com";
            Long mDNI = Long.valueOf(35777393);

            assertNotNull(new PatientProfileData(mName, mSName, mEmail, mDNI));
        }catch (PatientProfileDataNullSurnameException e){

        }
    }

    public void testPatientSurnameIsEmpty(){
        try{
            String mName = "Fernando";
            String mSName = "";
            String mEmail = "fer.romero1991@gmail.com";
            Long mDNI = Long.valueOf(35777393);

            assertNotNull(new PatientProfileData(mName, mSName, mEmail, mDNI));
        }catch (PatientProfileDataNullSurnameException e){

        }
    }

    public void testPatientDniIsNull(){
        try{
            String mName = "Fernando";
            String mSName = "Romero";
            String mEmail = "fer.romero1991@gmail.com";
            Long mDNI = null;

            assertNotNull(new PatientProfileData(mName, mSName, mEmail, mDNI));
        }catch (PatientProfileDataNullDniException e){

        }
    }

    public void testPatientDniIsZero(){
        try{
            String mName = "Fernando";
            String mSName = "Romero";
            String mEmail = "fer.romero1991@gmail.com";
            Long mDNI = Long.valueOf(0);

            assertNotNull(new PatientProfileData(mName, mSName, mEmail, mDNI));
        }catch (PatientProfileDataDniIsZeroException e){
        }
    }


}
