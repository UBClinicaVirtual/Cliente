package pcs.ub.edu.ar.clinicavirtual.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONObject;

import pcs.ub.edu.ar.clinicavirtual.R;

public class MainActivity extends BaseActivity {

    Button mBtnLogIn;
    Button mBtnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initButtons();
        createOnClickListenerButtons();
        
    }

    private void createOnClickListenerButtons() {

        mBtnSignIn.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent mIntentSignIn = new Intent(MainActivity.this, GoogleSignInActivity.class);
                startActivity(mIntentSignIn);
            }
        } );

        mBtnLogIn.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent mIntentLogIn = new Intent(MainActivity.this, MainScreenActivity.class);
                startActivity(mIntentLogIn);
            }
        } );


    }


    //the buttons are initialized
    private void initButtons() {

        mBtnLogIn = (Button) findViewById(R.id.btnLogIn);
        mBtnSignIn =(Button) findViewById(R.id.btnSignIn);

    }
}
