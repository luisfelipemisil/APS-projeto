# Define a imagem base que será usada para rodar o nosso código
FROM golang:1.17-alpine

# Define a pasta de trabalho para o nosso container
WORKDIR /app

# Copia os arquivos necessários para a pasta de trabalho do container
COPY . .

# Faz o download das dependências do nosso projeto
RUN go mod download

# Compila o nosso código e cria o executável
RUN go build -o main .

# Expõe a porta 8000 do container para o host
EXPOSE 8000

# Inicia o nosso servidor HTTP quando o container for executado
CMD ["./main"]
