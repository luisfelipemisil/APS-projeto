import Email from "./email";
import CPF from "./CPF";
class Colaborador {
    
    constructor(
      cpf: CPF,
      nome: string,
      senha:string,
      email: Email
    ) {
        this.cpf = cpf;
        this.senha = senha;
        this.nome = nome;
        this.email = email;
    }
  
    cpf: CPF;
    senha: string;
    nome: string;
    email: Email;
  }
  
  export default Colaborador;