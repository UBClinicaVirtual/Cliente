package pcs.ub.edu.ar.clinicavirtual.testCaseData;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import pcs.ub.edu.ar.clinicavirtual.data.ClinicProfileData;
import pcs.ub.edu.ar.clinicavirtual.data.Speciality;
import pcs.ub.edu.ar.clinicavirtual.data.exception.ClinicProfileDataException.ClinicProfileDataIdIsZeroException;
import pcs.ub.edu.ar.clinicavirtual.data.exception.ClinicProfileDataException.ClinicProfileDataNullBusinessNameException;
import pcs.ub.edu.ar.clinicavirtual.data.exception.ClinicProfileDataException.ClinicProfileDataNullIdException;
import pcs.ub.edu.ar.clinicavirtual.data.exception.ClinicProfileDataException.ClinicProfileDataNullSpecialitiesException;

import static org.junit.Assert.assertNotNull;

public class TestClinicProfileData {

    @Test
    public void testClinicProfilaDataNullBusinessName(){
        try{
            Integer mId = 1;
            String mBusinessName = null;


            String mNameSpeciality = "Speciality 1";
            String mNameSpeciality2 = "Speciality 2";
            Integer mIdSpeciality = 1;
            Integer mIdSpeciality2 = 2;
            List<Speciality> mSpecialties = new ArrayList<Speciality>();
            Speciality speciality1 = new Speciality(mIdSpeciality,mNameSpeciality);
            Speciality speciality2 = new Speciality(mIdSpeciality2,mNameSpeciality2);
            mSpecialties.add(speciality1);
            mSpecialties.add(speciality2);

            assertNotNull(new ClinicProfileData(mId,mBusinessName,mSpecialties));
        }catch (ClinicProfileDataNullBusinessNameException e){

        }
    }

    @Test
    public void testClinicProfileDataBusinessNameIsEmpty(){
        try{
            Integer mId = 1;
            String mBusinessName = "";

            String mNameSpeciality = "Speciality 1";
            String mNameSpeciality2 = "Speciality 2";
            Integer mIdSpeciality = 1;
            Integer mIdSpeciality2 = 2;
            List<Speciality> mSpecialties = new ArrayList<Speciality>();
            Speciality speciality1 = new Speciality(mIdSpeciality,mNameSpeciality);
            Speciality speciality2 = new Speciality(mIdSpeciality2,mNameSpeciality2);
            mSpecialties.add(speciality1);
            mSpecialties.add(speciality2);

            assertNotNull(new ClinicProfileData(mId,mBusinessName,mSpecialties));
        }catch (ClinicProfileDataNullBusinessNameException e){

        }
    }

    @Test
    public void testClinicProfileDataIdIsNull() {
            try {
                Integer mId = null;
                String mBusinessName = "Hospital de Clinicas";

                String mNameSpeciality = "Speciality 1";
                String mNameSpeciality2 = "Speciality 2";
                Integer mIdSpeciality = 1;
                Integer mIdSpeciality2 = 2;
                List<Speciality> mSpecialties = new ArrayList<Speciality>();
                Speciality speciality1 = new Speciality(mIdSpeciality, mNameSpeciality);
                Speciality speciality2 = new Speciality(mIdSpeciality2, mNameSpeciality2);
                mSpecialties.add(speciality1);
                mSpecialties.add(speciality2);

                assertNotNull(new ClinicProfileData(mId, mBusinessName, mSpecialties));
            } catch (ClinicProfileDataNullIdException e) {

            }
        }
    @Test
    public void testClinicProfileDataIdIsZero(){
        try {
            Integer mId = 0;
            String mBusinessName = "Hospital de Clinicas";

            String mNameSpeciality = "Speciality 1";
            String mNameSpeciality2 = "Speciality 2";
            Integer mIdSpeciality = 1;
            Integer mIdSpeciality2 = 2;
            List<Speciality> mSpecialties = new ArrayList<Speciality>();
            Speciality speciality1 = new Speciality(mIdSpeciality, mNameSpeciality);
            Speciality speciality2 = new Speciality(mIdSpeciality2, mNameSpeciality2);
            mSpecialties.add(speciality1);
            mSpecialties.add(speciality2);

            assertNotNull(new ClinicProfileData(mId, mBusinessName, mSpecialties));
        } catch (ClinicProfileDataIdIsZeroException e) {

        }
    }
    @Test
    public void testClinicProfileDataSpecialityIsNull(){
        try {
            Integer mId = 1;
            String mBusinessName = "Hospital de Clinicas";

            String mNameSpeciality = "Speciality 1";
            String mNameSpeciality2 = "Speciality 2";
            List<Speciality> mSpecialties = null;

            assertNotNull(new ClinicProfileData(mId, mBusinessName, mSpecialties));
        } catch (ClinicProfileDataNullSpecialitiesException e) {

        }
    }

     @Test
    public void testClinicProfileDataSpecialityIsEmpty(){
         try {
             Integer mId = 1;
             String mBusinessName = "Hospital de Clinicas";

             String mNameSpeciality = "Speciality 1";
             String mNameSpeciality2 = "Speciality 2";
             List<Speciality> mSpecialties = new ArrayList<Speciality>();

             assertNotNull(new ClinicProfileData(mId, mBusinessName, mSpecialties));
         } catch (ClinicProfileDataNullSpecialitiesException e) {

         }
     }

 



    }




