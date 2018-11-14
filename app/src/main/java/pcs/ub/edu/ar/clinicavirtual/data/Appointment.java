package pcs.ub.edu.ar.clinicavirtual.data;

import java.util.UUID;

import pcs.ub.edu.ar.clinicavirtual.interfaces.appointments.IClinicAppointment;
import pcs.ub.edu.ar.clinicavirtual.interfaces.appointments.IHCPAppointment;
import pcs.ub.edu.ar.clinicavirtual.interfaces.appointments.IPatientAppointment;

public class Appointment implements IClinicAppointment, IPatientAppointment, IHCPAppointment {

    String mID;
    Integer mClinicID;
    String  mClinicName;
    Integer mSpecialityID;
    String  mSpecialityName;
    Integer mPatientID;
    String  mPatientName;
    String  mDate;
    Integer mState;
    String  mStateLebel;
    String  mHCPName;
    Integer mHCPID;
    String  mImageUrl;





    public Appointment(String id, Integer clinicID, String clinicName, Integer specialityID,
                       String specialityName, Integer patiendID, String patientName, String date, Integer state, String stateLebel,
                       Integer hCPID, String hCPName){

        mID = id;
        mClinicID = clinicID;
        mClinicName = clinicName;
        mSpecialityID = specialityID;
        mSpecialityName = specialityName;
        mPatientID = patiendID;
        mPatientName = patientName;
        mDate = date;
        mState = state;
        mStateLebel = stateLebel;
        mHCPID = hCPID;
        mHCPName = hCPName;
    }

    public Appointment(String clinic, String date, String doctor, String status, String imageUrl) {
        mID = UUID.randomUUID().toString();
        mClinicName = clinic;
        mDate = date;
        mHCPName = doctor;
        mStateLebel = status;
        mImageUrl = imageUrl;
    }


    @Override
    public String getID() {
        return mID;
    }

    @Override
    public Integer getClinicID() {
        return mClinicID;
    }

    @Override
    public String getClinicName() {
        return mClinicName;
    }

    @Override
    public Integer getSpecialityID() {
        return mSpecialityID;
    }

    @Override
    public String getSpecialityName() {
        return mSpecialityName;
    }

    @Override
    public Integer getPatientID() {
        return mPatientID;
    }

    @Override
    public String getPatientName() {
        return mPatientName;
    }

    @Override
    public String getDate() {
        return mDate;
    }

    @Override
    public Integer getState() {
        return mState;
    }

    @Override
    public String getStateLebel() {
        return mStateLebel;
    }

    @Override
    public Integer getHCPID() {
        return mHCPID;
    }

    @Override
    public String getHCPName() {
        return mHCPName;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public void setmImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }

}