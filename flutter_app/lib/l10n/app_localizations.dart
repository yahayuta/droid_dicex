import 'dart:async';

import 'package:flutter/foundation.dart';
import 'package:flutter/widgets.dart';
import 'package:flutter_localizations/flutter_localizations.dart';
import 'package:intl/intl.dart' as intl;

import 'app_localizations_en.dart';
import 'app_localizations_ja.dart';

// ignore_for_file: type=lint

/// Callers can lookup localized strings with an instance of AppLocalizations
/// returned by `AppLocalizations.of(context)`.
///
/// Applications need to include `AppLocalizations.delegate()` in their app's
/// `localizationDelegates` list, and the locales they support in the app's
/// `supportedLocales` list. For example:
///
/// ```dart
/// import 'l10n/app_localizations.dart';
///
/// return MaterialApp(
///   localizationsDelegates: AppLocalizations.localizationsDelegates,
///   supportedLocales: AppLocalizations.supportedLocales,
///   home: MyApplicationHome(),
/// );
/// ```
///
/// ## Update pubspec.yaml
///
/// Please make sure to update your pubspec.yaml to include the following
/// packages:
///
/// ```yaml
/// dependencies:
///   # Internationalization support.
///   flutter_localizations:
///     sdk: flutter
///   intl: any # Use the pinned version from flutter_localizations
///
///   # Rest of dependencies
/// ```
///
/// ## iOS Applications
///
/// iOS applications define key application metadata, including supported
/// locales, in an Info.plist file that is built into the application bundle.
/// To configure the locales supported by your app, you’ll need to edit this
/// file.
///
/// First, open your project’s ios/Runner.xcworkspace Xcode workspace file.
/// Then, in the Project Navigator, open the Info.plist file under the Runner
/// project’s Runner folder.
///
/// Next, select the Information Property List item, select Add Item from the
/// Editor menu, then select Localizations from the pop-up menu.
///
/// Select and expand the newly-created Localizations item then, for each
/// locale your application supports, add a new item and select the locale
/// you wish to add from the pop-up menu in the Value field. This list should
/// be consistent with the languages listed in the AppLocalizations.supportedLocales
/// property.
abstract class AppLocalizations {
  AppLocalizations(String locale)
    : localeName = intl.Intl.canonicalizedLocale(locale.toString());

  final String localeName;

  static AppLocalizations? of(BuildContext context) {
    return Localizations.of<AppLocalizations>(context, AppLocalizations);
  }

  static const LocalizationsDelegate<AppLocalizations> delegate =
      _AppLocalizationsDelegate();

  /// A list of this localizations delegate along with the default localizations
  /// delegates.
  ///
  /// Returns a list of localizations delegates containing this delegate along with
  /// GlobalMaterialLocalizations.delegate, GlobalCupertinoLocalizations.delegate,
  /// and GlobalWidgetsLocalizations.delegate.
  ///
  /// Additional delegates can be added by appending to this list in
  /// MaterialApp. This list does not have to be used at all if a custom list
  /// of delegates is preferred or required.
  static const List<LocalizationsDelegate<dynamic>> localizationsDelegates =
      <LocalizationsDelegate<dynamic>>[
        delegate,
        GlobalMaterialLocalizations.delegate,
        GlobalCupertinoLocalizations.delegate,
        GlobalWidgetsLocalizations.delegate,
      ];

  /// A list of this localizations delegate's supported locales.
  static const List<Locale> supportedLocales = <Locale>[
    Locale('en'),
    Locale('ja'),
  ];

  /// No description provided for @appTitle.
  ///
  /// In en, this message translates to:
  /// **'DROID POKER OF DICE'**
  String get appTitle;

  /// No description provided for @hold.
  ///
  /// In en, this message translates to:
  /// **'HOLD'**
  String get hold;

  /// No description provided for @enterScore.
  ///
  /// In en, this message translates to:
  /// **'ENTER SCORE'**
  String get enterScore;

  /// No description provided for @checkStatus.
  ///
  /// In en, this message translates to:
  /// **'CHECK STATUS'**
  String get checkStatus;

  /// No description provided for @roll.
  ///
  /// In en, this message translates to:
  /// **'ROLL'**
  String get roll;

  /// No description provided for @scoreAces.
  ///
  /// In en, this message translates to:
  /// **'Aces'**
  String get scoreAces;

  /// No description provided for @scoreTwos.
  ///
  /// In en, this message translates to:
  /// **'Twos'**
  String get scoreTwos;

  /// No description provided for @scoreThrees.
  ///
  /// In en, this message translates to:
  /// **'Threes'**
  String get scoreThrees;

  /// No description provided for @scoreFours.
  ///
  /// In en, this message translates to:
  /// **'Fours'**
  String get scoreFours;

