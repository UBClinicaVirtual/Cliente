package pcs.ub.edu.ar.clinicavirtual.connection;

import android.os.StrictMode;


import java.io.BufferedReader;
import java.io.DataOutputStream;


import java.io.IOException;

import java.io.InputStreamReader;

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


import pcs.ub.edu.ar.clinicavirtual.connection.facade.pattern.connection.ServerRequest;
import pcs.ub.edu.ar.clinicavirtual.connection.facade.pattern.connection.ServerRequestAuthenticated;
import pcs.ub.edu.ar.clinicavirtual.connection.request.parameters.enums.*;
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
        request.apiToken( this.apiToken() );
        this.callInternal( request, listener );
    }

    @Override
    public void call(ServerRequest request, IServerResponseListener listener) {
        this.callInternal( request, listener );
    }

    private void callInternal(ServerRequest request, IServerResponseListener listener) {
        try {

            setPolicies();
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
			HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
            //con.setHostnameVerifier(DO_NOT_VERIFY);


            //Hosting redireccionado en http
            //HttpURLConnection con = (HttpURLConnection) obj.openConnection();



            //SET METHOD
            con.setRequestMethod(request.method());

            //SET HEADERS
            for (Map.Entry<HEADER, String> mHeader :request.headers().entrySet() ) {
                con.setRequestProperty( mHeader.getKey().getKey(), mHeader.getValue());
            }

            ///////////////////////////////////////////////////////////////////
            //Envio un access_token(el access token varia según el tiempo, deben generar uno propio en cada intento de login)
            //TODO buscar como refactorizar la generacion del json para enviar como parametro
            //String urlParameterss ="{\"access_token\": \"eyJhbGciOiJSUzI1NiIsImtpZCI6IjcyOGY0MDE2NjUyMDc5YjllZDk5ODYxYmIwOWJhZmM1YTQ1YmFhODYiLCJ0eXAiOiJKV1QifQ.eyJpc3MiOiJodHRwczovL2FjY291bnRzLmdvb2dsZS5jb20iLCJhenAiOiI0MzQwNDQ1Nzk5MDgtOGk1ZmtkYnJkdmhzaTVlbGVxa3JzamUyOHFjOW91c3EuYXBwcy5nb29nbGV1c2VyY29udGVudC5jb20iLCJhdWQiOiI0MzQwNDQ1Nzk5MDgtZWhxMTdmYnIxdXR0MDhub2U4dTRrYjJmNjZpc2RmNGUuYXBwcy5nb29nbGV1c2VyY29udGVudC5jb20iLCJzdWIiOiIxMDM0OTE2NDg0NjQyNzExMzMyNzQiLCJlbWFpbCI6ImdhYnllc3BpbmEuZ2VAZ21haWwuY29tIiwiZW1haWxfdmVyaWZpZWQiOnRydWUsIm5hbWUiOiJHYWJyaWVsIEVzcGluYSIsInBpY3R1cmUiOiJodHRwczovL2xoNi5nb29nbGV1c2VyY29udGVudC5jb20vLUhub1c5TEstSGFnL0FBQUFBQUFBQUFJL0FBQUFBQUFBRVo4LzN1QXFEWUpsYi1VL3M5Ni1jL3Bob3RvLmpwZyIsImdpdmVuX25hbWUiOiJHYWJyaWVsIiwiZmFtaWx5X25hbWUiOiJFc3BpbmEiLCJsb2NhbGUiOiJlcy00MTkiLCJpYXQiOjE1NDA0OTg5MjUsImV4cCI6MTU0MDUwMjUyNX0.rogwVfZYS0gNGKV2AOoIGjjrTTKvSStsRbCkPj0ZZ57kUZQ5Gr0NffPU8CFR59uXWzcUOjjDgyCrKfEZcEB6GBxPuWI2tWGX22C7GaL4oNEqHDUWuI0MkMenmtNGvagi-sCib5YRQU-KeZVf9VO2l33LsMCzFaWFX7HE_zxDXcR3d_BLhWWYphf7EPP93GWC0eKKkgaKrDWG2BU9-e8TCKlnDlANwlhj6BlZzbo4Xp4HcqT5RQ3TXehUfR-_tQXvWXzH-TcSBlXpB_vtFo9vQvDPVn_EFJdjjhxbgkEDHRhV6RWinj71i6oOMKAaKLA_hLROS4IyMtwYp-VYYWPFVQ\"}";
            String urlParameters = request.parameters();
            ///////////////////////////////////////////////////////////////////
            // Send post request

            if( !urlParameters.isEmpty() ) {
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

            BufferedReader in = new BufferedReader( new InputStreamReader(con.getInputStream()));
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
            listener.success(request);
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
}
