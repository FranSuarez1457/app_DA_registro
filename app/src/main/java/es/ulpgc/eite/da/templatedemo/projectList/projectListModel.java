package es.ulpgc.eite.da.templatedemo.projectList;

import java.util.ArrayList;
import java.util.List;

public class projectListModel implements projectListContract.Model {

    @Override
    public List<String> getSimulatedProjects() {
        // Creamos una lista falsa para que la pantalla no esté vacía al abrirla
        List<String> projects = new ArrayList<>();
        projects.add("Proyecto Alpha");
        projects.add("Proyecto Beta");
        projects.add("Actualización de Servidores");
        return projects;
    }
}