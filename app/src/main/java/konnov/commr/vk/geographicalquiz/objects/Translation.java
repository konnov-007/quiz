package konnov.commr.vk.geographicalquiz.objects;

public class Translation {
    private int questionId;
    private String translationId;
    private String title;
    private String imgLocation;
    private String answerOne;
    private String answerTwo;
    private String answerThree;
    private String answerFour;
    private String wrongAnswerComment;

    public Translation(int questionId, String translationId, String title, String imgLocation, String answerOne, String answerTwo, String answerThree, String answerFour, String wrongAnswerComment) {
        this.questionId = questionId;
        this.translationId = translationId;
        this.title = title;
        this.imgLocation = imgLocation;
        this.answerOne = answerOne;
        this.answerTwo = answerTwo;
        this.answerThree = answerThree;
        this.answerFour = answerFour;
        this.wrongAnswerComment = wrongAnswerComment;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getTranslationId() {
        return translationId;
    }

    public void setTranslationId(String translationId) {
        this.translationId = translationId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgLocation() {
        return imgLocation;
    }

    public void setImgLocation(String imgLocation) {
        this.imgLocation = imgLocation;
    }

    public String getAnswerOne() {
        return answerOne;
    }

    public void setAnswerOne(String answerOne) {
        this.answerOne = answerOne;
    }

    public String getAnswerTwo() {
        return answerTwo;
    }

    public void setAnswerTwo(String answerTwo) {
        this.answerTwo = answerTwo;
    }

    public String getAnswerThree() {
        return answerThree;
    }

    public void setAnswerThree(String answerThree) {
        this.answerThree = answerThree;
    }

    public String getAnswerFour() {
        return answerFour;
    }

    public void setAnswerFour(String answerFour) {
        this.answerFour = answerFour;
    }

    public String getWrongAnswerComment() {
        return wrongAnswerComment;
    }

    public void setWrongAnswerComment(String wrongAnswerComment) {
        this.wrongAnswerComment = wrongAnswerComment;
    }

    @Override
    public String toString() {
        return "questionId: " + questionId +
                "translationId: " + translationId +
                "title: " + title +
                "imgLocation: " + imgLocation +
                "answerOne: " + answerOne +
                "answerTwo: " + answerTwo +
                "answerThree: " + answerThree +
                "answerFour: " + answerFour +
                "wrongAnswerComment: " + wrongAnswerComment;
    }
}
