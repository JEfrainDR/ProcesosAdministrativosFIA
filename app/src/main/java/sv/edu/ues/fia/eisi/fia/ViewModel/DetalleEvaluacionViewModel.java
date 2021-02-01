package sv.edu.ues.fia.eisi.fia.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import sv.ues.fia.eisi.proyectopdm.db.entity.Alumno;
import sv.ues.fia.eisi.proyectopdm.db.entity.DetalleEvaluacion;
import sv.ues.fia.eisi.proyectopdm.repository.DetalleEvaluacionRepository;

public class DetalleEvaluacionViewModel extends AndroidViewModel {

    private DetalleEvaluacionRepository detalleEvaluacionRepository;
    private LiveData<List<DetalleEvaluacion>> allDetalles;

    public DetalleEvaluacionViewModel(@NonNull Application application){
        super(application);
        detalleEvaluacionRepository= new DetalleEvaluacionRepository(application);
        allDetalles=detalleEvaluacionRepository.getAllDetalleEvaluaciones();
    }


    //acciones crud

    public void insertDetaleEvalulacion(DetalleEvaluacion detalleEvaluacion){
        detalleEvaluacionRepository.insertarDetalleEvaluacion(detalleEvaluacion);
    }

    public void updateDetalleEvaluacion(DetalleEvaluacion detalleEvaluacion){
        detalleEvaluacionRepository.actualizarDetalleEvaluacion(detalleEvaluacion);
    }

    public void deleteDetalleEvaluacion(DetalleEvaluacion detalleEvaluacion){
        detalleEvaluacionRepository.borrarDetalleEvaluacion(detalleEvaluacion);
    }

    public void deleteAllDetalleEvaluacion(DetalleEvaluacion detalleEvaluacion){
        detalleEvaluacionRepository.borrarTodosDetalleEvaluaciones();
    }

    public LiveData<List<DetalleEvaluacion>> getAllDetalles(){ return allDetalles; }

    public LiveData<List<DetalleEvaluacion>> getDetallesPorUsuario(int id){ return detalleEvaluacionRepository.getDetallesPorUsuario(id); }

    public DetalleEvaluacion getDetalleEvaluacion(int id ) throws InterruptedException, ExecutionException, TimeoutException {
        return detalleEvaluacionRepository.obtenerDetalleEvaluacion(id);
    }

    public List<DetalleEvaluacion> getDetallePorAlumno(String id ) throws InterruptedException, ExecutionException, TimeoutException {
        return detalleEvaluacionRepository.obtenerDetallePorAlumno(id);
    }

    public DetalleEvaluacion getDetalleAlumnoEvaluacion(int idEval, String carnet ) throws InterruptedException, ExecutionException, TimeoutException {
        return detalleEvaluacionRepository.obtenerDetalleAlumnoEvaluacion(new DetalleEvaluacionRepository.ParametrosDetalles(idEval, carnet));
    }

    public List<DetalleEvaluacion> getNotasEvaluacion(int id ) throws InterruptedException, ExecutionException, TimeoutException {
        return detalleEvaluacionRepository.obtenerNotasEvaluacion(id);
    }

    public LiveData<List<DetalleEvaluacion>> getDetallePorUsuarioYEvaluacion(int id, int idEval) throws InterruptedException, ExecutionException, TimeoutException {
        return detalleEvaluacionRepository.getDetallesPorUsuarioYEvaluacion(id, idEval);
    }

    public List<Alumno> getAlumnosEvaluacion(int id) throws InterruptedException, ExecutionException, TimeoutException {
        return detalleEvaluacionRepository.obtenerAlumnosEnEvaluacion(id);
    }
}
