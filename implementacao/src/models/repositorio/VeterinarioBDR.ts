import pool from "../../db";
import Veterinario from "../entities/Veterinario";

class VeterinarioBDR {
    constructor() { }


    async storeVeterinario(veterinario: Veterinario) {

        try {
            const query = `INSERT INTO veterinario (cpf, password, name, email) 
            VALUES ($1, $2, $3, $4) RETURNING cpf`;

            const res = pool.query(query, [veterinario.cpf, veterinario.senha, veterinario.nome, veterinario.email])
            return res;
        } catch (err) {
            return err;
        }


    }

    async findUser(veterinario: Veterinario) {

        try {
            const query = `SELECT cpf, password FROM users WHERE cpf = $1`;

            const result = await pool.query(query, [veterinario.cpf])

            if (result.rows.length == 0) { return "user not found" }

            if (veterinario.senha == result.rows[0].password) return "found user";

            return "wrong password";

        } catch (err) {
            return err;
        }

    }

    async authenticateUser(veterinario: Veterinario) {
        const query = `SELECT cpf, password FROM users WHERE cpf = $1`;

        const result = await pool.query(query, [veterinario.cpf])

        if (result.rows.length == 0) { return "user not found" }

        if (veterinario.senha == result.rows[0].password) return "found user"

        return "wrong password"
    }

}

module.exports = VeterinarioBDR