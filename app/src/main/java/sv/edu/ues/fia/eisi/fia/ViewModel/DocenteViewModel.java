package sv.edu.ues.fia.eisi.fia.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import sv.ues.fia.eisi.proyectopdm.db.entity.Docente;
import sv.ues.fia.eisi.proyectopdm.repository.DocenteRepository;

public class DocenteViewModel extends AndroidViewModel {
    //atributos
    private DocenteRepository repo;
    private LiveData<List<Docente>> todosDocentes;

    //constructor
    public DocenteViewModel(@NonNull Application application) {
        super(application);
        repo=new DocenteRepository(application);
        todosDocentes=repo.getTodosDocentes();
    }

    //acciones crud
    public void insertDocente(Docente docente){
        repo.insertarDocente(docente);
    }

    public void updateDocente(Docente docente){
        repo.actualizarDocente(docente);
    }

    public void deleteDocente(Docente docente){
        repo.borrarDocente(docente);
    }

    public void deleteTodosDocentes(){
        repo.borrarTodasDocentees();
    }

    public LiveData<List<Docente>> getTodosDocentes() {
        return todosDocentes;
    }

    public Docente getDocente(String id) throws InterruptedException, ExecutionException, TimeoutException {
        return repo.obtenerDocente(id);
    }

    public Docente obtenerDocentePorIdUsuario(int id) throws InterruptedException, ExecutionException, TimeoutException {
        return repo.obtenerDocentePorIdUsuario(id);
    }
}
