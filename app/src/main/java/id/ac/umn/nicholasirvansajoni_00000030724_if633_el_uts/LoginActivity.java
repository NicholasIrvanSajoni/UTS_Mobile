package id.ac.umn.nicholasirvansajoni_00000030724_if633_el_uts;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    /* Define the UI elements */
    private EditText eName;
    private EditText ePassword;
    private TextView eAttemptsInfo;
    private Button eLogin;

    String userName = "";
    String userPassword = "";

    /* Class to hold credentials */
    class Credentials
    {
        String name = "uasmobile";
        String password = "uasmobilegenap";
    }

    boolean isValid = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        /* Bind the XML views to Java Code Elements */
        eName = findViewById(R.id.inuser);
        ePassword = findViewById(R.id.inpass);
        eLogin = findViewById(R.id.btnlgn);

        /* Describe the logic when the login button is clicked */
        eLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /* Obtain user inputs */
                userName = eName.getText().toString();
                userPassword = ePassword.getText().toString();

                /* Check if the user inputs are empty */
                if(userName.isEmpty() || userPassword.isEmpty())
                {
                    /* Display a message toast to user to enter the details */
                    Toast.makeText(id.ac.umn.nicholasirvansajoni_00000030724_if633_el_uts.LoginActivity.this, "Please enter name and password!", Toast.LENGTH_LONG).show();

                }else {

                    /* Validate the user inputs */
                    isValid = validate(userName, userPassword);

                    /* Validate the user inputs */

                    /* If not valid */
                    if (isValid) {
                        startActivity(new Intent(id.ac.umn.nicholasirvansajoni_00000030724_if633_el_uts.LoginActivity.this, id.ac.umn.nicholasirvansajoni_00000030724_if633_el_uts.ListLaguActivity.class));
                    }
                    else {
                        Toast.makeText(id.ac.umn.nicholasirvansajoni_00000030724_if633_el_uts.LoginActivity.this, "Username atau Password salah!", Toast.LENGTH_LONG).show();
                    }

                }
            }
        });
    }

    /* Validate the credentials */
    private boolean validate(String userName, String userPassword)
    {
        /* Get the object of Credentials class */
        Credentials credentials = new Credentials();

        /* Check the credentials */
        if(userName.equals(credentials.name) && userPassword.equals(credentials.password))
        {
            return true;
        }

        return false;
    }
}