from sentence_transformers import SentenceTransformer

print("Loading model...")

model = SentenceTransformer(
    "BAAI/bge-small-en-v1.5"
)

print("Model loaded!")

text = """
Root Cause:
HikariCP pool exhausted
"""

embedding = model.encode(text)

print("Embedding Dimension =", len(embedding))

print("\nFirst 10 values:")

print(embedding[:10])