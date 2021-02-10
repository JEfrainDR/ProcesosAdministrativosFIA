package sv.edu.ues.fia.eisi.fia.Adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import sv.edu.ues.fia.eisi.fia.Tabs.EnCurso;
import sv.edu.ues.fia.eisi.fia.Tabs.Finalizado;
import sv.edu.ues.fia.eisi.fia.Tabs.Pendientes;

public class TabFragmentAdapter extends FragmentPagerAdapter {
    int numTabs;
    String[] titulos={"PENDIENTES","EN CURSO","FINALIZADO"};
    public TabFragmentAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        this.numTabs=behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new Pendientes();
            case 1:
                return new EnCurso();
            case 2:
                return new Finalizado();
            default:
                return null;
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titulos[position];
    }

    @Override
    public int getCount() {
        return numTabs;
    }
}
