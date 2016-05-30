package notes;

import accesnotes.Requetes;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author mooneswar.ramburrun
 */
@WebServlet(name = "RequeteNotes", urlPatterns = {"/RequeteNotes"})
public class RequeteNotes extends HttpServlet {

    public static final int OK = 200;
    public static final int NOT_FOUND = 2;
    public static final int ERROR = -1;

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // doPost : quand on veut "uploader" au niveau de serveur(ex. formulaire remplie)
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String matiere =  request.getParameter("mat");

        String erreur;

        if (nom.trim().isEmpty() || prenom.trim().isEmpty()) {//trim enleve les blanc
            erreur = "Erreur - Vous n'avez pas rempli tous les champs obligatoires.";
            request.setAttribute("erreur", erreur);
            this.getServletContext().getRequestDispatcher("/erreur.jsp").forward(request, response);
            // ou response.sendRedirect("/erreur.jsp");
        }
        
        if(nom.equalsIgnoreCase("?")){
            
        }
        if(prenom.equalsIgnoreCase("?")){
            
        }
        if(matiere.equalsIgnoreCase("?")){
            
        }

        Requetes req;
        req = new Requetes(nom, prenom, matiere);
        int result = Requetes.rechEtu();
        Etudiant etudiant = req.getEtu();
        

        if (result != OK) {
            switch (result) {
                case NOT_FOUND:
                    erreur = "nom incorect";
                    break;
                default:
                    erreur = "Problème d'accès";
                    break;
            }
            request.setAttribute("erreur", erreur);
            this.getServletContext().getRequestDispatcher("/erreur.jsp").forward(request, response);
            // ou response.sendRedirect("/erreur.jsp");
        } else {
            request.setAttribute("etudiant", etudiant);
            request.setAttribute("matiere", req.getMatiere());
            request.setAttribute("moyenne", req.getMoyenne());
            this.getServletContext().getRequestDispatcher("/resultat.jsp").forward(request, response);
            // ou response.sendRedirect("/resultat.jsp");
        }
    }
}
