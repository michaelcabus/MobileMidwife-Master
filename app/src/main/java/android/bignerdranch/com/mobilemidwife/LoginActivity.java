package android.bignerdranch.com.mobilemidwife;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;


public class LoginActivity extends Activity {


    protected EditText mUsername;
    protected EditText mPassword;

    protected Button mLoginButton;

    protected TextView mRegisterTextView;





    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activty);

        mRegisterTextView = (TextView)findViewById(R.id.register);
        mRegisterTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);            }


        });

        mUsername = (EditText)findViewById(R.id.createusernamefield);
        mPassword = (EditText)findViewById(R.id.createpasswordfield);

        mLoginButton = (Button)findViewById(R.id.create);

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getting string values
                String username = mUsername.getText().toString();
                String password = mPassword.getText().toString();


                username = username.trim();
                password = password.trim();



                if (username.isEmpty() || password.isEmpty()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                    builder.setMessage(R.string.title_activity_login_activty);
                    builder.setTitle(R.string.login_error_title);
                    builder.setPositiveButton(android.R.string.ok, null);

                    AlertDialog dialog = builder.create();
                    dialog.show();



                }

                else {
                    //Login
                    ParseUser.logInInBackground(username, password, new LogInCallback() {
                        @Override
                        public void done(ParseUser user, ParseException e) {

                        if (e == null) {

                            Intent intent = new Intent(LoginActivity.this, MidwifeActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);


                        }

                        else {

                            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                            builder.setMessage(e.getMessage());
                            builder.setTitle(R.string.login_error_title);
                            builder.setPositiveButton(android.R.string.ok, null);

                            AlertDialog dialog = builder.create();
                            dialog.show();

                        }

                        }
                    });

                }

            }
        });


        //      ActionBar actionBar = getActionBar();
  //      actionBar.hide();

 //       TextView textView = (TextView)findViewById(R.id.title);
 //       Typeface typeFace = Typeface.createFromAsset(getAssets(), "fonts/desyrel.ttf");
 //       textView.setTypeface(typeFace);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login_activty, menu);
        return true;
    }


}
