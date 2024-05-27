# Projeto_Jogo_Java

Este é um simples jogo de labirinto desenvolvido em Java. Abaixo estão algumas informações sobre a estrutura do código e suas funcionalidades principais.

# Autores

Maria Clara C. Soares
GitHub: https://github.com/mclaracsoares?tab=repositories

Lucas Gurgel

Marco Antônio Arcoverde

Thiago Araújo

Pedro Filipe Macedo

## Arquivos Principais:

### Main.java:

Este é o arquivo principal que contém o método main que inicia o jogo.
Ele cria uma janela JFrame e adiciona o painel do jogo (GamePanel) a ela.
Inicializa o jogo e inicia a thread do jogo.

### GamePanel.java:

Este arquivo representa o painel do jogo onde a maioria da lógica do jogo é implementada.
Define a interface gráfica do jogo e seus elementos visuais.
Gerencia a atualização e renderização do jogo.
Contém a lógica para controle de entrada do teclado.
Instancia objetos do jogo, como o aventureiro e os objetos do cenário.

### AssetSetter.java:

Este arquivo é responsável por configurar os objetos do jogo, como chaves, portas e baús.
CollisionChecker.java:

Este arquivo lida com a detecção de colisão entre o jogador e os objetos do jogo, bem como os limites do mapa.
InputTeclado.java:

Gerencia as entradas do teclado do jogador.

### UI.java:

Controla a interface do usuário, incluindo a exibição de mensagens e elementos gráficos como chaves.

## Funcionalidades Principais:

O jogador pode mover-se pelo labirinto usando as teclas W (cima), A (esquerda), S (baixo) e D (direita).

O jogo detecta colisões entre o jogador e os objetos do cenário, como paredes e portas.

O jogador pode coletar chaves e abrir portas com elas.

Quando o jogador alcança o objetivo, uma mensagem de parabéns é exibida.

OBS: Há um Easter Egg no jogo...

## Como Executar o Jogo:

Certifique-se de ter o Java Development Kit (JDK) instalado em sua máquina.

Compile todos os arquivos .java.

Execute o arquivo Main.java usando o comando java para iniciar o jogo.

## Notas Adicionais:

Este jogo é um projeto educacional simples e pode ser expandido com mais funcionalidades e melhorias na jogabilidade, gráficos e som.

Os arquivos de imagem usados no jogo devem ser armazenados no diretório apropriado para que o jogo funcione corretamente.

Sinta-se à vontade para explorar e modificar o código conforme necessário para atender às suas necessidades ou para aprender mais sobre programação em Java e desenvolvimento de jogos. Divirta-se jogando! 🎮🚀
