package sv.edu.ues.fia.eisi.fia.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import sv.ues.fia.eisi.proyectopdm.db.entity.Local;
import sv.ues.fia.eisi.proyectopdm.repository.LocalRepository;

public class LocalViewModel extends AndroidViewModel {

    //Atributos de Clase
    private LocalRepository repository;
    private LiveData<List<Local>> allLocales;

    //Constructor
    public LocalViewModel(@NonNull Application application) {
        super(application);
        repository =  new LocalRepository(application);
        allLocales = repository.getAllLocales();
    }

    //Acciones CRUD para Local
    public void insert(Local local){
        repository.insertarLocal(local);
    }

    public void updateLocal(Local local){
        repository.actualizarLocal(local);
    }

    public void deleteLocal(Local local){
        repository.borrarLocal(local);
    }

    public void deleteAllLocales(){
        repository.borrarTodosLocales();
    }

    public LiveData<List<Local>> getAllLocales() {
        return allLocales;
    }

    public Local getLoc(String id) throws InterruptedException, ExecutionException, TimeoutException {
        return repository.getLocal(id);
    }
}
