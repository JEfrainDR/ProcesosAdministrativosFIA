package sv.edu.ues.fia.eisi.fia.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "Cargo")
public class Cargo {

    @PrimaryKey(autoGenerate = true)
    private int idCargo;
    @ForeignKey(
            entity = AreaAdm.class,
            parentColumns = "idDeptarmento",
            childColumns ="idAreaAdminFK"
    )
    private int idAreaAdminFK;
    private String nomCargo;


    public Cargo(int idAreaAdminFK, String nomCargo) {
        this.idAreaAdminFK = idAreaAdminFK;
        this.nomCargo = nomCargo;
    }

    public int getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(int idCargo) {
        this.idCargo = idCargo;
    }

    public int getIdAreaAdminFK() {
        return idAreaAdminFK;
    }

    public void setIdAreaAdminFK(int idAreaAdminFK) {
        this.idAreaAdminFK = idAreaAdminFK;
    }

    public String getNomCargo() {
        return nomCargo;
    }

    public void setNomCargo(String nomCargo) {
        this.nomCargo = nomCargo;
    }
}
