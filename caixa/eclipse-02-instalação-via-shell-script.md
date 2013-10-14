---
layout: post-alpha
title: "Instalação do eclipse via shell script"
author: "Anderson Amorim Silva"
user: "asilva26"
date: 2013-10-14
published: true 
partof: eclipse
num: 2
outof: 2
---

##Introdução

Automatização de tarefas está cada vez mais comum, principalmente com o rápido avanço da tecnologia, no entanto parece que alguns processos continuam manuais, como por exemplo, fazer download do eclipse. Mas será que é possível automatizar um download? Sim!!! E através deste artigo você saberá como realizar o download da versão 4.3 do eclipse.

Para conseguir realizar os processos que serão descritos neste artigo é necessário conhecimento em:  
[Linux](http://www.vivaolinux.com.br/linux/)
[Shell Script](http://www.devin.com.br/shell_script/)  
[Vim](http://dojo.objectos.com.br/caixa/linux-00-vim.html)  

###Criando um shell script para fazer download

Através do shell script é possível fazer muitas coisas interessantes como modificar a extensão de um arquivo, ou organizar a biblioteca de músicas. Uma dessas muitas coisas é fazer download de arquivos de forma automática, sendo necessário seguir uma estrutura de criação, para que o sistema operacional consiga ler e interpretar o arquivo criado. Uma das vantagens do shell script é não precisar ser compilado para rodar, mas é necessário indicar que ele é um arquivo executável, e isto é feito através da seguinte instrução:

	#!/bin/bash

Geralmente arquivo shell script possui a extensão `.sh`, esta extensão indica que o arquivo em questão é executável, porém não é obrigatório colocá-la.
Em seguida são colocados dois comentários onde está escrito a versão do eclipse.

#version="kepler-upgrade-20131009"
#version="kepler-jee-SR1-20131009"

A próxima linha é uma das mais importantes, é a url pela qual o download será realizado.

server="http://www.eclipse.org/downloads/download.php?file=/technology/epp/downloads/release/kepler/SR1/eclipse-jee-kepler-SR1-linux-gtk-x86_64.tar.gz&mirror_id=576"

O `server` é o nome da variável criada, semelhante com outras linguagens de programação.

