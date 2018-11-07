package pcs.ub.edu.ar.clinicavirtual.testCaseData;

import org.junit.Assert;
import org.junit.Test;

import pcs.ub.edu.ar.clinicavirtual.data.Speciality;
import pcs.ub.edu.ar.clinicavirtual.data.exception.Speciality.SpecialityIdIsZeroException;
import pcs.ub.edu.ar.clinicavirtual.data.exception.Speciality.SpecialityNullIdException;
import pcs.ub.edu.ar.clinicavirtual.data.exception.Speciality.SpecialityNullNameException;

import static org.junit.Assert.assertEquals;

public class TestSpeciality {

    @Test
    public void testSpecialityNullId() {

        try {
            String mName = "Division gineco";
            Integer mIdSpeciality = null;

            Assert.assertNotNull(new Speciality(mIdSpeciality, mName));
        } catch (SpecialityNullIdException e) {

        }
    }

    @Test
    public void testSpecialityIdIsZero() {
        try{
            String mName = "Division gineco";
            Integer mIdSpeciality = 0;

            Assert.assertNotNull(new Speciality(mIdSpeciality, mName));
        }catch (SpecialityIdIsZeroException e){

        }
    }

    @Test
    public void testNullNameSpeciality() {
        try{
            String mName = null;
            Integer mIdSpeciality =  1;

            Assert.assertNotNull(new Speciality(mIdSpeciality, mName));
        }catch (SpecialityNullNameException e){

        }
    }

    @Test
    public void testEmptyNameSpeciality() {
        try {
            String mName = "";
            Integer mIdspeciality = 1;

            Assert.assertNotNull(new Speciality(mIdspeciality,mName));
        }catch (SpecialityNullNameException e){

        }
    }

    @Test
    public void testGetNameSpeciality () {
        String mName = "Fertilidad";
        Integer mIdspeciality = 1;

        Speciality speciality = new Speciality(mIdspeciality,mName);

        assertEquals(mName, speciality.getmName());
    }

    @Test
    public void testGetIdSpeciality () {
        String mName = "Fertilidad";
        Integer mIdSpeciality = 1;

        Speciality speciality = new Speciality(mIdSpeciality, mName);

        assertEquals(mIdSpeciality, speciality.getmIdSpeciality());
    }
}
