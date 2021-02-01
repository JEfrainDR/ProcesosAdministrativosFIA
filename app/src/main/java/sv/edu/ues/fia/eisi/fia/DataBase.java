package sv.edu.ues.fia.eisi.fia;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import sv.edu.ues.fia.eisi.fia.dao.AccesoUsuarioDao;
import sv.edu.ues.fia.eisi.fia.dao.AlumnoDao;
import sv.edu.ues.fia.eisi.fia.dao.AreaAdmDao;
import sv.edu.ues.fia.eisi.fia.dao.AsignaturaDao;
import sv.edu.ues.fia.eisi.fia.dao.BitacoraDao;
import sv.edu.ues.fia.eisi.fia.dao.CargoDao;
import sv.edu.ues.fia.eisi.fia.dao.CicloDao;
import sv.edu.ues.fia.eisi.fia.dao.DetalleEvaluacionDao;
import sv.edu.ues.fia.eisi.fia.dao.DocenteDao;
import sv.edu.ues.fia.eisi.fia.dao.EncargadoImpresionDao;
import sv.edu.ues.fia.eisi.fia.dao.EscuelaDao;
import sv.edu.ues.fia.eisi.fia.dao.EvaluacionDao;
import sv.edu.ues.fia.eisi.fia.dao.InscripcionDao;
import sv.edu.ues.fia.eisi.fia.dao.LocalDao;
import sv.edu.ues.fia.eisi.fia.dao.OpcionCrudDao;
import sv.edu.ues.fia.eisi.fia.dao.PrimeraRevisionDao;
import sv.edu.ues.fia.eisi.fia.dao.SegundaRevisionDao;
import sv.edu.ues.fia.eisi.fia.dao.SegundaRevision_DocenteDao;
import sv.edu.ues.fia.eisi.fia.dao.SolicitudExtraordinarioDao;
import sv.edu.ues.fia.eisi.fia.dao.SolicitudImpresionDao;
import sv.edu.ues.fia.eisi.fia.dao.TipoEvaluacionDao;
import sv.edu.ues.fia.eisi.fia.dao.UsuarioDao;
import sv.edu.ues.fia.eisi.fia.entity.AccesoUsuario;
import sv.edu.ues.fia.eisi.fia.entity.Alumno;
import sv.edu.ues.fia.eisi.fia.entity.AreaAdm;
import sv.edu.ues.fia.eisi.fia.entity.Asignatura;
import sv.edu.ues.fia.eisi.fia.entity.Bitacora;
import sv.edu.ues.fia.eisi.fia.entity.CargaAcademica;
import sv.edu.ues.fia.eisi.fia.entity.Cargo;
import sv.edu.ues.fia.eisi.fia.entity.Ciclo;
import sv.edu.ues.fia.eisi.fia.entity.CicloAsignatura;
import sv.edu.ues.fia.eisi.fia.entity.DetalleEvaluacion;
import sv.edu.ues.fia.eisi.fia.entity.Docente;
import sv.edu.ues.fia.eisi.fia.entity.EncargadoImpresion;
import sv.edu.ues.fia.eisi.fia.entity.Escuela;
import sv.edu.ues.fia.eisi.fia.entity.Evaluacion;
import sv.edu.ues.fia.eisi.fia.entity.Inscripcion;
import sv.edu.ues.fia.eisi.fia.entity.Local;
import sv.edu.ues.fia.eisi.fia.entity.OpcionCrud;
import sv.edu.ues.fia.eisi.fia.entity.PrimeraRevision;
import sv.edu.ues.fia.eisi.fia.entity.SegundaRevision;
import sv.edu.ues.fia.eisi.fia.entity.SegundaRevision_Docente;
import sv.edu.ues.fia.eisi.fia.entity.SolicitudExtraordinario;
import sv.edu.ues.fia.eisi.fia.entity.SolicitudImpresion;
import sv.edu.ues.fia.eisi.fia.entity.TipoEvaluacion;
import sv.edu.ues.fia.eisi.fia.entity.Usuario;

/*
    En esta clase es donde creamos cada una de las entity's que usaremos y definir la version de la base
    como en SQLiteOpenHelper
 */
