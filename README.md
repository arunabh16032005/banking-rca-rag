# Banking RCA RAG System

AI Enabled Enterprise Software Operations with Monitoring and RCA Detection

## Overview

This project implements a Retrieval Augmented Generation (RAG) system for banking incident Root Cause Analysis (RCA).

The system:

1. Stores historical RCA incidents in a knowledge base
2. Splits documents into chunks
3. Generates embeddings using BGE model
4. Stores vectors in Elasticsearch
5. Performs semantic search on user queries
6. Retrieves relevant RCA documents
7. Builds a RAG prompt
8. Sends prompt to LLM (Anthropic/Gemma)
9. Returns RCA answer

---

## Technology Stack

### Backend

- Java 21
- Spring Boot 3.5.0
- Maven

### Messaging

- Apache Kafka

### Vector Database

- Elasticsearch 9.4.1

### Embeddings

- BAAI/bge-small-en-v1.5
- Embedding Dimension = 384

### AI Components

- Python 3.12.4
- sentence-transformers

---

## Project Structure

kafka-demo-1/

├── knowledge-base/

├── python/

├── src/

├── embeddings.json

├── pom.xml

---

## Knowledge Base

Location:

knowledge-base/

Contains RCA documents such as:

- database_connection_refused.txt
- database_pool.txt
- cpu_spike.txt
- network_timeout.txt
- redis_connection_failure.txt
- payment_gateway_timeout.txt

Total RCA files: 15

---

## Chunking

Documents are split into chunks.

Current chunk count:

74 chunks

Endpoint:

http://localhost:8080/kb/chunk-count

---

## Embedding Generation

Model:

BAAI/bge-small-en-v1.5

Dimension:

384

Generate embeddings:

python generate_embeddings.py

Output:

embeddings.json

---

## Elasticsearch Setup

Version:

9.4.1

Index:

knowledge-vectors

Vector Type:

dense_vector

Dimensions:

384

Similarity:

cosine

Verify:

http://localhost:9200/_cat/indices?v

---

## Upload Embeddings

Run:

python upload_embeddings.py

Uploads embeddings.json into Elasticsearch.

Verify:

http://localhost:9200/knowledge-vectors/_search

---

## Semantic Search

Run:

python semantic_search.py

Example Query:

database connection limit exceeded

Returns top matching RCA documents.

---

## Embedding API

Run:

python embedding_api.py

Server:

http://localhost:5000

Endpoint:

POST /embed

Input:

{
  "text": "database timeout"
}

Output:

{
  "embedding": [...]
}

---

## Spring Boot APIs

### Generate Embedding

GET

/api/rag/embed?query=database timeout

---

### Vector Search

GET

/api/rag/search?query=database timeout

Returns top RCA matches.

---

### RAG Prompt Builder

GET

/api/rag/ask?question=database timeout

Builds prompt using retrieved RCA documents.

---

## Current Status

Completed:

✔ Knowledge Base

✔ Chunking

✔ Embeddings

✔ Elasticsearch

✔ Vector Search

✔ RAG Retrieval

✔ Prompt Builder

Future Scope:

✖ Anthropic/Gemma Integration

✖ 30 Minute Log Context

✖ Final RCA Generation


## Additional Information:

Clone Repository:

git clone https://github.com/arunabh16032005/banking-rca-rag.git

Install:

- Java 21
- Maven
- Python 3.12
- Elasticsearch 9.4.1

Run:

python embedding_api.py

Start Elasticsearch

Start Spring Boot

Verify:

/api/rag/embed

/api/rag/search

/api/rag/ask

Next Step:

Connect some Anthropic/Gemma endpoint to RagAskService.
