//import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
//import java.util.Properties;


public class App {
    public static void main(String[] args) throws Exception {

        //Properties props = new Properties();
        //FileInputStream file = new FileInputStream("alura-stickers/src/properties/dados.properties");
        //props.load(file);


        //String url = "https://imdb-api.com/en/API/Top250Movies/"+props.getProperty("prop.apikey"); // Variável que contém a URL com API key

        // String url = "https://mocki.io/v1/9a7c1ca9-29b4-4eb3-8306-1adb9d159060";
        // ExtratorDeConteudo extrator =  new ExtratorDeConteudoDoIMDB();

        // String url = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2022-06-12&end_date=2022-06-14";
        // ExtratorDeConteudo extrator =  new ExtratorDeConteudoDaNasa();

        String url = "http://languages-alura-api.herokuapp.com/linguagens";
        ExtratorDeConteudo extrator = new ExtratorDeConteudoDoIMDB();

        // Fazer uma conexão HTTP e buscar os top 250 filmes (forma que iremos nos comunicar com a API)

        var http = new ClienteHttp();
        String json = http.buscaDados(url);

        /* Usamos um parser de "expressão regular", porém também poderiamos usar uma lib pra fazer o parse, para facilitar, como o Jackson */ 

        // Exibir e manipular os dados 
        List<Conteudo> conteudos = extrator.extraiConteudos(json);

        var geradora = new GeradoraDeFigurinhas();

        for (int i = 0; i<2; i++) { // percorre os 10 primeiros filmes

            Conteudo conteudo = conteudos.get(i); 

            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();

            String nomeArquivo = "saida/" +  conteudo.getTitulo() + ".png";

            geradora.cria(inputStream, nomeArquivo);

            System.out.println(conteudo.getTitulo());
            System.out.println();

        }

    }
}
