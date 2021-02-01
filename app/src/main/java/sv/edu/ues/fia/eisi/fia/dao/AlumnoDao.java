package sv.edu.ues.fia.eisi.fia.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import sv.edu.ues.fia.eisi.fia.entity.Alumno;


@Dao
public interface AlumnoDao {

    @Insert
    void insertarAlumno(Alumno alumno);

    @Update
    void updateAlumno(Alumno alumno);

    @Delete
    void deleteAlumno(Alumno alumno);

    /*
        En este Query nosotros borramos todos los datos que contenga la tabla Alumno
        Para borrar uno en especifico necesitaremos usar el id en el Query como un delete de SQL
     */

    @Query("delete from Alumno ")
    void borrarAlumnos();

    /*
        LiveData tiene ventajas como mostrar los datos siempre actualizados en la app usando ROOM
     */

    @Query("Select * from Alumno")
    LiveData<List<Alumno>> obtenerAlumnos();

    @Query("select * from Alumno where carnetAlumno == :alumnoid")
    Alumno obtenerAlumno(String alumnoid);

    @Query("Select * from Alumno")
    List<Alumno> getAlumnosAsync();
}
