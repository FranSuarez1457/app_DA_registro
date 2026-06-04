package es.ulpgc.eite.da.templatedemo.projectDetail;

import java.lang.ref.WeakReference;
import es.ulpgc.eite.da.templatedemo.app.AppMediator;
import es.ulpgc.eite.da.templatedemo.database.ProjectEntity;

public class projectDetailPresenter implements projectDetailContract.Presenter {

    private WeakReference<projectDetailContract.View> view;
    private projectDetailContract.Model model;

    public projectDetailPresenter(projectDetailContract.Model model) {
        this.model = model;
    }

    @Override
    public void injectView(WeakReference<projectDetailContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(projectDetailContract.Model model) {
        this.model = model;
    }

    @Override
    public void onResume() {
        String projectName = AppMediator.getInstance().currentProjectName;
        projectDetailViewModel viewModel = new projectDetailViewModel();

        if (projectName != null) {
            ProjectEntity project = model.getProjectByName(projectName);

            if (project != null) {
                viewModel.projectName = project.name;
                viewModel.projectDescription = project.description;
                viewModel.projectDate = project.startDate;
                viewModel.taskList = model.getTaskNamesByProjectId(project.projectId);
            }
        }
        view.get().displayData(viewModel);
    }

    @Override
    public void onFavoriteButtonClicked() {
    }
}