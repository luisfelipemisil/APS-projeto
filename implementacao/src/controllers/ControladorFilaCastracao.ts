const fabricaRepositorioBDR = require('../models/repositorio/FabricaRepositorioBDR')
import FilaCastracao from '../models/collections/FilaCastracao';
import Animal from "../models/entities/Animal";
import ControladorVeterinario from './ControladorVeterinario';
import ControladorColaborador from './ControladorColaborador';
import { Observable, Observer } from '../observer/observer';
import Veterinario from '../models/entities/Veterinario';

class ControladorFilaCastracao implements Observer {

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

    async listarAnimais() {
        return await this.filaCastracao.listAniamais()
    }

    async deletarAnimal(id: number) {
        return await this.filaCastracao.deletarAnimais(id)
    }

    async updateAnimal(id: number, name: string, status: string) {
        return await this.filaCastracao.updateAnimal(id, name, status)
    }

    update(observable: Observable): void {
        if (observable instanceof Veterinario) {
            const controladorColaborador = new ControladorColaborador();
            controladorColaborador.sendEmail("Animal pronto para busca: " + String());
        }
    }
}

export default ControladorFilaCastracao