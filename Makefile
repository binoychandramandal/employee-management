# Common settings
BRANCH ?= develop
NUMBER ?= 1
SHORT_COMMIT = ${BRANCH}-${NUMBER}
APP_NAME ?= employee-management
NAME_SPACE ?= search-component
PROJECT_ID ?= javacourse2022-19-oct
#IMAGE_NAME = us.gcr.io/rcp-devops-registry/$(APP_NAME):$(SHORT_COMMIT)
IMAGE_NAME = us.gcr.io/$(PROJECT_ID)/$(APP_NAME):$(SHORT_COMMIT)
all: build_deploy

clean:
	@echo 'Cleaning up...'

image_build: clean
	echo Building $(IMAGE_NAME)
	docker build -t $(IMAGE_NAME) .

image_push:
	echo Pushing $(IMAGE_NAME) to gcloud
	docker push $(IMAGE_NAME)

image_deploy:
	cat scripts/deployment.yaml | sed -e "s/ENV/$(PROJECT_ID)/" -e "s/APPNAME/$(APP_NAME)/" -e "s/NAMESPACE/$(NAME_SPACE)/" -e "s/VERSION/$(SHORT_COMMIT)/" |  kubectl apply -f - || { echo 'ERROR: deployment failed' ; exit 3; }


image_build_push: image_build image_push

build_deploy: image_build image_push image_deploy