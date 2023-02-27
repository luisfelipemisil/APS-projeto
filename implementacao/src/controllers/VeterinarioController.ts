const fachadaVet = require('./Fachada')

class VeterinarioController {

    constructor() {
        
    }

    cadastrarVeterinario(cpf: string, senha: string, nome: string, email: string) {
        fachadaVet.cadastrarVeterinario(cpf, senha, nome, email)
    }

    async autenticarVeterinario(cpf: string, senha: string, nome: string, email: string) {
        return await fachadaVet.autenticarVeterinario(cpf, senha, nome, email);
    }

}

module.exports = VeterinarioController