package pcs.ub.edu.ar.clinicavirtual.activitys;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import pcs.ub.edu.ar.clinicavirtual.*;
import pcs.ub.edu.ar.clinicavirtual.factory.JsonFactory.JsonUserInfo;

public class testJasonActivity extends BaseActivity {

    Button btnJson;
    TextView tvJsonResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_jason);
        initElement();

    }

    private void initElement() {
        btnJson = (Button) findViewById(R.id.btnMostrarJson);
        tvJsonResult = (TextView) findViewById(R.id.tvJsonResult);

        btnJson.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mostrarJson();
            }
        } );
    }

    public void mostrarJson(){
        JsonUserInfo jsonUserInfo = null;
        JSONObject jsonObject = null;
        String stringJson =  "{\n" +
                "    \"data\": {\n" +
                "        \"id\": 6,\n" +
                "        \"name\": \"walter ub\",\n" +
                "        \"email\": \"ubelarga@gmail.com\",\n" +
                "        \"email_verified_at\": null,\n" +
                "        \"created_at\": \"2018-09-20 17:30:52\",\n" +
                "        \"updated_at\": \"2018-09-27 15:06:20\",\n" +
                "        \"api_token\": \"HoFcCOSgtjyfD5GELBuyQ3xHZasPiBSyqGsFeDsYLXj4BewYcIEOvyqAn0iZ\",\n" +
                "        \"active\": 1\n" +
                "    }\n" +
                "}";


        try {
             jsonObject = new JSONObject(stringJson);
             jsonUserInfo = new JsonUserInfo( jsonObject  );
            // tvJsonResult.setText(":D");
             tvJsonResult.setText(jsonUserInfo.getmCreatedAt());
        } catch (JSONException e) {
            tvJsonResult.setText("Error Bro");
            e.printStackTrace();
        }


    }
}
