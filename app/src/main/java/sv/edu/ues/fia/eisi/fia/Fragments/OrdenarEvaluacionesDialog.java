package sv.edu.ues.fia.eisi.fia.Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import sv.edu.ues.fia.eisi.fia.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OrdenarEvaluacionesDialog#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrdenarEvaluacionesDialog extends DialogFragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RadioGroup radioOrdenar;

    public OrdenarEvaluacionesDialog() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OrdenarEvaluacionesDialog.
     */
    // TODO: Rename and change types and number of parameters
    public static OrdenarEvaluacionesDialog newInstance(String param1, String param2) {
        OrdenarEvaluacionesDialog fragment = new OrdenarEvaluacionesDialog();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.text_odenar_por);
        builder.setPositiveButton(R.string.text_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String orden = "fecha_inicio";
                switch (radioOrdenar.getCheckedRadioButtonId()){
                    case R.id.radio_asignatura:
                        orden = "asignatura";break;
                    case R.id.radio_fecha_inicio:
                        orden = "fecha_inicio";break;
                    case R.id.radio_fecha_fin:
                        orden = "fecha_fin";break;
                }
                dialog.dismiss();
            }
        }).setNegativeButton(R.string.text_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_ordenar_evaluaciones_dialog, null);
        radioOrdenar = view.findViewById(R.id.rgOrdenar);
        builder.setView(view);
        return builder.create();
    }
}