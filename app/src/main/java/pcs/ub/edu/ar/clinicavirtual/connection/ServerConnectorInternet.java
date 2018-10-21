package pcs.ub.edu.ar.clinicavirtual.connection;

import android.os.StrictMode;

import org.json.JSONException;
import org.json.JSONObject;

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
import pcs.ub.edu.ar.clinicavirtual.factory.JsonFactory.JsonToObjectFactory;
import pcs.ub.edu.ar.clinicavirtual.interfaces.*;
import pcs.ub.edu.ar.clinicavirtual.interfaces.data.interfaces.IClinicProfileData;
import pcs.ub.edu.ar.clinicavirtual.interfaces.data.interfaces.IHCPProfileData;
import pcs.ub.edu.ar.clinicavirtual.interfaces.data.interfaces.IPatientProfileData;
import pcs.ub.edu.ar.clinicavirtual.interfaces.data.interfaces.IUserProfileData;

public class ServerConnectorInternet implements IServerConnector {


    private StrictMode.ThreadPolicy mPolicy;
    private String mURL;
    private URL obj;
    private HttpURLConnection mHttpURLConnection;
    private String mUrlParameters;
    private String mHeaderURL = "http://www.ubclinicavirtual.tk/api/v1";
    private StringBuffer mResponse;
    private JsonToObjectFactory mJsonToObjectFactory = new JsonToObjectFactory();

    @Override
    public IUserProfileData register(String tokenGmail) {
        mResponse = new StringBuffer();
        try {
            //applies the necessary permissions
            setPolicies();
            
            //set the connection to the server
            setConnection(URI.REGISTER);

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
            mUrlParameters ="{\"access_token\": \" "+ tokenGmail +"\"}";

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
            return new UserData(1,"ERROR 2","ASD");
        } catch (ProtocolException e) {
            e.printStackTrace();
            return new UserData(2,"ERROR 3","ASD");
        } catch (IOException e) {
            e.printStackTrace();
            return new UserData(3,"ERROR 4","ASD");
        }


    }

    @Override
    public IUserProfileData login(String tokenGmail) {

        mResponse = new StringBuffer();
        try {
            //applies the necessary permissions
            setPolicies();

            setConnection(URI.LOGIN);

            addRequestParameters(METHOD.POST ,HEADER.ACCEPT, HEADER.CONTENT_TYPE);

            mUrlParameters ="{\"access_token\": \" "+ tokenGmail +"\"}";

            sendRequest();

            int responseCode = mHttpURLConnection.getResponseCode();

            getResponse();

            return new UserData(0,mResponse.toString(),"ASD");
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
            return new UserData(1,"ERROR 2","ASD");
        } catch (ProtocolException e) {
            e.printStackTrace();
            return new UserData(2,"ERROR 3","ASD");
        } catch (IOException e) {
            e.printStackTrace();
            return new UserData(3,"ERROR 4","ASD");
        }
    }

    @Override
    public void logout() {

    }

    @Override
    public IPatientProfileData addPatientProfileToUserAccount(JSONObject patientData) {
        mResponse = new StringBuffer();
        try {

            setPolicies();

            setConnection(URI.USER_PATIENT);

            addRequestParameters(METHOD.POST ,HEADER.ACCEPT, HEADER.CONTENT_TYPE, HEADER.AUTHORIZATION);

            mUrlParameters = patientData.toString();

            sendRequest();

            int responseCode = mHttpURLConnection.getResponseCode();

            getResponse();

            JSONObject mJsonObject = new JSONObject(mResponse.toString());

            return mJsonToObjectFactory.getPatientProfileData( mJsonObject );

        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

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

    private void addRequestParameters(METHOD post, HEADER header, HEADER header2, HEADER header3) throws ProtocolException {
        addRequestParameters(post, header, header2);
        mHttpURLConnection.setRequestProperty(header3.getKey(),header3.getValue());
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
