package cn.lo.struts;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class LoginAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		LoginActionForm laf = (LoginActionForm)form;
		String username = laf.getUsername();
		String password = laf.getPassword();
		if(username.equals("admin") && password.equals("admin"))
			return mapping.findForward("success");
		else
			return mapping.findForward("error");
	}

}
