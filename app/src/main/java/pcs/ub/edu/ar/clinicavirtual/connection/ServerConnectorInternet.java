package pcs.ub.edu.ar.clinicavirtual.connection;

import android.os.StrictMode;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import pcs.ub.edu.ar.clinicavirtual.R;
import pcs.ub.edu.ar.clinicavirtual.data.UserData;
import pcs.ub.edu.ar.clinicavirtual.interfaces.IClinicProfileData;
import pcs.ub.edu.ar.clinicavirtual.interfaces.IHCPProfileData;
import pcs.ub.edu.ar.clinicavirtual.interfaces.IPatientProfileData;
import pcs.ub.edu.ar.clinicavirtual.interfaces.IServerConnector;
import pcs.ub.edu.ar.clinicavirtual.interfaces.IUserProfileData;

public class ServerConnectorInternet implements IServerConnector {
    @Override
    public IUserProfileData register(String tokenGmail) {
        StringBuffer response = new StringBuffer();
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            ///////////////////////////////////////////////////////////////////
            //Preparo el requerimiento

            //hosting real en https
//			String url = "https://ubclinicavirtual.000webhostapp.com/api/v1/login";

            //hosting http
            String url = "http://www.ubclinicavirtual.tk/api/v1/login";
            URL obj = new URL(url);

            //Hosting en https
//			HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

            //Hosting redireccionado en http
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            ///////////////////////////////////////////////////////////////////
            //add reuqest header

            con.setRequestMethod("POST");
            con.setRequestProperty("Accept", "application/json");
            con.setRequestProperty("Content-Type", "application/json");

            ///////////////////////////////////////////////////////////////////
            //Envio un access_token(el access token varia según el tiempo, deben generar uno propio en cada intento de login)
            //TODO buscar como refactorizar la generacion del json para enviar como parametro
            String urlParameters ="{\"access_token\": \"ya29.GlwkBnUjEebsQzRKx7um1tFc2IXXoViCbu5LM_oSjha4tPAinOL7fTIVOEPFAD4OUkg1jxzyi0QEi15cqLjj6c44sLsASBjtOH_0m1RzXmjTXLC4NWOlUtQSw3eP_A\"}";

            ///////////////////////////////////////////////////////////////////
            // Send post request

            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();

            int responseCode = con.getResponseCode();

            ///////////////////////////////////////////////////////////////////
            //Analizo la respuesta

            System.out.println("\nSending 'POST' request to URL : " + url);
            System.out.println("Post parameters : " + urlParameters);
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader( new InputStreamReader(con.getInputStream()));
            String inputLine;


            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            ///////////////////////////////////////////////////////////////////
            //print result

            System.out.println(response.toString());

        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new UserData(3,response.toString(),"");
    }

    @Override
    public IUserProfileData login(String tokenGmail) {
        return null;
    }

    @Override
    public void logout() {

    }

    @Override
    public IPatientProfileData addPatientProfileToUserAccount(IPatientProfileData patientData) {
        return null;
    }

    @Override
    public IClinicProfileData addClinicProfileToUserAccount(IClinicProfileData clinicData) {
        return null;
    }

    @Override
    public IHCPProfileData addHCPProfileToUserAccount(IHCPProfileData hcpData) {
        return null;
    }

    @Override
    public IPatientProfileData getUserPatientProfile() {
        return null;
    }
}
