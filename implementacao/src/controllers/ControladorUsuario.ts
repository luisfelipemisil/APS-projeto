import UsuarioCollection from '../models/collections/UsuarioCollection';
import User from '../models/entities/Usuario';
import Email from '../models/entities/Email';
import CPF from '../models/entities/CPF';


class ControladorUsuario {
    cadastrarUsuario(cpf: string, senha: string, nome:string, email:string) {
        const emailObj = new Email(email)
        const cpfObj = new CPF(cpf)
        if(emailObj.validarEmail() && cpfObj.validarCPF()){
            const usuario = new User(cpfObj, nome, senha, emailObj);
            const usuarioCollection = new UsuarioCollection();
            usuarioCollection.storeUser(usuario)
        }

    }

    async autenticarUsuario(cpf: string, senha: string, nome:string, email:string) {
        const emailObj = new Email(email)
        const cpfObj = new CPF(cpf)
        if(emailObj.validarEmail() && cpfObj.validarCPF()){
            const usuario = new User(cpfObj, nome, senha, emailObj);
            const usuarioCollection = new UsuarioCollection();
            const res = await usuarioCollection.authenticateUser(usuario);
            return res === "found user" // success or not XD
        }
    }


}

export default ControladorUsuario