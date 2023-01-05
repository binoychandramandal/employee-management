Point GCP kubernates cluster
============================
gcloud container clusters get-credentials autopilot-cluster-1 --region us-central1 --project javacourse2022-19-oct

Set GCP KEY
===============
export GOOGLE_APPLICATION_CREDENTIALS="/Users/binoy.mandal/all/java course/javacourse2022-19-oct-8911528f27fb.json"

deploy
=======
export PROJECT_ID=javacourse2022-19-oct 
make build_deploy



Docker issue not able to push image
====================================
gcloud auth configure-docker