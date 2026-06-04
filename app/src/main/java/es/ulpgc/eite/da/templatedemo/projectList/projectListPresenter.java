package es.ulpgc.eite.da.templatedemo.projectList;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import es.ulpgc.eite.da.templatedemo.app.AppMediator;
import es.ulpgc.eite.da.templatedemo.database.ProjectEntity;

public class projectListPresenter implements projectListContract.Presenter {

    private WeakReference<projectListContract.View> view;
    private projectListState state;
    private projectListContract.Model model;

    public projectListPresenter(projectListState state) {
        this.state = state;
    }

    @Override
    public void injectView(WeakReference<projectListContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(projectListContract.Model model) {
        this.model = model;
    }

    @Override
    public void onResume() {
        List<ProjectEntity> realProjects = model.getProjectList();
        List<String> projectNames = new ArrayList<>();

        if (realProjects != null) {
            for (ProjectEntity project : realProjects) {
                projectNames.add(project.name);
            }
        }
        state.projectList = projectNames;
        view.get().displayData(state);
    }

    @Override
    public void onProjectClicked(String projectName) {
        AppMediator.getInstance().currentProjectName = projectName;
        view.get().navigateToProjectDetail();
    }
}