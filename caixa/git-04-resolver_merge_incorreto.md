---
layout: post-alpha
title: "Resolvendo merge incorreto"
author: "Caio Catanzaro Petreanu"
published: true
partof: git
num: 4
---

## Introdução

Digamos que, por acidente, você pode acabar fazendo _merge_ ou _pull_ de uma _branch_ para outra completamente distinta.

E você acaba mandando um _Pull Request_ com essas atualizações. Bem, isso não pode ocorrer! De forma alguma essas atualizações podem ser aceitas e integradas no projeto original!

Se você já presenciou uma das situações abaixo, este artigo lhe ajudará!

- Fiz várias alterações erradas em um _commit_
- Fiz um _push_ de uma _feature_ pro lugar errado
- Fiz um _merge_ com uma _branch_ totalmente diferente da que estou
- Removi arquivos muito grandes, mas eles aparecem em _commits_ anteriores
- Fiz muitas alterações erradas em vários _commits_

Importante: Um _pull_ nada mais é que um _fetch_ e um _merge_ no mesmo comando.

## Fiz um merge em uma branch que eu não deveria, e agora?

As formas de solucionar esse problema são diversas e possuem características próprias, mas é importante fazer uma breve explicação delas para que fique claro quais são as mais adequadas em quais situações. Primeiro, vamos entender de __histórico__:<br> 

O histórico mantém registrado a sequência de todas as atualizações realizadas no projeto local e remoto ( _fork_ e original).

### 1ª Opção
Com o `git revert` é feito um novo _commit_ contendo as atualizações inversas aos _commits_ selecionados. E esse _commit_ ainda é reversível! Usá-lo, manterá todo o histórico anterior, mas em alguns casos pode não ser o que queremos.

Basicamente, ele serve para todos os casos em que manter os _commits_ anteriores no histórico não representam um problema.

### 2ª Opção
Nos demais casos podemos usar um dos 3 procedimentos/comandos: `git checkout`, `git reset --hard` e `git rebase -i`. Com eles, remontamos o histórico da _branch_, sendo necessário um `push --force` para ser mandado ao Github. Essas ações não podem ser revertidas e, por causa disso, devem ser usadas com muita cautela e em situações específicas!

Nota: Tudo que está no histórico é mantido. Imagine que toda vez que alguém for acessar, ou fazer _download_ do seu projeto e ter que "pegar" 800mb a mais. O que você acha disso?

É por isso que nesses casos devemos alterar o histórico, para remover presenças indesejáveis nos diretórios do projeto.

### Importante

Uma outra solução, porém, menos recomendada é refazer a _fork_ do projeto e recuperar os _backups_.

Por fim, esses procedimentos representam tarefas simples, mas devem ser usadas com extrema cautela! Afinal, agora estaremos lidando mais diretamente com o histórico.

<div id="revert"> </div>

## Merge incorreto com remotos distintos

