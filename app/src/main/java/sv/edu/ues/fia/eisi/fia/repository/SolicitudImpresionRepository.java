package sv.edu.ues.fia.eisi.fia.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import sv.ues.fia.eisi.proyectopdm.DataBase;
import sv.ues.fia.eisi.proyectopdm.dao.SolicitudImpresionDao;
import sv.ues.fia.eisi.proyectopdm.db.entity.SolicitudImpresion;

public class SolicitudImpresionRepository {
    private SolicitudImpresionDao solicitudImpresionDao;
    private LiveData<List<SolicitudImpresion>> allSolicitudesImpresion;

    public SolicitudImpresionRepository(Application application){
        DataBase dataBase=DataBase.getInstance(application);
        solicitudImpresionDao=dataBase.solicitudImpresionDao();
        allSolicitudesImpresion=solicitudImpresionDao.obtenerSolicitudesImpresion();
    }

    public void insertar(SolicitudImpresion solicitudImpresion){
        new InsertarSolicitudImpresionAsyncTask(solicitudImpresionDao).execute(solicitudImpresion);
    }

    public void actualizar(SolicitudImpresion solicitudImpresion){
        new ActualizarSolicitudImpresionAsyncTask(solicitudImpresionDao).execute(solicitudImpresion);
    }

    public void eliminar(SolicitudImpresion solicitudImpresion){
        new BorrarSolicitudImpresionAsyncTask(solicitudImpresionDao).execute(solicitudImpresion);
    }

    public void eliminarTodas(){
        new DeleteAllSolicitudesImpresionAsyncTask(solicitudImpresionDao).execute();
    }

    public LiveData<List<SolicitudImpresion>> getAllSolicitudesImpresion(){
        return allSolicitudesImpresion;
    }

    public SolicitudImpresion obtenerSolicitudImpresion(Integer id) throws InterruptedException, ExecutionException, TimeoutException {
        return new ObtenerSolicitudImpresionAsyncTask(solicitudImpresionDao).execute(id).get(12, TimeUnit.SECONDS);
    }

    public LiveData<List<SolicitudImpresion>> obtenerSolicitudPorEstado(String estado) throws InterruptedException, ExecutionException, TimeoutException {
        return new ObtenerSolicitudesPorEstadoAsyncTask(solicitudImpresionDao).execute(estado).get(12, TimeUnit.SECONDS);
    }

    public LiveData<List<SolicitudImpresion>> ontenerSolicitudesPorCarnet(String carnetDocente) throws InterruptedException, ExecutionException, TimeoutException {
        return new ObtenerSolicitudesPorCarnetAsyncTask(solicitudImpresionDao).execute(carnetDocente).get(12, TimeUnit.SECONDS);
    }

    public LiveData<List<SolicitudImpresion>> obtenerSolicitudesPorDirector(String docDirector) throws InterruptedException, ExecutionException, TimeoutException {
        return new ObtenerSOlicitudesPorDirectorAsyncTask(solicitudImpresionDao).execute(docDirector).get(12, TimeUnit.SECONDS);
    }

    private static class InsertarSolicitudImpresionAsyncTask extends AsyncTask<SolicitudImpresion,Void,Void>{
        private SolicitudImpresionDao solicitudImpresionDao;

        public InsertarSolicitudImpresionAsyncTask(SolicitudImpresionDao solicitudImpresionDao) {
            this.solicitudImpresionDao = solicitudImpresionDao;
        }

        @Override
        protected Void doInBackground(SolicitudImpresion... solicitudImpresions) {
            solicitudImpresionDao.insertSolicitudImpresion(solicitudImpresions[0]);
            return null;
        }
    }

    private static class ActualizarSolicitudImpresionAsyncTask extends AsyncTask<SolicitudImpresion,Void,Void>{
        private SolicitudImpresionDao solicitudImpresionDao;

