docker run -u 0 -d -p 8080:8080 -p 50000:50000 -v /data/jenkins:/var/jenkins_home jenkins/jenkins

sudo sysctl -w vm.max_map_count=262144
sudo chmod 666 /var/run/docker.sock
