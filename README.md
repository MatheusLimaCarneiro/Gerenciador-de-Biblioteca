<h1>Gerenciador de Biblioteca</h1>
<p>Este é um projeto de um sistema de gerenciamento de biblioteca, que permite controlar empréstimos de livros e manter o histórico de empréstimos dos usuários. O sistema verifica se os livros estão disponíveis, gerencia devoluções e consulta os empréstimos ativos de cada usuário.</p>

<h2>Funcionalidades</h2>
<ul>
    <li>Realizar empréstimos de livros para usuários.</li>
    <li>Controlar a disponibilidade de livros.</li>
    <li>Registrar e verificar o histórico de empréstimos de cada usuário.</li>
    <li>Consultar livros disponíveis para empréstimo.</li>
    <li>Consultar empréstimos ativos por usuário.</li>
    <li>Gerenciar devoluções e atualizar status dos empréstimos.</li>
</ul>

<h2>Tecnologias Utilizadas</h2>
<ul>
    <li><strong>Java:</strong> Linguagem de programação principal do projeto.</li>
    <li><strong>JUnit e Mockito:</strong> Ferramentas de teste unitário e criação de mocks para simular objetos e serviços.</li>
</ul>

<h2>Pré-requisitos</h2>
<ul>
    <li>Java 8 ou superior</li>
    <li>Maven para gerenciar dependências</li>
</ul>

<h2>Como Clonar o Projeto</h2>
<p>Clone o repositório:</p>
<pre><code>git clone https://github.com/seu-usuario/gerenciador-biblioteca.git</code></pre>
<p>Navegue até o diretório do projeto:</p>
<pre><code>cd gerenciador-biblioteca</code></pre>
<p>Compile e execute os testes para verificar se está tudo correto:</p>
<pre><code>mvn clean install</code></pre>

<h2>Exemplo de Uso</h2>
<p>O projeto contém um arquivo <code>Main.java</code> que simula operações na biblioteca:</p>
<ul>
    <li>Criar Livro e Usuário: Um novo livro e um usuário são cadastrados no sistema.</li>
    <li>Realizar Empréstimo: O sistema verifica a disponibilidade do livro e realiza o empréstimo.</li>
    <li>Consultar Empréstimos Ativos: É possível consultar os empréstimos ativos de um usuário.</li>
    <li>Devolução de Livro: Após a devolução, o status do livro é atualizado para disponível.</li>
</ul>
<p>A lógica principal está na classe <code>BibliotecaService</code>, que possui métodos para realizar empréstimos, devolver livros, buscar livros disponíveis e consultar empréstimos ativos.</p>

<h2>Estrutura do Projeto</h2>
<p>O projeto está organizado da seguinte forma:</p>
<ul>
    <li><strong>model:</strong> Contém as classes principais de domínio, como Livro, Usuario, Emprestimo e Status.</li>
    <li><strong>repository:</strong> Contém as interfaces e classes de repositório em memória que gerenciam os dados.</li>
    <li><strong>service:</strong> A classe BibliotecaService implementa a lógica de negócio para gerenciar os empréstimos e consultas.</li>
    <li><strong>exception:</strong> Define exceções personalizadas, como BibliotecaException, para o tratamento de erros.</li>
</ul>
