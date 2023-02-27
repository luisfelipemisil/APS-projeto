import pool from "../../db";
import Animal from "../entities/Animal";

class AnimalBDR {
    constructor() { }

    async inserirAnimal(ficha: Animal) {
        try {
            const query = `INSERT INTO animais (name, especie, endereco, descricao, filhote, status, id, img) 
            VALUES ($1, $2, $3, $4, $5, $6, $7, $8)`;

            const res = await pool.query(query, [ficha.name, ficha.especie, ficha.endereco, ficha.descricao, ficha.filhote, ficha.status, ficha.id, ficha.img])
            console.log(res)
            return res;
        } catch (err) {
            return err;
        }

    }

    async listarAnimais() {
        try {
            const query = `SELECT id, name, description, calories FROM itens`;
            const res = await pool.query(query)

            return res.rows;
        } catch (err) {
            return err;
        }
    }

    async deletarPrato(id: number) {
        try {
            const query = `DELETE FROM itens WHERE id = $1`;
            const res = await pool.query(query, [id])
            return res;

        } catch (err) {
            return err;
        }
    }

    async updatePrato(id: number, name: string, description: string) {
        try {
            const query = `UPDATE itens
            SET name = $2, description = $3
            WHERE id = $1`;

            const res = pool.query(query, [id, name, description])

            return res;
        } catch (err) {
            return err;
        }
    }

}

export default PratoBDR;