package pcs.ub.edu.ar.clinicavirtual.interfaces.json.interfaces;

import org.json.JSONObject;

import java.util.List;

import pcs.ub.edu.ar.clinicavirtual.interfaces.data.interfaces.IClinicProfileData;
import pcs.ub.edu.ar.clinicavirtual.interfaces.data.interfaces.IHCPProfileData;
import pcs.ub.edu.ar.clinicavirtual.interfaces.data.interfaces.IPatientProfileData;
import pcs.ub.edu.ar.clinicavirtual.interfaces.data.interfaces.ISpeciality;
import pcs.ub.edu.ar.clinicavirtual.interfaces.data.interfaces.IUserProfileData;
import pcs.ub.edu.ar.clinicavirtual.interfaces.appointments.interfaces.IClinicAppointment;
import pcs.ub.edu.ar.clinicavirtual.interfaces.appointments.interfaces.IHCPAppointment;
import pcs.ub.edu.ar.clinicavirtual.interfaces.appointments.interfaces.IPatientAppointment;

public interface IObjectToJsonFactory {

    public JSONObject getUserProfileData(IUserProfileData jsonObject);

    public JSONObject getLogoutMsg(String jsonObject);

    public JSONObject getDesactivateMsg(String jsonObject);

    public JSONObject getClinicProfileData(IClinicProfileData jsonObject);

    public JSONObject getHCPProfileData(IHCPProfileData jsonObject);

    public JSONObject getPatientProfileData(IPatientProfileData jsonObject);

    public JSONObject getSpeciality(ISpeciality jsonObject);

    public JSONObject getHCPAppointments(List<IHCPAppointment> jsonObject);

    public JSONObject getClinicAppointments(List<IClinicAppointment> jsonObject);

    public JSONObject getPatientAppointments(List<IPatientAppointment> jsonObject);

}
