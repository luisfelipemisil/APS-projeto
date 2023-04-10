# Use uma imagem do Golang
FROM golang:latest

# Copie o código para a imagem
WORKDIR /app
COPY . .

# Construa o aplicativo
RUN go build -o main .

# Expõe a porta do servidor HTTP
EXPOSE 8060

# Inicie o aplicativo
CMD [ "./main" ]