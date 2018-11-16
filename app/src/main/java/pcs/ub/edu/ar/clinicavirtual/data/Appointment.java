package pcs.ub.edu.ar.clinicavirtual.data;

import java.io.Serializable;
import java.util.UUID;

import pcs.ub.edu.ar.clinicavirtual.interfaces.appointments.IClinicAppointment;
import pcs.ub.edu.ar.clinicavirtual.interfaces.appointments.IHCPAppointment;
import pcs.ub.edu.ar.clinicavirtual.interfaces.appointments.IPatientAppointment;

public class Appointment implements IClinicAppointment, IPatientAppointment, IHCPAppointment{

    String  mID;
    Integer mAppointmentID;
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
    PatientProfileData  mPatient;
    ClinicProfileData   mClinic;
    Speciality          mSpeciality;
    HCPProfileData      mHcp;






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

    public Appointment(Integer appointmentId, Integer clinicId, String clinicName, Integer specialityId,
                       String specialityName, Integer hcpId, String hcpFirstName, String hcpLastName,
                       String appointmentDate, Integer appointmentStatusId, String appointmentStatusName) {

         mAppointmentID = appointmentId;
         mClinic = new ClinicProfileData(clinicId,clinicName);
         mSpeciality = new Speciality(specialityId,specialityName);
         mHcp = new HCPProfileData(hcpId,hcpLastName,hcpFirstName);
         mDate = appointmentDate;
         mState = appointmentStatusId;
         mStateLebel = appointmentStatusName;



    }

    public Appointment(int id, int clinic_id, String clinic_business_name, int speciality_id, String speciality_name, int hcp_id, String hcp_first_name, String hcp_last_name, String appointment_date) {
        mAppointmentID = id;
        mClinic = new ClinicProfileData(clinic_id,clinic_business_name);
        mSpeciality = new Speciality(speciality_id,speciality_name);
        mHcp = new HCPProfileData(hcp_id,hcp_last_name,hcp_first_name);
        mDate = appointment_date;
        mState = null;
        mStateLebel = "Disponible";
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


    public PatientProfileData getmPatient() {
        return mPatient;
    }

    public void setmPatient(PatientProfileData mPatient) {
        this.mPatient = mPatient;
    }

    public ClinicProfileData getmClinic() {
        return mClinic;
    }

    public void setmClinic(ClinicProfileData mClinic) {
        this.mClinic = mClinic;
    }

    public Speciality getmSpeciality() {
        return mSpeciality;
    }

    public void setmSpeciality(Speciality mSpeciality) {
        this.mSpeciality = mSpeciality;
    }


    public Integer getmAppointmentID() {
        return mAppointmentID;
    }

    public void setmAppointmentID(Integer mAppointmentID) {
        this.mAppointmentID = mAppointmentID;
    }
    public HCPProfileData getmHcp() {
        return mHcp;
    }

    public void setmHcp(HCPProfileData mHcp) {
        this.mHcp = mHcp;
    }


}