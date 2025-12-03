import 'dart:math';
import 'package:flutter/foundation.dart';
import '../data/database_helper.dart';
import '../data/score_entity.dart';

enum GameStatus { title, playing, end }

class GameProvider with ChangeNotifier {
  // Constants for score types
  static const int SCR_1 = 0;
  static const int SCR_2 = 1;
  static const int SCR_3 = 2;
  static const int SCR_4 = 3;
  static const int SCR_5 = 4;
  static const int SCR_6 = 5;
  static const int SCR_3CARD = 6;
  static const int SCR_4CARD = 7;
  static const int SCR_FULL = 8;
  static const int SCR_SSTGHT = 9;
  static const int SCR_LSTGHT = 10;
  static const int SCR_CHANCE = 11;
  static const int SCR_DICEX = 12;

  // Pair check constants
  static const int PCK_3CARD = 1;
  static const int PCK_4CARD = 2;
  static const int PCK_FULL = 3;
  static const int PCK_DICEX = 4;

  final Random _rnd = Random();
  final DatabaseHelper _dbHelper = DatabaseHelper();

  GameStatus _gameStatus = GameStatus.title;
  int _chance = 3;
  List<int> _cardNum = List.filled(5, 0);
  int _currentScoreChecked = 0;
  int _selectedScr1 = 0;
  int _selectedScr2 = 0;
  int _gameLeft = 13;
  int _bonusCount = 0;
  int _total = 0;
  List<bool> _hold = List.filled(5, false);
  List<bool> _scoreUsed = List.filled(13, false);
  List<int> _scoreGet = List.filled(13, 0);
  bool _bonusFlag = false;

  // Getters
  GameStatus get gameStatus => _gameStatus;
  int get chance => _chance;
  List<int> get cardNum => _cardNum;
  int get currentScoreChecked => _currentScoreChecked;
  int get selectedScr1 => _selectedScr1;
  int get selectedScr2 => _selectedScr2;
  int get total => _total;
  List<bool> get hold => _hold;
  List<bool> get scoreUsed => _scoreUsed;
  List<int> get scoreGet => _scoreGet;
  bool get bonusFlag => _bonusFlag;
  int get gameLeft => _gameLeft;

  void init() {
    _hold = List.filled(5, false);
    _scoreUsed = List.filled(13, false);
    _scoreGet = List.filled(13, 0);
    _cardNum = List.filled(5, 0);
    _gameStatus = GameStatus.title;
    _bonusCount = 0;
    _total = 0;
    _chance = 3;
    _gameLeft = 13;
    _bonusFlag = false;
    _currentScoreChecked = 0;

    flushCards();
    checkScore1();
    checkScore2();
    notifyListeners();
  }

  void flushCards() {
    for (int i = 0; i < 5; i++) {
      if (!_hold[i]) {
        _cardNum[i] = _rnd.nextInt(6);
      }
    }
  }

  void rollDice() {
    flushCards();
    _chance--;
    checkScore1();
    checkScore2();
    notifyListeners();
  }

  void setHold(int index, bool value) {
    _hold[index] = value;
    notifyListeners();
  }

  void setCurrentScore(int index) {
    _currentScoreChecked = index;
    checkScore1();
    checkScore2();
    notifyListeners();
  }

  void checkScore1() {
    if (_currentScoreChecked == SCR_1) {
      _selectedScr1 = _addNumbersScore(1, SCR_1, _cardNum);
    } else if (_currentScoreChecked == SCR_2) {
      _selectedScr1 = _addNumbersScore(2, SCR_2, _cardNum);
    } else if (_currentScoreChecked == SCR_3) {
      _selectedScr1 = _addNumbersScore(3, SCR_3, _cardNum);
    } else if (_currentScoreChecked == SCR_4) {
      _selectedScr1 = _addNumbersScore(4, SCR_4, _cardNum);
    } else if (_currentScoreChecked == SCR_5) {
      _selectedScr1 = _addNumbersScore(5, SCR_5, _cardNum);
    } else if (_currentScoreChecked == SCR_6) {
      _selectedScr1 = _addNumbersScore(6, SCR_6, _cardNum);
    } else {
      _selectedScr1 = 0;
    }
  }

  int _addNumbersScore(int actScr, int cdNum, List<int> inscr) {
    int scre = 0;
    for (int i = 0; i < 5; i++) {
      if (inscr[i] == cdNum) {
        scre += actScr;
      }
    }
    return scre;
  }

