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

### Deploy Application

1. Open terminal/cmd @ root directory
2. Run: `cd ci_pipeline/env`
3. Run `docker compose up -d`

