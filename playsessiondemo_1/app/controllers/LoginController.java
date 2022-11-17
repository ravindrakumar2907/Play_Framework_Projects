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
public class LoginController extends Controller {

	//@Inject
	FormFactory formFactory;
	
	
	@Inject
	public LoginController(FormFactory formFactory){
		this.formFactory = formFactory;
	}
	
    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result login(Http.Request request) {
    	//Form<User> userForm = formFactory.form(User.class);
        return ok(views.html.login.render(""));
    }

    public Result loginSubmit(Http.Request request){
    	String email = request.queryString("email").get();
    	String password = request.queryString("password").get();
    	DynamicForm requestData = formFactory.form().bindFromRequest(request);
    	if(isNoneBlank(email) && isNoneBlank(password)){
    		Registration reg = null;
    		reg = DBStore.getData(email);
    		if(isNoneBlank(reg)){
    			if(password.equals(reg.getPassword())){
    				request.session().adding("email", email);
    				request.session().adding("name", reg.getFirstName());
    				return redirect("/home").addingToSession(request, "name", reg.getFirstName()).
    						addingToSession(request, "email", email);
    			}
    		}
    	}
    	//return ok("Hello " + email + " " + password);
    	return ok(views.html.login.render("Username or password is not valid"));
    }
    
    
    public static boolean isNoneBlank(String value){
    	if(value != null && value != ""){
    		return true;
    	}
    	return false;
    }
    
    public static boolean isNoneBlank(Object value){
    	if(value != null){
    		return true;
    	}
    	return false;
    }
    
    public Result loginSubmit1(Http.Request request){
    	request.queryString("email");
    	request.queryString("password");
    	DynamicForm requestData = formFactory.form().bindFromRequest(request);
    	
    	String firstname = requestData.get("email");
    	String lastname = requestData.get("password");
    	return ok("Hello " + firstname + " " + lastname);
    }
}
