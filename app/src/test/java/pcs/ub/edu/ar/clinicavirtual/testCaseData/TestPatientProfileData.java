package pcs.ub.edu.ar.clinicavirtual.testCaseData;

import pcs.ub.edu.ar.clinicavirtual.data.PatientProfileData;
import pcs.ub.edu.ar.clinicavirtual.data.exception.PatientProfileDataException.PatientProfileDataDniIsZeroException;
import pcs.ub.edu.ar.clinicavirtual.data.exception.PatientProfileDataException.PatientProfileDataNullDniException;
import pcs.ub.edu.ar.clinicavirtual.data.exception.PatientProfileDataException.PatientProfileDataNullNameException;
import pcs.ub.edu.ar.clinicavirtual.data.exception.PatientProfileDataException.PatientProfileDataNullSurnameException;

import static org.junit.Assert.assertEquals;
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
    @Test
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


    @Test
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
    @Test
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
    @Test
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
    @Test
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
/*


    @Override
    public String getmName() {
        return mName;
    }


    @Override
    public Long getDNI() {
        return mDNI;
    }
*/


    @Test
    public void testGetPatientmName () {
        String mName = "Fernando";
        String mSName = "Romero";
        String mEmail = "fer.romero1991@gmail.com";
        Long mDNI = Long.valueOf(35777393);

        PatientProfileData patient = new PatientProfileData(mName, mSName, mEmail, mDNI);

        assertEquals(mName, patient.getmName());
    }


    @Test
    public void testGetPatientDNI(){
        String mName = "Fernando";
        String mSName = "Romero";
        String mEmail = "fer.romero1991@gmail.com";
        Long mDNI = Long.valueOf(35777393);

        PatientProfileData patient = new PatientProfileData(mName, mSName, mEmail, mDNI);

        assertEquals(mName, patient.getmName());
    }
}