from sentence_transformers import SentenceTransformer
import os
import json

# Load embedding model
print("Loading BGE model...")

model = SentenceTransformer(
    "BAAI/bge-small-en-v1.5"
)

print("Model loaded!")

# Folder containing knowledge files
KNOWLEDGE_BASE = r"C:\Users\Arunabh\Documents\workspace-spring-tools-for-eclipse-5.1.1.RELEASE\kafka-demo-1\knowledge-base"

all_chunks = []
chunk_id = 1

# Read all txt files
for filename in os.listdir(KNOWLEDGE_BASE):

    if filename.endswith(".txt"):

        filepath = os.path.join(
            KNOWLEDGE_BASE,
            filename
        )

        with open(
                filepath,
                "r",
                encoding="utf-8") as file:

            document = file.read()

        # Same chunking logic as Java
        sections = document.split("Affected Services:")

        for section in sections:

            chunk = section.strip()

            if chunk:

                print(
                    f"Embedding chunk {chunk_id}"
                )

                embedding = model.encode(
                    chunk
                ).tolist()

                all_chunks.append({
                    "chunkId":
                        f"chunk-{chunk_id}",
                    "file":
                        filename,
                    "content":
                        chunk,
                    "embedding":
                        embedding
                })

                chunk_id += 1

# Save output
with open(
        "embeddings.json",
        "w",
        encoding="utf-8") as outfile:

    json.dump(
        all_chunks,
        outfile,
        indent=2
    )

print(
    f"\nCreated {len(all_chunks)} embeddings"
)

print(
    "Saved to embeddings.json"
)