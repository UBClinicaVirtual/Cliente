package pcs.ub.edu.ar.clinicavirtual.connection;

import android.os.StrictMode;
import android.widget.Toast;


import java.io.BufferedReader;
import java.io.DataOutputStream;


import java.io.IOException;

import java.io.InputStream;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;


import pcs.ub.edu.ar.clinicavirtual.activitys.base.BaseActivity;
import pcs.ub.edu.ar.clinicavirtual.connection.check.internet.Internet;
import pcs.ub.edu.ar.clinicavirtual.connection.facade.pattern.connection.ServerRequest;
import pcs.ub.edu.ar.clinicavirtual.connection.facade.pattern.connection.ServerRequestAuthenticated;
import pcs.ub.edu.ar.clinicavirtual.enums.HEADER;
import pcs.ub.edu.ar.clinicavirtual.factory.JsonFactory.JsonToObjectFactory;
import pcs.ub.edu.ar.clinicavirtual.interfaces.*;

public class ServerConnectorInternet extends ServerConnector {
    private StrictMode.ThreadPolicy mPolicy;
    private JsonToObjectFactory mJsonToObjectFactory = new JsonToObjectFactory();

    // always verify the host - dont check for certificate
    final static HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier() {
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    };

    public ServerConnectorInternet(String urlBase) {
        super(urlBase);

        // En este constructor podria verificar si existe el archivo con el api_token logeado
        // para emular el login automatico
        // (Pero puede quedar pendiente para después)
    }

    @Override
    public void call(ServerRequestAuthenticated request, IServerResponseListener listener) {
        //request.apiToken(  apiToken() );
        this.callInternal( request, listener );
    }

    @Override
    public void call(ServerRequest request, IServerResponseListener listener) {
        this.callInternal( request, listener );
    }

    private void callInternal(ServerRequest request, IServerResponseListener listener) {
        if(!(Internet.isNetAvailable((BaseActivity)listener) && Internet.isOnlineNet())){
            request.response("Sin conexion a internet");
            listener.error(request);
        }else {

            try {


                //setPolicies();
                ///////////////////////////////////////////////////////////////////
                //Preparo el requerimiento

                //hosting real en https
//			String url = "https://ubclinicavirtual.000webhostapp.com/api/v1/login";

                //hosting http
                // String url = "http://www.ubclinicavirtual.tk/api/v1/login";
                String url = urlBase() + request.path();
                URL obj = new URL(url);
                //Hosting en https
                //trustAllHosts();
//			HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                //con.setHostnameVerifier(DO_NOT_VERIFY);


                //Hosting redireccionado en http
                //HttpURLConnection con = (HttpURLConnection) obj.openConnection();


                //SET METHOD
                con.setRequestMethod(request.method());

                //SET HEADERS
                for (Map.Entry<HEADER, String> mHeader : request.headers().entrySet()) {
                    con.setRequestProperty(mHeader.getKey().getKey(), mHeader.getValue());
                }

                ///////////////////////////////////////////////////////////////////
                //Envio un access_token(el access token varia según el tiempo, deben generar uno propio en cada intento de login)
                String urlParameters = request.parameters();
                ///////////////////////////////////////////////////////////////////
                // Send post request

                if (!urlParameters.isEmpty()) {
                    con.setDoOutput(true);
                    DataOutputStream wr = new DataOutputStream(con.getOutputStream());
                    wr.writeBytes(urlParameters);
                    wr.flush();
                    wr.close();
                }

                int responseCode = con.getResponseCode();

                ///////////////////////////////////////////////////////////////////
                //Analizo la respuesta

                System.out.println("\nSending 'POST' request to URL : " + url);
                System.out.println("Post parameters : " + urlParameters);
                System.out.println("Response Code : " + responseCode);

                BufferedReader in = new BufferedReader(new InputStreamReader(getInputStream(responseCode, con)));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                in.close();

                ///////////////////////////////////////////////////////////////////
                //print result

                System.out.println(response.toString());

                request.response(response.toString());

                if (isErrorCode(responseCode))
                    listener.error(request);
                else
                    listener.success(request);

            } catch (MalformedURLException e1) {
                e1.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private InputStream getInputStream(int responseCode, HttpURLConnection con) throws IOException {
        // If its a valid response, returns the data stream to process
        if(isErrorCode(responseCode))
            return con.getErrorStream();
        return con.getInputStream();
    }

    private boolean isErrorCode(int responseCode) {
        return responseCode < 200 || responseCode >= 300;
    }

    private void trustAllHosts() {
        // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return new java.security.cert.X509Certificate[] {};
            }

            public void checkClientTrusted(X509Certificate[] chain,
                                           String authType) throws CertificateException {
            }

            public void checkServerTrusted(X509Certificate[] chain,
                                           String authType) throws CertificateException {
            }
        } };

        // Install the all-trusting trust manager
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection
                    .setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    public void apiToken(String apiToken) {
        // Aca es donde deberia grabarlo en el archivo para poder hacer
        // el login automatico al iniciar la app
        // (Pero puede quedar pendiente para después)
        super.apiToken(apiToken);
    }

    //------------------------------------------------------------------------
    private void setPolicies() {
        mPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(mPolicy);
    }


    @Override
    protected Object doInBackground(Object[] objects) {
        this.callInternal((ServerRequest) objects[0] , (IServerResponseListener) objects[1] );
        return null;
    }
}