  void checkScore2() {
    List<int> subscr = List.from(_cardNum);
    
    // Bubble sort
    for (int i = 0; i < 4; i++) {
      for (int ii = 0; ii < 4; ii++) {
        int first = subscr[ii];
        int second = subscr[ii + 1];
        if (first > second) {
          subscr[ii] = second;
          subscr[ii + 1] = first;
        }
      }
    }

    int pairChkdStatus = _rtnCheckPair(subscr);
    int hldScr = 0;

    if (_currentScoreChecked == SCR_3CARD) {
      if (pairChkdStatus == PCK_3CARD ||
          pairChkdStatus == PCK_4CARD ||
          pairChkdStatus == PCK_FULL ||
          pairChkdStatus == PCK_DICEX) {
        for (int i = 0; i < 5; i++) {
          hldScr += subscr[i] + 1;
        }
        _selectedScr2 = hldScr;
      } else {
        _selectedScr2 = 0;
      }
    } else if (_currentScoreChecked == SCR_4CARD) {
      if (pairChkdStatus == PCK_4CARD || pairChkdStatus == PCK_DICEX) {
        for (int i = 0; i < 5; i++) {
          hldScr += subscr[i] + 1;
        }
        _selectedScr2 = hldScr;
      } else {
        _selectedScr2 = 0;
      }
    } else if (_currentScoreChecked == SCR_FULL) {
      _selectedScr2 = pairChkdStatus == PCK_FULL ? 25 : 0;
    } else if (_currentScoreChecked == SCR_SSTGHT) {
      int smStraightFlag = 0;
      for (int i = 0; i < 4; i++) {
        if (subscr[i] == subscr[i + 1] - 1) {
          smStraightFlag++;
        }
      }
      _selectedScr2 = smStraightFlag > 2 ? 30 : 0;
    } else if (_currentScoreChecked == SCR_LSTGHT) {
      int lgStraightFlag = 0;
      for (int i = 0; i < 4; i++) {
        if (subscr[i] == subscr[i + 1] - 1) {
          lgStraightFlag++;
        }
      }
      _selectedScr2 = lgStraightFlag == 4 ? 40 : 0;
    } else if (_currentScoreChecked == SCR_CHANCE) {
      for (int i = 0; i < 5; i++) {
        hldScr += subscr[i] + 1;
      }
      _selectedScr2 = hldScr;
    } else if (_currentScoreChecked == SCR_DICEX) {
      _selectedScr2 = pairChkdStatus == PCK_DICEX ? 50 : 0;
    } else {
      _selectedScr2 = 0;
    }
  }

  int _rtnCheckPair(List<int> incd) {
    int pair = 0;
    bool threeCardFlag = false;
    bool fourCardFlag = false;
    bool yahtFlag = false;

    for (int i = 0; i < 4; i++) {
      if (incd[i] == incd[i + 1]) {
        pair++;
        if (pair == 1 && i + 2 < 5 && incd[i] == incd[i + 2]) {
          threeCardFlag = true;
          if (threeCardFlag && i + 3 < 5 && incd[i] == incd[i + 3]) {
            fourCardFlag = true;
            if (fourCardFlag && i + 4 < 5 && incd[i] == incd[i + 4]) {
              yahtFlag = true;
            }
          }
        }
      }
    }

    if (yahtFlag) return PCK_DICEX;
    if (fourCardFlag) return PCK_4CARD;
    if (!fourCardFlag && pair == 3) return PCK_FULL;
    if (threeCardFlag) return PCK_3CARD;
    return 0;
  }

  Future<void> enterScore() async {
    if (!_scoreUsed[_currentScoreChecked]) {
      _total += _selectedScr1 + _selectedScr2;
      _scoreGet[_currentScoreChecked] = _selectedScr1 + _selectedScr2;
      _bonusCount += _selectedScr1;

      if (_bonusCount > 62 && !_bonusFlag) {
        _total += 35;
        _bonusFlag = true;
      }

      _gameLeft--;
      _scoreUsed[_currentScoreChecked] = true;
      _chance = 3;

      // Find next unused score
      while (_scoreUsed[_currentScoreChecked] && _currentScoreChecked < SCR_DICEX) {
        _currentScoreChecked++;
      }

      // Reset hold
      _hold = List.filled(5, false);

      // Roll new dice
      flushCards();
      checkScore1();
      checkScore2();

      if (_gameLeft == 0) {
        _gameStatus = GameStatus.end;
        await _saveScore(_total);
      }

      notifyListeners();
    }
  }

  Future<void> _saveScore(int score) async {
    String scoreDate = DateTime.now().millisecondsSinceEpoch.toString();
    ScoreEntity entity = ScoreEntity(
      scoreDate: scoreDate,
      score: score.toString(),
    );
    await _dbHelper.insertScore(entity);
  }

  Future<List<ScoreEntity>> getScoreHistory() async {
    return await _dbHelper.getAllScores();
  }
}
