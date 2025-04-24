import java.util.Random;
//colocando as variaveis necessarias para o inimigo
public class Inimigo {
    String nome;
    int vida;
    int ataque;
    int defesa;
    int recompensaExperiencia;
//criando construtor de inimigo
    public Inimigo(String nome, int vida, int ataque, int defesa, int recompensaExperiencia) {
        this.nome = nome;
        this.vida = vida;
        this.ataque = ataque;
        this.defesa = defesa;
        this.recompensaExperiencia = recompensaExperiencia;
    }
//calculando o dano que o inimigo irá causar no jogador
    public void atacar(Jogador jogador) {
        int dano = Math.max(0, this.ataque - jogador.defesa + new Random().nextInt(4));
        jogador.receberDano(dano);
    }
}
