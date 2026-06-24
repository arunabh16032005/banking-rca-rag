async function askQuestion() {

    const question =
        document.getElementById(
            "question"
        ).value;

    if(question.trim() === "") {

        alert(
            "Please enter a question"
        );

        return;
    }

    document.getElementById(
        "answerBox"
    ).innerHTML =
        "Generating answer...";

    try {

        const response =
            await fetch(
                "/api/rag/ask?question="
                + encodeURIComponent(
                    question
                )
            );

        const data =
            await response.json();

        document.getElementById(
            "answerBox"
        ).innerText =
            data.answer;

    }
    catch(error) {

        document.getElementById(
            "answerBox"
        ).innerHTML =
            "Error generating answer";

        console.error(error);
    }
}