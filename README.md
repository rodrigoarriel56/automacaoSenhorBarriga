# Teste - Seu Barriga


## Caro Candidato

Queremos conhecer um pouco mais sobre como é seu processo de desenvolvimento, por isso, temos o seguinte desafio que faz parte do nosso processo de seleção. 


## Descrição

Somos uma startup do ramo financeiro e precisamos testar nossa aplicação web, nos proporcionando qualidade, eficiência e performance.

>>>
`CT1: Cadastro de novo usuário`
* Step 1 – Acessar o site: https://srbarriga.herokuapp.com/cadastro
* Step 2 – Na aba "Novo usuário" inserir um nome, e-mail e senha e realizar o cadastro.
* Step 3 – Validar texto "Usuário inserido com sucesso". [Print da Tela]
>>>

>>>
`CT2: Acesso a conta`
* Step 1 – Acessar aba Login e inserir e-mail cadastrado anteriormente.
* Step 2 – Inserir a senha cadastrada anteriormente.
* Step 3 – Clicar em "Entrar" e validar o texto "Bem vindo, provas!". [Print da Tela]
* Step 4 – Clicar em Sair.
>>>

>>>
`CT3: Cadastro de usuário` (Teste negativo).
* Step 1 - Na aba "Novo usuário" inserir um nome, e-mail e senha e realizar o cadastro. (cadastrado anteriormente).
* Step 2 – Validar texto "Endereço de e-mail já utilizado". [Print da Tela]
>>>

>>>
`CT4: Cadastro de conta`
* Step 1 – Acessar aba "Login", inserir e-mail e senha cadastrado anteriormente e clicar no botão "Entrar".
* Step 2 – Clicar no menu "Contas" e após em "Adicionar".
* Step 3 – Inserir um valor no campo "Nome" e clicar no botão "Salvar".
* Step 4 – Validar texto "Conta adicionada com sucesso!". [Print da Tela]
* Step 5 - Comparar os dados cadastrados com Equals.
* Step 6 - Fechamento do WebDriver [Chrome].
>>>



#### Métricas de Avaliação

1. Indentação do código.
    * Organização do código. (Declaração das variáveis)
    * Variáveis seguindo a ação do site. Exemplo: Método de cadastro de conta chamado: cadConta.
    * Comentários. (Os comentários conforme a função de cada método).

2. Lógica aplicada 
    * Estrutura das classes, métodos.
    * Cada classe terá a ação correspondente da sua tela.

3. Criar três classes.
    * Os nomes das classes serão: Login, Menu e Cadastro.

4. Script funcional
    * A automação seguindo os passos determinados na métrica de avaliação (1, 2, 3 e 5).

5. Coleta de evidências
    * Fechamento do WebDriver (Chrome)
    * Coleta das evidências para o cadastro com sucesso e conta já existente, (Teste negativo).


                
### Forma de avaliação
Cada tópico de métricas de avaliação, terá o peso de 2 na nota. 

>>> 
`Exemplo:` Com apenas um tópico correto a nota será dois, 
estando corretamente dois tópicos a nota será quatro e assim por diante, 
podendo chegar ao total de 10 pontos.
>>>

| Métrica |  Peso  |
| ------- | :----: |
| 1       | 20     |
| 2       | 20     |
| 3       | 20     |
| 4       | 20     |
| 5       | 20     |
| Total   | 100    |

