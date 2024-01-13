package basic;

import java.util.List;

public class FunctionalProgramingDemo {
    public Double avgScore(List<StudentScore> studentScores) {
        Double sumScore = 0d;
        for (StudentScore studentScore : studentScores) {
            sumScore += studentScore.getScore();
        }
        return sumScore / studentScores.size();
    }

    public Double avgScoreFP(List<StudentScore> studentScores) {
        return studentScores.stream().map(StudentScore::getScore)
            .reduce((d1, d2) -> (d1 + d2) / 2).orElse(0d);
    }

    public void updateScore(StudentScore studentScore, Double newScore) {
        studentScore.setScore(newScore);
    }

    public StudentScore updatedScoreFP(StudentScore studentScore, Double newScore) {
        return new StudentScore(studentScore.id, studentScore.studentId, studentScore.courseId, studentScore.classId, newScore);
    }

    public static void main(String[] args) {

    }
}

class StudentScore {
    public String id;

    public String studentId;

    public String courseId;

    public String classId;

    public Double score;

    public StudentScore(String id, String studentId, String courseId, String classId, Double score) {
        this.id = id;
        this.studentId = studentId;
        this.courseId = courseId;
        this.classId = classId;
        this.score = score;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }
}
class StudentScoreSummary {
    public String courseId;

    public String classId;

    public Double avgScore;

    public StudentScoreSummary(String courseId, String classId, Double avgScore) {
        this.courseId = courseId;
        this.classId = classId;
        this.avgScore = avgScore;
    }

    public void setAvgScore(Double avgScore) {
        this.avgScore = avgScore;
    }

    public Double getAvgScore() {
        return avgScore;
    }
}