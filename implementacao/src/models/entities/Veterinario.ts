import Email from "./Email";
import CPF from "./CPF";
import { Observable, Observer } from "../../observer/observer";

class Veterinario implements Observable {
    constructor(
      cpf: CPF,
      nome: string,
      senha:string,
      email: Email
    ) {
        this.cpf = cpf;
        this.senha = senha;
        this.nome = nome;
        this.email = email;
    }
  
    cpf: CPF;
    senha: string;
    nome: string;
    email: Email;

    private observers: Observer[] = [];
  
    subscribe(...observers: Observer[]): void {
      observers.forEach((observer) => {
        if (!this.observers.includes(observer)) {
          this.observers.push(observer);
        }
      });
    }
  
    unsubscribe(observer: Observer): void {
      const observerIndex = this.observers.indexOf(observer);
  
      if (observerIndex !== -1) {
        this.observers.splice(observerIndex, 1);
      }
    }
  
    notify(): void {
      this.observers.forEach((observer) => observer.update(this));
    }
  }
  
  export default Veterinario;