# inspira-o-projeto-social
Inspiração para o futuro projeto de achados e perdidos que irei fazer 
# 🎒 Sistema de Achados e Perdidos Escolar
> **Componente Curricular:** Programação Orientada a Objetos (P.O.O)  
> **Instituição:** Escola Paulo Petrola  
> **Curso:** Técnico em Informática (TI)

Este sistema foi desenvolvido para solucionar um **desconforto** comum no ambiente escolar: a perda de pertences (casacos, chaves, materiais, documentos) e a falta de um canal centralizado e eficiente para a sua recuperação. A aplicação otimiza a gestão escolar permitindo o cadastro, a listagem e a baixa de itens encontrados.

---

## 📋 Alinhamento com os Requisitos da Avaliação (P.O.O 8.6)

O projeto cumpre rigorosamente todos os critérios estabelecidos em sala de aula (totalizando **10,0 pontos**):

1. **Uso de IA Generativa para Proposta de Projeto (2,0 pt):**
   * A inteligência artificial foi utilizada ativamente na fase de concepção e idealização do escopo do sistema. A proposta ataca diretamente um problema real da comunidade escolar (o desconforto do sumiço de objetos), transformando essa dor em um ecossistema lógico de classes.

2. **Repositório GitHub e Proposta no `main` (2,0 pt):**
   * O código fonte está integralmente hospedado no GitHub, contendo a estrutura de classes de forma organizada e este documento explicativo (`README.md`) detalhando a proposta do sistema e os **requisitos funcionais**.

3. **Herança (2,0 pt):**
   * Implementada na relação entre a classe mãe abstrata `Item` e suas respectivas subclasses especializadas: `ObjetoComum` e `Documento`. As subclasses herdam os atributos estruturais (como nome, local encontrado e data) e os métodos da classe base.

4. **Encapsulamento e Polimorfismo (2,0 pt):**
   * **Encapsulamento:** Todos os atributos da classe base `Item` são estritamente privados (`private`) e controlados de forma segura através de métodos acessores e modificadores (`getters` e `setters`).
   * **Polimorfismo:** A classe abstrata `Item` define o método `public abstract String getDetalhes();`. Cada subclasse sobrescreve (`@Override`) este método para exibir as informações específicas de sua natureza (ex: `ObjetoComum` exibe a cor, enquanto `Documento` exibe o nome do dono).

5. **Modo Gráfico com Swing (2,0 pt):**
   * A interação com o usuário é feita de forma visual, amigável e dinâmica utilizando a biblioteca gráfica `javax.swing` (através de `JOptionPane`), sem o uso de terminal texto para a navegação de menus.

6. **Robustez e Tratamento de Erros (2,0 pt):**
   * O sistema implementa uma exceção customizada chamada `SistemaException` (herdando de `Exception`). O código utiliza blocos `try-catch` para interceptar falhas críticas na interface gráfica, como tentativas de cadastrar campos em branco ou o fornecimento de índices inválidos e formatos incorretos, impedindo o fechamento inesperado (crash) do software.

7. **Conexão com Banco de Dados (Restrição):**
   * **NÃO** foi feita ligação com bancos de dados relacionais ou externos. Toda a persistência é tratada de forma limpa em memória através da coleção dinâmica `ArrayList<Item>`, simulando perfeitamente uma tabela de dados em tempo de execução.

---

## 🛠️ Requisitos Funcionais (RF)

* **RF-001 [Cadastrar Objeto Comum]:** O sistema deve permitir a inserção de objetos genéricos, capturando nome, local onde foi encontrado, data da operação e a cor do item.
* **RF-002 [Cadastrar Documento]:** O sistema deve permitir o registro de documentos perdidos, capturando o nome do item, local, data e o nome do titular/dono (se visível).
* **RF-003 [Listar Itens Disponíveis]:** O sistema deve exibir em modo gráfico uma listagem dinâmica contendo apenas os itens que ainda não foram recuperados pelos donos.
* **RF-004 [Dar Baixa / Devolução]:** O usuário deve conseguir marcar um item específico como "Retirado", removendo-o da fila de pendências visíveis após o dono legítimo resgatá-lo.
* **RF-005 [Validação de Dados]:** O sistema deve obrigar o preenchimento dos campos essenciais, disparando alertas visuais caso haja dados corrompidos ou nulos.

---

## 📂 Arquivos do Projeto

* `Item.java`: Classe abstrata que serve de molde e dita os atributos encapsulados do sistema.
* `ObjetoComum.java`: Subclasse que herda de `Item` e adiciona características de objetos físicos.
* `Documento.java`: Subclasse que herda de `Item` e mapeia identificações pessoais de estudantes.
* `SistemaException.java`: Classe responsável pela regra de tratamento de erros personalizada.
* `MainInterface.java`: Motor gráfico do sistema que desenha os menus Swing e gerencia o banco de dados em memória.
