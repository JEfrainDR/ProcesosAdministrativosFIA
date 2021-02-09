package sv.edu.ues.fia.eisi.fia.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import sv.edu.ues.fia.eisi.fia.R;
import sv.edu.ues.fia.eisi.fia.entity.Recurso;

public class RecursosAdapter extends RecyclerView.Adapter<RecursosAdapter.ViewHolderRecursos> {

    List<Recurso> listRecursos;

    public RecursosAdapter(List<Recurso> listRecursos){
        this.listRecursos = listRecursos;
    }


    @NonNull
    @Override
    public ViewHolderRecursos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.recurso_item, parent, false);
        return new ViewHolderRecursos(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderRecursos holder, int position) {
        holder.textTitulo.setText(listRecursos.get(position).getNombre());
        holder.textTamaño.setText(listRecursos.get(position).getTamaño());
        holder.imgIcon.setImageResource(listRecursos.get(position).getIcon());
    }

    @Override
    public int getItemCount() {
        return listRecursos.size();
    }

    public class ViewHolderRecursos extends RecyclerView.ViewHolder {

        TextView textTitulo, textTamaño;
        ImageView imgIcon;

        public ViewHolderRecursos(@NonNull View itemView) {
            super(itemView);
            textTitulo = (TextView) itemView.findViewById(R.id.text_titulo_archivo);
            textTamaño = (TextView) itemView.findViewById(R.id.text_tamano_archivo);
            imgIcon = (ImageView) itemView.findViewById(R.id.text_icono_archivo);
        }
    }
}
