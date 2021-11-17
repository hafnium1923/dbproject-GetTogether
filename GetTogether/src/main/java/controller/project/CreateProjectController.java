package controller.project;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import controller.Controller;
import controller.user.UserSessionUtils;
import model.Project;
import model.ProjectManager;

public class CreateProjectController implements Controller{
	
	@Override
	// request�� ó���� �� �̵��� URL�� ��ȯ
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		if(!UserSessionUtils.hasLogined(request.getSession())) {
			return "redirect:/member/login/form";
		}
		
		Project project = new Project(0,request.getParameter("title"),request.getParameter("field"),request.getParameter("language"),request.getParameter("subtitle"), null,null,null,null,
				request.getParameter("goal"), Integer.parseInt(request.getParameter("applicationNum")), request.getParameter("description"), false, 0, 0,0);
	
		try {
			ProjectManager projectMan = ProjectManager.getInstance();
			projectMan.createProject(project);
			
			return "redirect:/project/list.jsp"; //������ �̵�
		} catch(Exception e) {
			request.setAttribute("registerFailed", true);
			request.setAttribute("ExistingProjectException", e);
			request.setAttribute("Project", project);
			return "/project/form.jsp"; //���н� �����
		}
	}

}
