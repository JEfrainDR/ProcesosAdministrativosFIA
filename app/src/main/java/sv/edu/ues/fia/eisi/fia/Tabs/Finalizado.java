package sv.edu.ues.fia.eisi.fia.Tabs;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import sv.edu.ues.fia.eisi.fia.Adapters.EvaluacionesAdapter;
import sv.edu.ues.fia.eisi.fia.R;
import sv.edu.ues.fia.eisi.fia.ViewModel.EvaluacionViewModel;
import sv.edu.ues.fia.eisi.fia.entity.Evaluacion;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Finalizado#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Finalizado extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private EvaluacionesAdapter evaluacionesAdapter;
    private EvaluacionViewModel evaluacionViewModel;
    private Application application;
    private Activity activity;

    public Finalizado() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Finalizado.
     */
    // TODO: Rename and change types and number of parameters
    public static Finalizado newInstance(String param1, String param2) {
        Finalizado fragment = new Finalizado();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_finalizado, container, false);
        evaluacionesAdapter = new EvaluacionesAdapter();
        application = getActivity().getApplication();
        activity = getActivity();
        RecyclerView recyclerEvaluaciones = view.findViewById(R.id.recycler_evaluaciones_finalizado);
        recyclerEvaluaciones.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerEvaluaciones.setAdapter(evaluacionesAdapter);

        try {
            evaluacionViewModel = new ViewModelProvider.AndroidViewModelFactory(application).create(EvaluacionViewModel.class);
            evaluacionViewModel.obtenerEvaluacionesPorEstado("FINALIZADO").observe((LifecycleOwner) activity, new Observer<List<Evaluacion>>() {
                @Override
                public void onChanged(List<Evaluacion> evaluacions) {
                    evaluacionesAdapter.setListEvaluaciones(evaluacions);
                    evaluacionesAdapter.notifyDataSetChanged();
                }
            });
        } catch (Exception e){

        }

        return view;
    }

    public void ordenarLista(String orden){
        try {
            evaluacionViewModel = new ViewModelProvider.AndroidViewModelFactory(application).create(EvaluacionViewModel.class);
            evaluacionViewModel.obtenerEvaluacionesPorEstadoOrderBy("FINALIZADO",orden).observe((LifecycleOwner) activity, new Observer<List<Evaluacion>>() {
                @Override
                public void onChanged(List<Evaluacion> evaluacions) {
                    evaluacionesAdapter.setListEvaluaciones(evaluacions);
                    evaluacionesAdapter.notifyDataSetChanged();
                }
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

}