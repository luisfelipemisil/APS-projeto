const UsuarioBDR = require('./UsuarioBDR.ts')
const VeterinarioBDR = require('./VeterinarioBDR.ts')
const ColaboradorBDR = require('./ColaboradorBDR.ts')
const IFabricaRepositorioAbstrata = require('./FabricaRepositorioAbstrata')

class FabricaRepositorioBDR extends IFabricaRepositorioAbstrata {

    constructor () {
        super();
    }

    criarRepositorioUsuario() {
        return new UsuarioBDR();
    }
    criarRepositorioVeterianario() {
        return new VeterinarioBDR();
    }
    criarRepositorioColaborador() {
        return new ColaboradorBDR();
    }
}

module.exports = FabricaRepositorioBDR