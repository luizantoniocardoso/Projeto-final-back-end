# 📚 Projeto Final Back-End - Sistema de Gerenciamento de Livros 

## feito por: Gabriel Milano e Luiz Antonio de Souza Cardoso

Este projeto é uma API REST desenvolvida em **Java 17** com **Spring Boot**, integrada ao **Firebase Firestore** como banco de dados. A aplicação permite o cadastro e gerenciamento de bibliotecas, estantes de livros (listas) e livros obtidos automaticamente através da [BrasilAPI ISBN](https://brasilapi.com.br/).

---

## 🛠️ Tecnologias Utilizadas

- Java 17
- Spring Boot 3
- Firebase Firestore
- BrasilAPI (ISBN)
- Gradle
- Gson (para deserialização de dados externos)

---

## 📂 Funcionalidades

- 📚 **Cadastrar livros por ISBN**
- 🗂️ **Criar, editar e excluir listas de livros**
- 🏛️ **Gerenciar bibliotecas**
- ⏱️ **Listar registros inativos (última atualização há mais de 7 dias)**

---

## 🚀 Como rodar o projeto

### 1. Pré-requisitos

- Java 17 instalado
- Gradle instalado (ou usar o wrapper `./gradlew`)
- Firebase com Firestore ativado
- Um arquivo de credenciais do Firebase (JSON)
- criar uma conta no firebase, criar um projeto no firebase
- criar um banco de dados no projeto do firebase e adicionar a key de segurança no serviceAccountKey.json dentro de resources
---

### 2. Clonar o repositório

```bash
git clone https://github.com/luizantoniocardoso/Projeto-final-back-end.git
cd Projeto-final-back-end
