import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;

public class ClienteHttp {
    
   public String buscaDados(String url){

    try {

        var endereco = URI.create(url); // Criando URI a partir da URL
        var client = HttpClient.newHttpClient(); // Criando um cliente
        var request = HttpRequest.newBuilder(endereco).GET().build(); // Criando um request do tipo GET
        var response = client.send(request, BodyHandlers.ofString()); // Enviando o request do tipo GET e recebendo um response
        String body = response.body(); // Guardando o response.body (o texto)
        return body;

    } catch(IOException  | InterruptedException ex){
        throw new RuntimeException(ex);
    }


   }
}
