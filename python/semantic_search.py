from sentence_transformers import SentenceTransformer
from elasticsearch import Elasticsearch

# Connect Elasticsearch

es = Elasticsearch(
    "http://localhost:9200"
)

# Load BGE model

model = SentenceTransformer(
    "BAAI/bge-small-en-v1.5"
)

query = input(
    "Enter Query: "
)

# Generate query embedding

query_vector = model.encode(
    query
).tolist()

# KNN Search

response = es.search(
    index="knowledge-vectors",
    knn={
        "field": "embedding",
        "query_vector": query_vector,
        "k": 5,
        "num_candidates": 20
    }
)

print("\nTop Matches:\n")

for hit in response["hits"]["hits"]:

    print("=" * 50)

    print(
        "Score:",
        hit["_score"]
    )

    print(
        hit["_source"]["file"]
    )

    print(
        hit["_source"]["content"]
    )