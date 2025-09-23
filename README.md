💻 Projeto Java: Clone do Jogo "Termo"
Este é um projeto de um jogo estilo "Termo" (similar ao Wordle) desenvolvido em Java para ser executado no terminal. O jogo desafia o jogador a adivinhar uma palavra de 5 letras em 6 tentativas, fornecendo feedback colorido a cada palpite.

Além da lógica do jogo, o projeto inclui um sistema simples de cadastro e login de usuários, com pontuação persistente salva localmente.

✅ Funcionalidades
Sistema de Usuários:

Cadastro com nome de usuário e senha.

Login para acessar o jogo.

Os dados dos usuários e suas pontuações são salvos no arquivo data/users.json.

Lógica do Jogo:

Uma palavra secreta de 5 letras é escolhida aleatoriamente de uma lista.

O jogador tem 6 tentativas para adivinhar a palavra.

Feedback visual no terminal para cada tentativa:

VERDE: Letra correta na posição correta.

AMARELO: Letra correta na posição errada.

CINZA: Letra não existe na palavra.

Sistema de Pontuação:

Cada vitória concede 1 ponto ao jogador.

É possível visualizar um ranking com os 5 jogadores com as maiores pontuações.

📁 Estrutura do Projeto
O projeto está organizado da seguinte forma:

TermoGame/
│
├── src/
│   ├── Main.java         # Ponto de entrada e menu principal
│   ├── Game.java         # Lógica central do jogo
│   ├── User.java         # Classe modelo para o usuário
│   ├── UserManager.java  # Gerencia cadastro, login e pontuações
│   └── WordLoader.java   # Carrega as palavras do arquivo
│
├── data/
│   ├── users.json        # Armazena os dados dos usuários
│   └── palavras.txt      # Lista de palavras de 5 letras
│
└── README.md

🚀 Como Compilar e Executar
Pré-requisitos:

É necessário ter o JDK (Java Development Kit) instalado em sua máquina.

Compilação:

Abra o terminal na pasta raiz do projeto (TermoGame/).

Compile todos os arquivos .java do diretório src para a pasta bin (crie-a se não existir):

mkdir bin
javac -d bin src/*.java

Execução:

Ainda no terminal, na pasta raiz, execute a classe principal Main:

java -cp bin Main

O menu do jogo será exibido e você poderá começar a interagir.