// ignore: unused_import
import 'package:intl/intl.dart' as intl;
import 'app_localizations.dart';

// ignore_for_file: type=lint

/// The translations for English (`en`).
class AppLocalizationsEn extends AppLocalizations {
  AppLocalizationsEn([String locale = 'en']) : super(locale);

  @override
  String get appTitle => 'DROID POKER OF DICE';

  @override
  String get hold => 'HOLD';

  @override
  String get enterScore => 'ENTER SCORE';

  @override
  String get checkStatus => 'CHECK STATUS';

  @override
  String get roll => 'ROLL';

  @override
  String get scoreAces => 'Aces';

  @override
  String get scoreTwos => 'Twos';

  @override
  String get scoreThrees => 'Threes';

  @override
  String get scoreFours => 'Fours';

  @override
  String get scoreFives => 'Fives';

  @override
  String get scoreSixes => 'Sixes';

  @override
  String get score3Kind => '3 of a kind';

  @override
  String get score4Kind => '4 of a kind';

  @override
  String get scoreFullHouse => 'Full House';

  @override
  String get scoreSmallStraight => 'Small Straight';

  @override
  String get scoreLargeStraight => 'Large Straight';

  @override
  String get scoreChance => 'Chance';

  @override
  String get scoreDiceX => '5 of a kind';

  @override
  String get msgPicked => 'Already picked, cannot pick this one!!';

  @override
  String get msgBonus => 'BONUS35';

  @override
  String get msgScore => 'SCORE:';

  @override
  String get msgRollValue => 'ROLL VALUE:';

  @override
  String get msgGameOver => 'GAME OVER';

  @override
  String get msgResult => 'YOUR SCORE:';

  @override
  String get msgExport => 'EXPORT RESULT';

  @override
  String get msgStatus => 'STATUS';

  @override
  String get msgBonusGet => 'BONUS GET 35';

  @override
  String get menuExport => 'EXPORT YOUR SCORE';

  @override
  String get menuChart => 'SCORE HISTORY CHART';

  @override
  String get menuReset => 'RESTART GAME';

  @override
  String get chartName => 'SCORE HISTORY';

  @override
  String get chartX => 'DATE';

  @override
  String get chartY => 'SCORE';

  @override
  String get chartPlot => 'SCORE';
}
