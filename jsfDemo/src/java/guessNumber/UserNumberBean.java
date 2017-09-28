/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guessNumber;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Random;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Bext
 */
@Named(value = "userNumberBean")
@SessionScoped
public class UserNumberBean implements Serializable {
    Integer randomInt;
    Integer userNumber;
    String Response;
    /** 
     * Creates a new instance of UserNumberBean
     */
    public UserNumberBean() {
        Random randomGR = new Random(); 
        randomInt = randomGR.nextInt(10);
        System.out.println("numero de Duke: " + randomInt);
    }

    public Integer getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(Integer userNumber) {
        this.userNumber = userNumber;
    }

    public String getResponse() {
        if ((userNumber != null) && (userNumber.compareTo(randomInt) == 0)) {
            //invalidate user session
            FacesContext context = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
            session.invalidate();
            return "Muy Bien, Lo adivinaste!";
        } else {
            return "<p>Lo Siento, " + userNumber + " no es.</p>" +
                    "<p>Intenta de nuevo...</p>";
        }
    }
    
}
