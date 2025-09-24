# 💻 Projeto Java: Clone do Jogo "Termo"

Este é um projeto de um jogo estilo **"Termo"** (similar ao **Wordle**) desenvolvido em **Java** com uma interface gráfica utilizando a biblioteca **Swing**.

O jogo desafia o jogador a adivinhar uma **palavra de 5 letras em 6 tentativas**, fornecendo feedback colorido a cada palpite diretamente na janela do jogo.

-----

## ✅ Funcionalidades

### 🎮 Lógica do Jogo:

- Uma palavra secreta de 5 letras é escolhida aleatoriamente de um arquivo de lista de palavras (`data/palavras.txt`).
- O jogador tem 6 tentativas para adivinhar a palavra.
- A interface gráfica é atualizada a cada tentativa com feedback visual:
  - 🟩 **VERDE**: Letra correta na posição correta.
  - 🟨 **AMARELO**: Letra correta na posição errada.
  - ⬛ **CINZA ESCURO**: A letra não existe na palavra.
- O jogador pode iniciar um novo jogo a qualquer momento após o fim de uma partida.

-----

## 🚀 Como Executar o Projeto

Para compilar e executar este projeto, você precisará ter o **JDK (Java Development Kit)** instalado em sua máquina.

### 1. Compilação

Abra o terminal ou prompt de comando na pasta raiz do projeto (`TermoGame`) e execute o seguinte comando para compilar os arquivos `.java`:

```bash
javac -d . src/*.java
```

*Este comando compilará todos os arquivos-fonte da pasta `src` e colocará os arquivos `.class` gerados na raiz do projeto.*

### 2. Execução

Após a compilação, execute o jogo com o seguinte comando:

```bash
java -cp src Main
```

*Este comando iniciará a aplicação, e a janela do jogo deverá aparecer.*

-----

## 📁 Estrutura do Projeto

- `src/Main.java`: Ponto de entrada da aplicação. Inicia a interface gráfica do jogo.
- `src/GameGUI.java`: Contém toda a lógica da interface gráfica e o fluxo do jogo (tabuleiro, entrada de texto, feedback de cores).
- `src/WordLoader.java`: Classe responsável por carregar as palavras do arquivo `palavras.txt`.
- `data/palavras.txt`: Arquivo de texto contendo a lista de palavras válidas para o jogo.
