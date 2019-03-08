package konnov.commr.vk.geographicalquiz.objects;

public class Question {
    private int questionId;
    private int difficulty;
    private int rightAnswer;

    public Question(int questionId, int difficulty, int rightAnswer) {
        this.questionId = questionId;
        this.difficulty = difficulty;
        this.rightAnswer = rightAnswer;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public int getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(int rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    @Override
    public String toString() {
        return "question id: " + questionId + ", " +
                " difficulty: " + difficulty + ", " +
                " right answer: " + rightAnswer;
    }
}
