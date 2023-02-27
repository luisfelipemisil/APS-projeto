const fabricaRepositorioBDR = require('../models/repositorio/FabricaRepositorioBDR')
import FilaCastracao from './collections/FilaCastracao';
import Animal from "../models/entities/Animal";


class ControladorFilaCastracao {

    filaCastracao: FilaCastracao

    constructor() {
        this.filaCastracao = new FilaCastracao;
    }

    addPedidoResgate (nome:string, especie:string, endereco:string, descricao:string, filhote: boolean, status: string, img:string): number {

        const animal = new Animal(nome, especie, endereco, descricao, filhote, status, 0, img);

        this.filaCastracao.storeAnimal(animal)
    }

    async listarAnimais() {
        return await this.filaCastracao.listAniamis()
    }

    async deletarPrato(id: number) {
        return await this.filaCastracao.deletarAnimais(id)
    }

    async updatePrato(id: number, name: string, status: string) {
        return await this.filaCastracao.updateAnimal(id, name, status)
    }
}

module.exports = ControladorFilaCastracao