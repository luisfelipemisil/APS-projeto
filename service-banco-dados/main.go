package main

import (
	"encoding/json"
	"fmt"
	"io/ioutil"
	"net/http"
	"strings"
)

type User struct {
	Nome   string `json:"nome"`
	Email  Email  `json:"email"`
	Senha  string `json:"senha"`
	CPF    string `json:"cpf"`
	Cartao Cartao `json:"cartao"`
}

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

type Email struct {
	Endereco string `json:"endereco"`
}

type Cartao struct {
	Numero   string `json:"numero"`
	Validade string `json:"validade"`
}

type Users []User

var users Users
var animals []Animal

func main() {
	// Carrega os usuários do arquivo de dados
	loadUsers()

	// Define as rotas HTTP para o subserviço
	http.HandleFunc("/adduser", addUserHandler)
	http.HandleFunc("/updateuser", updateUserHandler)
	http.HandleFunc("/removeuser", removeUserHandler)
	http.HandleFunc("/finduser", findUserHandler)
	http.HandleFunc("/pedido", listAnimalsHandler)
	http.HandleFunc("/pedido/add", addAnimalHandler)
	http.HandleFunc("/pedido/remove/", removeAnimalHandler)
	http.HandleFunc("/pedido/update/", updateAnimalHandler)
	http.HandleFunc("/pedido/find/", findAnimalHandler)

	// Inicia o servidor HTTP
	fmt.Println("Servidor iniciado na porta 8080")
	err := http.ListenAndServe(":8080", nil)
	if err != nil {
		panic(err)
	}
}

// Handler para adicionar um novo usuário
func addUserHandler(w http.ResponseWriter, r *http.Request) {
	if r.Method != http.MethodPost {
		http.Error(w, "Método não permitido", http.StatusMethodNotAllowed)
		return
	}
	user := User{}
	err := json.NewDecoder(r.Body).Decode(&user)
	if err != nil {
		http.Error(w, "Erro ao decodificar o JSON do usuário", http.StatusBadRequest)
		return
	}
	addUser(user)
	w.WriteHeader(http.StatusOK)
}

// Handler para atualizar o nome e senha de um usuário existente
func updateUserHandler(w http.ResponseWriter, r *http.Request) {
	if r.Method != http.MethodPost {
		http.Error(w, "Método não permitido", http.StatusMethodNotAllowed)
		return
	}
	newUser := User{}
	err := json.NewDecoder(r.Body).Decode(&newUser)
	if err != nil {
		http.Error(w, "Erro ao decodificar o JSON do usuário", http.StatusBadRequest)
		return
	}
	updateUser(newUser)
	w.WriteHeader(http.StatusOK)
}

// Handler para remover um usuário existente
func removeUserHandler(w http.ResponseWriter, r *http.Request) {
	if r.Method != http.MethodPost {
		http.Error(w, "Método não permitido", http.StatusMethodNotAllowed)
		return
	}
	user := User{}
	err := json.NewDecoder(r.Body).Decode(&user)
	if err != nil {
		http.Error(w, "Erro ao decodificar o JSON do usuário", http.StatusBadRequest)
		return
	}
	removeUser(user)
	w.WriteHeader(http.StatusOK)
}

// Encontra um usuário pelo email
func findUserHandler(w http.ResponseWriter, r *http.Request) {
	// Extrai o email da query string
	email := Email{Endereco: r.URL.Query().Get("email")}

	// Verifica se o email é válido
	if email.Endereco == "" {
		http.Error(w, "Email inválido", http.StatusBadRequest)
		return
	}

	// Procura o usuário pelo email
	user, found := findUserByEmail(email)

	// Se o usuário não foi encontrado, retorna um erro 404
	if !found {
		http.Error(w, "Usuário não encontrado", http.StatusNotFound)
		return
	}

	// Se o usuário foi encontrado, retorna suas informações em formato JSON
	w.Header().Set("Content-Type", "application/json")
	json.NewEncoder(w).Encode(user)
}

// Adiciona um novo usuário à lista de usuários
func addUser(user User) {
	users = append(users, user)
	saveUsers()
}

// Remove um usuário da lista de usuários
func removeUser(user User) {
	for i, u := range users {
		if u.Email.Endereco == user.Email.Endereco && u.Senha == user.Senha {
			users = append(users[:i], users[i+1:]...)
			saveUsers()
			return
		}
	}
}

// Atualiza o nome e a senha de um usuário existente
func updateUser(newUser User) {
	for i, user := range users {
		if user.Email.Endereco == newUser.Email.Endereco && user.Senha == newUser.Senha {
			users[i].Nome = newUser.Nome
			users[i].Senha = newUser.Senha
			saveUsers()
			return
		}
	}
}

// Encontra um usuário pelo email
func findUserByEmail(email Email) (User, bool) {
	for _, user := range users {
		if user.Email.Endereco == email.Endereco {
			return user, true
		}
	}
	return User{}, false
}

