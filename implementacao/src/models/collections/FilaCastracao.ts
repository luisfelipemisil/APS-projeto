import Animal from "../entities/Animal";
import AnimalBDR from "../repositorio/AnimalBDR";
const isNumber = (val: any) => typeof val === "number" && val === val;


class FilaCastracao {
  fichaBDR: AnimalBDR;
  constructor() {
    this.fichaBDR = new AnimalBDR();
  }


  storeAnimal(ficha: Animal): number {
    if (isNumber( this.fichaBDR.inserirAnimal(ficha) )){
      return Number(this.fichaBDR.inserirAnimal(ficha))
    }else{
      return 0
    }
  }

  async listAniamis() {
    return await this.fichaBDR.listarAnimais();
  }

  deletarAnimais(id: number) {
    return this.fichaBDR.deletarAnimal(id);
  }

  updateAnimal(id: number, name: string, status: string) {
    return this.fichaBDR.updateAnimal(id, name, status);
  }
}

export default FilaCastracao