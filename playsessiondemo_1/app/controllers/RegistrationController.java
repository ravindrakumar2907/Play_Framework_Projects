package controllers;

import javax.inject.Inject;

import db.DBStore;
import model.Registration;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class RegistrationController extends Controller {


	FormFactory formFactory;
	
	
	@Inject
	public RegistrationController(FormFactory formFactory){
		this.formFactory = formFactory;
	}
	
    public Result showRegister(Http.Request request) {
        return ok(views.html.registration.render(""));
    }
    
    public Result register(Http.Request request) {
    	DynamicForm requestData = formFactory.form().bindFromRequest(request);
    	if(requestData != null){
    		String email = requestData.get("email");
    		if(email != null && email != ""){
    			Registration reg = new Registration();
    			String firstName = requestData.get("firstName");
    			String lastName = requestData.get("lastName");
    			String mobileNumber = requestData.get("mobileNumber");
    			String password = requestData.get("password");
    			
    			reg.setEmail(email);
    			reg.setFirstName(firstName);
    			reg.setLastName(lastName);
    			reg.setMobileNumber(mobileNumber);
    			reg.setPassword(password);
    			DBStore.setData(email, reg);
    			return ok("success");
    		}else{
    			return ok(views.html.registration.render("Please enter the all details"));
    		}
    		
    	}
    	
    	return ok(views.html.registration.render("Please enter the all details"));
    }

}


