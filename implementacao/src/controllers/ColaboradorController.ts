const fachadaCol = require('./Fachada')

class ColaboradorController {

    constructor() {
        
    }

    cadastrarColaborador(cpf: string, senha: string, nome: string, email: string) {
        fachadaVet.cadastrarColaborador(cpf, senha, nome, email)
    }

    async autenticarColaborador(cpf: string, senha: string, nome: string, email: string) {
        return await fachadaVet.autenticarColaborador(cpf, senha, nome, email);
    }

}

module.exports = ColaboradorController