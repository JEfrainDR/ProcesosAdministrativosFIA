package sv.edu.ues.fia.eisi.fia.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;


@Entity(tableName = "PrimeraRevision")
public class PrimeraRevision {

    @PrimaryKey(autoGenerate = true)
    private int idPrimerRevision;
    @ForeignKey(
            entity = Local.class,
            parentColumns = "idLocal",
            childColumns = "idLocalFK"
    )@NonNull
    private String idLocalFK;
    @ForeignKey(
            entity = DetalleEvaluacion.class,
            parentColumns = "idDetalleEv",
            childColumns = "idDetalleEvFk"
    )
    private int idDetalleEvFK;
    private String fechaSolicitudPrimRev;
    private boolean estadoPrimeraRev;
    private double notasAntesPrimeraRev;
    private double notaDespuesPrimeraRev;
    private String observacionesPrimeraRev;

    public PrimeraRevision(@NonNull String idLocalFK, int idDetalleEvFK, String fechaSolicitudPrimRev, boolean estadoPrimeraRev, double notasAntesPrimeraRev, double notaDespuesPrimeraRev, String observacionesPrimeraRev) {
        this.idLocalFK = idLocalFK;
        this.idDetalleEvFK = idDetalleEvFK;
        this.fechaSolicitudPrimRev = fechaSolicitudPrimRev;
        this.estadoPrimeraRev = estadoPrimeraRev;
        this.notasAntesPrimeraRev = notasAntesPrimeraRev;
        this.notaDespuesPrimeraRev = notaDespuesPrimeraRev;
        this.observacionesPrimeraRev = observacionesPrimeraRev;
    }

    @NonNull
    public int getIdPrimerRevision() {
        return idPrimerRevision;
    }

    public void setIdPrimerRevision(int idPrimerRevision) {
        this.idPrimerRevision = idPrimerRevision;
    }

    @NonNull
    public String getIdLocalFK() {
        return idLocalFK;
    }

    public void setIdLocalFK(@NonNull String idLocalFK) {
        this.idLocalFK = idLocalFK;
    }

    public int getIdDetalleEvFK() {
        return idDetalleEvFK;
    }

    public void setIdDetalleEvFK(int idDetalleEvFK) {
        this.idDetalleEvFK = idDetalleEvFK;
    }

    public String getFechaSolicitudPrimRev() {
        return fechaSolicitudPrimRev;
    }

    public void setFechaSolicitudPrimRev(String fechaSolicitudPrimRev) {
        this.fechaSolicitudPrimRev = fechaSolicitudPrimRev;
    }

    public boolean isEstadoPrimeraRev() {
        return estadoPrimeraRev;
    }

    public void setEstadoPrimeraRev(boolean estadoPrimeraRev) {
        this.estadoPrimeraRev = estadoPrimeraRev;
    }

    public double getNotasAntesPrimeraRev() {
        return notasAntesPrimeraRev;
    }

    public void setNotasAntesPrimeraRev(double notasAntesPrimeraRev) {
        this.notasAntesPrimeraRev = notasAntesPrimeraRev;
    }

    public double getNotaDespuesPrimeraRev() {
        return notaDespuesPrimeraRev;
    }

    public void setNotaDespuesPrimeraRev(double notaDespuesPrimeraRev) {
        this.notaDespuesPrimeraRev = notaDespuesPrimeraRev;
    }

    public String getObservacionesPrimeraRev() {
        return observacionesPrimeraRev;
    }

    public void setObservacionesPrimeraRev(String observacionesPrimeraRev) {
        this.observacionesPrimeraRev = observacionesPrimeraRev;
    }
}
