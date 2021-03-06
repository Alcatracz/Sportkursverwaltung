package validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.faces.validator.Validator;


public class LoginValidator implements Validator {
    public void validate(FacesContext context, UIComponent component,
            Object value) throws ValidatorException {
        String user = (String) value;
        if (!user.equalsIgnoreCase("tester") && !user.equalsIgnoreCase("trainer") ) {
            FacesMessage message = new FacesMessage();
            message.setDetail("User " + user + " does not exists");
            message.setSummary("Login Incorrect");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
           // throw new ValidatorException(message);
        }
    }
}
