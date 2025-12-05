class ScoreEntity {
  final String scoreDate;
  final String score;

  ScoreEntity({required this.scoreDate, required this.score});

  Map<String, dynamic> toMap() {
    return {
      'scoredate': scoreDate,
      'score': score,
    };
  }

  factory ScoreEntity.fromMap(Map<String, dynamic> map) {
    return ScoreEntity(
      scoreDate: map['scoredate'],
      score: map['score'],
    );
  }
}
