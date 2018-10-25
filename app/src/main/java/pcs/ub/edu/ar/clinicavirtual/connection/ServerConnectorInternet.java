package pcs.ub.edu.ar.clinicavirtual.connection;

import android.os.StrictMode;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

import pcs.ub.edu.ar.clinicavirtual.connection.facade.pattern.connection.ServerRequest;
import pcs.ub.edu.ar.clinicavirtual.connection.request.parameters.enums.*;
import pcs.ub.edu.ar.clinicavirtual.data.UserData;
import pcs.ub.edu.ar.clinicavirtual.factory.JsonFactory.JsonToObjectFactory;
import pcs.ub.edu.ar.clinicavirtual.interfaces.*;
import pcs.ub.edu.ar.clinicavirtual.interfaces.data.interfaces.IClinicProfileData;
import pcs.ub.edu.ar.clinicavirtual.interfaces.data.interfaces.IHCPProfileData;
import pcs.ub.edu.ar.clinicavirtual.interfaces.data.interfaces.IPatientProfileData;
import pcs.ub.edu.ar.clinicavirtual.interfaces.data.interfaces.IUserProfileData;
import pcs.ub.edu.ar.clinicavirtual.interfaces.facade.pattern.connection.interfaces.IResponseListener;
import pcs.ub.edu.ar.clinicavirtual.interfaces.facade.pattern.connection.interfaces.IServerRequest;

public class ServerConnectorInternet extends ServerConnector {
    private StrictMode.ThreadPolicy mPolicy;
    private String mURL;
    private URL obj;
    private HttpURLConnection mHttpURLConnection;
    private String mUrlParameters;
    private String mHeaderURL = "http://www.ubclinicavirtual.tk/api/v1";
    private StringBuffer mResponse;
    private JsonToObjectFactory mJsonToObjectFactory = new JsonToObjectFactory();
    private String ExceptionMensaje;

    public ServerConnectorInternet(String urlBase) {
        super(urlBase);

        // En este constructor podria verificar si existe el archivo con el api_token logeado
        // para emular el login automatico
        // (Pero puede quedar pendiente para después)
    }

    @Override
    public void call(ServerRequest request, IServerResponseListener listener) {
        mResponse = new StringBuffer();
        try {
            //applies the necessary permissions
            setPolicies();

            setConnection(request.path());

            addRequestParameters(request.method(),request.headers());

            mUrlParameters = request.parameters();

            sendRequest();

            request.response(getResponse());

            int responseCode = mHttpURLConnection.getResponseCode();



            listener.success(request);

        } catch (MalformedURLException e1) {
            e1.printStackTrace();

        } catch (ProtocolException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
            ExceptionMensaje = e.getMessage();

        }

    }

    @Override
    public void apiToken(String apiToken) {
        // Aca es donde deberia grabarlo en el archivo para poder hacer
        // el login automatico al iniciar la app
        // (Pero puede quedar pendiente para después)
        super.apiToken(apiToken);
    }

   /*


    public void call(IServerRequest req, IResponseListener listener) {


        mResponse = new StringBuffer();
        try {
            //applies the necessary permissions
            //setPolicies();

            setConnection(req.path());

            addRequestParameters(req.method(),req.headers());

            mUrlParameters = req.parameters();

            sendRequest();

            int responseCode = mHttpURLConnection.getResponseCode();

            req.setResponse(  getResponse() );

            listener.notify(req);

        } catch (MalformedURLException e1) {
            e1.printStackTrace();

        } catch (ProtocolException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();

        }
    }
/**/
    //------------------------------------------------------------------------
    //methods of connection to the server
    private void addRequestParameters(String method, Map<HEADER, String> headers) throws ProtocolException {
        mHttpURLConnection.setRequestMethod( method );

        for (Map.Entry<HEADER, String> mHeader :headers.entrySet() ) {
            mHttpURLConnection.setRequestProperty( mHeader.getKey().getKey(), mHeader.getValue());
        }

    }

    private void setConnection(String uri) throws IOException {
        //hosting http
        mURL = urlBase() + uri;
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

    private String getResponse() throws IOException {

        InputStream inputStream= mHttpURLConnection.getInputStream();
        InputStreamReader inputStreamReader =  new InputStreamReader(inputStream);
        BufferedReader in = new BufferedReader(inputStreamReader);
        String mInputLine;


        while ((mInputLine = in.readLine()) != null) {
            mResponse.append(mInputLine);
        }
        in.close();

        return mResponse.toString();
    }

    //connector.call(new ServerRequestLogin(username, pass));


}
