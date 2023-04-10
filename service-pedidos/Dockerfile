# Use a imagem base oficial do Go
FROM golang:1.17.1-alpine3.14

# Define o diretório de trabalho dentro do container
WORKDIR /app

# Copia o arquivo Go e o arquivo de dados para o diretório de trabalho
COPY . .

# Baixa as dependências especificadas no arquivo go.mod
RUN go mod download

# Compila o programa Go
RUN go build -o main .

# Expor a porta 8080 para comunicação externa
EXPOSE 8080

# Define o comando de entrada do container
CMD ["/app/main"]