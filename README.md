# 💻 Projeto Java: Clone do Jogo "Termo"

Este é um projeto de um jogo estilo **"Termo"** (similar ao **Wordle**) desenvolvido em **Java** para ser executado no terminal.  
O jogo desafia o jogador a adivinhar uma **palavra de 5 letras em 6 tentativas**, fornecendo feedback colorido a cada palpite.

Além da lógica do jogo, o projeto inclui um **sistema simples de cadastro e login de usuários**, com **pontuação persistente salva localmente**.

---

## ✅ Funcionalidades

### 🔐 Sistema de Usuários:
- Cadastro com nome de usuário e senha.
- Login para acessar o jogo.
- Dados dos usuários e suas pontuações são salvos no arquivo `data/users.json`.

### 🎮 Lógica do Jogo:
- Uma palavra secreta de 5 letras é escolhida aleatoriamente de uma lista.
- O jogador tem 6 tentativas para adivinhar a palavra.
- Feedback visual no terminal para cada tentativa:
  - 🟩 **VERDE**: Letra correta na posição correta.
  - 🟨 **AMARELO**: Letra correta na posição errada.
  - ⬜ **CINZA**: Letra não existe na palavra.

### 🏆 Sistema de Pontuação:
- Cada vitória concede **1 ponto** ao jogador.
- Visualização de um **ranking** com os **5 jogadores com maiores pontuações**.

---

## 📁 Estrutura do Projeto

