package sv.edu.ues.fia.eisi.fia.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import sv.ues.fia.eisi.proyectopdm.DataBase;
import sv.ues.fia.eisi.proyectopdm.dao.LocalDao;
import sv.ues.fia.eisi.proyectopdm.db.entity.Local;

public class LocalRepository {
    //Atributos de Clase
    private LocalDao localDao;
    private LiveData<List<Local>> allLocales;

    //Constructor
    public LocalRepository(Application application){
        DataBase dataBase = DataBase.getInstance(application);
        localDao = dataBase.localDao();
        allLocales = localDao.obtenerLocales();
    }

    //Insertar de forma asíncrona
    public void insertarLocal(Local local){
        new InsertarLocalAsyncTask(localDao).execute(local);
    }

    //Actualizar de forma asíncrona
    public void actualizarLocal(Local local){
        new actualizarLocalAsyncTask(localDao).execute(local);
    }

    //Borrar un local de forma asíncrona
    public void borrarLocal(Local local){
        new BorrarLocalAsyncTask(localDao).execute(local);
    }

    //Borrar todos los locales de forma asíncrona
    public void borrarTodosLocales(){
        new DeleteAllLocalesAsyncTask(localDao).execute();
    }

    //Obtener todos los locales
    public LiveData<List<Local>> getAllLocales() {
        return allLocales;
    }

    //Obtener un local de forma asíncrona
    public Local getLocal(String id) throws InterruptedException, ExecutionException, TimeoutException {
        return new getLocalAsyncTask(localDao).execute(id).get(12, TimeUnit.SECONDS);
    }

    //Clase Asíncrona de Insertar
    private static class InsertarLocalAsyncTask extends AsyncTask<Local, Void, Void>{
        private LocalDao localDao;

        private InsertarLocalAsyncTask(LocalDao localDao){
            this.localDao=localDao;
        }

        @Override
        protected Void doInBackground(Local... locales) {
            localDao.insertarLocal(locales[0]);
            return null;
        }
    }

    //Clase Asíncrona de Actualizar
    private static class actualizarLocalAsyncTask extends AsyncTask<Local, Void, Void>{
        private LocalDao localDao;

        private actualizarLocalAsyncTask(LocalDao localDao){
            this.localDao=localDao;
        }

        @Override
        protected Void doInBackground(Local... locales) {
            localDao.updateLocal(locales[0]);
            return null;
        }
    }

    //Clase Asíncrona de BorrarLocal
    private static class BorrarLocalAsyncTask extends AsyncTask<Local, Void, Void>{
        private LocalDao localDao;

        private BorrarLocalAsyncTask(LocalDao localDao){
            this.localDao=localDao;
        }

        @Override
        protected Void doInBackground(Local... locales) {
            localDao.deleteLocal(locales[0]);
            return null;
        }
    }

    //Clase Asíncrona de Borrar Todos
    private static class DeleteAllLocalesAsyncTask extends AsyncTask<Void, Void, Void>{
        private LocalDao localDao;

        private DeleteAllLocalesAsyncTask(LocalDao localDao){
            this.localDao=localDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            localDao.borrarLocales();
            return null;
        }
    }

    //Clase Asíncrona de Obtener Local
    private static class  getLocalAsyncTask extends AsyncTask<String, Void, Local>{
        private LocalDao localDao;
        private getLocalAsyncTask(LocalDao localDao){
            this.localDao = localDao;
        }

        @Override
        protected Local doInBackground(String... locales) {
            return localDao.obtenerLocal(locales[0]);
        }
    }
}
