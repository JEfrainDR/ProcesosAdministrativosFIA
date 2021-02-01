package sv.edu.ues.fia.eisi.fia.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import sv.ues.fia.eisi.proyectopdm.DataBase;
import sv.ues.fia.eisi.proyectopdm.dao.CargoDao;
import sv.ues.fia.eisi.proyectopdm.db.entity.Cargo;

public class CargoRepository {
    private CargoDao cargoDao;
    private LiveData<List<Cargo>> allCargos;

    public CargoRepository(Application application){
        DataBase dataBase=DataBase.getInstance(application);
        cargoDao=dataBase.cargoDao();
        allCargos=cargoDao.obtenerCargos();
    }

    public void insertar(Cargo cargo){
        new InsertarCargoAsyncTask(cargoDao).execute(cargo);
    }

    public void actualizar(Cargo cargo){
        new ActualizarCargoAsyncTask(cargoDao).execute(cargo);
    }

    public void eliminar(Cargo cargo){
        new EliminarCargoAsyncTask(cargoDao).execute(cargo);
    }

    public void eliminarTodas(){
        new DeleteAllCargosAsyncTask(cargoDao).execute();
    }

    public Cargo obtenerCargo(Integer integer) throws InterruptedException, ExecutionException, TimeoutException {
        return new obtenerCargoAsyncTask(cargoDao).execute(integer).get(12, TimeUnit.SECONDS);
    }

    public LiveData<List<Cargo>> getAllCargos(){
        return allCargos;
    }

    public static class InsertarCargoAsyncTask extends AsyncTask<Cargo,Void,Void>{
        private CargoDao cargoDao;

        public InsertarCargoAsyncTask(CargoDao cargoDao) {
            this.cargoDao = cargoDao;
        }

        @Override
        protected Void doInBackground(Cargo... cargos) {
            cargoDao.insertCargo(cargos[0]);
            return null;
        }
    }

    public static class ActualizarCargoAsyncTask extends AsyncTask<Cargo,Void,Void>{
        private CargoDao cargoDao;

        public ActualizarCargoAsyncTask(CargoDao cargoDao) {
            this.cargoDao = cargoDao;
        }

        @Override
        protected Void doInBackground(Cargo... cargos) {
            cargoDao.updateCargo(cargos[0]);
            return null;
        }
    }

    public static class EliminarCargoAsyncTask extends AsyncTask<Cargo,Void,Void>{
        private CargoDao cargoDao;

        public EliminarCargoAsyncTask(CargoDao cargoDao) {
            this.cargoDao = cargoDao;
        }

        @Override
        protected Void doInBackground(Cargo... cargos) {
            cargoDao.deleteCargo(cargos[0]);
            return null;
        }
    }

    public static class DeleteAllCargosAsyncTask extends AsyncTask<Void,Void,Void>{
        private CargoDao cargoDao;

        public DeleteAllCargosAsyncTask(CargoDao cargoDao) {
            this.cargoDao = cargoDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            cargoDao.borrarCargo();
            return null;
        }
    }
    private static class  obtenerCargoAsyncTask extends AsyncTask<Integer, Void, Cargo>{
        private CargoDao cargoDao;
        private obtenerCargoAsyncTask(CargoDao cargoDao){
            this.cargoDao = cargoDao;
        }

        @Override
        protected Cargo doInBackground(Integer... cargos) {
            return cargoDao.obtenerCargo(cargos[0]);
        }
    }

}
