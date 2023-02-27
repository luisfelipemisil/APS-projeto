const fachadaAdoc = require('./Fachada')

class PedidoAdocaoController {

    constructor() {
        
    }

    pedidoAdocao(nomeCliente:String, cpf:string, descricaoPedido:string ,nome:string, especie:string, endereco:string, descricao:string, filhote: boolean, id:number, img:string){
        fachadaPed.pedidoAdocao(nomeCliente, cpf, descricaoPedido, nome, especie, endereco, descricao, filhote, "pedidoCastracao", img)
    }

}

module.exports = PedidoAdocaoController