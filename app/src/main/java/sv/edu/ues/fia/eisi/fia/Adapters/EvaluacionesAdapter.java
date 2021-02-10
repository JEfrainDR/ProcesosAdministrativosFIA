package sv.edu.ues.fia.eisi.fia.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import sv.edu.ues.fia.eisi.fia.R;
import sv.edu.ues.fia.eisi.fia.entity.Evaluacion;

public class EvaluacionesAdapter extends RecyclerView.Adapter<EvaluacionesAdapter.ViewHolderEvaluaciones> {

    private List<Evaluacion> listEvaluaciones = new ArrayList<>();

    public void setListEvaluaciones(List<Evaluacion> listEvaluaciones) {
        this.listEvaluaciones = listEvaluaciones;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolderEvaluaciones onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.evaluacion_item, parent, false);
        return new ViewHolderEvaluaciones(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderEvaluaciones holder, int position) {
        holder.textTitulo.setText(listEvaluaciones.get(position).getNomEvaluacion().toUpperCase());
        holder.textAsignatura.setText(listEvaluaciones.get(position).getCodigoAsignaturaFK());
        holder.textDescripcion.setText(listEvaluaciones.get(position).getDescripcion());
        holder.textFechaInicio.setText(listEvaluaciones.get(position).getFechaInicio());
        holder.textFechaFin.setText(listEvaluaciones.get(position).getFechaFin());
    }

    @Override
    public int getItemCount() {
        return listEvaluaciones.size();
    }

    public class ViewHolderEvaluaciones extends RecyclerView.ViewHolder {

        TextView textTitulo, textAsignatura, textDescripcion, textFechaInicio, textFechaFin;

        public ViewHolderEvaluaciones(@NonNull View itemView) {
            super(itemView);
            textTitulo = (TextView)itemView.findViewById(R.id.text_titulo_evaluacion);
            textAsignatura = (TextView)itemView.findViewById(R.id.text_asignatura);
            textDescripcion = (TextView)itemView.findViewById(R.id.text_descripcion_evaluacion);
            textFechaInicio = (TextView)itemView.findViewById(R.id.text_fecha_inicio_ev);
            textFechaFin = (TextView)itemView.findViewById(R.id.text_fecha_fin_ev);
        }
    }
}
