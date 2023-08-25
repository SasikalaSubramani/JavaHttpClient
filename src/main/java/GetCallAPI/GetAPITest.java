package GetCallAPI;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class GetAPITest {
	
	public static void main(String[] args) {
	
	try {
		HttpRequest httpRequest = 	HttpRequest.newBuilder()
					.uri(new URI("https://reqres.in/api/users?page=2"))
					.GET()
					.build();
		
		
		HttpResponse<String> httpResponse = 
											HttpClient
													.newHttpClient()
													.send(httpRequest, HttpResponse.BodyHandlers.ofString());
		System.out.println(httpResponse.statusCode());
		System.out.println(httpResponse.body());
		
		
	} catch (URISyntaxException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	}

}
