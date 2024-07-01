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