// Salva os usuários no arquivo de dados
func saveUsers() {
	data, err := json.Marshal(users)
	if err != nil {
		panic(err)
	}
	err = ioutil.WriteFile("./data/users.json", data, 0644)
	if err != nil {
		panic(err)
	}
}

func saveDataAnimals() {
	// Codifica a lista de animais em formato JSON
	data, err := json.Marshal(animals)
	if err != nil {
		panic(err)
	} // Grava os dados no arquivo JSON
	err = ioutil.WriteFile("./data/animals.json", data, 0644)
	if err != nil {
		panic(err)
	}
}

// Carrega os usuários do arquivo de dados
func loadUsers() {
	data, err := ioutil.ReadFile("./data/users.json")
	if err != nil {
		// Arquivo não encontrado ou vazio
		users = make(Users, 0)
		return
	}
	if len(data) == 0 {
		// Arquivo vazio
		users = make(Users, 0)
		return
	}
	err = json.Unmarshal(data, &users)
	if err != nil {
		panic(err)
	}
}

func loadAnimals() {
	// Lê os dados do arquivo JSON
	data, err := ioutil.ReadFile("./data/animals.json")
	if err != nil {
		panic(err)
	}
	if len(data) == 0 {
		// Arquivo vazio
		animals = make([]Animal, 0)
		return
	}
	// Decodifica os dados do arquivo JSON para a variável animals
	err = json.Unmarshal(data, &animals)
	if err != nil {
		panic(err)
	}
}

func listAnimalsHandler(w http.ResponseWriter, r *http.Request) {
	// Converte a lista de animais para um formato JSON e envia na resposta HTTP
	jsonData, err := json.Marshal(animals)
	if err != nil {
		panic(err)
	}
	w.Header().Set("Content-Type", "application/json")
	w.Write(jsonData)
}

func addAnimalHandler(w http.ResponseWriter, r *http.Request) {
	// Lê o corpo da requisição HTTP
	body, err := ioutil.ReadAll(r.Body)
	if err != nil {
		panic(err)
	}

	// Decodifica o corpo da requisição HTTP em um objeto Animal
	var animal Animal
	err = json.Unmarshal(body, &animal)
	if err != nil {
		panic(err)
	}

	// Adiciona o animal na lista
	animals = append(animals, animal)

	// Salva a lista atualizada no arquivo JSON
	saveDataAnimals()

	// Envia uma resposta HTTP com status 201 Created
	w.WriteHeader(http.StatusCreated)
}

func removeAnimalHandler(w http.ResponseWriter, r *http.Request) {
	// Extrai o nome do animal a partir da URL
	parts := strings.Split(r.URL.Path, "/")
	nome := parts[len(parts)-1]

	// Remove o animal da lista
	for i, animal := range animals {
		if animal.Nome == nome {
			animals = append(animals[:i], animals[i+1:]...)
			saveDataAnimals()
			w.WriteHeader(http.StatusNoContent)
			return
		}
	}

	// Se o animal não foi encontrado, retorna um erro HTTP 404 Not Found
	w.WriteHeader(http.StatusNotFound)
}

func updateAnimalHandler(w http.ResponseWriter, r *http.Request) {
	// Extrai o nome do animal a partir da URL
	parts := strings.Split(r.URL.Path, "/")
	nome := parts[len(parts)-1]

	// Lê o corpo da requisição HTTP
	body, err := ioutil.ReadAll(r.Body)
	if err != nil {
		panic(err)
	}

	// Decodifica o corpo da requisição HTTP em um objeto Animal
	var animal Animal
	err = json.Unmarshal(body, &animal)
	if err != nil {
		panic(err)
	}

	// Atualiza o status do animal na lista
	for i, a := range animals {
		if a.Nome == nome {
			animals[i].Status = animal.Status
			saveDataAnimals()
			w.WriteHeader(http.StatusNoContent)
			return

		}
	}

	// Se o animal não foi encontrado, retorna um erro HTTP 404 Not Found
	w.WriteHeader(http.StatusNotFound)
}

func findAnimalHandler(w http.ResponseWriter, r *http.Request) {
	// Extrai o nome do animal a partir da URL
	parts := strings.Split(r.URL.Path, "/")
	nome := parts[len(parts)-1]
	// Procura o animal na lista pelo nome
	for _, animal := range animals {
		if animal.Nome == nome {
			// Converte o animal encontrado para um formato JSON e envia na resposta HTTP
			jsonData, err := json.Marshal(animal)
			if err != nil {
				panic(err)
			}
			w.Header().Set("Content-Type", "application/json")
			w.Write(jsonData)
			return
		}
	}

	// Se o animal não foi encontrado, retorna um erro HTTP 404 Not Found
	w.WriteHeader(http.StatusNotFound)
}
