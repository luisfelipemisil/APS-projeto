class Animal {
    constructor(
        name: string,
        especie: string,
        endereco: string,
        descricao: string,
        filhote: boolean,
        status: string,
        id: number,
        img: string

    ) {
        this.name = name;
        this.especie = especie;
        this.endereco = endereco;
        this.descricao =  descricao;
        this.filhote = filhote;
        this.status = status; 
        this.id = id;
        this.img = img;
    }

    name: string;
    especie: string;
    endereco: string;
    descricao: string;
    filhote: boolean;
    status: string;
    id: number;
    img: string;

    mudaStatus(status: string) {
        this.status = status;
    }
}

export default Animal;