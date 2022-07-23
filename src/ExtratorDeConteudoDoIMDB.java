import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtratorDeConteudoDoIMDB implements ExtratorDeConteudo {
    public List<Conteudo> extraiConteudos(String json){
        // Extrair só os dados que interessam (titulo, poster, classificação) (parse)
        var parser = new JsonParser();

        List<Map<String, String>> listaDeAtributos = parser.parse(json); // É feito um parse no body e salvo os elementos (chave: valor)

        List<Conteudo> conteudos = new ArrayList<>();

        // popular a lista de conteúdos

        for (Map<String, String> atributos : listaDeAtributos) {
            String titulo = atributos.get("title");
            String urlImagem = atributos.get("image").replaceAll("(@+)(.*).jpg$", "$1.jpg");;
            var conteudo = new Conteudo(titulo, urlImagem);

            conteudos.add(conteudo);
        }

        return conteudos;
    }
}
