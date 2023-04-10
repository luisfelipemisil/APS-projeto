package main

import (
	"encoding/json"
	"fmt"
	"io/ioutil"
	"net/http"
)

// Define a estrutura de uma conta bancária
type Account struct {
	Owner   string  `json:"owner"`
	Balance float64 `json:"balance"`
}

// Cria um mapa para armazenar as contas bancárias
var accounts = make(map[string]Account)

// Define o handler para a rota /accounts
func accountsHandler(w http.ResponseWriter, r *http.Request) {
	switch r.Method {
	case http.MethodGet:
		// Retorna todas as contas
		accountsList := make([]Account, 0, len(accounts))
		for _, account := range accounts {
			accountsList = append(accountsList, account)
		}
		jsonData, _ := json.Marshal(accountsList)
		w.Header().Set("Content-Type", "application/json")
		w.Write(jsonData)

	case http.MethodPost:
		// Adiciona uma nova conta
		body, _ := ioutil.ReadAll(r.Body)
		var account Account
		json.Unmarshal(body, &account)
		accounts[account.Owner] = account
		jsonData, _ := json.Marshal(account)
		w.Header().Set("Content-Type", "application/json")
		w.WriteHeader(http.StatusCreated)
		w.Write(jsonData)

	default:
		w.WriteHeader(http.StatusMethodNotAllowed)
		fmt.Fprintf(w, "Método não permitido: %v", r.Method)
	}
}

// Define o handler para a rota /accounts/{owner}
func accountHandler(w http.ResponseWriter, r *http.Request) {
	// Extrai o nome do proprietário da conta a partir da URL
	owner := r.URL.Path[len("/accounts/"):]

	switch r.Method {
	case http.MethodGet:
		// Retorna a conta do proprietário especificado
		account, ok := accounts[owner]
		if !ok {
			w.WriteHeader(http.StatusNotFound)
			return
		}
		jsonData, _ := json.Marshal(account)
		w.Header().Set("Content-Type", "application/json")
		w.Write(jsonData)

	case http.MethodPut:
		// Atualiza o saldo da conta do proprietário especificado
		body, _ := ioutil.ReadAll(r.Body)
		var update struct {
			Balance float64 `json:"balance"`
		}
		json.Unmarshal(body, &update)
		account, ok := accounts[owner]
		if !ok {
			w.WriteHeader(http.StatusNotFound)
			return
		}
		account.Balance = update.Balance
		accounts[owner] = account
		w.WriteHeader(http.StatusNoContent)

	default:
		w.WriteHeader(http.StatusMethodNotAllowed)
		fmt.Fprintf(w, "Método não permitido: %v", r.Method)
	}
}

func main() {

	// Configura as rotas e os handlers HTTP
	http.HandleFunc("/accounts", accountsHandler)
	http.HandleFunc("/accounts/", accountHandler)

	// Inicia o servidor HTTP na porta 8000
	fmt.Println("Servidor iniciado na porta 8000...")
	http.ListenAndServe(":8000", nil)
}
