package android.bignerdranch.com.mobilemidwife;


import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;


public class MidwifeRegisterActivity extends ActionBarActivity {

    protected EditText mPracticeName;
    protected EditText mPrimaryContact;
    protected EditText mPracticeEmail;
    protected EditText mPracticePassword;
    protected Button mSignUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_midwife_register);

        mPracticeName = (EditText) findViewById(R.id.enterpracticename);
        mPrimaryContact = (EditText) findViewById(R.id.primarycontactnameField);
        mPracticeEmail = (EditText) findViewById(R.id.createPracticeEmailEmailfield);
        mPracticePassword = (EditText) findViewById(R.id.createmidwifepasswordfield);
        mSignUpButton = (Button) findViewById(R.id.createmidwife);


        mSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getting string values
                String practicename = mPracticeName.getText().toString();
                String primarycontact = mPrimaryContact.getText().toString();
                String email = mPracticeEmail.getText().toString();
                String password = mPracticePassword.getText().toString();


                practicename = practicename.trim();
                primarycontact = primarycontact.trim();
                email = email.trim();
                password = password.trim();

                if (practicename.isEmpty() || primarycontact.isEmpty() || email.isEmpty() || password.isEmpty()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MidwifeRegisterActivity.this);
                    builder.setMessage(R.string.sign_up_error_message);
                    builder.setTitle(R.string.signup_error_title);
                    builder.setPositiveButton(android.R.string.ok, null);

                    AlertDialog dialog = builder.create();
                    dialog.show();


                } else {
                    //create new user
                    ParseUser newUser = new ParseUser();
                    newUser.setUsername(practicename);
                    newUser.setPassword(password);
                    newUser.setEmail(email);

                    newUser.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                //success
                                Intent intent = new Intent(MidwifeRegisterActivity.this, MidwifeActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);

                            } else {

                                AlertDialog.Builder builder = new AlertDialog.Builder(MidwifeRegisterActivity.this);
                                builder.setMessage(e.getMessage());
                                builder.setTitle(R.string.signup_error_title);
                                builder.setPositiveButton(android.R.string.ok, null);

                                AlertDialog dialog = builder.create();
                                dialog.show();
                            }

                        }
                    });

                }

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_midwife_register, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
