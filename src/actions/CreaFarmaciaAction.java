package actions;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by facst on 30/05/2017.
 */
public class CreaFarmaciaAction extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String nf= request.getParameter("nf");
        String indirizzof= request.getParameter("if");
        String tel= request.getParameter("tel");
        String namet= request.getParameter("namet");
        String surnamet= request.getParameter("surnamet");
        /*
        * DA CONTINUARE
        *
        * */
        return(mapping.findForward("success"));
    }
}
