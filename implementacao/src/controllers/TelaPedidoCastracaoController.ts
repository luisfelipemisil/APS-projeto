const fachadaPed = require('./Fachada')

class PedidoCastracaoController {

    pedidoCastracao(nome:string, especie:string, endereco:string, descricao:string, filhote: boolean, id:number, img:string){
        fachadaPed.pedidoResgate(nome, especie, endereco, descricao, filhote, "pedidoCastracao", img)
    }

}

module.exports = PedidoCastracaoController