# LinkedIn Clone Backend - (Spring Boot)

#### This project is a LinkedIn-inspired backend platform built with Spring Boot, designed to connect users and facilitate professional networking through an efficient and scalable architecture.

# Key Features:

+ **User Profiles:** Users can create profiles, view connections, accept connection requests and send connections requests.
+ **Post Management:** Users can create and like on posts.
+ **Connection System:** Allows users to send, accept, and reject connection requests.
+ **Event-Driven Architecture:** Kafka is used for efficient, real-time event processing for actions like connection requests and post interactions.
+ **Neo4j-Powered Connections:** Utilizes Neo4j for efficient storage and retrieval of connection relationships, optimizing queries in the connection service.

## Security Features:
+ **Authentication:** JWT tokens are implemented for secure authentication and session management.
+ **Authorization:** Role-based access control ensures users can only access features pertinent to their role.
+ **Data Protection:** Sensitive data, such as passwords, are securely encrypted to safeguard user information.

# Technologies Used:
+ **Back-End:** Spring Boot, Spring Security, Spring Data JPA
+ **Databases:** PostgreSQL for structured data and Neo4j for connection relationships.
+ **Event Processing:** Kafka for real-time data processing and event-driven functionality.
+ **Service Discovery and Load Balancing:** Eureka Discovery Server for dynamic service registration and API Gateway for efficient routing.

# Controllers:

### Connections Controller
- `/core/first-degree` - `GET`: Retrieve a list of first-degree connections.
- `/core/request/{userId}` - `POST`: Send a connection request to the specified user.
- `/core/accept/{userId}` - `POST`: Accept a pending connection request from the specified user.
- `/core/reject/{userId}` - `POST`: Reject a pending connection request from the specified user.

### Likes Controller
- `/likes/{postId}` - `POST`: Like the specified post.
- `/likes/{postId}` - `DELETE`: Unlike the specified post.

### Posts Controller
- `/core` - `POST`: Create a new post.
- `/core/{postId}` - `GET`: Retrieve a specific post by its ID.

### Auth Controller
- `/auth/signup` - `POST`: Register a new user account.
- `/auth/login` - `POST`: Login and receive a JWT token for authentication.
