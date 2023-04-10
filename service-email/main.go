package main

import (
	"fmt"
	"net/http"
)

// EmailServer é a interface que define o comportamento esperado do servidor de email
type EmailServer interface {
	SendEmail(email *Email) error
}

// Email representa um email
type Email struct {
	From    string
	To      string
	Subject string
	Body    string
}

// ThirdPartyEmail é uma implementação concreta do EmailServer que se conecta a um servidor de email de terceiros
type ThirdPartyEmail struct{}

// SendEmail envia um email usando um servidor de email de terceiros
func (t *ThirdPartyEmail) SendEmail(email *Email) error {
	// Implementação que envia o email usando um servidor de email de terceiros
	return nil
}

// EmailFacade é uma fachada que simplifica o envio de emails
type EmailFacade struct {
	server EmailServer
}

// NewEmailFacade cria uma nova instância de EmailFacade
func NewEmailFacade(server EmailServer) *EmailFacade {
	return &EmailFacade{
		server: server,
	}
}

// SendEmail envia um email usando o servidor de email configurado na fachada
func (f *EmailFacade) SendEmail(from, to, subject, body string) error {
	email := &Email{
		From:    from,
		To:      to,
		Subject: subject,
		Body:    body,
	}
	return f.server.SendEmail(email)
}

func main() {
	// Cria uma nova instância do servidor de email de terceiros
	emailServer := &ThirdPartyEmail{}

	// Cria uma nova instância da fachada de email, usando o servidor de email de terceiros
	emailFacade := NewEmailFacade(emailServer)

	// Cria um handler HTTP que recebe um email por POST e o envia usando a fachada de email
	http.HandleFunc("/send_email", func(w http.ResponseWriter, r *http.Request) {
		if r.Method != http.MethodPost {
			http.Error(w, "Only POST requests are allowed", http.StatusMethodNotAllowed)
			return
		}

		from := r.FormValue("from")
		to := r.FormValue("to")
		subject := r.FormValue("subject")
		body := r.FormValue("body")

		err := emailFacade.SendEmail(from, to, subject, body)
		if err != nil {
			http.Error(w, fmt.Sprintf("Failed to send email: %s", err.Error()), http.StatusInternalServerError)
			return
		}

		fmt.Fprintln(w, "Email sent successfully! The email: "+body)
	})

	// Inicia o servidor HTTP na porta 8080
	http.ListenAndServe(":8060", nil)
}
