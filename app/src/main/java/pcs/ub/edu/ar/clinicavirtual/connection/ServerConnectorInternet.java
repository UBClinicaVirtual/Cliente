package pcs.ub.edu.ar.clinicavirtual.connection;

import android.os.StrictMode;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import pcs.ub.edu.ar.clinicavirtual.connection.request.parameters.enums.*;
import pcs.ub.edu.ar.clinicavirtual.data.UserData;
import pcs.ub.edu.ar.clinicavirtual.interfaces.*;

public class ServerConnectorInternet implements IServerConnector {


    private StrictMode.ThreadPolicy mPolicy;
    private String mURL;
    private URL obj;
    private HttpURLConnection mHttpURLConnection;
    private String mUrlParameters;
    private String mHeaderURL = "http://www.ubclinicavirtual.tk/api";
    private StringBuffer mResponse;


    @Override
    public IUserProfileData register(String tokenGmail) {
        mResponse = new StringBuffer();
        try {
            //applies the necessary permissions
            setPolicies();
            
            //set the connection to the server
            setConnection(URI.LOGIN_9);

            ///////////////////////////////////////////////////////////////////
            //Preparo el requerimiento

            //hosting real en https
//			String url = "https://ubclinicavirtual.000webhostapp.com/api/v1/login";

            //Hosting en https
//			HttpsURLConnection mHttpURLConnection = (HttpsURLConnection) obj.openConnection();

            ///////////////////////////////////////////////////////////////////
            //add request parameters
            addRequestParameters(METHOD.POST ,HEADER.ACCEPT, HEADER.CONTENT_TYPE);

            ///////////////////////////////////////////////////////////////////
            //Envio un access_token(el access token varia según el tiempo, deben generar uno propio en cada intento de login)
            //TODO buscar como refactorizar la generacion del json para enviar como parametro
            mUrlParameters ="{\"access_token\": \"ya29.GlwkBnUjEebsQzRKx7um1tFc2IXXoViCbu5LM_oSjha4tPAinOL7fTIVOEPFAD4OUkg1jxzyi0QEi15cqLjj6c44sLsASBjtOH_0m1RzXmjTXLC4NWOlUtQSw3eP_A\"}";

            ///////////////////////////////////////////////////////////////////
            // Send post request
            sendRequest();


            int responseCode = mHttpURLConnection.getResponseCode();

            ///////////////////////////////////////////////////////////////////
            //Analizo la respuesta

            /*System.out.println("\nSending 'POST' request to URL : " + mURL);
            System.out.println("Post parameters : " + mUrlParameters);
            System.out.println("Response Code : " + responseCode);*/

            getResponse();

            return new UserData(0,mResponse.toString(),"ASD");
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
            return new UserData(1,"ERROR 1","ASD");
        } catch (ProtocolException e) {
            e.printStackTrace();
            return new UserData(2,"ERROR 2","ASD");
        } catch (IOException e) {
            e.printStackTrace();
            return new UserData(3,e.getMessage(),"ASD");
        }


    }

    @Override
    public IUserProfileData login(String tokenGmail) {

        return new UserData(3,"asd","");
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





    
    //------------------------------------------------------------------------
    //methods of connection to the server
    private void addRequestParameters(METHOD method, HEADER header) throws ProtocolException {
        mHttpURLConnection.setRequestMethod( method.getKey() );
        mHttpURLConnection.setRequestProperty( header.getKey(), header.getValue());
    }

    private void addRequestParameters(METHOD method, HEADER header, HEADER header2) throws ProtocolException {
        addRequestParameters(method,header);
        mHttpURLConnection.setRequestProperty(header2.getKey(), header2.getValue());
    }

    private void setConnection(URI uri) throws IOException {
        //hosting http
        mURL = mHeaderURL + uri;
        obj = new URL( mURL );
        mHttpURLConnection = (HttpURLConnection) obj.openConnection();

    }

    private void setPolicies() {
        mPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(mPolicy);
    }

    private void sendRequest() throws IOException {
        mHttpURLConnection.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(mHttpURLConnection.getOutputStream());
        wr.writeBytes(mUrlParameters);
        wr.flush();
        wr.close();
    }

    private void getResponse() throws IOException {
        BufferedReader in = new BufferedReader( new InputStreamReader(mHttpURLConnection.getInputStream()));
        String mInputLine;


        while ((mInputLine = in.readLine()) != null) {
            mResponse.append(mInputLine);
        }
        in.close();
    }
}
