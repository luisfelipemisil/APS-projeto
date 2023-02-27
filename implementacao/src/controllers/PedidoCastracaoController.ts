const fachadaPed = require('./Fachada')

class PedidoCastracaoController {

    constructor() {
        
    }

    pedidoCastracao(nome:string, especie:string, endereco:string, descricao:string, filhote: boolean, id:number, img:string){
        fachadaPed.pedidoResgate(nome, especie, endereco, descricao, filhote, "pedidoCastracao", img)
    }

}

module.exports = PedidoCastracaoController