package sv.edu.ues.fia.eisi.fia.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import sv.edu.ues.fia.eisi.fia.DataBase;
import sv.edu.ues.fia.eisi.fia.dao.PrimeraRevisionDao;
import sv.edu.ues.fia.eisi.fia.entity.Docente;
import sv.edu.ues.fia.eisi.fia.entity.Evaluacion;
import sv.edu.ues.fia.eisi.fia.entity.PrimeraRevision;

public class PrimeraRevisionRepository {

    private PrimeraRevisionDao primeraRevisionDao;
    private LiveData<List<PrimeraRevision>> allPrimerasRevisiones;

    public PrimeraRevisionRepository(Application application){
        DataBase base = DataBase.getInstance(application);
        primeraRevisionDao = base.primeraRevisionDao();
        allPrimerasRevisiones = primeraRevisionDao.obtenerPrimerasRevisiones();
    }

    public void insertarPrimeraRevision(PrimeraRevision primeraRevision){
        new InsertarPrimeraRevisionAsyncTask(primeraRevisionDao).execute(primeraRevision);
    }

    public void actualizarPrimeraRevision(PrimeraRevision primeraRevision){
        new ActualizarPrimeraRevisionAsyncTask(primeraRevisionDao).execute(primeraRevision);
    }

    public void borrarPrimeraRevision(PrimeraRevision primeraRevision){
        new BorrarPrimeraRevisionAsyncTask(primeraRevisionDao).execute(primeraRevision);
    }

    public void borrarTodasPrimerasRevisiones(){
        new DeleteAllPrimeraRevisionesAsyncTask(primeraRevisionDao).execute();
    }

    public PrimeraRevision obtenerPrimeraRevision(Integer integer) throws InterruptedException, ExecutionException, TimeoutException {
        return new obtenerPrimeraRevisionAsyncTask(primeraRevisionDao).execute(integer).get(12, TimeUnit.SECONDS);
    }

    public LiveData<List<PrimeraRevision>> getAllPrimerasRevisiones(){
        return allPrimerasRevisiones;
    }

    public List<PrimeraRevision> obtenerRevisionPorDetalle(int id) throws InterruptedException, ExecutionException, TimeoutException {
        return new PrimeraRevisionRepository.obtenerRevisionPorDetalleAsyncTask(primeraRevisionDao).execute(id).get(12, TimeUnit.SECONDS);
    }

    public LiveData<List<PrimeraRevision>> getPRDocente(String carnet){
        return primeraRevisionDao.obtenerPrimerasRevisionesAsDocente(carnet);
    }

    public Docente obtenerDocUsuario(int id) throws InterruptedException, ExecutionException, TimeoutException {
        return new PrimeraRevisionRepository.obtenerDocUsuarioAsyncTask(primeraRevisionDao).execute(id).get(12, TimeUnit.SECONDS);
    }

    public Evaluacion obtenerEvaluacionEnPR(int id) throws InterruptedException, ExecutionException, TimeoutException {
        return new obtenerEvaluacionEnPRAsyncTask(primeraRevisionDao).execute(id).get(12, TimeUnit.SECONDS);
    }

    public static class InsertarPrimeraRevisionAsyncTask extends AsyncTask<PrimeraRevision, Void, Void>{
        private PrimeraRevisionDao primeraRevisionDao;

        private InsertarPrimeraRevisionAsyncTask(PrimeraRevisionDao primeraRevisionDao){
            this.primeraRevisionDao=primeraRevisionDao;
        }
        @Override
        protected Void doInBackground(PrimeraRevision... primeraRevisiones) {
            primeraRevisionDao.insertPrimeraRevision(primeraRevisiones[0]);
            return null;
        }
    }

    public static class ActualizarPrimeraRevisionAsyncTask extends AsyncTask<PrimeraRevision, Void, Void>{
        private PrimeraRevisionDao primeraRevisionDao;

        private ActualizarPrimeraRevisionAsyncTask(PrimeraRevisionDao primeraRevisionDao){
            this.primeraRevisionDao=primeraRevisionDao;
        }
        @Override
        protected Void doInBackground(PrimeraRevision... primeraRevisiones) {
            primeraRevisionDao.updatePrimeraRevision(primeraRevisiones[0]);
            return null;
        }
    }

    public static class BorrarPrimeraRevisionAsyncTask extends AsyncTask<PrimeraRevision, Void, Void>{
        private PrimeraRevisionDao primeraRevisionDao;

        private BorrarPrimeraRevisionAsyncTask(PrimeraRevisionDao primeraRevisionDao){
            this.primeraRevisionDao=primeraRevisionDao;
        }
        @Override
        protected Void doInBackground(PrimeraRevision... primeraRevisiones) {
            primeraRevisionDao.deletePrimeraRevision(primeraRevisiones[0]);
            return null;
        }
    }

    public static class  DeleteAllPrimeraRevisionesAsyncTask extends AsyncTask<Void, Void, Void>{
        private PrimeraRevisionDao primeraRevisionDao;

        private DeleteAllPrimeraRevisionesAsyncTask(PrimeraRevisionDao primeraRevisionDao){
            this.primeraRevisionDao=primeraRevisionDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            primeraRevisionDao.deleteAllPrimerasRevisiones();
            return null;
        }
    }

    public static class obtenerPrimeraRevisionAsyncTask extends AsyncTask <Integer, Void, PrimeraRevision>{
        private PrimeraRevisionDao primeraRevisionDao;

        private obtenerPrimeraRevisionAsyncTask(PrimeraRevisionDao primeraRevisionDao){
            this.primeraRevisionDao=primeraRevisionDao;
        }

        @Override
        protected PrimeraRevision doInBackground(Integer... primeraRevisiones) {
            return primeraRevisionDao.obtenerPrimeraRevision(primeraRevisiones[0]);
        }
    }

    private static class obtenerRevisionPorDetalleAsyncTask extends AsyncTask<Integer, Void, List<PrimeraRevision>>{
        private PrimeraRevisionDao primeraRevisionDao;

        private obtenerRevisionPorDetalleAsyncTask(PrimeraRevisionDao primeraRevisionDao){
            this.primeraRevisionDao=primeraRevisionDao;
        }
        @Override
        protected List<PrimeraRevision> doInBackground(Integer... ints) {
            return primeraRevisionDao.obtenerRevisionPorDetalle(ints[0]);
        }
    }


    private static class obtenerDocUsuarioAsyncTask extends AsyncTask<Integer, Void, Docente>{
        private PrimeraRevisionDao docenteDao;
        private obtenerDocUsuarioAsyncTask(PrimeraRevisionDao docenteDao){
            this.docenteDao=docenteDao;
        }
        @Override
        protected Docente doInBackground(Integer... id) {
            return docenteDao.obtenerDocUsuario(id[0]);
        }
    }

    public static class obtenerEvaluacionEnPRAsyncTask extends AsyncTask<Integer, Void, Evaluacion>{
        private PrimeraRevisionDao evaluacionDao;
        private obtenerEvaluacionEnPRAsyncTask(PrimeraRevisionDao evaluacionDao){
            this.evaluacionDao = evaluacionDao;
        }
        @Override
        protected Evaluacion doInBackground(Integer... id) {
            return evaluacionDao.obtenerEvaluacionEnPR(id[0]);
        }
    }
}
