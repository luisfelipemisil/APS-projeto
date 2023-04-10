# Imagem base
FROM golang:1.17-alpine AS build

# Cria a pasta do projeto dentro do container
WORKDIR /app

# Copia o código-fonte para dentro do container
COPY . .

# Baixa as dependências especificadas no arquivo go.mod
RUN go mod download

# Compila o código
RUN go build -o main .

# Imagem final
FROM alpine:3.14
COPY --from=build /app/main /app/main
EXPOSE 8080
CMD ["/app/main"]
