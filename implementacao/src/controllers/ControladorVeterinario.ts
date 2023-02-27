import VeterinarioCollection from '../models/collections/VeterinarioCollection';
import Email from '../models/entities/Email';
import CPF from '../models/entities/CPF';
import Veterinario from '../models/entities/Veterinario';


class ControladorVeterinario {

    cadastrarVeterinario(cpf: string, senha: string, nome:string, email:string) {
        const emailObj = new Email(email)
        const cpfObj = new CPF(cpf)
        if(emailObj.validarEmail() && cpfObj.validarCPF()){
            const veterinario = new Veterinario(cpfObj, nome, senha, emailObj);
            const veterinarioColection = new VeterinarioCollection();
            veterinarioColection.storeVeterinario(veterinario)
        }

    }

    async autenticarVeterinario(cpf: string, senha: string, nome:string, email:string) {
        const emailObj = new Email(email)
        const cpfObj = new CPF(cpf)
        if(emailObj.validarEmail() && cpfObj.validarCPF()){
            const veterinario = new Veterinario(cpfObj, nome, senha, emailObj);
            const veterinarioCollection = new VeterinarioCollection();
            const res = await veterinarioCollection.authenticateVeterinario(veterinario);
            return res === "found veterinario" // success or not XD
        }

    }

    sendEmail(texto: string){
        const veterinarioColection = new VeterinarioCollection();
        let listVet = veterinarioColection.listVeterinarios();
        listVet.forEach(element => console.log(element.email.email))
    }


}

export default ControladorVeterinario