const FabricaRepositorioBDRClass = require('../repositorio/FabricaRepositorioBDR')

import Colaborador from "../entities/Colaborador";

class ColaboradorCollection {

  colaboradores: Colaborador[] = [];

    fabricaBDR : typeof FabricaRepositorioBDRClass;
    colaboradorBDR: typeof ColaboradorBDR;

  constructor() {
    this.fabricaBDR = new FabricaRepositorioBDRClass()
    this.colaboradorBDR = this.fabricaBDR.criarRepositorioUsuario()
  }

  storeColaborador( colaborador:Colaborador ) {
    this.colaboradorBDR.storeColaborador(colaborador);
    this.colaboradores.push(colaborador)
  }

  async authenticateColaborador(colaborador:Colaborador) {
    return await this.colaboradorBDR.authenticateColaborador(colaborador)
  }

  getColaborador(nome:string) {
    return this.colaboradores.find( element => element.nome == nome );
  }

  listColaboradores(): Colaborador[]{
    return this.colaboradores
  }

}

export default ColaboradorCollection