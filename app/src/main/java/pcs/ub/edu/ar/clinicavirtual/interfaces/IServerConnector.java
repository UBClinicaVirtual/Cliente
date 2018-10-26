package pcs.ub.edu.ar.clinicavirtual.interfaces;

import org.json.JSONObject;

import pcs.ub.edu.ar.clinicavirtual.connection.facade.pattern.connection.ServerRequest;
import pcs.ub.edu.ar.clinicavirtual.connection.facade.pattern.connection.ServerRequestAuthenticated;
import pcs.ub.edu.ar.clinicavirtual.interfaces.data.interfaces.IClinicProfileData;
import pcs.ub.edu.ar.clinicavirtual.interfaces.data.interfaces.IHCPProfileData;
import pcs.ub.edu.ar.clinicavirtual.interfaces.data.interfaces.IPatientProfileData;
import pcs.ub.edu.ar.clinicavirtual.interfaces.data.interfaces.IUserProfileData;
import pcs.ub.edu.ar.clinicavirtual.interfaces.facade.pattern.connection.interfaces.IResponseListener;
import pcs.ub.edu.ar.clinicavirtual.interfaces.facade.pattern.connection.interfaces.IServerRequest;

public interface IServerConnector{
    public void call(ServerRequestAuthenticated request, IServerResponseListener listener );
    public void call(ServerRequest request, IServerResponseListener listener );
    public void apiToken(String apiToken);
}
