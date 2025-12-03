import 'package:sqflite/sqflite.dart';
import 'package:path/path.dart';
import 'score_entity.dart';

class DatabaseHelper {
  static final DatabaseHelper _instance = DatabaseHelper._internal();
  static Database? _database;

  factory DatabaseHelper() {
    return _instance;
  }

  DatabaseHelper._internal();

  Future<Database> get database async {
    if (_database != null) return _database!;
    _database = await _initDatabase();
    return _database!;
  }

  Future<Database> _initDatabase() async {
    String path = join(await getDatabasesPath(), 'DiceX.db');
    return await openDatabase(
      path,
      version: 1,
      onCreate: _onCreate,
    );
  }

  Future<void> _onCreate(Database db, int version) async {
    await db.execute(
      'CREATE TABLE DiceXScore(scoredate TEXT PRIMARY KEY, score TEXT NOT NULL)',
    );
  }

  Future<int> insertScore(ScoreEntity score) async {
    Database db = await database;
    return await db.insert('DiceXScore', score.toMap());
  }

  Future<List<ScoreEntity>> getAllScores() async {
    Database db = await database;
    final List<Map<String, dynamic>> maps = await db.query('DiceXScore');
    return List.generate(maps.length, (i) {
      return ScoreEntity.fromMap(maps[i]);
    });
  }

  Future<void> deleteAllScores() async {
    Database db = await database;
    await db.delete('DiceXScore');
  }
}
