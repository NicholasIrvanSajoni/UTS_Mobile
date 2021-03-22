package id.ac.umn.nicholasirvansajoni_00000030724_if633_el_uts;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void OpenProfilActivity (View view){
        Intent intent = new Intent(this, id.ac.umn.nicholasirvansajoni_00000030724_if633_el_uts.ProfilActivity.class);
        startActivity(intent);
    }

    public void OpenLoginActivity (View view){
        Intent intent = new Intent(this, id.ac.umn.nicholasirvansajoni_00000030724_if633_el_uts.LoginActivity.class);
        startActivity(intent);
    }
}