package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface TeamRecommand {
	// request�� ó���� �� �̵��� URL�� ��ȯ
    public String execute(HttpServletRequest request, HttpServletResponse response) 
    		throws Exception;
}
