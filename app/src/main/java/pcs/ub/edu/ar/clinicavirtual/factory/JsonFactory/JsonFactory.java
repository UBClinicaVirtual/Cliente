package pcs.ub.edu.ar.clinicavirtual.factory.JsonFactory;

import pcs.ub.edu.ar.clinicavirtual.interfaces.json.IJsonFactory;
import pcs.ub.edu.ar.clinicavirtual.interfaces.json.IJsonToObjectFactory;
import pcs.ub.edu.ar.clinicavirtual.interfaces.json.IObjectToJsonFactory;

public class JsonFactory implements IJsonFactory {

    IJsonToObjectFactory mJsonToObjectFactory = new JsonToObjectFactory();
    IObjectToJsonFactory mObjectToJsonFactory = new ObjectToJsonFactory();


    @Override
    public IJsonToObjectFactory jsonToObject() {
        return mJsonToObjectFactory;
    }

    @Override
    public IObjectToJsonFactory objectToJson() {
        return mObjectToJsonFactory ;
    }
}
