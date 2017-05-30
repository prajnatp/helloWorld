package com.example.user.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  {

    private EditText etName;
    private Button btnSave;
    public static final String KEY_MESSAGE="welcome";
    public static final String KEY_LOCATION="loc";
    public static final int KEY_RESULT =12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeMyActivity();

        setListeners();
    }

    private void initializeMyActivity(){
        etName = (EditText) findViewById(R.id.et_name);
        btnSave = (Button) findViewById(R.id.btn_send);
    }

    private void setListeners(){
        btnSave.setOnClickListener(new  View.OnClickListener(){

            @Override
            public void onClick(View v){
                fnSaveClicked();
            }
        });
    }

    public void fnSaveClicked(){
            Intent intentWelcome = new Intent(this,SecondaryActivity.class);
            intentWelcome.putExtra(KEY_MESSAGE,etName.getText().toString());

            startActivityForResult(intentWelcome, KEY_RESULT);

        //KEY_RESULT is set by me it is at the top. it is SET as STATIC  FINAL
        // HERE FINAL means CONSTANT; the compiler does not allow the user to make changes on 'final' variables
        //STATIC is some what similar to SHARED in VB.net; STATIC variables means the variable belongs to CLASS rather than object.
        // STATIC means all object instances of a class will use this variable;
        //  if I set   KEY_RESULT =12 and make 20 objects of MainActivity
        //Every single object will have KEY_RESULT=12; and since it is final it cannot be changed.
        //And could be accessed like this : MainActivity.KEY_RESULT. i.e. Accessed through Class (thats why its called Class variable)
        // If it was not STATIC then it should be accessed like this
        // MainActivity activity=new MainActivity(); creating an instance of the class
        //activity.KEY_RESULT   i.e. accessing through object.
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        String strLocation;
        //KEY_RESULT is set by me its at the top. SET as static final.
        //RESULT_OK is not set by me it was available in the java library. there is also RESULT_CANCEL same as Form Dialog in VB.net
        if (requestCode== KEY_RESULT){
            if (resultCode == RESULT_OK){
              strLocation=  data.getStringExtra(KEY_LOCATION);
                Toast.makeText(this, "Your Location is " + strLocation, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
