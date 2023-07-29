FROM jenkins/jenkins:jdk17

USER root
#DOCKER
RUN apt-get update -qq \
    && apt-get install -qqy apt-transport-https ca-certificates curl gnupg2 software-properties-common
RUN curl -fsSL https://download.docker.com/linux/debian/gpg | apt-key add -
RUN add-apt-repository \
   "deb [arch=amd64] https://download.docker.com/linux/debian \
   $(lsb_release -cs) \
   stable"
RUN apt-get update  -qq \
    && apt-get -y install docker-ce
RUN usermod -aG docker jenkins
RUN apt-get update

#MAVEN
RUN apt-get install -y maven

#KUBECTL
RUN apt-get install ca-certificates
RUN curl -fsSL https://packages.cloud.google.com/apt/doc/apt-key.gpg | gpg --dearmor -o /etc/apt/keyrings/kubernetes-archive-keyring.gpg
RUN echo "deb [signed-by=/etc/apt/keyrings/kubernetes-archive-keyring.gpg] https://apt.kubernetes.io/ kubernetes-xenial main" | tee /etc/apt/sources.list.d/kubernetes.list
RUN apt update
RUN apt install kubectl

COPY ca.crt /home/user/.minikube/ca.crt
COPY client.crt /home/user/.minikube/profiles/minikube/client.crt
COPY client.key /home/user/.minikube/profiles/minikube/client.key

#HELM
RUN curl -fsSL -o get_helm.sh https://raw.githubusercontent.com/helm/helm/master/scripts/get-helm-3
RUN chmod 700 get_helm.sh
RUN ./get_helm.sh
RUN curl https://get.datree.io | /bin/bash
RUN apt-get install unzip
