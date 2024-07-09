# WsWorkJava

<h1> Controle de Carros</h1>

<p>Esse projeto tem como teste, o desenvolvimento de uma
api para controle carros, modelos e marca. As explicações serão dadas a seguir.
Lembrando que todos os métodos são usados as anotações do 
spring para melhor documentar na pagina principal do Swagger
(http://localhost:8080/swagger-ui/index.html) com o SpringDoc.</p>

<h1><strong>Carro Controller</strong></h1>
<h4>Find All </h4>
<p>Para devolver uma response personalizada, conforme requistio obrigatório,
faço a requisição para o service, que me devolve um carro do tipo CarroVO (utilizei
como padrão de projeto o Value Object), faço a busca, atribuo
separadamente os modelos e valores de carro, e devolvo pro controller. Com
isso, crio um map (response) e adiciono dentre dele, o resultado do meu FindAll, para 
poder atender ao requisito solicitado de padrão Json a ser devolvido</p>

<h4>Find By Id </h4>
<p>Busco no service algum carro, converto para CarroDTO (nesse padrão, o VO que deve se comunicar
com o "Front", nunca o objeto em si, tentei fazer isso). e devovlo o carroDTO para a requisição.</p>


<h4>create</h4>
<p>Envio um CarroDTO para o serviço, faço a conversão para Carro (para poder me comunicar com o banco)
, verifico se existe algum modelo com o ID passado, pois devbe ser obrigatório existir, caso tudo certo,
faço a inserção no banco e retorno para o usuário</p>


<h4>update</h4>
<p>Envio via url, o ID a ser atualizado (conforme boas práticas sugeridas no site do Spring), encaminho o Jso para o service (não
me preocupei em configurar par XML e/ou Yaml), busco o carro a ser atualizado, verifico se o novo modelo 
inserido nessa atualização existe, então atualizo os dados que serão atualizados e salvo eles.
</p>

<h4>Delete</h4>
<p>Encaminho o ID para fazer a exclusão do carro, busco por ele e deleto.
</p>

<h1>Observações</h1>
<p>Como são parecidos os controllers de Modelo, Carro e Marca, colocarei os pontos principais apenas de cada um,.</p>


<h1><strong>Modelo Controller</strong></h1>
<h4>Delete</h4>
<p>Para o delete, eu não poderei deletar se houver algum carro relacionado com o modelo, 
pois isso implica, no meu entendimento, que há informações úteis que possam ser sensíveis a serem deletadas. O correto,
nesse modelo, é apagar todos os carros com o ID do modelo em questão, e então deletar o modelo.</p>

<h1><strong>Marca Controller</strong></h1>
<h4>Sem observações</h4>
