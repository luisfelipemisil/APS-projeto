import ControladorFilaCastracao from "../controllers/ControladorFilaCastracao";
import Veterinario from "../models/entities/Veterinario";

export interface Observable {
    subscribe(...observers: Observer[]): void;
    unsubscribe(observer: Observer): void;
    notify(): void;
  }
  
export interface Observer {
    update(...args: unknown[]): void;
  }
