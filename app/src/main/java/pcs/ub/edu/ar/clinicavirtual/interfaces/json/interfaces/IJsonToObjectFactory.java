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
