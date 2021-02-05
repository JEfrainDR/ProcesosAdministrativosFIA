package sv.edu.ues.fia.eisi.fia.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(tableName = "Recursos")
public class Recursos {
    @ForeignKey(
            entity = Evaluacion.class,
            parentColumns = "idEvaluacion",
            childColumns = "idEvaluacionFK"
    )
    private String idEvaluacionFK;
    private String urlRecurso;

    public Recursos(String idEvaluacionFK, String urlRecurso) {
        this.idEvaluacionFK = idEvaluacionFK;
        this.urlRecurso = urlRecurso;
    }

    public String getIdEvaluacionFK() {
        return idEvaluacionFK;
    }

    public void setIdEvaluacionFK(String idEvaluacionFK) {
        this.idEvaluacionFK = idEvaluacionFK;
    }

    public String getUrlRecurso() {
        return urlRecurso;
    }

    public void setUrlRecurso(String urlRecurso) {
        this.urlRecurso = urlRecurso;
    }
}
