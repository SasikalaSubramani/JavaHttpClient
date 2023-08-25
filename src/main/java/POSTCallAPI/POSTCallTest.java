package POSTCallAPI;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class POSTCallTest {

	public static void main(String[] args) {

		// pojo
		User user = new User("Madhuri", "SDET");
		// pojo to json: serialization: use jackson api
		ObjectMapper mapper = new ObjectMapper();
		String requestJsonBody = null;
		try {
			requestJsonBody = mapper.writeValueAsString(user);
		} catch (JsonProcessingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			HttpRequest httpRequest = HttpRequest.newBuilder().uri(new URI("https://reqres.in/api/users"))
					.headers("Content-Type", "application/json")
					.POST(HttpRequest.BodyPublishers.ofString(requestJsonBody)).build();

			HttpResponse<String> httpResponse = HttpClient.newHttpClient().send(httpRequest,
					HttpResponse.BodyHandlers.ofString());

			System.out.println(httpResponse.statusCode());
			System.out.println(httpResponse.body());

//			Deserialization: JSON TO POJO :with jackson
			User userRes = mapper.readValue(httpResponse.body(), User.class);
			System.out.println(userRes.getName() + ":" + userRes.getJob());
			System.out.println(userRes.getId() + ":" + userRes.getCreatedAt());

			if (userRes.getName().equals(user.getName())) {
				System.out.println("user name is same");
			}

			// GET CALL:

			try {
				HttpRequest httpGetRequest = HttpRequest.newBuilder()
						.uri(new URI("https://reqres.in/api/users" + userRes.getId())).GET().build();

				HttpResponse<String> httpGETResponse = HttpClient.newHttpClient().send(httpGetRequest,
						HttpResponse.BodyHandlers.ofString());
				System.out.println(httpGETResponse.statusCode());
				System.out.println(httpGETResponse.body());

			} catch (URISyntaxException e) {

				e.printStackTrace();
			} catch (IOException e) {

				e.printStackTrace();
			} catch (InterruptedException e) {

				e.printStackTrace();
			}

		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		} catch (InterruptedException e) {

			e.printStackTrace();
		}

	}

}
