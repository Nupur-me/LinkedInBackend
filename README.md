Developed a backend for a LinkedIn-like platform using a microservices architecture, integrating advanced technologies to handle user, post, and connection functionalities efficiently, with an emphasis on scalability and modularity.

Microservices Architecture: Created with Spring Boot, where services for user management, posts, and connections are independently managed and scalable.

Data Management:

PostgreSQL: Used for structured data storage, managing the user and post services.
Neo4j: Integrated within the connection service to efficiently handle relationship-based queries, similar to LinkedIn’s professional networking.
Real-Time Data Processing: Implemented Kafka to handle event-driven data flow for responsive interactions, managing topics such as connection requests, post creation, and post likes.

Service Discovery and Load Balancing:

Discovery Server: A Eureka-based Discovery Server is used to register and locate services dynamically, ensuring seamless scaling and efficient communication between services.
API Gateway: Acts as a centralized entry point, providing streamlined, secure API requests across services.
This project showcases a robust backend architecture with a focus on distributed systems, real-time event processing, and database optimization, making it well-suited for social networking applications.
