package sv.edu.ues.fia.eisi.fia.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import sv.edu.ues.fia.eisi.fia.Fragments.SetFechaDialogFragment;
import sv.edu.ues.fia.eisi.fia.Fragments.SetHoraDialogFragment;
import sv.edu.ues.fia.eisi.fia.R;

public class NuevaEvaluacionActivity extends AppCompatActivity {

    private TextView textFechaInicio, textFechaFin, textHoraInicio, textHoraFin;
    private ImageView imgFechainicio, imgFechaFin, imgHoraInicio, imgHoraFin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_evaluacion);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        textFechaInicio = findViewById(R.id.text_fecha_inicio);
        textFechaFin = findViewById(R.id.text_fecha_fin);
        textHoraInicio = findViewById(R.id.text_hora_inicio);
        textHoraFin = findViewById(R.id.text_hora_fin);
        imgFechainicio = findViewById(R.id.img_fecha_inicio);
        imgFechaFin = findViewById(R.id.img_fecha_fin);
        imgHoraInicio = findViewById(R.id.img_hora_inicio);
        imgHoraFin = findViewById(R.id.img_hora_fin);

        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        SimpleDateFormat formatFecha = new SimpleDateFormat("yyy-MM-dd");
        SimpleDateFormat formatHora = new SimpleDateFormat("HH:mm:ss");
        formatFecha.setTimeZone(TimeZone.getDefault());
        formatHora.setTimeZone(TimeZone.getDefault());

        textFechaInicio.setText(formatFecha.format(date));
        textFechaFin.setText(formatFecha.format(date));
        textHoraInicio.setText(formatHora.format(date));
        textHoraFin.setText(formatHora.format(date));

        imgFechainicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFechaDialog();
            }
        });
        imgHoraInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setHoraDialog();
            }
        });
        imgFechaFin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFechaDialog();
            }
        });
        imgHoraFin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setHoraDialog();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_nueva_evaluacion,menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void setFechaDialog(){
        Bundle bundle = new Bundle();
        bundle.putString("fecha_actual",textFechaInicio.getText().toString());

        FragmentManager fragmentManager = getSupportFragmentManager();
        SetFechaDialogFragment fechaDialogFragment = new SetFechaDialogFragment();
        fechaDialogFragment.setArguments(bundle);
        fechaDialogFragment.show(fragmentManager, "setFechaDialog");
    }

    public void setHoraDialog(){
        Bundle bundle = new Bundle();
        bundle.putString("hora_actual",textHoraInicio.getText().toString());

        FragmentManager fragmentManager = getSupportFragmentManager();
        SetHoraDialogFragment horaDialogFragment = new SetHoraDialogFragment();
        horaDialogFragment.setArguments(bundle);
        horaDialogFragment.show(fragmentManager, "setHoraDialog");
    }
}