        public ActualizarSolicitudImpresionAsyncTask(SolicitudImpresionDao solicitudImpresionDao) {
            this.solicitudImpresionDao = solicitudImpresionDao;
        }

        @Override
        protected Void doInBackground(SolicitudImpresion... solicitudImpresions) {
            solicitudImpresionDao.updateSolicitudImpresion(solicitudImpresions[0]);
            return null;
        }
    }

    private static class BorrarSolicitudImpresionAsyncTask extends AsyncTask<SolicitudImpresion,Void,Void>{
        private SolicitudImpresionDao solicitudImpresionDao;

        public BorrarSolicitudImpresionAsyncTask(SolicitudImpresionDao solicitudImpresionDao) {
            this.solicitudImpresionDao = solicitudImpresionDao;
        }

        @Override
        protected Void doInBackground(SolicitudImpresion... solicitudImpresions) {
            solicitudImpresionDao.deleteSolicitudImpreison(solicitudImpresions[0]);
            return null;
        }
    }

    private static class DeleteAllSolicitudesImpresionAsyncTask extends AsyncTask<Void,Void,Void>{
        SolicitudImpresionDao solicitudImpresionDao;

        public DeleteAllSolicitudesImpresionAsyncTask(SolicitudImpresionDao solicitudImpresionDao) {
            this.solicitudImpresionDao = solicitudImpresionDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            solicitudImpresionDao.borrarSolicitudesImpresion();
            return null;
        }
    }

    private static class ObtenerSolicitudImpresionAsyncTask extends AsyncTask<Integer,Void,SolicitudImpresion>{
        private SolicitudImpresionDao solicitudImpresionDao;

        public ObtenerSolicitudImpresionAsyncTask(SolicitudImpresionDao solicitudImpresionDao) {
            this.solicitudImpresionDao = solicitudImpresionDao;
        }

        @Override
        protected SolicitudImpresion doInBackground(Integer... SolicitudImpresions) {
            return solicitudImpresionDao.obtenerSolicitudImpresion(SolicitudImpresions[0]);
        }
    }

    private static class ObtenerSolicitudesPorEstadoAsyncTask extends AsyncTask<String,Void, LiveData<List<SolicitudImpresion>>>{
        private SolicitudImpresionDao solicitudImpresionDao;

        public ObtenerSolicitudesPorEstadoAsyncTask(SolicitudImpresionDao solicitudImpresionDao) {
            this.solicitudImpresionDao = solicitudImpresionDao;
        }

        @Override
        protected LiveData<List<SolicitudImpresion>> doInBackground(String... strings) {
            return solicitudImpresionDao.obtenerSolicitudesPorEstado(strings[0]);
        }
    }

    private static class ObtenerSolicitudesPorCarnetAsyncTask extends AsyncTask<String,Void, LiveData<List<SolicitudImpresion>>>{
        private SolicitudImpresionDao solicitudImpresionDao;

        public ObtenerSolicitudesPorCarnetAsyncTask(SolicitudImpresionDao solicitudImpresionDao) {
            this.solicitudImpresionDao = solicitudImpresionDao;
        }

        @Override
        protected LiveData<List<SolicitudImpresion>> doInBackground(String... strings) {
            return solicitudImpresionDao.obtenerSolicitudesPorCarnet(strings[0]);
        }
    }

    private static class ObtenerSOlicitudesPorDirectorAsyncTask extends AsyncTask<String,Void, LiveData<List<SolicitudImpresion>>>{
        private SolicitudImpresionDao solicitudImpresionDao;

        public ObtenerSOlicitudesPorDirectorAsyncTask(SolicitudImpresionDao solicitudImpresionDao) {
            this.solicitudImpresionDao = solicitudImpresionDao;
        }

        @Override
        protected LiveData<List<SolicitudImpresion>> doInBackground(String... strings) {
            return solicitudImpresionDao.obtenerSolicitudesPorDirector(strings[0]);
        }
    }
}
