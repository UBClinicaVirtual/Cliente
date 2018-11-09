package pcs.ub.edu.ar.clinicavirtual.factory.JsonFactory;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

import pcs.ub.edu.ar.clinicavirtual.data.*;
import pcs.ub.edu.ar.clinicavirtual.interfaces.appointments.IClinicAppointment;
import pcs.ub.edu.ar.clinicavirtual.interfaces.appointments.IHCPAppointment;
import pcs.ub.edu.ar.clinicavirtual.interfaces.appointments.IPatientAppointment;
import pcs.ub.edu.ar.clinicavirtual.interfaces.data.IClinicProfileData;
import pcs.ub.edu.ar.clinicavirtual.interfaces.data.IHCPProfileData;
import pcs.ub.edu.ar.clinicavirtual.interfaces.data.IPatientProfileData;
import pcs.ub.edu.ar.clinicavirtual.interfaces.data.ISpeciality;
import pcs.ub.edu.ar.clinicavirtual.interfaces.data.IUserProfileData;
import pcs.ub.edu.ar.clinicavirtual.interfaces.json.IJsonToObjectFactory;

public class JsonToObjectFactory implements IJsonToObjectFactory {

    public IUserProfileData getUserProfileData (JSONObject jsonObject){

        /*try {
            JSONObject dataJsonObject = jsonObject.getJSONObject("data");

            IUserProfileData mUserProfileData = new UserData(
                    dataJsonObject.getInt("id"),
                    dataJsonObject.getString("name"),
                    dataJsonObject.getString("email")
                    );

            return mUserProfileData;

        } catch (JSONException e) {
            e.printStackTrace();
        }*/


        return null;

    }


    public String getLogoutMsg(JSONObject jsonObject){
        try {
            return jsonObject.getString("data");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getDesactivateMsg(JSONObject jsonObject) {
        try {
            return jsonObject.getString("data");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public IClinicProfileData getClinicProfileData(JSONObject jsonObject) {

        try {
            JSONObject mClinicJsonObject = jsonObject.getJSONObject("clinic");

            IClinicProfileData mClinicProfileData = new ClinicProfileData
                    (
                            mClinicJsonObject.getInt("id"),
                            mClinicJsonObject.getString("business_name"),
                            null
                    );

            return mClinicProfileData;

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    public IHCPProfileData getHCPProfileData(JSONObject jsonObject) {
        try {
            JSONObject mJsonObject = jsonObject.getJSONObject("hcp");

            JSONObject mHCPJsonObject = mJsonObject.getJSONObject("hcp");
            JSONObject mSpecialitieJsonObject1 = mJsonObject.getJSONObject("specialities");

            ISpeciality mSpeciality = new Speciality
                    (
                            mSpecialitieJsonObject1.getInt("id"),
                            mSpecialitieJsonObject1.getString("name")

                    );

            List<ISpeciality> mSpecialities = new LinkedList<ISpeciality>();

            mSpecialities.add(mSpeciality);

            IHCPProfileData mHCPProfileData = new HCPProfileData
                    (
                        mHCPJsonObject.getInt("id"),
                        mHCPJsonObject.getString("name"),
                        Long.parseLong(mHCPJsonObject.getString("identification_number")),
                        mSpecialities
                    );

            return mHCPProfileData;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public IPatientProfileData getPatientProfileData(JSONObject jsonObject) {





        return new PatientProfileData("pepe","juancho","pepe@juancho",new Long(321));
    }

    @Override
    public ISpeciality getSpeciality(JSONObject jsonObject) {
        return null;
    }

    @Override
    public List<IHCPAppointment> getHCPAppointments(JSONObject jsonObject) {
        return null;
    }

    @Override
    public List<IClinicAppointment> getClinicAppointments(JSONObject jsonObject) {
        return null;
    }

    @Override
    public List<IPatientAppointment> getPatientAppointments(JSONObject jsonObject) {
        return null;
    }

}
