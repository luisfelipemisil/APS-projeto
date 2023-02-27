import ColaboradorCollection from '../models/collections/ColaboradorCollection';
import Colaborador from '../models/entities/Colaborador';
import Email from '../models/entities/Email';
import CPF from '../models/entities/CPF';

class ControladorColaborador {
    cadastrarColaborador(cpf: string, senha: string, nome:string, email:string) {
        const emailObj = new Email(email)
        const cpfObj = new CPF(cpf)
        if(emailObj.validarEmail()){
            const colaborador = new Colaborador(cpfObj, nome, senha, emailObj);
            const colaboradorCollection = new ColaboradorCollection();
            colaboradorCollection.storeColaborador(colaborador)
        }

    }

    async autenticarColaborador(cpf: string, senha: string, nome:string, email:string) {
        const emailObj = new Email(email)
        const cpfObj = new CPF(cpf)
        if(emailObj.validarEmail()){
            const colaborador = new Colaborador(cpfObj, nome, senha, emailObj);
            const colaboradorCollection = new ColaboradorCollection();
            const res = await colaboradorCollection.authenticateColaborador(colaborador);
            return res === "found veterinario" // success or not XD
        }

    }

    sendEmail(texto: string){
        const colaboradorCollection = new ColaboradorCollection();
        let listCol = colaboradorCollection.listColaboradores();
        listCol.forEach(element => console.log(element.email.email))
    }

}

export default ControladorColaborador