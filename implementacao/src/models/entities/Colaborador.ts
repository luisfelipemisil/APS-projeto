class Colaborador {
    constructor(
      cpf: string,
      nome: string,
      email: string,
      senha:string
    ) {
        this.cpf = cpf;
        this.senha = senha;
        this.nome = nome;
        this.email = email;
    }
  
    cpf: string;
    senha: string;
    nome: string;
    email: string;
  }
  
  export default Colaborador;