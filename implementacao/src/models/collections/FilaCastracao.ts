import Animal from "../entities/Animal";
import AnimalBDR from "../repositorio/AnimalBDR";
const isNumber = (val: any) => typeof val === "number" && val === val;


class FilaCastracao {
  fichaBDR: AnimalBDR;
  fichas: Animal[] = [];
  constructor() {
    this.fichaBDR = new AnimalBDR();
  }

  storeAnimal(ficha: Animal): number {
    if (isNumber( this.fichaBDR.inserirAnimal(ficha) )){
      this.fichas.push(ficha)
      return Number(this.fichaBDR.inserirAnimal(ficha))
    }else{
      return 0
    }
  }

  getAniamal(nome:string) {
    return this.fichas.find( element => element.name == nome );
  }

  listFichas(): Animal[] {
    return this.fichas;
  }

  async listAniamais() {
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