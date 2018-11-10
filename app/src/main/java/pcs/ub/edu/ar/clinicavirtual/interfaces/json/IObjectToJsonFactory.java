package pcs.ub.edu.ar.clinicavirtual.interfaces.json;

import org.json.JSONObject;

import java.util.List;

import pcs.ub.edu.ar.clinicavirtual.interfaces.data.IClinicProfileData;
import pcs.ub.edu.ar.clinicavirtual.interfaces.data.IHCPProfileData;
import pcs.ub.edu.ar.clinicavirtual.interfaces.data.IPatientProfileData;
import pcs.ub.edu.ar.clinicavirtual.interfaces.data.ISpeciality;
import pcs.ub.edu.ar.clinicavirtual.interfaces.data.IUserProfileData;
import pcs.ub.edu.ar.clinicavirtual.interfaces.appointments.IClinicAppointment;
import pcs.ub.edu.ar.clinicavirtual.interfaces.appointments.IHCPAppointment;
import pcs.ub.edu.ar.clinicavirtual.interfaces.appointments.IPatientAppointment;

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
