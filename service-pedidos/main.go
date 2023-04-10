package main

import (
	"encoding/json"
	"fmt"
	"io/ioutil"
	"log"
	"net/http"
	"os"
)

type Animal struct {
	Nome           string `json:"nome"`
	Idade          int    `json:"idade"`
	Raca           string `json:"raca"`
	Gato           bool   `json:"gato"`
	Cachorro       bool   `json:"cachorro"`
	Filhote        bool   `json:"filhote"`
	NomeDoCliente  string `json:"nome_do_cliente"`
	EmailDoCliente string `json:"email_do_cliente"`
	Descricao      string `json:"descricao"`
	Endereco       string `json:"endereco"`
	Status         string `json:"status"`
}

func main() {
	http.HandleFunc("/animal", animalHandler)
	log.Fatal(http.ListenAndServe(":8080", nil))
}

func animalHandler(w http.ResponseWriter, r *http.Request) {
	switch r.Method {
	case http.MethodGet:
		nome := r.URL.Query().Get("nome")
		animal := getAnimal(nome)
		json.NewEncoder(w).Encode(animal)
	case http.MethodPost:
		var animal Animal
		body, err := ioutil.ReadAll(r.Body)
		if err != nil {
			log.Println(err)
			w.WriteHeader(http.StatusBadRequest)
			return
		}
		if err := json.Unmarshal(body, &animal); err != nil {
			log.Println(err)
			w.WriteHeader(http.StatusBadRequest)
			return
		}
		if err := saveAnimal(animal); err != nil {
			log.Println(err)
			w.WriteHeader(http.StatusInternalServerError)
			return
		}
		w.WriteHeader(http.StatusCreated)
	case http.MethodPut:
		var animal Animal
		body, err := ioutil.ReadAll(r.Body)
		if err != nil {
			log.Println(err)
			w.WriteHeader(http.StatusBadRequest)
			return
		}
		if err := json.Unmarshal(body, &animal); err != nil {
			log.Println(err)
			w.WriteHeader(http.StatusBadRequest)
			return
		}
		if err := updateAnimal(animal); err != nil {
			log.Println(err)
			w.WriteHeader(http.StatusInternalServerError)
			return
		}
		w.WriteHeader(http.StatusOK)
	case http.MethodDelete:
		nome := r.URL.Query().Get("nome")
		if err := deleteAnimal(nome); err != nil {
			log.Println(err)
			w.WriteHeader(http.StatusInternalServerError)
			return
		}
		w.WriteHeader(http.StatusOK)
	}
}

func getAnimal(nome string) Animal {
	animals, err := readAnimals()
	if err != nil {
		log.Println(err)
		return Animal{}
	}
	for _, animal := range animals {
		if animal.Nome == nome {
			return animal
		}
	}
}
func saveAnimal(animal Animal) error {
	animals, err := readAnimals()
	if err != nil {
		return err
	}
	for _, a := range animals {
		if a.Nome == animal.Nome {
			return fmt.Errorf("animal already exists")
		}
		if a.NomeDoCliente == animal.NomeDoCliente && a.EmailDoCliente == animal.EmailDoCliente {
			return fmt.Errorf("client already has an animal")
		}
		if a.Cachorro && animal.Cachorro || a.Gato && animal.Gato {
			return fmt.Errorf("client already has a %s", a.Raca)
		}
		if a.Endereco == animal.Endereco {
			return fmt.Errorf("address already has an animal")
		}
		if a.NomeDoCliente == animal.NomeDoCliente {
			return fmt.Errorf("client already has an animal with a different name")
		}
		if a.EmailDoCliente == animal.EmailDoCliente {
			return fmt.Errorf("client already has an animal with a different name")
		}
	}
	animals = append(animals, animal)
	return writeAnimals(animals)
}

func updateAnimal(animal Animal) error {
	animals, err := readAnimals()
	if err != nil {
		return err
	}
	for i, a := range animals {
		if a.Nome == animal.Nome {
			animals[i] = animal
			return writeAnimals(animals)
		}
	}
	return fmt.Errorf("animal not found")
}

func deleteAnimal(nome string) error {
	animals, err := readAnimals()
	if err != nil {
		return err
	}
	for i, a := range animals {
		if a.Nome == nome {
			animals = append(animals[:i], animals[i+1:]...)
			return writeAnimals(animals)
		}
	}
	return fmt.Errorf("animal not found")
}

func readAnimals() ([]Animal, error) {
	file, err := os.OpenFile("animals.json", os.O_RDONLY|os.O_CREATE, 0644)
	if err != nil {
		return nil, err
	}
	defer file.Close()
	animals := []Animal{}
	if err := json.NewDecoder(file).Decode(&animals); err != nil {
		return nil, err
	}
	return animals, nil
}

func writeAnimals(animals []Animal) error {
	file, err := os.OpenFile("animals.json", os.O_WRONLY|os.O_TRUNC, 0644)
	if err != nil {
		return err
	}
	defer file.Close()
	if err := json.NewEncoder(file).Encode(animals); err != nil {
		return err
	}
	return nil
}
