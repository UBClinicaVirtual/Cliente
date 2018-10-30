package pcs.ub.edu.ar.clinicavirtual.testCaseData;

import org.junit.Assert;
import org.junit.Test;

import pcs.ub.edu.ar.clinicavirtual.data.Speciality;
import pcs.ub.edu.ar.clinicavirtual.data.exception.Speciality.SpecialityNullIdException;

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
            String mName = ""
        }
    }
}
