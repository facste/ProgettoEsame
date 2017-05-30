package actions;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import util.Utilita;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by facst on 30/05/2017.
 */
public class CreaMessaggioAction extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String dest=request.getParameter("dest");
        String testo=request.getParameter("testo") ;
        HttpSession session = request.getSession();
        String mittente= session.getAttribute("user").toString();
        String tipo=session.getAttribute("tipo").toString();
        Utilita ut=new Utilita();
        String tipodest= ut.trovatipo(dest);
        if(tipodest!=null){
            if(tipodest.equals('OB')):
                    if(tipo.equals(""))
            }
            return(mapping.findForward("success"));
        }
    }
}
