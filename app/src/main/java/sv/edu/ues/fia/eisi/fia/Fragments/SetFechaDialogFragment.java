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
import android.widget.DatePicker;

import sv.edu.ues.fia.eisi.fia.R;

public class SetFechaDialogFragment extends DialogFragment {

    private static final String ARG_PARAM1 = "fecha_actual";

    private String mParam1;
    private DatePicker datePicker;

    public SetFechaDialogFragment() {
        // Required empty public constructor
    }

    public static SetFechaDialogFragment newInstance(String param1) {
        SetFechaDialogFragment fragment = new SetFechaDialogFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.text_fecha);
        builder.setPositiveButton(R.string.text_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).setNegativeButton(R.string.text_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_set_fecha_dialog, null);
        datePicker = view.findViewById(R.id.date_picker);
        builder.setView(view);
        return builder.create();
    }
}