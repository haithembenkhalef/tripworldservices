# tripworldservices

## Installation

Use the docker compose on the project directroy for installing the data base and the manager.

```bash
docker compose up -d
```

Reload maven project in case of any dependency issue and clean goal.

Launch hotel-batch module for executing the batch program.

Launch hotel-resources module for the api.

Enter the hal explorator:

```bash
http://localhost:8080/api/v1
```
OR
Use postman to test the api routes.
