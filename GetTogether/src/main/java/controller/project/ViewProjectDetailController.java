package controller.project;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.ProjectManager;
import model.Project;

public class ViewProjectDetailController implements Controller {

	@Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {			
    	
    	Project project = null;
		ProjectManager manager = ProjectManager.getInstance();
		int pid = Integer.parseInt(request.getParameter("pid"));
		project = manager.findProject(pid);		// ���� �˻�			
		
		request.setAttribute("project", project);	//  ���� ����				
		return "/project/detail.jsp";				// ȭ������ �̵�
    }
}
