import { Pool } from "pg";

const pool = new Pool({
  user: "mybjrivv",
  host: "motty.db.elephantsql.com",
  port: 5432,
  database: "mybjrivv",
  password: "EKWdg_Bx9Fow46GkKq3E8SOZK3n-kKwL",
});

export default pool;