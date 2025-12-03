// ignore: unused_import
import 'package:intl/intl.dart' as intl;
import 'app_localizations.dart';

// ignore_for_file: type=lint

/// The translations for Japanese (`ja`).
class AppLocalizationsJa extends AppLocalizations {
  AppLocalizationsJa([String locale = 'ja']) : super(locale);

  @override
  String get appTitle => 'DROID POKER OF DICE';

  @override
  String get hold => 'ホールド';

  @override
  String get enterScore => 'スコア入力';

  @override
  String get checkStatus => 'ステータス';

  @override
  String get roll => 'ロール';

  @override
  String get scoreAces => 'エース(1)';

  @override
  String get scoreTwos => 'デュース(2)';

  @override
  String get scoreThrees => 'トレイ(3)';

  @override
  String get scoreFours => 'フォー(4)';

  @override
  String get scoreFives => 'ファイブ(5)';

  @override
  String get scoreSixes => 'シックス(6)';

  @override
  String get score3Kind => 'スリーカード';

  @override
  String get score4Kind => 'フォーカード';

  @override
  String get scoreFullHouse => 'フルハウス';

  @override
  String get scoreSmallStraight => '小ストレート';

  @override
  String get scoreLargeStraight => '大ストレート';

  @override
  String get scoreChance => 'チャンス';

  @override
  String get scoreDiceX => 'DiceX';

  @override
  String get msgPicked => '選択済みです';

  @override
  String get msgBonus => 'ボーナス35';

  @override
  String get msgScore => 'スコア:';

  @override
  String get msgRollValue => 'ロール値:';

  @override
  String get msgGameOver => 'ゲームオーバー';

  @override
  String get msgResult => 'あなたのスコア:';

  @override
  String get msgExport => '結果出力';

  @override
  String get msgStatus => 'ステータス';

  @override
  String get msgBonusGet => 'ボーナス35ゲット';

  @override
  String get menuExport => 'スコア出力';

  @override
  String get menuChart => 'スコア履歴';

  @override
  String get menuReset => 'リセット';

  @override
  String get chartName => 'スコア履歴';

  @override
  String get chartX => '日付';

  @override
  String get chartY => 'スコア';

  @override
  String get chartPlot => 'スコア';
}
