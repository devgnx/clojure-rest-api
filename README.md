# Clojure REST API

A simple Clojure boilerplate for REST API Apps

### Requirements:  
Install Leiningen v1.0.0 from https://leiningen.org

___

### Usage:

#### Run App
```sh
lein run
```

#### API Routes
```sh
curl -s http://localhost:8080/api/ping

# pong
```

```sh
curl -s http://localhost:8080/api/hello-world

# Hello, World!
```

```sh
curl -s -X PATCH http://localhost:8080/api/hello-name -H "Content-Type: application/json" -d '{"name":"Quentin Tarantino"}'

# Hello, Quentin Tarantino
```

```sh
curl -s http://localhost:8080/api/get-by-id/2

# {"id":2,"name":"Jules Winnfield"}
```

```sh
curl -s -X POST http://localhost:8080/api/save -H "Content-Type: application/json" -d '{"movie":"Pulp Fiction","year":1995}'

# {"movie":"Pulp Fiction","year":1995}
```

```sh
curl -s -X PUT http://localhost:8080/api/person -H "Content-type: application/json" -d '{"person":{"name":"Marcellus Wallace"}}'

# {"name":"Marcellus Wallace"}
```

___

### Testing

#### Run auto-tests (dev environment only)
```sh
lein auto-test
```
#### Run tests
```sh
lein test
```
