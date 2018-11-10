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

public interface IJsonToObjectFactory {

    public IUserProfileData getUserProfileData(JSONObject jsonObject);

    public String getLogoutMsg(JSONObject jsonObject);

    public String getDesactivateMsg(JSONObject jsonObject);

    public IClinicProfileData getClinicProfileData(JSONObject jsonObject);

    public IHCPProfileData getHCPProfileData(JSONObject jsonObject);

    public IPatientProfileData getPatientProfileData(JSONObject jsonObject);

    public ISpeciality getSpeciality(JSONObject jsonObject);

    public List<IHCPAppointment> getHCPAppointments(JSONObject jsonObject);

    public List<IClinicAppointment> getClinicAppointments(JSONObject jsonObject);

    public List<IPatientAppointment> getPatientAppointments(JSONObject jsonObject);

}
