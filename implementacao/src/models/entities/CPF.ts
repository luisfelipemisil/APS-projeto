class CPF {
    constructor(
      cpf: string
    ) {
        this.cpf = cpf;
    }
    cpf: string;

    validarCPF(){
        //validar email
        const regex : RegExp = /^\d{3}\.\d{3}\.\d{3}\-\d{2}$/; 
        if(regex.test(this.cpf)){
            return true
        }else{
            return false
        }
    }
  }
  
  export default CPF;