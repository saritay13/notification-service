# Getting Started

### Reference Documentation
#### For Kafka setup follow below steps:
with docker(Confluent Kafka)
1. Download Docker desktop : https://www.docker.com/products/docker-desktop/
2. create a folder anywhere in your desktop and create folder give any name : "kafka-local"
3. Create a new file inside the kafka-local folder named: docker-compose.yml
4. Run below docker-compose up -d to run kafka
   What this does:
   1. Downloads the Kafka Docker image (confluentinc/cp-kafka:7.6.1 )
   2. Starts Kafka in KRaft mode (confluentinc/cp-zookeeper:7.6.1)
   3. Exposes it on localhost:9092
4. Confirm Kafka is running using command : docker ps
5. Exec into the Kafka container to run kafka commands : docker exec -it <Container_Name> bash
6. Create a test topic : kafka-topics --create --topic test-topic --bootstrap-server localhost:9092
7. Start a consumer (to listen for messages) : kafka-console-consumer --topic test-topic --from-beginning --bootstrap-server localhost:9092
8. Open a new terminal to produce messages : docker exec -it kafka-local-kafka-1 bash
9. Then open producer: kafka-console-producer --topic test-topic --bootstrap-server localhost:9092
10. And send messages

#### Once producer, consumer works lets build API
1. Setup application.yaml to provide kafka configuartions
2. Create springboot project with below dependencies - 
   1. Spring Web 
   2. Spring Kafka 
   3. Lombok (optional but helpful)
4. create producer api and corresponding services to produce and consume messages with simple setup
5. curl -X POST http://localhost:8080/api/messages -H "Content-Type: text/plain" -d "Hello There"


