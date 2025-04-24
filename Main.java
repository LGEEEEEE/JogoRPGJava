import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
//apresenta o jogo
        System.out.println("🌱 Bem-vindo ao RPG Verde: A Jornada Pela Terra!");
        System.out.print("Digite o nome do seu herói: ");
        //lê o nome que o jogador colocar no terminal EX: Jefferson Rodrigues
        String nomeJogador = entrada.nextLine();
//cria novo jogador
        Jogador jogador = new Jogador(nomeJogador);
        //seleciona o nome do jogador definido anteriormente com scanner
        System.out.println("Olá, " + jogador.nome + "! A Terra precisa da sua ajuda!");

        //criando os inimigos e definindo seus atributos
        Inimigo[] inimigos = {
                new Inimigo("Poluidor Urbano", 40, 8, 2, 20),
                new Inimigo("Goblin do Petróleo", 45, 9, 3, 25),
                new Inimigo("Slime Químico", 50, 10, 3, 30),
                new Inimigo("Desmatador", 60, 11, 4, 35),
                new Inimigo("Caçador Ilegal", 80, 12, 5, 50)
        };

        //após isso, o teminal irá apresentar a opção de ir entre 2 caminhos, toda vez após que derrotar um inimigo essa
        // mensagem também irá ser mostrada até que o ultimo seja vencido
        for (int i = 0; i < inimigos.length; i++) {
            System.out.println("\n🧭 Você chegou a uma bifurcação. Qual caminho deseja seguir?");
            System.out.println("1 - Caminho da Floresta (batalha comum)");
            System.out.println("2 - Caminho da Indústria (batalha mais difícil, mais XP)");
//escolhe o inimigo com base na escolha do usuario
            int caminho = entrada.nextInt();
            Inimigo inimigoEscolhido;
//cria um inimigo mais forte para aumentar a dificuldade do usuario
            if (caminho == 2) {
                inimigoEscolhido = new Inimigo("Máquina Poluente", 70, 14, 6, 60);
            } else {
                inimigoEscolhido = inimigos[i];
            }

            System.out.println("\n⚠️ Um novo inimigo apareceu: " + inimigoEscolhido.nome + "!");
//cria um laço que proporciona o combate estilo rpg, em que a luta não acaba até que o laço seja completado
            while (inimigoEscolhido.vida > 0 && jogador.vida > 0) {
                System.out.println("\nSeu HP: " + jogador.vida + " | HP do inimigo: " + inimigoEscolhido.vida);
                System.out.println("Digite '1' para atacar ou '2' para usar magia:");
                int escolha = entrada.nextInt();
//permite ao jogador escolher que tipo de ataque quer usar, mas com limitações pelo nível
                if (escolha == 1) {
                    jogador.atacar(inimigoEscolhido);
                } else if (escolha == 2) {
                    jogador.mostrarMagias();
                    System.out.print("Escolha a magia: ");
                    int indice = entrada.nextInt();
                    jogador.usarMagia(indice, inimigoEscolhido);
                }
//caso derrote o inimigo, o código te encaminhará ao próximo
                if (inimigoEscolhido.vida > 0) {
                    inimigoEscolhido.atacar(jogador);
                }
            }
//em caso de derrota, o console apresentará a meensagem declarada entre parenteses
            if (jogador.vida <= 0) {
                System.out.println("💀 Você foi derrotado. A natureza perdeu...");
                return;
            }
//juntamente com essa mensagem de próximo inimigo, o console apresentará as recompensas adquiridas pelo usuario
// após derrotar o inimigo, curando ele sempre em 30% de sua vida atual e ganhando XP(experiencia para subir de nivel
// e conquistar novas magias)
            System.out.println("✅ " + inimigoEscolhido.nome + " derrotado!");
            int cura = (int)(jogador.vida * 0.3);
            jogador.vida += cura;
            System.out.println("🩹 Você se curou em " + cura + " de vida. Vida atual: " + jogador.vida);

            jogador.experiencia += inimigoEscolhido.recompensaExperiencia;

            if (jogador.experiencia >= jogador.nivel * 30) {
                jogador.subirDeNivel();
                jogador.experiencia = 0;
            }
        }

//quando o laço terminar para todos os inimigos, o console irá apresnetar a mensagem de que você zerou o jogo
// e salvou o planeta
        System.out.println("\n🌎 Parabéns, " + jogador.nome + "! Você salvou o planeta!");
        entrada.close();
    }
}