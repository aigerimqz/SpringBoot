# SIS6 — Social Media Backend with Apache Kafka

A production-ready Spring Boot application demonstrating event-driven architecture using Apache Kafka.
When a user creates a post, Kafka automatically triggers feed and notification updates in real time.

---

## Tech Stack

| Technology | Version | Purpose |
|---|---|---|
| Java | 25 | Programming language |
| Spring Boot | 3.5.11 | Application framework |
| Apache Kafka | 3.9.1 | Message broker |
| PostgreSQL | 18.1 | Database |
| Flyway | 11.7.2 | Database migrations |
| springdoc-openapi | 2.8.6 | Swagger UI documentation |
| Lombok | 1.18.42 | Boilerplate reduction |
| MapStruct | 1.5.5 | Object mapping |
| Docker | latest | Running Kafka & PostgreSQL |

---

## Architecture

```
POST /api/v1/posts
        │
        ▼
  PostController
        │
        ▼
   PostService  ──► saves to ──► posts table (PostgreSQL)
        │
        ▼
  PostProducer  ──► publishes ──► Kafka topic: "posts"
                                        │
                          ┌─────────────┴─────────────┐
                          ▼                           ▼
                   FeedConsumer              NotificationConsumer
                  (feed-group)            (notification-group)
                          │                           │
                          ▼                           ▼
               feed_items table            notifications table
```

---

## Prerequisites

- Java 17+
- Maven
- Docker Desktop

---

## Getting Started

### 1. Start Infrastructure (Docker)

Run Kafka:
```bash
docker run -d --name kafka -p 9092:9092 -e KAFKA_NODE_ID=1 -e KAFKA_PROCESS_ROLES=broker,controller -e KAFKA_CONTROLLER_QUORUM_VOTERS=1@localhost:9093 -e KAFKA_LISTENERS=PLAINTEXT://0.0.0.0:9092,CONTROLLER://0.0.0.0:9093 -e KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://localhost:9092 -e KAFKA_LISTENER_SECURITY_PROTOCOL_MAP=PLAINTEXT:PLAINTEXT,CONTROLLER:PLAINTEXT -e KAFKA_CONTROLLER_LISTENER_NAMES=CONTROLLER -e KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR=1 -e CLUSTER_ID=Mk3OEYBSD34fcwNTJENDLw apache/kafka:3.7.0
```

Run PostgreSQL:
```bash
docker run -d --name postgres -p 5432:5432 -e POSTGRES_DB=social_db -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres postgres:15
```

Verify both are running:
```bash
docker ps
```

You should see both `kafka` and `postgres` with status `Up`.

### 2. Run the Application

```bash
mvn spring-boot:run
```

Or run `Sis6Application.java` directly from IntelliJ.

### 3. Open Swagger UI

```
http://localhost:8080/swagger-ui.html
```

---

## API Endpoints

### Create a Post
```
POST /api/v1/posts
```
Request body:
```json
{
  "userId": "user-42",
  "content": "Hello Kafka!",
  "hashtags": ["kafka", "spring"]
}
```
Response `201 Created`:
```json
{
  "id": "164078eb-ac20-47aa-b5b7-53871e7943a9",
  "userId": "user-42",
  "content": "Hello Kafka!",
  "hashtags": ["kafka", "spring"],
  "status": "PUBLISHED",
  "createdAt": "2026-03-11T16:39:25"
}
```

### Get Post by ID
```
GET /api/v1/posts/{postId}
```

### Get Feed for a User
```
GET /api/v1/feed?userId=user-42
```

---

## Database Schema

### posts
| Column | Type | Description |
|---|---|---|
| id | UUID | Primary key |
| user_id | VARCHAR(100) | Author of the post |
| content | TEXT | Post content |
| hashtags | TEXT | Comma-separated hashtags |
| status | VARCHAR(50) | PUBLISHED |
| created_at | TIMESTAMP | Creation time |

### feed_items
| Column | Type | Description |
|---|---|---|
| id | UUID | Primary key |
| post_id | UUID | Reference to original post |
| user_id | VARCHAR(100) | User who the feed item belongs to |
| content | TEXT | Post content copy |
| hashtags | TEXT | Hashtags copy |
| created_at | TIMESTAMP | Creation time |

### notifications
| Column | Type | Description |
|---|---|---|
| id | UUID | Primary key |
| post_id | UUID | Reference to triggering post |
| user_id | VARCHAR(100) | User being notified |
| message | TEXT | Notification message |
| created_at | TIMESTAMP | Creation time |

---

## Kafka Configuration

| Setting | Value |
|---|---|
| Bootstrap server | localhost:9092 |
| Topic | posts |
| Producer serializer | JsonSerializer |
| Consumer deserializer | JsonDeserializer |
| Feed consumer group | feed-group |
| Notification consumer group | notification-group |
| Trusted packages | * |

---

## Project Structure

```
src/main/java/kz/kbtu/sis6/
├── Sis6Application.java
├── post/
│   ├── controller/    PostController.java
│   ├── service/       PostService.java
│   ├── kafka/         PostProducer.java
│   ├── repository/    PostRepository.java
│   ├── entity/        Post.java
│   ├── dto/           CreatePostRequest.java, PostResponse.java
│   └── event/         PostCreatedEvent.java
├── feed/
│   ├── controller/    FeedController.java
│   ├── kafka/         FeedConsumer.java
│   ├── repository/    FeedItemRepository.java
│   ├── entity/        FeedItem.java
│   └── event/         PostCreatedEvent.java
├── notification/
│   ├── kafka/         NotificationConsumer.java
│   ├── repository/    NotificationRepository.java
│   └── entity/        Notification.java
└── config/
    └── OpenApiConfig.java

src/main/resources/
├── application.yml
└── db/migration/
    ├── V1__create_posts_table.sql
    ├── V2__create_feed_items_table.sql
    └── V3__create_notifications_table.sql
```

---

## How It Works

1. Client sends `POST /api/v1/posts` with userId and content
2. `PostService` validates and saves the post to the `posts` table
3. `PostProducer` publishes a `PostCreatedEvent` to Kafka topic `posts`
4. Kafka delivers the event to **both** consumers simultaneously:
   - `FeedConsumer` (group: `feed-group`) saves a record to `feed_items`
   - `NotificationConsumer` (group: `notification-group`) saves a record to `notifications`
5. Client receives `201 Created` with the post data

The post service does **not** call the feed or notification logic directly — it only publishes an event. This is **event-driven architecture**: services are decoupled and communicate only through Kafka.

---

## Troubleshooting

**Kafka connection refused on startup**
```
Connection to node -1 (localhost/127.0.0.1:9092) could not be established
```
→ Docker is not running or the kafka container is stopped. Run `docker ps` and start the container.

**Swagger UI shows 500 error**
```
NoSuchMethodError: ControllerAdviceBean.<init>
```
→ Wrong springdoc version. Make sure pom.xml has `springdoc-openapi-starter-webmvc-ui` version `2.8.6`.

**Flyway migration fails**
```
Found more than one migration with version 1
```
→ Check that migration files use double underscores: `V1__create_posts_table.sql`

---

## Author

Aigerim Manat | 23B031116 — KBTU, Spring 2026