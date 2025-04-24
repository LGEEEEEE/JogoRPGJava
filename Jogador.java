import java.util.Random;
import java.util.ArrayList;
//definindo as variaveis
public class Jogador {
    String nome;
    int nivel = 1;
    int vida = 100;
    int ataque = 10;
    int defesa = 5;
    int experiencia = 0;
    ArrayList<Magia> magias = new ArrayList<>();
//criando construtor do jogador
    public Jogador(String nome) {
        this.nome = nome;
        aprenderMagia(new Magia("Hidrogênio Verde", "Explosão limpa e poderosa", 25, 2));
        aprenderMagia(new Magia("Chuva Revitalizante", "Recupera vida e refresca o planeta", -20, 3));
    }
//calculando o dano que o jogador irá causar no inimigo e escrevendo isso no terminal
    public void atacar(Inimigo inimigo) {
        int dano = Math.max(0, this.ataque - inimigo.defesa + new Random().nextInt(6));
        inimigo.vida -= dano;
        System.out.println(this.nome + " atacou " + inimigo.nome + " causando " + dano + " de dano!");
    }
//calculando também o dano recebido pelo inimigo e tambem sendo retransmitido no terminal
    public void receberDano(int dano) {
        this.vida -= dano;
        System.out.println(this.nome + " recebeu " + dano + " de dano!");
    }
//subindo o nível do personagem que foi criado e aprendendo magias novas como raizes protetoras e luz solar concentrada
    public void subirDeNivel() {
        nivel++;
        vida += 20;
        ataque += 5;
        defesa += 3;
        System.out.println("⚔️ " + nome + " subiu para o nível " + nivel + "!");
        if (nivel == 2) aprenderMagia(new Magia("Raízes Protetoras", "Aumenta sua defesa", 0, 2));
        if (nivel == 3) aprenderMagia(new Magia("Luz Solar Concentrada", "Ataque poderoso contra inimigos das trevas", 40, 3));
    }
//se o nível do personagem coincidir com o nível necessário para se adquirir uma nova magia, o código irá printar no terminal
    public void aprenderMagia(Magia magia) {
        if (nivel >= magia.nivelMinimo) {
            magias.add(magia);
            System.out.println("🔮 Nova magia aprendida: " + magia.nome + "!");
        }
    }
//usa magia recebendo cura e defesa pelas raízes protetoras
    public void usarMagia(int indice, Inimigo inimigo) {
        if (indice >= 0 && indice < magias.size()) {
            Magia magia = magias.get(indice);
            if (magia.poder > 0) {
                magia.usar(inimigo);
            } else {
                int cura = -magia.poder;
                this.vida += cura;
                System.out.println("💧 Você usou " + magia.nome + " e se curou em " + cura + " de vida!");
            }
            //caso o nivel não seja compativel ou use uma magia não especificadaa anteriormaente o código irá printar
        } else {
            System.out.println("❌ Magia inválida.");
        }
    }
//ao selecionar a aba de magias o terminal irá mostrar as magias que estão disponiveis para serem utiilizadas e suas descrições
    public void mostrarMagias() {
        System.out.println("🔮 Suas magias:");
        for (int i = 0; i < magias.size(); i++) {
            System.out.println(i + " - " + magias.get(i).nome + " (" + magias.get(i).descricao + ")");
        }
    }
}
