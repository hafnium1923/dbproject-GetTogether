package controller.project;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Project;
import model.ProjectManager;

public class UpdateProjectController implements Controller{

	@Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
 
		int pid = Integer.parseInt(request.getParameter("pid"));
		
		if (request.getMethod().equals("GET")) {	
    		// GET request: ���� ���� form ��û	
    		ProjectManager projectMan = ProjectManager.getInstance();
			Project project = projectMan.findProject(pid);	// �����Ϸ��� ���� ���� �˻�
			request.setAttribute("project", project);		
			return "/project/form.jsp";   // �˻��� ������ update form���� ����     
	    }	
    	
		try {
    	// POST request (������Ʈ ������ parameter�� ���۵�)
    	Project project = new Project(	// project ��ü�� �����Ͽ� ���� ������ ����
    					pid,
    					request.getParameter("title"),
    					request.getParameter("field"),
    					request.getParameter("language"),
    					request.getParameter("subtitle"),null,null,null,null,
    					request.getParameter("goal"),
    					Integer.parseInt(request.getParameter("applicationNum")),
    					request.getParameter("description"),
    					true,
    					Integer.parseInt(request.getParameter("mnum")),
    					Integer.parseInt(request.getParameter("recommendCnt")),
    					Integer.parseInt(request.getParameter("lookupCnt")));

		ProjectManager manager = ProjectManager.getInstance();
		manager.updateProject(project);			
        return "redirect:/project/detail.jsp";	
		}
		catch (Exception e) {
			return "/project/detail.jsp";
		}
    }
}
