package es.ulpgc.eite.da.templatedemo.projectDetail;

public class projectDetailModel implements projectDetailContract.Model {

    @Override
    public projectDetailState getSimulatedProject() {
        projectDetailState dummyState = new projectDetailState();
        dummyState.projectName = "Proyecto Alpha";
        dummyState.projectDate = "Fecha de inicio: 2026-06-01";
        dummyState.projectDescription = "Este es el proyecto principal de la compañía. Se requiere finalizar todas las tareas antes del 7 de junio.";
        dummyState.isFavorite = false;
        return dummyState;
    }
}