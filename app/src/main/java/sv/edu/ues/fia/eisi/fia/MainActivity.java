package sv.edu.ues.fia.eisi.fia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public static final String USERNAME = "USER_NAME";
    public static final String USER_PASSWORD = "USER_PASSWORD";

    Button btnInicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        PreferenceSingleton.getInstance().Initialize(getApplicationContext());
        String usuario=PreferenceSingleton.getInstance().readPreference(USERNAME);
        String password=PreferenceSingleton.getInstance().readPreference(USER_PASSWORD);

        btnInicio = findViewById(R.id.btn_inicio);
        btnInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PantallaPrincipal.class);
                startActivity(intent);
            }
        });
    }
}