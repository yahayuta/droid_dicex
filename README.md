# DroidDiceX - Dice Poker Game

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Android](https://img.shields.io/badge/Android-API%2035-green.svg)](https://developer.android.com/about/versions/15)
[![Flutter](https://img.shields.io/badge/Flutter-3.9+-blue.svg)](https://flutter.dev)
[![Java](https://img.shields.io/badge/Java-17%20%7C%2021-orange.svg)](https://www.oracle.com/java/)

A classic dice poker game available in both native Android and cross-platform Flutter versions, featuring Yahtzee-style gameplay with modern UI and score tracking capabilities.

## 🎲 Features

- **Classic Dice Poker Gameplay**: Roll 5 dice and score combinations like Yahtzee
- **Multiple Scoring Categories**: 
  - Aces, Twos, Threes, Fours, Fives, Sixes
  - 3 of a Kind, 4 of a Kind, Full House
  - Small Straight, Large Straight
  - Chance, 5 of a Kind (DiceX)
- **Score Tracking**: Save and export your game results
- **Bonus System**: Earn bonus points for high upper section scores
- **Hold Feature**: Keep specific dice between rolls
- **Bilingual Support**: English and Japanese language support
- **Cross-Platform**: Available as native Android and Flutter apps

## 📱 Available Versions

### Flutter (Recommended)
Cross-platform version supporting Android, iOS, Web, and Desktop.

### Native Android
Original Android-only version with Java.

## 🚀 Getting Started

### Flutter Version

#### Prerequisites
- Flutter SDK 3.9+
- Dart 3.0+
- Android Studio or VS Code with Flutter extensions

#### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/droid_dicex.git
   cd droid_dicex/flutter_app
   ```

2. **Install dependencies**
   ```bash
   flutter pub get
   ```

3. **Run the app**
   ```bash
   flutter run
   ```

### Android Version

#### Prerequisites
- Android Studio Hedgehog or later
- Android SDK API 35 (Android 15)
- JDK 17 or 21 (LTS versions recommended)
- Gradle 8.9

#### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/droid_dicex.git
   cd droid_dicex
   ```

2. **Open in Android Studio**
   - Launch Android Studio
   - Select "Open an existing Android Studio project"
   - Navigate to the cloned directory and select the `android` folder

3. **Build and Run**
   - Connect an Android device or start an emulator
   - Click the "Run" button (green play icon) in Android Studio
   - The app will install and launch on your device

#### Building from Command Line

```bash
# Navigate to Android project directory
cd droid_dicex/android

# Build the project
./gradlew build

# Install on connected device
./gradlew installDebug
```

## 🎮 How to Play

1. **Start a Game**: Launch the app and begin a new game
2. **Roll the Dice**: Tap "ROLL" to roll all 5 dice (3 chances per turn)
3. **Hold Dice**: Check the "HOLD" boxes to keep specific dice between rolls
4. **Choose Score**: Select a scoring category from the dropdown menu
5. **Enter Score**: Tap "ENTER SCORE" to record your points
6. **Complete Game**: Fill all 13 scoring categories to finish
7. **Bonus**: Score 63+ in the upper section to earn a 35-point bonus

### Scoring Categories

| Category | Description | Points |
|----------|-------------|--------|
| Aces-Sixes | Sum of dice showing that number | Sum of dice |
| 3 of a Kind | Three dice showing same number | Sum of all dice |
| 4 of a Kind | Four dice showing same number | Sum of all dice |
| Full House | Three of one, two of another | 25 points |
| Small Straight | Four consecutive numbers | 30 points |
| Large Straight | Five consecutive numbers | 40 points |
| Chance | Sum of all dice | Sum of all dice |
| 5 of a Kind | All five dice showing same number | 50 points |

## 🛠️ Technical Details

### Flutter Version Architecture
- **Language**: Dart 3.0+
- **Framework**: Flutter 3.9+
- **State Management**: Provider
- **Database**: SQLite (sqflite)
- **Localization**: flutter_localizations with ARB files

### Android Version Architecture
- **Language**: Java 17
- **Platform**: Android (API 35, Android 15)
- **Build Tools**: Gradle 8.9, AGP 8.7.0
- **Database**: SQLite for score persistence
- **UI**: XML layouts with Java activities

### Project Structure
```
droid_dicex/
├── flutter_app/              # Flutter cross-platform version
│   ├── lib/
│   │   ├── data/            # Data models and database
│   │   ├── providers/       # State management
│   │   ├── screens/         # UI screens
│   │   ├── l10n/           # Localization files
│   │   └── main.dart
│   └── assets/images/       # Dice images
├── android/                 # Native Android version
│   ├── app/
│   │   ├── src/main/
│   │   │   ├── java/driod/dicex/
│   │   │   │   ├── DriodDiceXActivity.java
│   │   │   │   ├── DriodDiceXDBHelper.java
│   │   │   │   └── DriodDiceXScoreEntity.java
│   │   │   └── res/
│   │   └── build.gradle
│   └── build.gradle
└── README.md
```

### Key Components

#### Flutter Version
- **GameProvider**: State management with game logic
- **DatabaseHelper**: SQLite operations for score storage
- **GameScreen**: Main game UI with dice, controls, and scoring
- **Localization**: English and Japanese support via ARB files

#### Android Version
- **DriodDiceXActivity**: Main game logic and UI controller
- **DriodDiceXDBHelper**: SQLite database operations
- **DriodDiceXScoreEntity**: Score data model

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request. For major changes, please open an issue first to discuss what you would like to change.

### Development Setup

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📝 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 👨‍💻 Author

**yasupong** - *Initial work* - [GitHub Profile](https://github.com/yasupong)

## 🙏 Acknowledgments

- Inspired by the classic Yahtzee dice game
- Built with modern development best practices
- Uses MIT License for open source contribution

## 📞 Support

If you encounter any issues or have questions:

1. Check the [Issues](https://github.com/yourusername/droid_dicex/issues) page
2. Create a new issue with detailed description
3. Include device information and platform version

---

**Enjoy playing DroidDiceX! 🎲**