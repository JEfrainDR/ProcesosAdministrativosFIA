package sv.edu.ues.fia.eisi.fia.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import sv.edu.ues.fia.eisi.fia.entity.Docente;
import sv.edu.ues.fia.eisi.fia.entity.Evaluacion;
import sv.edu.ues.fia.eisi.fia.entity.PrimeraRevision;
import sv.edu.ues.fia.eisi.fia.repository.PrimeraRevisionRepository;

public class PrimeraRevisionViewModel extends AndroidViewModel {

    private PrimeraRevisionRepository primeraRevisionRepository;
    private LiveData<List<PrimeraRevision>> allPrimerasRevisiones;

    public PrimeraRevisionViewModel(@NonNull Application application) {
        super(application);
        primeraRevisionRepository = new PrimeraRevisionRepository(application);
        allPrimerasRevisiones = primeraRevisionRepository.getAllPrimerasRevisiones();
    }

    public void insertPrimeraRevision(PrimeraRevision primeraRevision){
        primeraRevisionRepository.insertarPrimeraRevision(primeraRevision);
    }

    public void updatePrimeraRevision(PrimeraRevision primeraRevision){
        primeraRevisionRepository.actualizarPrimeraRevision(primeraRevision);
    }

    public void deletePrimeraRevision(PrimeraRevision primeraRevision){
        primeraRevisionRepository.borrarPrimeraRevision(primeraRevision);
    }

    public void deleteAllPrimeraRevision() {primeraRevisionRepository.borrarTodasPrimerasRevisiones(); }

    public LiveData<List<PrimeraRevision>> getAllPrimerasRevisiones() { return allPrimerasRevisiones; }

    public PrimeraRevision getPrimeraRevision(int id) throws InterruptedException, ExecutionException, TimeoutException {
        return primeraRevisionRepository.obtenerPrimeraRevision(id);
    }

    public List<PrimeraRevision> getRevisionPorDetalle(int id) throws InterruptedException, ExecutionException, TimeoutException {
        return primeraRevisionRepository.obtenerRevisionPorDetalle(id);
    }

    public LiveData<List<PrimeraRevision>> obtenerPRDocente(String carnet){
        return primeraRevisionRepository.getPRDocente(carnet);
    }

    public Docente obtenerDocUsuario(int id) throws InterruptedException, ExecutionException, TimeoutException{
        return primeraRevisionRepository.obtenerDocUsuario(id);
    }

    public Evaluacion obtenerEvaPR(int id) throws InterruptedException, ExecutionException, TimeoutException{
        return primeraRevisionRepository.obtenerEvaluacionEnPR(id);
    }
    
}
