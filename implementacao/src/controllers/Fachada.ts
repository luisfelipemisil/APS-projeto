import Animal from "../models/entities/Animal";

const ControladorUserClass = require('../models/ControladorUsuario')
const ControladorFilaCastClass = require('../models/ControladorFilaCastracao')

class Fachada {
    static pedidoAdocao(cpf: string, ficha: Animal) {

        let controladorFilaCastracao = new ControladorFilaCastClass();
    }

    static cadastrarUsuario(cpf: string, senha: string) {

        let controladorUsuario = new ControladorUserClass();

        controladorUsuario.cadastrarUsuario(cpf, senha);

    }
 
    static pedidoResgate(nome:string, especie:string, endereco:string, descricao:string, filhote: boolean, status: string, id:number, img:string) {

        let controladorFilaCastracao = new ControladorFilaCastClass();

        controladorFilaCastracao.addPedidoResgate(nome, especie, endereco, descricao, filhote, status,img)
    }
    

    static async autenticarUsuario(cpf: string, senha: string) {
        let controladorUsuario = new ControladorUserClass();

        return await controladorUsuario.autenticarUsuario(cpf, senha)
    }


}

module.exports = Fachada;