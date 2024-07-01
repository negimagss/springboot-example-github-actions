# springboot-example
Spring Boot Example Application


API Endpoint: http://localhost:8080/

Added context: 

1: Enable docker super user in EC2, this will cause problems if not addressed 

a) groups $USER 

b) sudo usermod -aG docker $USER 

c) Restart the shell and re login to EC2 


2) sudo systemctl restart docker


3) Edit incound rules from your EC2 for the port

4) ls -l /var/run/docker.sock

5) sudo chown root:docker /var/run/docker.sock

6) sudo chmod 660 /var/run/docker.sock

7) docker run hello-world : To check if it works


To install Docker 
 1) sudo yum update  # Update package repositories (for Amazon Linux/CentOS)

 2) sudo yum install -y yum-utils device-mapper-persistent-data lvm2

 3) sudo yum-config-manager --add-repo https://download.docker.com/linux/centos/docker-ce.repo

 4) sudo yum install docker-ce docker-ce-cli containerd.io

 5) sudo yum install docker

 6) sudo systemctl start docker

 7) sudo systemctl enable docker

 