@Database(entities = {
        //Aca se definen todas las entidades que vamos a utlizar junto con la version vigente de la BD
        Alumno.class, AreaAdm.class, Asignatura.class, CargaAcademica.class, Cargo.class,
        Ciclo.class, CicloAsignatura.class, DetalleEvaluacion.class, Docente.class,
        EncargadoImpresion.class, Escuela.class, Evaluacion.class, Inscripcion.class,
        Local.class, PrimeraRevision.class, SegundaRevision.class, SegundaRevision_Docente.class,
        SolicitudExtraordinario.class, SolicitudImpresion.class, TipoEvaluacion.class, Usuario.class,
        AccesoUsuario.class, OpcionCrud.class, Bitacora.class
    }, version = 1)
public abstract class DataBase extends RoomDatabase {

    private static DataBase instance;

    //Este atributo es para poder acceder a la clase Dao
    public abstract AlumnoDao alumnoDao();
    public abstract AreaAdmDao areaAdmDao();
    public abstract AsignaturaDao asignaturaDao();
    public abstract CargoDao cargoDao();
    public abstract CicloDao cicloDao();
    public abstract DocenteDao docenteDao();
    public abstract EncargadoImpresionDao encargadoImpresionDao();
    public abstract EscuelaDao escuelaDao();
    public abstract InscripcionDao inscripcionDao();
    public abstract LocalDao localDao();
    public abstract TipoEvaluacionDao tipoEvaluacionDao();
    public abstract SolicitudExtraordinarioDao solicitudExtraordinarioDao();
    public abstract EvaluacionDao evaluacionDao();
    public abstract SolicitudImpresionDao solicitudImpresionDao();
    public abstract SegundaRevisionDao segundaRevisionDao();
    public abstract DetalleEvaluacionDao detalleEvaluacionDao();
    public abstract PrimeraRevisionDao primeraRevisionDao();
    public abstract SegundaRevision_DocenteDao segundaRevision_docenteDao();
    public abstract UsuarioDao usuarioDao();
    public abstract AccesoUsuarioDao accesoUsuarioDao();
    public abstract OpcionCrudDao opcionCrudDao();
    public abstract BitacoraDao bitacoraDao();
    /*
        synchronized garantiza el patron singleton para que solo haya una instancia de una clase
        es util para cuando todos los usuarios esten usando la misma instancia
     */
    public static synchronized DataBase getInstance(Context context){
        if(instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext(),
                    DataBase.class,"PDMDataBaseVer2.s3db").fallbackToDestructiveMigration().addCallback(roomCallback)
                    .setJournalMode(JournalMode.TRUNCATE)
                    .build();
        }
        return instance;
    }

    /*
        En este metodo particularmente solo hemos creado la base de datos en onCreate y se asigna
        cada uno de los valores conel metodo PoblarBDAsyncTask a la instancia
     */
    private static RoomDatabase.Callback roomCallback=new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PoblarDBAsyncTask(instance).execute();
            try {
                //ACCESOUSUARIO
                /*db.execSQL("create trigger after_update_acceso_usuario after update on AccesoUsuario " +
                        "begin " +
                        "insert into Bitacora values (null,new.idAccesoUsuario,'AccesoUsuario','UPDATE'); "+
                        "end ");
                //SOLICITUDIMPRESION
                db.execSQL("create trigger after_delete_soli_impres after delete on SolicitudImpresion " +
                        "begin " +
                        "insert into Bitacora values (null,old.idImpresion,'SolicitudImpresion','DELETE'); "+
                        "end ");
                db.execSQL("create trigger after_update_soli_impres after update on SolicitudImpresion " +
                        "begin " +
                        "insert into Bitacora values (null,new.idImpresion,'SolicitudImpresion','UPDATE'); "+
                        "end ");
                db.execSQL("create trigger after_insert_soli_impres after insert on SolicitudImpresion " +
                        "begin " +
                        "insert into Bitacora values (null,new.idImpresion,'SolicitudImpresion','INSERT'); "+
                        "end ");
                db.execSQL("create trigger eliminar_solicitudImpr before delete on Docente " +
                        "begin " +
                        "delete from SolicitudImpresion where SolicitudImpresion.carnetDocenteFK=old.carnetDocente; " +
                        "end");*/
                db.execSQL("create trigger eliminar_asignatura before delete on AreaAdm " +
                        "begin " +
                        "delete from Asignatura where Asignatura.idDepartamentoFK=old.idDeptarmento; " +
                        "end");
                db.execSQL("create trigger eliminar_evaluacion1 before delete on Asignatura " +
                        "begin " +
                        "delete from Evaluacion where Evaluacion.codigoAsignaturaFK=old.codigoAsignatura; " +
                        "end");
                db.execSQL("create trigger eliminar_evaluacion2 before delete on Docente " +
                        "begin " +
                        "delete from Evaluacion where Evaluacion.carnetDocenteFK=old.carnetDocente; " +
                        "end");
                db.execSQL("create trigger eliminar_detalle before delete on Evaluacion \n" +
                        " begin \n" +
                        " delete from DetalleEvaluacion where DetalleEvaluacion.idEvaluacionFK=old.idEvaluacion; \n" +
                        " end");
                db.execSQL("create trigger eliminar_pr before delete on DetalleEvaluacion " +
                        "begin " +
                        "delete from PrimeraRevision where PrimeraRevision.idDetalleEvFK=old.idDetalleEv; " +
                        "end");
                db.execSQL("create trigger eliminar_sr before delete on PrimeraRevision " +
                        "begin " +
                        "delete from SegundaRevision where SegundaRevision.idPrimeraRevisionFK=old.idPrimerRevision; " +
                        "end");
                db.execSQL("create trigger eliminar_rev_doc1 before delete on Docente " +
                        "begin " +
                        "delete from SegundaRevision_Docente where SegundaRevision_Docente.carnetDocenteFK=old.carnetDocente; " +
                        "end");

                db.execSQL("create trigger eliminar_rev_doc2 before delete on SegundaRevision " +
                        "begin " +
                        "delete from SegundaRevision_Docente where SegundaRevision_Docente.idSegundaRevisionFK=old.idSegundaRevision; " +
                        "end");
                db.execSQL("create trigger eliminar_alumno before delete on Alumno " +
                        "begin " +
                        "delete from DetalleEvaluacion where DetalleEvaluacion.carnetAlumnoFK=old.carnetAlumno; " +
                        "end");
            } catch (Exception e) {
                Log.e("dbError", e.getMessage() + "\n",e.fillInStackTrace());
            }
        }
    };

    //Aca llenamos la base de datos con los objetos dao y las operaciones asignadas
    private static class PoblarDBAsyncTask extends AsyncTask<Void, Void, Void>{
        private AlumnoDao alumnoDao;
        private AreaAdmDao areaAdmDao;
        private AsignaturaDao asignaturaDao;
        private CargoDao cargoDao;
        private CicloDao cicloDao;
        private DocenteDao docenteDao;
        private EncargadoImpresionDao encargadoImpresionDao;
        private EscuelaDao escuelaDao;
        private InscripcionDao inscripcionDao;
        private LocalDao localDao;
        private TipoEvaluacionDao tipoEvaluacionDao;
        private SolicitudExtraordinarioDao solicitudExtraordinarioDao;
        private EvaluacionDao evaluacionDao;
        private SolicitudImpresionDao solicitudImpresionDao;
        private SegundaRevisionDao segundaRevisionDao;
        private DetalleEvaluacionDao detalleEvaluacionDao;
        private PrimeraRevisionDao primeraRevisionDao;
        private SegundaRevision_DocenteDao segundaRevision_docenteDao;
        private UsuarioDao usuarioDao;
        private AccesoUsuarioDao accesoUsuarioDao;
        private OpcionCrudDao opcionCrudDao;
        private BitacoraDao bitacoraDao;

        private PoblarDBAsyncTask(DataBase db){
            escuelaDao=db.escuelaDao();
            areaAdmDao=db.areaAdmDao();
            asignaturaDao=db.asignaturaDao();
            alumnoDao=db.alumnoDao();
            inscripcionDao=db.inscripcionDao();
            cicloDao=db.cicloDao();
            tipoEvaluacionDao=db.tipoEvaluacionDao();
            cargoDao=db.cargoDao();
            docenteDao=db.docenteDao();
            localDao=db.localDao();
            encargadoImpresionDao=db.encargadoImpresionDao();
            solicitudExtraordinarioDao=db.solicitudExtraordinarioDao();
            evaluacionDao=db.evaluacionDao();
            solicitudImpresionDao=db.solicitudImpresionDao();
            segundaRevisionDao=db.segundaRevisionDao();
            detalleEvaluacionDao = db.detalleEvaluacionDao();
            primeraRevisionDao = db.primeraRevisionDao();
            segundaRevision_docenteDao = db.segundaRevision_docenteDao();
            usuarioDao = db.usuarioDao();
            accesoUsuarioDao = db.accesoUsuarioDao();
            opcionCrudDao = db.opcionCrudDao();
            bitacoraDao = db.bitacoraDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                //Opciones Crud para menus.
                /*opcionCrudDao.insertOpcionCrud(new OpcionCrud("AlumnoMenu",0));//1
                opcionCrudDao.insertOpcionCrud(new OpcionCrud("EvaluacionMenu",0));
                opcionCrudDao.insertOpcionCrud(new OpcionCrud("CargoMenu",0));//3
                opcionCrudDao.insertOpcionCrud(new OpcionCrud("AreaAdmMenu",0));
                opcionCrudDao.insertOpcionCrud(new OpcionCrud("SoliImpresMenu",0));//5
                opcionCrudDao.insertOpcionCrud(new OpcionCrud("AsignaturaMenu",0));
                opcionCrudDao.insertOpcionCrud(new OpcionCrud("PrimRevMenu",0));//7
                opcionCrudDao.insertOpcionCrud(new OpcionCrud("CicloMenu",0));
                opcionCrudDao.insertOpcionCrud(new OpcionCrud("LocalMenu",0));//9
                opcionCrudDao.insertOpcionCrud(new OpcionCrud("SoliExtrMenu",0));
                opcionCrudDao.insertOpcionCrud(new OpcionCrud("DocenteMenu",0));//11
                opcionCrudDao.insertOpcionCrud(new OpcionCrud("EncImpresMenu",0));
                opcionCrudDao.insertOpcionCrud(new OpcionCrud("InscripcionMenu",0));//13
                opcionCrudDao.insertOpcionCrud(new OpcionCrud("UsuarioMenu",0));//14
                //Opciones Crud para editar, crear y eliminar.
                opcionCrudDao.insertOpcionCrud(new OpcionCrud("CrearAlumno",2));//15
                opcionCrudDao.insertOpcionCrud(new OpcionCrud("EditarAlumno",1));
                opcionCrudDao.insertOpcionCrud(new OpcionCrud("EliminarAlumno",3));
                opcionCrudDao.insertOpcionCrud(new OpcionCrud("CrearEvaluacion",2));//18
                opcionCrudDao.insertOpcionCrud(new OpcionCrud("EditarEvaluacion",1));
                opcionCrudDao.insertOpcionCrud(new OpcionCrud("EliminarEvaluacion",3));
                opcionCrudDao.insertOpcionCrud(new OpcionCrud("CrearCargo",2));//21
                opcionCrudDao.insertOpcionCrud(new OpcionCrud("EditarCargo",1));
                opcionCrudDao.insertOpcionCrud(new OpcionCrud("EliminarCargo",3));
                opcionCrudDao.insertOpcionCrud(new OpcionCrud("CrearAreaAdm",2));//24
                opcionCrudDao.insertOpcionCrud(new OpcionCrud("EditarAreaAdm",1));
                opcionCrudDao.insertOpcionCrud(new OpcionCrud("EliminarAreaAdm",3));
                opcionCrudDao.insertOpcionCrud(new OpcionCrud("CrearSoliImpres",2));//27
                opcionCrudDao.insertOpcionCrud(new OpcionCrud("EditarSoliImpres",1));
                opcionCrudDao.insertOpcionCrud(new OpcionCrud("EliminarSoliImpres",3));
                opcionCrudDao.insertOpcionCrud(new OpcionCrud("CrearAsignatura",2));//30
                opcionCrudDao.insertOpcionCrud(new OpcionCrud("EditarAsignatura",1));
                opcionCrudDao.insertOpcionCrud(new OpcionCrud("EliminarAsignatura",3));
                opcionCrudDao.insertOpcionCrud(new OpcionCrud("CrearPrimRev",2));//33
                opcionCrudDao.insertOpcionCrud(new OpcionCrud("EditarPrimRev",1));
                opcionCrudDao.insertOpcionCrud(new OpcionCrud("EliminarPrimRev",3));
                opcionCrudDao.insertOpcionCrud(new OpcionCrud("CrearCiclo",2));//36
                opcionCrudDao.insertOpcionCrud(new OpcionCrud("EditarCiclo",1));
                opcionCrudDao.insertOpcionCrud(new OpcionCrud("EliminarCiclo",3));
                opcionCrudDao.insertOpcionCrud(new OpcionCrud("CrearLocal",2));//39
                opcionCrudDao.insertOpcionCrud(new OpcionCrud("EditarLocal",1));
                opcionCrudDao.insertOpcionCrud(new OpcionCrud("EliminarLocal",3));
                opcionCrudDao.insertOpcionCrud(new OpcionCrud("CrearSoliExtr",2));//42
                opcionCrudDao.insertOpcionCrud(new OpcionCrud("EditarSoliExtr",1));
                opcionCrudDao.insertOpcionCrud(new OpcionCrud("EliminarSoliExtr",3));
                opcionCrudDao.insertOpcionCrud(new OpcionCrud("CrearDocente",2));//45
                opcionCrudDao.insertOpcionCrud(new OpcionCrud("EditarDocente",1));
                opcionCrudDao.insertOpcionCrud(new OpcionCrud("EliminarDocente",3));
                opcionCrudDao.insertOpcionCrud(new OpcionCrud("CrearEncImpres",2));//48
                opcionCrudDao.insertOpcionCrud(new OpcionCrud("EditarEncImpres",1));
                opcionCrudDao.insertOpcionCrud(new OpcionCrud("EliminarEncImpres",3));
                //Usuarios con rol de director
                usuarioDao.insertUser(new Usuario("DOCEISI1", "chicas", 1));//1
                usuarioDao.insertUser(new Usuario("DOCEIQA1", "torres", 1));
                //Usuarios con rol de docente
                usuarioDao.insertUser(new Usuario("DOCEISI2", "gonzalez", 2));//3
                usuarioDao.insertUser(new Usuario("DOCEISI3", "carballo", 2));
                usuarioDao.insertUser(new Usuario("DOCEIQA2", "gamero", 2));//5
                //Usuarios con rol de estudiante
                usuarioDao.insertUser(new Usuario("PP15001", "rubper", 3));
                usuarioDao.insertUser(new Usuario("DR17010", "efrain", 3));//7
                usuarioDao.insertUser(new Usuario("BC14026", "arelyb", 3));
                usuarioDao.insertUser(new Usuario("MM16045", "fredyrol", 3));//9
                usuarioDao.insertUser(new Usuario("MG17030", "jairois", 3));
                usuarioDao.insertUser(new Usuario("MC16022", "julioc", 3));//11
                //Usuario con rol de encargado de impresiones
                usuarioDao.insertUser(new Usuario("EURFIA1","eliseo", 4));
                //Usuario administrador
                usuarioDao.insertUser(new Usuario("admin", "admin", 5));
                //AccesoUsuario para menus de Directores
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(1,2));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(1,4));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(1,5));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(1,6));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(1,7));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(1,10));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(2,2));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(2,4));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(2,5));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(2,6));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(2,7));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(2,10));
                //Accesousuario para opciones de Directores
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(1,18));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(1,19));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(1,20));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(1,27));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(1,28));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(1,29));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(1,30));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(1,31));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(1,32));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(1,33));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(1,34));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(1,35));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(1,42));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(1,43));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(1,44));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(2,18));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(2,19));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(2,20));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(2,27));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(2,28));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(2,29));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(2,30));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(2,31));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(2,32));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(2,33));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(2,34));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(2,35));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(2,42));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(2,43));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(2,44));
                //AccesoUsuario para menus de Docentes
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(3,2));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(3,5));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(3,7));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(3,10));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(4,2));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(4,5));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(4,7));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(4,10));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(5,2));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(5,5));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(5,7));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(5,10));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(5,18));
                //Accesousuario para opciones de Docentes
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(3,18));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(3,19));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(3,20));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(3,27));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(3,28));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(3,29));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(3,33));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(3,34));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(3,35));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(3,42));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(3,43));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(3,44));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(4,18));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(4,19));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(4,20));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(4,27));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(4,28));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(4,29));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(4,33));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(4,34));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(4,35));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(4,42));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(4,43));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(4,44));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(5,18));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(5,19));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(5,20));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(5,27));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(5,28));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(5,29));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(5,33));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(5,34));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(5,35));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(5,42));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(5,43));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(5,44));
                //AccesoUsuario para menus de Alumnos
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(6,2));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(6,10));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(7,2));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(7,10));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(8,2));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(8,10));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(9,2));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(9,10));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(10,2));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(10,10));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(11,2));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(11,10));
                //Accesousuario para opciones de Alumnos
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(6,42));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(6,43));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(6,44));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(7,42));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(7,43));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(7,44));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(8,42));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(8,43));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(8,44));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(9,42));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(9,43));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(9,44));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(10,42));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(10,43));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(10,44));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(11,42));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(11,43));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(11,44));
                //AccesoUsuario para menus de Encargado de Impresion
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(12,5));
                //AccesoUsuario para menus de Administrador
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(13,1));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(13,2));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(13,3));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(13,4));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(13,5));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(13,6));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(13,7));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(13,8));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(13,9));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(13,10));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(13,11));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(13,12));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(13,13));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(13,14));
                //Accesousuario para opciones de Administrador
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(13,15));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(13,16));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(13,17));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(13,18));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(13,19));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(13,20));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(13,21));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(13,22));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(13,23));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(13,24));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(13,25));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(13,26));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(13,27));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(13,28));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(13,29));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(13,30));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(13,31));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(13,32));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(13,33));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(13,34));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(13,35));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(13,36));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(13,37));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(13,38));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(13,39));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(13,40));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(13,41));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(13,42));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(13,43));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(13,44));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(13,45));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(13,46));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(13,47));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(13,48));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(13,49));
                accesoUsuarioDao.insertAccesoUsuario(new AccesoUsuario(13,50));
                //Escuelas
                escuelaDao.insert(new Escuela("Escuela de Ingeniería de Sistemas Informáticos","Ingeniería de Sistemas Informáticos","DOCEISI1"));
                escuelaDao.insert(new Escuela("Escuela de Ingeniería Química e Ingeniería de Alimentos","Ingeniería Quimica","DOCEIQA2"));
                //Departamentos
                areaAdmDao.insertAreaAdm(new AreaAdm(1, "Dpto. de Comunicaciones y CC.de la Computación"));
                areaAdmDao.insertAreaAdm(new AreaAdm(1, "Dpto. de Programación y Manejo de datos"));
                areaAdmDao.insertAreaAdm(new AreaAdm(1, "Dpto. de Desarrollo de Sistemas"));
                areaAdmDao.insertAreaAdm(new AreaAdm(1, "Dpto. de Administración"));
                areaAdmDao.insertAreaAdm(new AreaAdm(2, "Dpto. de CC. Básicas de la Ingeniería Quíimica"));
                //Cargo
                cargoDao.insertCargo(new Cargo(1, "Docente"));
                cargoDao.insertCargo(new Cargo(1, "Jefe de Departamento"));
                cargoDao.insertCargo(new Cargo(2, "Docente"));
                cargoDao.insertCargo(new Cargo(2, "Jefe de Departamento"));
                cargoDao.insertCargo(new Cargo(3, "Docente"));
                cargoDao.insertCargo(new Cargo(3, "Jefe de Departamento"));
                cargoDao.insertCargo(new Cargo(4, "Docente"));
                cargoDao.insertCargo(new Cargo(4, "Jefe de Departamento"));
                cargoDao.insertCargo(new Cargo(5, "Docente"));
                cargoDao.insertCargo(new Cargo(5, "Jefe de Departamento"));

                //Docente
                docenteDao.insertDocente(new Docente("DOCEISI1", 1, 1, "Rudy Wilfredo", "Chicas Villegas", "chicas@ues.edu.sv", "+50378923456"));
                docenteDao.insertDocente(new Docente("DOCEIQA1", 9, 2,"Tania", "Torres Rivera", "torres@ues.edu.sv", "+50364589879"));
                docenteDao.insertDocente(new Docente("DOCEISI2", 3, 3,"Cesar Augusto", "González", "gonzalez@ues.edu.sv", "+50368923457"));
                docenteDao.insertDocente(new Docente("DOCEISI3", 6, 4,"Elmer Arturo", "Carballo Ruiz", "carballo@ues.edu.sv", "+50368793456"));
                docenteDao.insertDocente(new Docente("DOCEIQA2", 9, 5,"Eugenia Salvadora", "Gamero de Ayala", "gamero@ues.edu.sv", "+50365789034"));
                //Asignaturas por area administrativa(Departamentos)
                asignaturaDao.insertAsignatura(new Asignatura("DSI115", 3, "Diseño de Sistemas I"));
                asignaturaDao.insertAsignatura(new Asignatura("SGG115", 3, "Sistemas de Información Geográficos"));
                asignaturaDao.insertAsignatura(new Asignatura("PDM115", 2, "Programación para Dispositivos Móviles"));
                asignaturaDao.insertAsignatura(new Asignatura("MIP115", 2, "Microprogramación"));
                asignaturaDao.insertAsignatura(new Asignatura("TAD115", 4, "Teoría Administrativa"));
                asignaturaDao.insertAsignatura(new Asignatura("FQR215", 5, "Fisicoquímica II"));
                //Alumnos
                alumnoDao.insertarAlumno(new Alumno("MM16045", "Fredy Rolando", "Martínez Méndez", "1","fredymartinezues@gmail.com",9));
                alumnoDao.insertarAlumno(new Alumno("BC14026", "Vilma Arely", "Bárcenas Cruz", "1","crzabarcenas@gmail.com", 8));
                alumnoDao.insertarAlumno(new Alumno("PP15001", "Rubén Alejandro", "Pérez Pineda", "1","rub.per96@gmail.com", 6));
                alumnoDao.insertarAlumno(new Alumno("DR17010", "José Efraín", "Díaz Rivas", "1","efra.00@gmail.com", 7));
                alumnoDao.insertarAlumno(new Alumno("MG17030", "Jairo Isaac", "Montoya Galdámez", "1","jairomontoya.raices@gmail.com", 10));
                alumnoDao.insertarAlumno(new Alumno("MC16022", "Julio Antonio", "Merino Corcio", "5","corcio@gmail.com", 11));
                //Inscripción
                inscripcionDao.insertInscripcion(new Inscripcion("MM16045", "DSI115", 2, 1, 2));
                inscripcionDao.insertInscripcion(new Inscripcion("MM16045", "PDM115", 3, 1, 3));
                inscripcionDao.insertInscripcion(new Inscripcion("MG17030","PDM115", 2, 1, 2));
                inscripcionDao.insertInscripcion(new Inscripcion("MG17030", "MIP115", 1, 1, 1));
                inscripcionDao.insertInscripcion(new Inscripcion("DR17010", "DSI115", 1,2, 1));
                inscripcionDao.insertInscripcion(new Inscripcion("DR17010", "TAD115", 1, 1, 1));
                inscripcionDao.insertInscripcion(new Inscripcion("PP15001", "DSI115", 2, 1, 1));
                inscripcionDao.insertInscripcion(new Inscripcion("PP15001", "TAD115", 2, 1, 1));
                inscripcionDao.insertInscripcion(new Inscripcion("BC14026", "PDM115", 2, 1, 1));
                inscripcionDao.insertInscripcion(new Inscripcion("MC16022", "FQR215", 1, 1, 1));
                //Ciclo
                cicloDao.insertCiclo(new Ciclo("08-08-19", "10-12-19", 6));
                cicloDao.insertCiclo(new Ciclo("17-02-2020", "20-06-2020", 7));
                //Tipo de evaluación
                tipoEvaluacionDao.insertarTipoEv(new TipoEvaluacion("Ordinario"));
                tipoEvaluacionDao.insertarTipoEv(new TipoEvaluacion("Repetido"));
                tipoEvaluacionDao.insertarTipoEv(new TipoEvaluacion("Diferido"));
                //Evaluación
                evaluacionDao.insertEvaluacion(new Evaluacion("DOCEISI3",1,"DSI115","Parcial de prueba","11/11/2000","12/11/2005","descripción de parcial de prueba","Sin Fecha",2,40));
                evaluacionDao.insertEvaluacion(new Evaluacion("DOCEISI3",1,"DSI115","Tarea de prueba","11/11/2000","11/11/2000","segunda prueba de descripción","Sin Fecha",12,50));
                evaluacionDao.insertEvaluacion(new Evaluacion("DOCEISI3",1,"DSI115","Actividad de prueba","11/11/2000","10/11/2002","tercera prueba de descripción esta vez mucho más larga más de una línea","Sin Fecha",2,60));
                evaluacionDao.insertEvaluacion(new Evaluacion("DOCEISI3",1,"DSI115","Control de lectura","11/11/2000","11/11/2000","cuarta prueba de descripción","Sin Fecha",32,70));
                evaluacionDao.insertEvaluacion(new Evaluacion("DOCEISI2",1,"PDM115","Ensayo de prueba","11/11/2000","10/10/2010","prueba corta","Sin Fecha",52,70));
                evaluacionDao.insertEvaluacion(new Evaluacion("DOCEISI2",1,"PDM115","Parcial de unidad","11/11/2000","11/11/2000","prueba de distintas longitudes de descripción","Sin Fecha",102,100));
                //Local
                localDao.insertarLocal(new Local("LComp1","Laboratorio 1","Escuela de Ingeniería de Sistemas Informáticos", 13.721252d, -89.200072d));
                localDao.insertarLocal(new Local("EIIC2", "Cúbiculo 2", "Escuela de Ingeniería Industrial", 13.721303d, -89.200351d));
                localDao.insertarLocal(new Local("BIB301","Salón 1 de la biblioteca","Biblioteca de Ingeniería y Arquitectura", 13.720522d, -89.201918d));
                localDao.insertarLocal(new Local("EIQIAC1", "Cúbiculo 1", "Escuela de Ingeniería Química e Ingeniería de Alimentos", 13.720333d, -89.202191d));
                localDao.insertarLocal(new Local("EIMC3","Cúbiculo 3", "Escuela de Ingeniería Mecánica", 13.721254d,-89.200997d));
                localDao.insertarLocal(new Local("F2","Laboratorio F2", "Unidad de Ciencias Básicas",13.719670d,-89.200853d));
                localDao.insertarLocal(new Local("LabArq", "Aula EA", "Laboratorio de Arquitectura", 13.721739d, -89.200349d));
                localDao.insertarLocal(new Local("EIEC4", "Cúbiculo 4", "Escuela de Ingeniería Eléctrica", 13.637352d, -89.377031d));
                //Detalle de evaluación
                detalleEvaluacionDao.insertDetalleEvaluacion(new DetalleEvaluacion(1, "MM16045", 7.9f));
                detalleEvaluacionDao.insertDetalleEvaluacion(new DetalleEvaluacion(2,"DR17010", 8f));
                detalleEvaluacionDao.insertDetalleEvaluacion(new DetalleEvaluacion(3,"PP15001", 8f));
                detalleEvaluacionDao.insertDetalleEvaluacion(new DetalleEvaluacion(4,"DR17010", 8f));
                detalleEvaluacionDao.insertDetalleEvaluacion(new DetalleEvaluacion(5, "BC14026", 7.5f));
                //Primera revisión
                primeraRevisionDao.insertPrimeraRevision(new PrimeraRevision("LComp1", 1, "7/06/2020", true, 7f, 9f, "Ejercicio 1"));
                primeraRevisionDao.insertPrimeraRevision(new PrimeraRevision("BIB301", 2, "9/06/2020", true, 6f, 8f, "Ejercicio 2"));
                primeraRevisionDao.insertPrimeraRevision(new PrimeraRevision("LComp1", 3, "9/07/2020", true, 6f, 8f, "Pregunta 2"));
                primeraRevisionDao.insertPrimeraRevision(new PrimeraRevision("EIIC2", 4, "9/07/2020", true, 6f, 8f, "Pregunta 2"));
                primeraRevisionDao.insertPrimeraRevision(new PrimeraRevision("F2", 5, "9/07/2020", true, 6f, 8f, "Pregunta 2"));
                //Encargado de impresión
                encargadoImpresionDao.insertEncargadoImpresion(new EncargadoImpresion( "Pedro Eliseo Peñate", 12));
                //Segunda revisión
                segundaRevisionDao.insertSegundaRevision(new SegundaRevision(1, "9/06/2020", "12:22:00",10,"", "8/06/2020"));
                segundaRevisionDao.insertSegundaRevision(new SegundaRevision(2, "9/06/2020", "12:22:00", "8/06/2020"));
                //Solicitud de extraordinario
                solicitudExtraordinarioDao.insertSolicitudExtraordinario(new SolicitudExtraordinario("PP15001", 1, 3, "Enfermedad", "16-06-2020", true));
                */
            }catch (Exception e){
                Log.d("equisde: ", e.getMessage() + "\n");
                e.fillInStackTrace();
            }
            return null;
        }
    }
}