  /// No description provided for @scoreFives.
  ///
  /// In en, this message translates to:
  /// **'Fives'**
  String get scoreFives;

  /// No description provided for @scoreSixes.
  ///
  /// In en, this message translates to:
  /// **'Sixes'**
  String get scoreSixes;

  /// No description provided for @score3Kind.
  ///
  /// In en, this message translates to:
  /// **'3 of a kind'**
  String get score3Kind;

  /// No description provided for @score4Kind.
  ///
  /// In en, this message translates to:
  /// **'4 of a kind'**
  String get score4Kind;

  /// No description provided for @scoreFullHouse.
  ///
  /// In en, this message translates to:
  /// **'Full House'**
  String get scoreFullHouse;

  /// No description provided for @scoreSmallStraight.
  ///
  /// In en, this message translates to:
  /// **'Small Straight'**
  String get scoreSmallStraight;

  /// No description provided for @scoreLargeStraight.
  ///
  /// In en, this message translates to:
  /// **'Large Straight'**
  String get scoreLargeStraight;

  /// No description provided for @scoreChance.
  ///
  /// In en, this message translates to:
  /// **'Chance'**
  String get scoreChance;

  /// No description provided for @scoreDiceX.
  ///
  /// In en, this message translates to:
  /// **'5 of a kind'**
  String get scoreDiceX;

  /// No description provided for @msgPicked.
  ///
  /// In en, this message translates to:
  /// **'Already picked, cannot pick this one!!'**
  String get msgPicked;

  /// No description provided for @msgBonus.
  ///
  /// In en, this message translates to:
  /// **'BONUS35'**
  String get msgBonus;

  /// No description provided for @msgScore.
  ///
  /// In en, this message translates to:
  /// **'SCORE:'**
  String get msgScore;

  /// No description provided for @msgRollValue.
  ///
  /// In en, this message translates to:
  /// **'ROLL VALUE:'**
  String get msgRollValue;

  /// No description provided for @msgGameOver.
  ///
  /// In en, this message translates to:
  /// **'GAME OVER'**
  String get msgGameOver;

  /// No description provided for @msgResult.
  ///
  /// In en, this message translates to:
  /// **'YOUR SCORE:'**
  String get msgResult;

  /// No description provided for @msgExport.
  ///
  /// In en, this message translates to:
  /// **'EXPORT RESULT'**
  String get msgExport;

  /// No description provided for @msgStatus.
  ///
  /// In en, this message translates to:
  /// **'STATUS'**
  String get msgStatus;

  /// No description provided for @msgBonusGet.
  ///
  /// In en, this message translates to:
  /// **'BONUS GET 35'**
  String get msgBonusGet;

  /// No description provided for @menuExport.
  ///
  /// In en, this message translates to:
  /// **'EXPORT YOUR SCORE'**
  String get menuExport;

  /// No description provided for @menuChart.
  ///
  /// In en, this message translates to:
  /// **'SCORE HISTORY CHART'**
  String get menuChart;

  /// No description provided for @menuReset.
  ///
  /// In en, this message translates to:
  /// **'RESTART GAME'**
  String get menuReset;

  /// No description provided for @chartName.
  ///
  /// In en, this message translates to:
  /// **'SCORE HISTORY'**
  String get chartName;

  /// No description provided for @chartX.
  ///
  /// In en, this message translates to:
  /// **'DATE'**
  String get chartX;

  /// No description provided for @chartY.
  ///
  /// In en, this message translates to:
  /// **'SCORE'**
  String get chartY;

  /// No description provided for @chartPlot.
  ///
  /// In en, this message translates to:
  /// **'SCORE'**
  String get chartPlot;
}

class _AppLocalizationsDelegate
    extends LocalizationsDelegate<AppLocalizations> {
  const _AppLocalizationsDelegate();

  @override
  Future<AppLocalizations> load(Locale locale) {
    return SynchronousFuture<AppLocalizations>(lookupAppLocalizations(locale));
  }

  @override
  bool isSupported(Locale locale) =>
      <String>['en', 'ja'].contains(locale.languageCode);

  @override
  bool shouldReload(_AppLocalizationsDelegate old) => false;
}

AppLocalizations lookupAppLocalizations(Locale locale) {
  // Lookup logic when only language code is specified.
  switch (locale.languageCode) {
    case 'en':
      return AppLocalizationsEn();
    case 'ja':
      return AppLocalizationsJa();
  }

  throw FlutterError(
    'AppLocalizations.delegate failed to load unsupported locale "$locale". This is likely '
    'an issue with the localizations generation tool. Please file an issue '
    'on GitHub with a reproducible sample app and the gen-l10n configuration '
    'that was used.',
  );
}
