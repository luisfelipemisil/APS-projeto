class Email {
    constructor(
      email: string
    ) {
        this.email = email;
    }
    email: string;

    validarEmail(){
        //validar email
        const regex : RegExp = /^[a-z0-9.]+@[a-z0-9]+\.[a-z]+\.([a-z]+)?$/i; 
        if(regex.test(this.email)){
            return true
        }else{
            return false
        }
    }
  }
  
  export default Email;