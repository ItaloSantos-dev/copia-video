# 🎥 Copia Vídeo

Projeto desenvolvido com o objetivo de **centralizar e validar ideias de vídeos** a partir de um **nicho escolhido pelo usuário**, utilizando dados reais da **API do YouTube** para identificar conteúdos com **alto potencial de alcance** (mais visualizações e lançados recentemente).

A aplicação permite que cada usuário organize suas próprias ideias de forma segura, estruturada e baseada em métricas reais.

---

## 🚀 Tecnologias Utilizadas

### 🔹 Back-end
- **Java 17+**
- **Spring Boot**
- **Spring Security + JWT**
- **Spring Data JPA**
- **PostgreSQL**
- **API do YouTube Data v3**

### 🔹 Front-end
- **Angular**
- **TypeScript**
- **Reactive Forms**
- **Signals**
- **Consumo de API REST**
- **Embed de vídeos do YouTube**

### 🔹 Banco de Dados
- **PostgreSQL**

---

## 🧠 Ideia do Projeto

O projeto foi pensado para resolver um problema comum na criação de conteúdo:

> *“Como encontrar ideias de vídeos validadas, dentro de um nicho específico, usando dados reais?”*

A aplicação permite:
- Escolher um **nicho**
- Buscar vídeos **recentes** e com **alto número de visualizações**
- Salvar ideias de vídeos para uso futuro
- Centralizar todas as ideias em um único lugar
- Garantir que cada usuário visualize **apenas seus próprios dados**

---

## 🔐 Segurança

- Autenticação baseada em **JWT**
- Endpoints protegidos por **Spring Security**
- Cada recurso (ideia/vídeo) é vinculado a um **usuário**
- Validação de acesso para garantir que o usuário só possa acessar seus próprios dados

---

## 📌 Funcionalidades

- Cadastro e autenticação de usuários
- Busca de vídeos via **YouTube API**
- Filtro por nicho
- Salvamento de ideias de vídeos
- Visualização e edição de ideias
- Exclusão de ideias
- Embed de vídeos diretamente na aplicação
- Controle de acesso por usuário

---

## 🗄️ Modelagem (Resumo)

- **User**
  - id
  - name
  - email
  - password
- **Idea
  - id
  - title
  - videoId

Relacionamento:
- **User 1:N Idea**
