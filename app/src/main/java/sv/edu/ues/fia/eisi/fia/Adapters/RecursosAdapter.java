package sv.edu.ues.fia.eisi.fia.Adapters;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecursosAdapter extends RecyclerView.Adapter<RecursosAdapter.ViewHolderRecursos> {
    @NonNull
    @Override
    public ViewHolderRecursos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderRecursos holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolderRecursos extends RecyclerView.ViewHolder {
        public ViewHolderRecursos(@NonNull View itemView) {
            super(itemView);
        }
    }
}
