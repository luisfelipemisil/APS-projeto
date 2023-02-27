const FabricaRepositorioBDRClass = require('../repositorio/FabricaRepositorioBDR')

import Veterinario from "../entities/Veterinario";

class VeterinarioCollection {
  veterinarios: Array<Veterinario> = [];
    fabricaBDR : typeof FabricaRepositorioBDRClass;
    veterinarioBDR: typeof VeterinarioBDR;

  constructor() {
    this.fabricaBDR = new FabricaRepositorioBDRClass()
    this.veterinarioBDR = this.fabricaBDR.criarRepositorioUsuario()
  }

  storeVeterinario( veterinario:Veterinario ) {
    this.veterinarioBDR.storeVeterinario(veterinario);
    this.veterinarios.push(veterinario)
  }

  async authenticateVeterinario(veterinario: Veterinario) {
    return await this.veterinarioBDR.authenticateVeterinario(veterinario)
  }

  getVeterinario(nome:string) {
    return this.veterinarios.find( element => element.nome == nome );
  }

  listVeterinarios(): Array<Veterinario>{
    return this.veterinarios
  }

}

export default VeterinarioCollection