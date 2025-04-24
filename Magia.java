//definindo as variaveis de magia
public class Magia {
    String nome;
    String descricao;
    int poder; // Pode ser negativo para cura
    int nivelMinimo;
//criando construtor da magia
    public Magia(String nome, String descricao, int poder, int nivelMinimo) {
        this.nome = nome;
        this.descricao = descricao;
        this.poder = poder;
        this.nivelMinimo = nivelMinimo;
    }
//ao selecionar a magia de luz concentrada, o código vai subtrair a vida do inimigo em 40pts
    public void usar(Inimigo inimigo) {
        inimigo.vida -= poder;
        System.out.println("✨ " + nome + " atingiu " + inimigo.nome + " causando " + poder + " de dano!");
    }
}
