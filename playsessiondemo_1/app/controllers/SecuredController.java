package controllers;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import play.mvc.Http.Request;
import play.mvc.Result;

public class SecuredController extends play.mvc.Action.Simple{

	@Override
	public CompletionStage<Result> call(Request request) {
		
		Optional<String> name = null;
    	name = request.session().get("name");
		
		//return CompletableFuture.completedFuture(ok("done"));;
		CompletionStage<Result> result = delegate.call(request);
		return result;
	}

}
