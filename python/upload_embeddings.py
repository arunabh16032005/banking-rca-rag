from elasticsearch import Elasticsearch
import json

# Connect to Elasticsearch

es = Elasticsearch(
    "http://localhost:9200"
)

# Read embeddings.json

with open(
        "embeddings.json",
        "r",
        encoding="utf-8") as file:

    embeddings = json.load(file)

count = 0

for chunk in embeddings:

    es.index(
        index="knowledge-vectors",
        document={
            "chunkId":
                chunk["chunkId"],

            "file":
                chunk["file"],

            "content":
                chunk["content"],

            "embedding":
                chunk["embedding"]
        }
    )

    count += 1

    print(
        f"Uploaded {count}"
    )

print(
    f"\nFinished Uploading {count} Documents"
)