from flask import Flask, request, jsonify
from sentence_transformers import SentenceTransformer

app = Flask(__name__)

print("Loading BGE model...")

model = SentenceTransformer(
    "BAAI/bge-small-en-v1.5"
)

print("Model loaded!")

@app.route("/embed", methods=["POST"])
def embed():

    data = request.json

    text = data["text"]

    vector = model.encode(text).tolist()

    return jsonify({
        "embedding": vector
    })

if __name__ == "__main__":

    app.run(
        host="0.0.0.0",
        port=5000
    )