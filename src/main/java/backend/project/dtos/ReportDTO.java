package backend.project.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportDTO {
    private int nroMes;
    private String nombre;
    private int cantidad;
    private double monto;
    private String nombreMes;

    public void CargarNombreMes() {
        String mes[] = {"", "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        this.nombreMes = mes[nroMes];
    }
}
