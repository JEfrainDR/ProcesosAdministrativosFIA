package sv.edu.ues.fia.eisi.fia.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import sv.ues.fia.eisi.proyectopdm.db.entity.Asignatura;
import sv.ues.fia.eisi.proyectopdm.repository.AsignaturaRepository;

public class AsignaturaViewModel extends AndroidViewModel {

    private AsignaturaRepository asignaturaRepository;
    private LiveData<List<Asignatura>> allAsignaturas;

    public AsignaturaViewModel(@NonNull Application application) {
        super(application);
        asignaturaRepository=new AsignaturaRepository(application);
        allAsignaturas=asignaturaRepository.getAllAsignaturas();
    }

    public void insert(Asignatura asignatura){
        asignaturaRepository.insertar(asignatura);
    }

    public void update(Asignatura asignatura){
        asignaturaRepository.actualizar(asignatura);
    }

    public void delete(Asignatura asignatura){
        asignaturaRepository.eliminar(asignatura);
    }

    public void deleteAllAsignatiras(){
        asignaturaRepository.eliminarTodas();
    }

    public LiveData<List<Asignatura>> getAllAsignaturas(){
        return allAsignaturas;
    }

    public Asignatura obtenerAsignatura(String id) throws InterruptedException, ExecutionException, TimeoutException {
        return asignaturaRepository.obtenerAsignatura(id);
    }
}
