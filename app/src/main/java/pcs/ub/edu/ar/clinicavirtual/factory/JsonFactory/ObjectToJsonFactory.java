package pcs.ub.edu.ar.clinicavirtual.factory.JsonFactory;

import org.json.JSONObject;

import java.util.List;

import pcs.ub.edu.ar.clinicavirtual.interfaces.appointments.interfaces.IClinicAppointment;
import pcs.ub.edu.ar.clinicavirtual.interfaces.appointments.interfaces.IHCPAppointment;
import pcs.ub.edu.ar.clinicavirtual.interfaces.appointments.interfaces.IPatientAppointment;
import pcs.ub.edu.ar.clinicavirtual.interfaces.data.interfaces.IClinicProfileData;
import pcs.ub.edu.ar.clinicavirtual.interfaces.data.interfaces.IHCPProfileData;
import pcs.ub.edu.ar.clinicavirtual.interfaces.data.interfaces.IPatientProfileData;
import pcs.ub.edu.ar.clinicavirtual.interfaces.data.interfaces.ISpeciality;
import pcs.ub.edu.ar.clinicavirtual.interfaces.data.interfaces.IUserProfileData;
import pcs.ub.edu.ar.clinicavirtual.interfaces.json.interfaces.IObjectToJsonFactory;

public class ObjectToJsonFactory implements IObjectToJsonFactory {
    @Override
    public JSONObject getUserProfileData(IUserProfileData jsonObject) {
        return null;
    }

    @Override
    public JSONObject getLogoutMsg(String jsonObject) {
        return null;
    }

    @Override
    public JSONObject getDesactivateMsg(String jsonObject) {
        return null;
    }

    @Override
    public JSONObject getClinicProfileData(IClinicProfileData jsonObject) {
        return null;
    }

    @Override
    public JSONObject getHCPProfileData(IHCPProfileData jsonObject) {
        return null;
    }

    @Override
    public JSONObject getPatientProfileData(IPatientProfileData jsonObject) {
        return null;
    }

    @Override
    public JSONObject getSpeciality(ISpeciality jsonObject) {
        return null;
    }

    @Override
    public JSONObject getHCPAppointments(List<IHCPAppointment> jsonObject) {
        return null;
    }

    @Override
    public JSONObject getClinicAppointments(List<IClinicAppointment> jsonObject) {
        return null;
    }

    @Override
    public JSONObject getPatientAppointments(List<IPatientAppointment> jsonObject) {
        return null;
    }
}
