package sv.edu.ues.fia.eisi.fia.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import sv.edu.ues.fia.eisi.fia.Activities.NuevaEvaluacionActivity;
import sv.edu.ues.fia.eisi.fia.R;
import sv.edu.ues.fia.eisi.fia.Adapters.TabFragmentAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Evaluaciones#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Evaluaciones extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Evaluaciones() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Evaluaciones.
     */
    // TODO: Rename and change types and number of parameters
    public static Evaluaciones newInstance(String param1, String param2) {
        Evaluaciones fragment = new Evaluaciones();
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
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_evaluaciones, container, false);
        TabLayout tabLayout = view.findViewById(R.id.tab_layout);
        FloatingActionButton btnAddEvaluacion = view.findViewById(R.id.btn_add_evaluacion);
        final ViewPager viewPager = view.findViewById(R.id.viewPager);

        final TabFragmentAdapter tabFragmentAdapter = new TabFragmentAdapter(getActivity().getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(tabFragmentAdapter);
        tabLayout.setupWithViewPager(viewPager);

        btnAddEvaluacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NuevaEvaluacionActivity.class);
                startActivity(intent);
            }
        });

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_baseline_playlist_play_24);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_baseline_view_headline_24);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_baseline_playlist_add_check_24);

        tabLayout.getTabAt(0).setText(R.string.item_en_curso);
        tabLayout.getTabAt(1).setText(R.string.item_pendientes);
        tabLayout.getTabAt(2).setText(R.string.item_finalizado);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.options_menu_evaluaciones_fragment,menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_add_evaluacion:
                Intent intent = new Intent(getActivity(), NuevaEvaluacionActivity.class);
                startActivity(intent);
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}