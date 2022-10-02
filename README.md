# KPFC

## Setup the application

### Build Docker Image
The follwing commands explain how to create a docker image of the backend:

#### Windows 
1. Open cmd @ root directory
2. Run: `gradle bootjar`
3. Run: docker build . -t blog-backend:local

#### Linux
1. Open terminal @ root directory
2. Run: `./ci_pipeline/cdBuild.sh`

## Run Application

1. Open terminal/cmd @ root directory
2. Run: `cd ci_pipeline/env`
3. Run `docker compose up -d`


## Services

### OpenAPI Documentation
http://localhost:8080/v3/api-docs/ 

### Login 

HTTP Operation: `POST`

HTTP Path: `/login`

Possible HTTP Status Code:
* 200: Sussecfull Login
* 401: Password is Wrong
* 404: Email is not associated with any user

#### cURL Examples
* 200: Sussecfull Login

`curl --location --request POST 'localhost:8080/login' \
--header 'Content-Type: application/json' \
--data-raw '{
    "email": "a@b.com",
    "password": "1234"
}'`

* 401: Password is Wrong

`curl --location --request POST 'localhost:8080/login' \
--header 'Content-Type: application/json' \
--data-raw '{
    "email": "a@b.com",
    "password": "123"
}'`

* 404: Email is not associated with any user

`curl --location --request POST 'localhost:8080/login' \
--header 'Content-Type: application/json' \
--data-raw '{
    "email": "test@bs.com",
    "password": "123"
}'`

### Users
HTTP Operation: `GET`

HTTP Path: `/users`

HTTP Params: 
|  Name | Description |
|-------|-------------|
| name | Retrieve users containg part of the name|
| email | Retrieve users containg part of the email |
| pageIndex | Retrieve users of a given page (default: 1)|
| pageSize | Identifies the size of the page (default: 10)|

Possible HTTP Status Code:
* 200: Retrieve Users

#### cURL Examples

`curl --location --request GET 'localhost:8080/users?name=t&email=t&pageIndex=0&pageSize=2'`

### Posts

HTTP Operation: `GET`

HTTP Path: `/post`

HTTP Params: 
|  Name | Description |
|-------|-------------|
| title | Retrieve posts containg part of the title|
| body | Retrieve posts containg part of the body |
| pageIndex | Retrieve posts of a given page (default: 1)|
| pageSize | Identifies the size of the page (default: 10)|

Possible HTTP Status Code:
* 200: Retrieve Posts

#### cURL Examples

`curl --location --request GET 'localhost:8080/post?title=tes&body=post&pageIndex=0&pageSize=2'`

### Comments

HTTP Operation: `GET`

HTTP Path: `/comments`

HTTP Params: 
|  Name | Description |
|-------|-------------|
| title | Retrieve comments containg part of the title|
| body | Retrieve comments containg part of the body |
| pageIndex | Retrieve comments of a given page (default: 1)|
| pageSize | Identifies the size of the page (default: 10)|

Possible HTTP Status Code:
* 200: Retrieve comments

#### cURL Examples

`curl --location --request GET 'localhost:8080/comments?title=comment&body=comment&pageIndex=0&pageSize=2'`
