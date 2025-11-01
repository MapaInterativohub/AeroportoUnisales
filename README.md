🧭 README — Sistema Aeroporto 
🛫 Descrição Geral

O Sistema Aeroporto é uma aplicação desenvolvida em Java (Swing) para gerenciar voos, passagens e processos de check-in em um ambiente de simulação de aeroporto.
Agora o sistema possui dois modos de acesso:

👤 Modo Cliente: permite visualizar voos e comprar passagens.

🛠️ Modo Admin: permite cadastrar voos, aprovar passagens e realizar check-ins.

Ambos os modos compartilham os mesmos dados, permitindo que ações feitas no modo Admin apareçam em tempo real para o Cliente.

🧱 Estrutura do Projeto
com.aeroporto/
│
├── Dados/
│   ├── Colors.java
│   └── Dados.java
│
├── Passagens/
│   ├── Passageiro.java
│   ├── AdicionarPassagem.java
│   ├── AprovarPassagem.java
│   └── CheckIn.java
│
├── Voos/
│   ├── Voo.java
│   └── AdicionarVoo.java
│
├── PainelPrincipal.java
├── PainelPrincipalCliente.java
├── PainelPrincipalAdmin.java
├── TelaInicial.java
└── Main.java

💻 Tecnologias Utilizadas

Linguagem: Java 17+

Interface gráfica: Swing (JFrame, JPanel, JButton, JLabel, etc.)

Estruturas de dados: ArrayList, LinkedList, Stack

IDE recomendada: IntelliJ IDEA / VS Code / Eclipse

Gerenciamento: Maven / Manual (sem dependências externas)

🚀 Novas Funcionalidades
🪟 Tela Inicial (TelaInicial.java)

Exibe dois botões: Cliente e Admin.

Ao clicar, abre a respectiva versão do painel principal.

Mantém instâncias compartilhadas de dados (Dados) para sincronização em tempo real.

Inclui voos iniciais de exemplo, simulando dados do sistema.

👤 Painel do Cliente (PainelPrincipalCliente.java)

Mostra o mesmo layout visual.

Exibe apenas o botão “Comprar Passagem”.

Permite visualizar voos e disponibilidade.

🛠️ Painel do Admin (PainelPrincipalAdmin.java)

Exibe todos os botões administrativos, exceto “Comprar Passagem”.

Permite adicionar voos, aprovar passagens e realizar check-ins.

Atualiza automaticamente os dados visíveis ao cliente.

🔁 Painel Principal (PainelPrincipal.java)

Responsável por montar o layout base.

Inclui botão “Voltar” para retornar à tela inicial (sem fechar as outras janelas).

As janelas não são mais fechadas automaticamente, permitindo que Cliente e Admin funcionem simultaneamente.

⚙️ Fluxo de Funcionamento
flowchart TD
A[Tela Inicial] --> B[Modo Cliente]
A --> C[Modo Admin]
C --> D[Adicionar Voo]
C --> E[Aprovar Passagem]
C --> F[Check-In]
D & E & F --> G[Voos Atualizados]
G --> B


O Admin cadastra voos e gerencia passagens.

O Cliente compra passagens e visualiza os voos disponíveis.

Ambos compartilham os mesmos dados (Dados voos, Dados checkIn).

🎨 Cores e Estilo (classe Colors)
Cor	Função
Azul	Fundo principal e topo da interface
Verde	Voo com assentos disponíveis
Laranja	Voo quase cheio
Vermelho	Voo cheio / erro
Cinza	Área administrativa e seções neutras
▶️ Execução
Pré-requisitos

Java 17+

IDE com suporte a Swing

Estrutura de pacotes conforme acima

Como Executar

Compile todas as classes.

Execute Main.java.

Escolha o modo desejado:

Cliente → para visualizar voos e comprar passagens.

Admin → para cadastrar voos e gerenciar o sistema.

👩‍💻 Autores

Desenvolvido por:
Ismailer Gregorio, Phablo, Cleber, Amanda Cesário

📘 Projeto acadêmico — Java Swing e Estruturas de Dados.