Se estivermos trabalhando com repositórios remotos distintos conforme tratado no artigo [Procedimento de rebase com repositórios remotos distintos](http://dojo.objectos.com.br/caixa/git-07-procedimento-rebase-remoto.html)
e considerarmos que vários usuários estejam trabalhando em nosso projeto ao mesmo tempo, em algum 
momento poderemos nos deparar com o seguinte problema: um _merge_ pode ter sido realizado erroneamente
antes do processo de _rebase_.
  
Embora este tipo de erro seja pouco comum teremos problemas na próxima vez que fizermos o _rebase_ do projeto.
Isso ocorre porque ao deixarmos de fazer o _rebase_, as alterações de nosso branch não foram reaplicadas
e o nosso branch passou a apontar para um _commit_ diferente do _commit_ apontado pelo _master_. Desta forma,
nosso repositório passará a não possuir a mesma organização que o repositório remoto que adicionamos.

Podemos visualizar essas diferenças através do _github_, se compararmos o `gh-pages` de ambos repositórios
veremos que a ordem de _commits_ e _merges_ não é mesma.

Para resolver este problema, precisamos "apontar" nosso repositório para o mesmo _commit_ que está no
_master_ do repositório remoto que adicionamos anteriormente.

Para isso, verificamos no _github_ o número do último _commit_ do repositório remoto e guardamos esta
informação.  

Voltamos para o terminal e agora mudaremos para o master através do comando abaixo:

    $ git checkout master
  
Agora utilizamos o comando `git reset --hard` e em seguida colamos o número do _commit_, para que desta
forma nosso repositório aponte para o mesmo _commit_ que o repositório remoto:

    $ git reset --hard numerodocommit
	
Em seguida voltamos para nosso branch:

    $ git checkout meubranch_01
	
Agora executaremos o comando `git push origin` para enviar as alterações para nosso repositório remoto.
Colocaremos o sinal de adição "+" antes do nome do branch para forçar o comando:  
	 
    $ git push origin +meubranch_01 	
	
Se verificarmos no _github_, veremos que agora o último _commit_ de nosso repositório é o mesmo que o
do repositório remoto.
	
## Praticando: O primeiro cenário

1. Faça um novo repositório chamado __repo__ no Github;
2. Siga o procedimento do Github ( _Global setup_ e _Next steps_ ) para criar a _branch master_

3. Acesse o diretório em seu computador onde você criou o __repo__.

A partir da master crie uma branch chamada __letras__.

    $ git checkout master
    $ git checkout -b letras

Nela crie, adicione, faça _commit_ e _push_, para cada um dos 5 arquivos de texto abaixo

    $ touch A.txt
    $ git add A.txt 
    $ git commit -m "Add: A.txt"
    $ git push origin letras
     
    $ touch B.txt
    $ git add B.txt 
    $ git commit -m "Add: B.txt"
    $ git push origin letras
    
    $ touch C.txt
    $ git add C.txt 
    $ git commit -m "Add: C.txt"
    $ git push origin letras
    
    $ touch D.txt
    $ git add D.txt 
    $ git commit -m "Add: D.txt"
    $ git push origin letras
    
    $ touch E.txt
    $ git add E.txt 
    $ git commit -m "Add: E.txt"
    $ git push origin letras

Volte a _master_ e crie uma _branch_ chamada __numeros__, entre nela, e execute os comandos abaixo.

    $ git checkout master
    $ git checkout -b numeros

    $ touch 1.txt
    $ git add 1.txt 
    $ git commit -m "Add: 1.txt"
    $ git push origin numeros
     
    $ touch 2.txt
    $ git add 2.txt 
    $ git commit -m "Add: 2.txt"
    $ git push origin numeros
    
    $ touch 3.txt
    $ git add 3.txt 
    $ git commit -m "Add: 3.txt"
    $ git push origin numeros
    
    $ touch 4.txt
    $ git add 4.txt 
    $ git commit -m "Add: 4.txt"
    $ git push origin numeros
    
    $ touch 5.txt
    $ git add 5.txt 
    $ git commit -m "Add: 5.txt"
    $ git push origin numeros

Cheque no Github se todos os arquivos foram adicionados. Então volte para o terminal.

Crie a partir da _master_ 4 novas _branches_ usando os comandos:

    $ git checkout master
    $ git checkout -b cenario_revert
    $ git checkout -b cenario_procedimento_de_checkout
    $ git checkout -b cenario_reset
    $ git checkout -b cenario_rebase

### 1. O git revert

Como dito anteriormente, o _revert_:

* É feito a partir de um novo _commit_;

* Mantém o histórico anterior a esse _commit_;

* Pode reverter um ou mais _commits_;

* Pode ser revertido por outro _revert_.

Portanto, ele é indicado nos casos em que são poucos os arquivos indesejáveis, representando um histórico cujo tamanho não é significantemente grande a ponto de termos de alterá-lo ou removê-lo. 

Antes de continuarmos para a solução, por que não simularmos algo dando errado?

#### 1.1 Simulando um erro

Entre na _branch_ do tópico e faça um _merge_ dela com __numeros__.

    $ git checkout cenario_revert
    $ git merge numeros

Ok! Agora você terá os arquivos de textos do 1.txt ao 5.txt na sua _branch_. Faça um _push_.

    $ git push origin cenario_revert

Verifique no Github, se a _branch_  __cenario\_revert__ contém 5 arquivos.

Muito bem! Agora remova os arquivos pares, ou seja 2.txt e 4.txt. Crie também o 7.txt.

    $ rm 2.txt 4.txt
    $ touch 7.txt

Adicione o novo arquivo com _add_ e remova os dois pares com _rm_, depois um _commit_ e o _push_.

    $ git rm 2.txt
    $ git rm 4.txt
    $ git add 7.txt
    $ git commit -m "Arquivos pares"
    $ git push origin cenario_revert

Verifique no Github se sua branch está apenas com arquivos ímpares.

Agora, imagine que você não podia ter adcionado o arquivo 7.txt e muito menos removido o 4.txt. E agora, como reverter isso?

Importante: Sempre cheque seus arquivos na _branch_, e seus _Pull Requests_ com o __Diff__ na página do Github para identificar aquilo que foi mandado para _origin_, ou que está sendo mandado para o projeto original. Se não souber verificar o problema, de nada adianta saber solucioná-lo!

#### 1.2 Solução

Devemos encontrar o _commit_ incorreto e reverte-lo. Para isso você pode usar o Github. Se preferir use o ``git log`` assim:

    $ git log

Que retornará uma série de registros dos commits feitos, como estes:

    commit 51283b9dee534378c6dba77e12c7e0adfb29493e
    Author: Caio Petreanu <caio.petreanu@objectos.com.br>
    Date:   Mon Mar 19 19:05:40 2012 -0300
    
        Arquivos pares
    
    commit 0b31782b35e1c80ba03ae4ae9da2c96b63e7c6fc
    Author: Caio Petreanu <caio.petreanu@objectos.com.br>
    Date:   Mon Mar 19 18:24:10 2012 -0300
    
        Add: 5.txt
    
    commit e702e819ab3a3bc74b147c50e9fe87064996aee7
    Author: Caio Petreanu <caio.petreanu@objectos.com.br>
    Date:   Mon Mar 19 18:24:06 2012 -0300
    
        Add: 4.txt
    
    commit 773fa02ebf3248c95e0f7c4d64560062b57052ad
    Author: Caio Petreanu <caio.petreanu@objectos.com.br>
    Date:   Mon Mar 19 18:24:03 2012 -0300
    
        Add: 3.txt
    
    commit 7e17f4dde6f43bd99d1b7abacf2a97f0846c263f
    Author: Caio Petreanu <caio.petreanu@objectos.com.br>
    Date:   Mon Mar 19 18:23:59 2012 -0300
    
        Add: 2.txt
    
    commit 6987e2c1f584f40f4db1d79750879c8373858dc6
    Author: Caio Petreanu <caio.petreanu@objectos.com.br>
    Date:   Mon Mar 19 18:23:55 2012 -0300
    
        Add: 1.txt
    
    commit 426bca1b80fd19e22d5f3fb31f49b3f15698142f
    Author: Caio Petreanu <caio.petreanu@objectos.com.br>
    Date:   Fri Mar 16 17:42:32 2012 -0300
    
        first commit

No caso de você ter feito muitos _logs_ após o _commit_ errôneo, tente usar o `git log -10` para listar os 10 últimos _commits_ (ou o número que desejar).

Usando o Github ou olhando com calma o _log_, podemos ver que o _commit_ que precisamos reverter é o "Arquivos pares".

Muito bem. Copie o hash do _commit_ que neste caso é o primeiro `0b31782b35e1c80ba03ae4ae9da2c96b63e7c6fc` e aperte a tecla __Q__ para sair dessa tela.

Agora faça o _revert_ duas vezes, _commit_ e _push_ 

    $ git revert 0b31782b35e1c80ba03ae4ae9da2c96b63e7c6fc
    $ git revert 0b31782b35e1c80ba03ae4ae9da2c96b63e7c6fc
    $ git commit -m "reverter commit"
    $ git push origin cenario_revert

Você verá que há no histórico um novo commit que reverte as atualizações daquele que foi selecionado. 

    commit e22f46c3fb58bc2f382cdba5babd9b94fb300f29
    Author: Caio Petreanu <caio.petreanu@objectos.com.br>
    Date:   Mon Mar 19 19:11:31 2012 -0300
    
        Revert "Arquivos pares"
        
        This reverts commit 51283b9dee534378c6dba77e12c7e0adfb29493e.
        
            copied:     7.txt -> 2.txt
            renamed:    7.txt -> 4.txt

Simples, não? Mas e se precisarmos reverter um _merge_?

#### 1.3 Resolvendo um merge indesejado com revert

Faça um _merge_ "acidental" com letras. Por acidental, quero dizer que esse merge não poderia ter sido feito, e muito menos mandado para a origin.

Vamos usar o que aprendemos para resolver isso:

    $ git log -10

Você porderá ver que apareceu um novo commit.

    commit 37687b9de11e7f12dd0ba20f02ac90e219498b08
    Merge: e22f46c 36277bb
    Author: Caio Petreanu <caio.petreanu@objectos.com.br>
    Date:   Mon Mar 19 19:15:50 2012 -0300
    
        Merge branch 'letras' into cenario_revert

Vamos reverte-lo

    git revert 37687b9de11e7f12dd0ba20f02ac90e219498b08

Não funcionou né? Pois é, sabemos que um merge local deixa as duas branches envolvidas exatamente iguais. Independentemente de qual branch você está agora, ao reverter um merge deve ser informada para qual branch devemos voltar.

O que, nesse caso são __e22f46c__ (vinda da cenario_revert e representada por 1) e __36277bb__ (vinda da letras e representada por 2).

Em caso de dúvida, cheque o log. Fica fácil ver que o commit anterior na branch em que estamos é o 1 (e22f46c3fb58bc2f382cdba5babd9b94fb300f29).

Agora usamos a opção ``git revert <commit> -m <parent>`` para informar que o revert do merge deverá fazer com que voltem as alterações para o commit 1, proveniente da branch cenario_revert.

    $ git revert 37687b9de11e7f12dd0ba20f02ac90e219498b08 -m 1
    $ git revert 37687b9de11e7f12dd0ba20f02ac90e219498b08 -m 1
    $ git commit -m "reverter merge"
    $ git push origin cenario_revert

## Praticando: O segundo cenário

Entre na branch __cenario_procedimento_de_checkout__ e execute os comandos ``git remote`` e ``git pull gh-pages`` abaixo. Ah, e não se esqueça de fazer. um push para origin disso!

    $ git checkout cenario_procedimento_de_checkout
    $ git remote add objectos https://github.com/objectos/objectos-dojo.git
    $ git pull objectos gh-pages
    $ git push origin cenario_procedimento_de_checkout

Aparecerão todos os arquivos de _gh-pages_ para sua _branch_ local e remota. Veja no Github a quantidade de arquivos que entraram no seu projeto com esse _commit_.

E o log?

    $ git log

Parece que ele está cheio de registros tanto em __cenario_procedimento_de_checkout__ quanto em __gh-pages__. Olhe o seu _commit_ gerado pelo _pull_ também, ele deve estar assim:

    commit 280fde94001ab2b8f89276c6a076d1e3f66ab019
    Merge: 426bca1 a5f651f
    Author: Caio Petreanu <caio.petreanu@objectos.com.br>
    Date:   Tue Mar 20 14:17:40 2012 -0300

    Merge branch 'gh-pages' of https://github.com/objectos/objectos-dojo into cenario_procedimento_de_checkout

Bom, como todo bom leitor você não pulou o tópico de `git revert` e me sugere: 

> "Podemos dar um revert que volte as alterações desses que estão todos resolvidos!"

Muito bem! Concordo que é uma possibilidade. Mas, você não concorda que todos esses arquivos que vieram continuarão mantidos no __histórico__?

O revert resolve? Com certeza! Mas devemos usá-lo sabendo que toda vez que for feito _merge_, _pull_ ou _clone_ do projeto (ou _fork_ ) será baixado o _gh-pages_ inteiro? Não mesmo!!!

Antes de avançar, faça um _merge_ , _push_ das _branches_ __cenario_reset__ e __cenario_rebase__ com a que estamos.

    $ git checkout cenario_reset
    $ git merge cenario_procedimento_de_checkout
    $ git push origin cenario_reset
    
    $ git checkout cenario_rebase
    $ git merge cenario_procedimento_de_checkout
    $ git push origin cenario_rebase

### 1. O procedimento do git checkout -b

Entre na _branch_ __cenario_procedimento_de_checkout__ e se preferir, use o `git log`.

    $ git checkout cenario_procedimento_de_checkout

Como podemos ver ao montar o cenário, o _merge_ feito com gh-pages ocorreu no commit `280fde94001ab2b8f89276c6a076d1e3f66ab019`. Mas não é ele que usaremos para reverter o histórico.

Precisamos do último commit válido antes do _merge_. E, como _gh-pages_ veio com vários outros commits, esse commit não poderá ter vindo dela.

Então consulte o Github e/ou o _log_ e encontre esse _commit_. Seguindo o conceito de que o histórico é sequencial (como as pilhas de estrutura de dados), e com um pouco de memória, não será tão difícil assim.

No nosso caso, trabalharemos o nosso primeiro commit.

    commit 426bca1b80fd19e22d5f3fb31f49b3f15698142f
    Author: Caio Petreanu <caio.petreanu@objectos.com.br>
    Date:   Fri Mar 16 17:42:32 2012 -0300
    
        first commit

 Lembre-se: A identificação do seu provavelmente será diferente.

Faça o `checkout -b` para uma _branch_ nova chamada __bkp__ pelo _commit_:

    $ git checkout -b bkp 426bca1b80fd19e22d5f3fb31f49b3f15698142f
    Switched to a new branch 'bkp'

Remova a _branch_ __cenario_procedimento_de_checkout__ e renomeie __bkp__ para __cenario\_procedimento\_de\_checkout__.

    $ git branch -D cenario_procedimento_de_checkout
    $ git branch -M cenario_procedimento_de_checkout

Pelo visto a _branch_ __cenario_procedimento_de_checkout__ já não é mais a mesma... ela é, digamos, o _commit_ "first commit".

Mas pra que isso? Se você quiser descobrir, faça esse comando:

    $ git push origin cenario_procedimento_de_checkout
    To git@github.com:cpetreanu/repo.git
     ! [rejected]        cenario_procedimento_de_checkout -> cenario_procedimento_de_checkout (non-fast-forward)
    error: failed to push some refs to 'git@github.com:cpetreanu/repo.git'
    To prevent you from losing history, non-fast-forward updates were rejected
    Merge the remote changes (e.g. 'git pull') before pushing again.  See the
    'Note about fast-forwards' section of 'git push --help' for details.

O que houve aí?!

> To prevent you from losing history, non-fast-forward updates were rejected

Estamos recebendo uma alerta, pelo visto, é porque estamos pra alterar o histórico. E agora?

__Force seu push__, ignorando o aviso:

    $ git push origin cenario_procedimento_de_checkout --force
    Total 0 (delta 0), reused 0 (delta 0)
    To git@github.com:cpetreanu/repo.git
     + 280fde9...426bca1 cenario_procedimento_de_checkout -> cenario_procedimento_de_checkout (forced update)

Veja: __(forced update)__. Ah, mas será que funcionou? Vá ao Github e depois volte.

Funcionou! veja como ficou todo o git _log_:

    commit 426bca1b80fd19e22d5f3fb31f49b3f15698142f
    Author: Caio Petreanu <caio.petreanu@objectos.com.br>
    Date:   Fri Mar 16 17:42:32 2012 -0300
    
        first commit

Você entrou em um _commit_ (de preferência o último válido) e substituiu a _branch_ "corrompida" por ele. Por fim, realizarou um ``push --force`` para a origem, revertendo todo o histórico para o estado desse _commit_.

### 2. O git reset --hard

Entre na branch __cenario_reset__.

    $ git checkout cenario_reset

Ao invés de fazer o procedimento de checkout do capítulo anterior, existe a opção de executar todo ele em apenas 2 comandos!
O ``git reset --hard`` e o ``git push --force``.

Sabemos já que o último _commit_ válido é `426bca1b80fd19e22d5f3fb31f49b3f15698142f`. Use o comando abaixo para que ele se 
torne o `HEAD` (último _commit_ ) da sua _branch_.

    $ git reset --hard 426bca1b80fd19e22d5f3fb31f49b3f15698142f
    HEAD is now at 426bca1 first commit

A opção `--hard` faz com que todos os arquivos no _stage_, que ainda não foram para um _commit_, sejam apagados. Em contrapartida, 
existe a opção `--soft`.

Feita a alteração do `HEAD`, faça o _push_ para _origin_. Claro que você só conseguirá isso usando o argumento `--force`.

    $ git push --force origin cenario_reset
    Total 0 (delta 0), reused 0 (delta 0)
    To git@github.com:cpetreanu/repo.git
     + 280fde9...426bca1 cenario_reset -> cenario_reset (forced update)

Feito isso, verifique no Github.

Nesse caso, você apontou o _commit_ HEAD de sua _branch_ para o último _commit_ válido antes do _merge_, e mandou essa alteração de
histórico para a origem remota. Pode-se concluir que trata-se uma solução análoga à de checkout.

### 3. O git rebase -i

Entre na sua branch __cenario_rebase__.

Novamente estaremos no mesmo cenário. Faça `ls -l` e confira se quiser!

Temos o cenário e o _commit_ `426bca1b80fd19e22d5f3fb31f49b3f15698142f`. Vamos usar o _rebase_ no modo interativo ( __-i__ , que abre um editor de texto) e ver as atualizações de todos os _commits_ entre "first commit" e o `HEAD` (cenario_rebase).

    $ git rebase -i 426bca1b80fd19e22d5f3fb31f49b3f15698142f cenario_rebase

Note os _commits_ vindos de _gh-pages_. Aqui podemos modificar aqueles que serão mantidos ou removidos do histórico, __remontando-o__.

Apague todos os _commits_ menos um (não importa qual seja). Salve e feche o arquivo. Como podemos ver, o _rebase_ parou por causa de conflitos de _path_. Faça um `git status`.

    $ git checkout cenario_rebase
    error: could not apply b82ad6a... Finalizado objetos falsos
    hint: after resolving the conflicts, mark the corrected paths
    hint: with 'git add <paths>' and run 'git rebase --continue'
    Could not apply b82ad6a... Finalizado objetos falsos
    
    $ git status
    # Not currently on any branch.
    # Unmerged paths:
    #   (use "git reset HEAD <file>..." to unstage)
    #   (use "git add/rm <file>..." as appropriate to mark resolution)
    #
    #	deleted by us:      procedimento/crud-entidade/00.2-criando-objetos-falsos.md
    #
    no changes added to commit (use "git add" and/or "git commit -a")

Existe um _path_ incorreto/inexistente para o arquivo mostrado. Para resolver isso vamos remover essa atualização, e por fim dar continuidade ao _rebase_.

    $ git checkout -f
    
    $ git status
    # Not currently on any branch.
    nothing to commit (working directory clean)
    
    $ git rebase --continue
    Successfully rebased and updated refs/heads/cenario_rebase.

Faça um ``push --force`` e vejá no Github os resultados. E o _log_ também só para ter certeza!

    $ git push origin cenario_rebase

Pronto! Trabalho realizado com sucesso!

Assim, você consegue reverter o _merge_ (e histórico) ao remover todos os _commits_ (menos 1), entre o "first commit" e o `HEAD` da _branch_ com o _merge_ e _gh-pages_. Na sequência removeu as alterações desse _commit_, fazendo com que o único remanescente fosse o `426bca1b80fd19e22d5f3fb31f49b3f15698142f`.

### 4. Refazer a fork?

Em alguns casos, quando seu trabalho na branch acabou de começar, existem poucos arquivos no projeto original ou existem uma série de erros na execução dos comandos anteriores, uma opção simples é refazer a _fork_ do projeto original.

Para isso, você pode seguir os seguintes passos:

* Faça cuidadosamente o backup de todos os arquivos importantes que você criou ou modificou

* Remova a pasta do projeto da sua máquina, usando um comando como:

       $ rm -f -R /tmp/repo/

* Vá no repositório do _fork_ __repo__ em sua página.

* Clique no botão "Admin", na parte superior à direita da página

* Clique no botão "Delete this repository" ... e confirme a remoção do _fork_ clicando em "I understand, delete this repository"

* Acesse a página do projeto original e faça novamente um _fork_ e clone dele (se não lembrar como fazer clique [aqui][1])

* Use os comandos:

        mkdir /tmp/repo/
        cd /tmp/repo

Se necessário configure a url do projeto original usando

    $git remote add <alias-da-url> <url-do-projeto-original>

Seu _fork_ estará idêntico a última versão do projeto.

* Entre na _branch_ correta e recupere os _backups_ feitos.

* Pronto, você acaba de desfazer os comandos errados usando uma das formas mais árduas (tirando os possíveis erros nos comandos citados nas outras seções).

## Referências

[objectos-dojo :: Tutorial gh-pages][1]

[Git Book - Undoing in Git][2]

[0]: #referencias "Referências"
[1]: ../contribua/00-importar.html "objectos-dojo :: Tutorial gh-pages"
[2]: http://book.git-scm.com/4_undoing_in_git_-_reset,_checkout_and_revert.html "Git Book - Undoing in Git - Reset, Checkout and Revert"