const fabricaRepositorioBDR = require('../models/repositorio/FabricaRepositorioBDR')
import ColaboradorCollection from '../models/collections/ColaboradorCollection';
import FilaCastracao from '../models/collections/FilaCastracao';
import Animal from "../models/entities/Animal";
const ControladorVeterinario = require('./ControladorVeterinario')

class ControladorFilaCastracao {

    filaCastracao: FilaCastracao

    constructor() {
        this.filaCastracao = new FilaCastracao;
    }

    addPedidoResgate (nome:string, especie:string, endereco:string, descricao:string, filhote: boolean, status: string, img:string) {

        const animal = new Animal(nome, especie, endereco, descricao, filhote, status, 0, img);

        animal.id = this.filaCastracao.storeAnimal(animal)

        const controlerVeterinario = new ControladorVeterinario();

        controlerVeterinario.sendEmail("pedido avaliação animal id: " + String(animal.id) )
        // falta implementar o resto aqui
    
    }

    sendEmail(texto: string){
        const colaboradorCollection = new ColaboradorCollection();
        var listCol = colaboradorCollection.listColaboradores();
        listCol.forEach(element => {
            console.log(element.email.email)
        });
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