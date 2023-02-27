const fabricaRepositorioBDR = require('../models/repositorio/FabricaRepositorioBDR')
import FilaCastracao from './collections/FilaCastracao';
import Animal from "../models/entities/Animal";


class ControladorFilaCastracao {

    filaCastracao: FilaCastracao
    colaborador =  new ColaboradorController;
    
    constructor() {
        this.filaCastracao = new FilaCastracao;
    }

    addPedidoResgate (nome:string, especie:string, endereco:string, descricao:string, filhote: boolean, status: string, img:string) {

        const animal = new Animal(nome, especie, endereco, descricao, filhote, status, 0, img);

        animal.id = this.filaCastracao.storeAnimal(animal)
    
    }


    addPedidoAdocao (nomeCliente:string, cpf:string, descricaoPedido:string ,nome:string, especie:string, endereco:string, descricao:string, filhote: boolean, status: string, img:string) {

        const animal = new Animal(nome, especie, endereco, descricao, filhote, status, 0, img);

        animal.id = this.filaCastracao.storeAnimal(animal)
    
    }

    async listarAnimais() {
        return await this.filaCastracao.listAniamais()
    }

    async deletarAnimal(id: number) {
        return await this.filaCastracao.deletarAnimais(id)
    }

    async updateAnimal(id: number, name: string, status: string) {
        return await this.filaCastracao.updateAnimal(id, name, status)
    }
}

module.exports = ControladorFilaCastracao