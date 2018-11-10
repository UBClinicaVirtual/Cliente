package pcs.ub.edu.ar.clinicavirtual.connection;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

import pcs.ub.edu.ar.clinicavirtual.connection.facade.pattern.connection.ServerRequest;
import pcs.ub.edu.ar.clinicavirtual.connection.facade.pattern.connection.ServerRequestAuthenticated;
import pcs.ub.edu.ar.clinicavirtual.enums.HEADER;
import pcs.ub.edu.ar.clinicavirtual.interfaces.IServerResponseListener;

public class ServerConnectorDevelopment extends ServerConnector {

    private Queue<String> colaDeRespuestas;

    public ServerConnectorDevelopment(String urlBase, Collection<String> emulatedResponses) {
        super(urlBase);
        colaDeRespuestas( new LinkedList<String>( emulatedResponses ) );
    }

    @Override
    public void call(ServerRequestAuthenticated request, IServerResponseListener listener) {

    }

    @Override
    public void call(ServerRequest request, IServerResponseListener response) {

        System.out.println("------------------------------------------------------------");
        System.out.println("Inicio del llamado");
        //Imprimo la informacion que deberia usar para conectarme al server
        System.out.println( request.method() );
        System.out.println( urlRequest(request) );
        System.out.println( request.parameters() );
        System.out.println( request.requesterId() );

        //Agregar el header con el token que da el WS al hacer login.
        if( !apiToken().isEmpty() )
            request.headers().put(HEADER.AUTHORIZATION, "Bearer " + apiToken() );

        //Imprimo los encabezados que vienen con el request
        for( HEADER header : request.headers().keySet() )
            System.out.println( header + ": " + request.headers().get(header) );

        //Fuerzo una respuesta JSON de la cola de respuestas emuladas
        request.response( response() );

        //Imprimo la respuesta forzada
        System.out.println( request.response() );

        //Notifico a los listener que tengo la respuesta del requerimiento
        response.success( request );

        System.out.println("Fin del llamado");
        System.out.println("------------------------------------------------------------");
    }

    private String response() {
        return emulatedResponses().poll();
    }

    private Queue<String> emulatedResponses() {
        return colaDeRespuestas;
    }

    private void colaDeRespuestas(Queue<String> colaDeRespuestas) {
        this.colaDeRespuestas = colaDeRespuestas;
    }

    /**
     * Override this method to perform a computation on a background thread. The
     * specified parameters are the parameters passed to {@link #execute}
     * by the caller of this task.
     * <p>
     * This method can call {@link #publishProgress} to publish updates
     * on the UI thread.
     *
     * @param objects The parameters of the task.
     * @return A result, defined by the subclass of this task.
     * @see #onPreExecute()
     * @see #onPostExecute
     * @see #publishProgress
     */
    @Override
    protected Object doInBackground(Object[] objects) {
        return null;
    }
}
