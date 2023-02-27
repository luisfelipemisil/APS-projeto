import pool from "../../db";
import Colaborador from "../entities/Colaborador";

class ColaboradorBDR {
    constructor() { }


    async storeColaborador(colaborador: Colaborador) {

        try {
            const query = `INSERT INTO colaboradores (cpf, password, name, email) 
            VALUES ($1, $2, $3, $4) RETURNING cpf`;

            const res = pool.query(query, [colaborador.cpf, colaborador.senha, colaborador.nome, colaborador.email])
            return res;
        } catch (err) {
            return err;
        }


    }

    async findUser(colaborador: Colaborador) {

        try {
            const query = `SELECT cpf, password FROM users WHERE cpf = $1`;

            const result = await pool.query(query, [colaborador.cpf])

            if (result.rows.length == 0) { return "user not found" }

            if (colaborador.senha == result.rows[0].password) return "found user";

            return "wrong password";

        } catch (err) {
            return err;
        }

    }

    async authenticateUser(colaborador: Colaborador) {
        const query = `SELECT cpf, password FROM users WHERE cpf = $1`;

        const result = await pool.query(query, [colaborador.cpf])

        if (result.rows.length == 0) { return "user not found" }

        if (colaborador.senha == result.rows[0].password) return "found user"

        return "wrong password"
    }

}

module.exports = ColaboradorBDR