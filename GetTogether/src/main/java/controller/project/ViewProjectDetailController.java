package controller.project;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Project;
import model.service.ProjectManager;

public class ViewProjectDetailController implements Controller {

	@Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {			

		ProjectManager manager = ProjectManager.getInstance();
		int pid = Integer.parseInt(request.getParameter("pid"));
		Project project = manager.findProject(pid);		// ���� �˻�			

		request.setAttribute("project", project);	//  ���� ����				
		return "/project/detail.jsp";				// ȭ������ �̵�
    }
}