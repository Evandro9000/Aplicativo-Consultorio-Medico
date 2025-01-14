# Aplicativo Consultorio Medico
 Este repositório contém o código-fonte de um aplicativo móvel desenvolvido em Java no Android Studio, voltado para simular o atendimento de um consultório médico. 

## Descrição  
Este é um aplicativo móvel desenvolvido em *Java* no *Android Studio, projetado para simular o atendimento em um consultório médico. Ele utiliza o **Firebase* para autenticação, agendamento de consultas e outras funcionalidades. O objetivo é oferecer uma solução prática e funcional para usuários, preservando a segurança dos dados pessoais e sensíveis.  

---

## Funcionalidades Principais  
- *Autenticação de Usuários*:  
  - Login e criação de contas via Firebase Authenticator.  
  - Segurança e praticidade utilizando e-mail e senha.  

- *Acesso a Revistas Médicos*:  
  - Redirecionamento para revistas e sites médicos especializados.  

- *Agendamento de Consultas*:  
  - Marque consultas com médicos específicos.  
  - Dados armazenados no Firebase Realtime Database para gerenciar horários em tempo real.  

- *Compra de Medicamentos*:  
  - Carrinho de compras funcional para adquirir medicamentos.  

---

## Configuração do Firebase  

*Nota importante*: Este projeto não inclui o arquivo real google-services.json para proteger dados sensíveis do Firebase. Para testar este aplicativo, siga os passos abaixo para configurar o Firebase:  

1. Crie um projeto no Firebase ([firebase.google.com](https://firebase.google.com)).  
2. Adicione seu aplicativo Android ao projeto.  
3. Baixe todas as dependências e bibliotecas necessárias para realizar a conexão com o Firebase (recomendo pesquisar no Youtube, tem vários canais ensinando)  
4. Configure as permissões e regras de segurança no Firebase para utilização do Autenticator para criação de uma conta/login e do Realtime Database para proteger os dados e criar suas tabelas.  

Exemplo de arquivo google-services.json (substitua pelos seus dados reais):  
```json
{
  "project_info": {
    "project_id": "exemplo-projeto",
    "firebase_url": "https://exemplo.firebaseio.com",
    "storage_bucket": "exemplo.appspot.com"
  },
  "client": [
    {
      "client_info": {
        "package_name": "com.exemplo.app"
      }
    }
  ]
}
Desenvolvendo seu APP e inciando a conexão com o Firebase via propriedades do Gradle e bibliotecas, esse arquivo será modificado, mas este é somente um exemplo de um arquivo básico que será criado.