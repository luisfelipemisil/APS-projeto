
const ControladorUserClass = require('./ControladorUsuario')
const ControladorVeterinario = require('./ControladorVeterinario')
const ControladorFilaCastClass = require('./ControladorFilaCastracao')

class Fachada {

    static cadastrarUsuario(cpf: string, senha: string, nome: string, email: string) {

        let controladorUsuario = new ControladorUserClass();

        controladorUsuario.cadastrarUsuario(cpf, senha, nome, email);

    }

    static async autenticarUsuario(cpf: string, senha: string, nome: string, email: string) {
        let controladorUsuario = new ControladorUserClass();

        return await controladorUsuario.autenticarUsuario(cpf, senha, nome, email)
    }
 
    static cadastrarVeterinario(cpf: string, senha: string, nome: string, email: string) {

        let controladorUsuario = new ControladorVeterinario();

        controladorUsuario.cadastrarUsuario(cpf, senha, nome, email);

    }

    static async autenticarVeterinario(cpf: string, senha: string, nome: string, email: string) {
        let controladorUsuario = new ControladorVeterinario();

        return await controladorUsuario.autenticarUsuario(cpf, senha, nome, email)
    }

    static pedidoResgate(nome:string, especie:string, endereco:string, descricao:string, filhote: boolean, status: string, id:number, img:string) {

        let controladorFilaCastracao = new ControladorFilaCastClass();

        controladorFilaCastracao.addPedidoResgate(nome, especie, endereco, descricao, filhote, status, img)
    }


    static pedidoAdocao(nomeCliente:string, cpf:string, descricaoPedido:string ,nome:string, especie:string, endereco:string, descricao:string, filhote: boolean, status: string, id:number, img:string) {

        let controladorFilaCastracao = new ControladorFilaCastClass();

        controladorFilaCastracao.addPedidoAdocao(nomeCliente, cpf, descricaoPedido, nome, especie, endereco, descricao, filhote, status, img)
    }

}

module.exports = Fachada;