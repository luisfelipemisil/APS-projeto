import pool from "../../db/";
import User from "../entities/Usuario";

class UsuarioBDR {
    constructor() { }


    async storeUser(user: User) {

        try {
            const query = `INSERT INTO users (cpf, password, name, email) 
            VALUES ($1, $2, $3, $4) RETURNING cpf`;

            const res = pool.query(query, [user.cpf.cpf, user.senha, user.nome, user.email.email])
            return res;
        } catch (err) {
            return err;
        }


    }

    async findUser(user: User) {

        try {
            const query = `SELECT cpf, password FROM users WHERE cpf = $1`;

            const result = await pool.query(query, [user.cpf.cpf])

            if (result.rows.length == 0) { return "user not found" }

            if (user.senha == result.rows[0].password) return "found user";

            return "wrong password";

        } catch (err) {
            return err;
        }

    }

    async authenticateUser(user: User) {
        const query = `SELECT cpf, password FROM users WHERE cpf = $1`;

        const result = await pool.query(query, [user.cpf.cpf])

        if (result.rows.length == 0) { return "user not found" }

        if (user.senha == result.rows[0].password) return "found user"

        return "wrong password"
    }

}

module.exports = UsuarioBDR