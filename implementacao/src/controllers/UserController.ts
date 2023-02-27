const fachadaUser = require('./Fachada')

class UserController {

    constructor() {
        
    }

    cadastrarUsuario(cpf: string, senha: string, nome: string, email: string) {
        fachadaUser.cadastrarUsuario(cpf, senha, nome, email)
    }

    async autenticarUsuario(cpf: string, senha: string, nome: string, email: string) {
        return await fachadaUser.autenticarUsuario(cpf, senha, nome, email);
    }

}

module.exports = UserController