import pool from "../../db";
import Animal from "../entities/Animal";

class AnimalBDR {
    constructor() { }

    async inserirAnimal(ficha: Animal) {
        try {
            const query = `INSERT INTO animais (name, especie, endereco, descricao, filhote, status, img) 
            VALUES ($1, $2, $3, $4, $5, $6, $7, $8)`;

            const res = await pool.query(query, [ficha.name, ficha.especie, ficha.endereco, ficha.descricao, ficha.filhote, ficha.status,ficha.img])
            console.log(res);

            const query2 = `SELECT max(id) FROM animais`;
            const res2 = await pool.query(query2)
            return Number(res2.rows[0].id);

        } catch (err) {
            return 0;
        }
    }

    async listarAnimais() {
        try {
            const query = `SELECT id, name, especie, endereco, descricao, filhote, status, img FROM animais`;
            const res = await pool.query(query)

            return res.rows;
        } catch (err) {
            return err;
        }
    }

    async deletarAnimal(id: number) {
        try {
            const query = `DELETE FROM animais WHERE id = $1`;
            const res = await pool.query(query, [id])
            return res;

        } catch (err) {
            return err;
        }
    }

    async updateAnimal(id: number, name: string, status: string) {
        try {
            const query = `UPDATE animais
            SET name = $2, status = $3
            WHERE id = $1`;

            const res = pool.query(query, [id, name, status])

            return res;
        } catch (err) {
            return err;
        }
    }

}

export default AnimalBDR;