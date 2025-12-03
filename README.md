# DroidDiceX - Android Dice Poker Game

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Android](https://img.shields.io/badge/Android-API%2029+-green.svg)](https://developer.android.com/about/versions/android-10)
[![Java](https://img.shields.io/badge/Java-19-orange.svg)](https://www.oracle.com/java/)

A classic dice poker game for Android devices, featuring Yahtzee-style gameplay with modern UI and score tracking capabilities.

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
- **Game Statistics**: View your score history and performance
- **Bilingual Support**: English and Japanese language support

## 📱 Screenshots

*Screenshots coming soon*

## 🚀 Getting Started

### Prerequisites

- Android Studio Arctic Fox or later
- Android SDK API 29+ (Android 10)
- Java 19
- Gradle 7.0+

### Installation

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

### Building from Command Line

```bash
# Navigate to project directory
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

### Architecture
- **Language**: Java 19
- **Platform**: Android (API 29+)
- **Database**: SQLite for score persistence
- **UI**: XML layouts with Java activities

### Project Structure
```
droid_dicex/
├── android/
│   ├── app/
│   │   ├── src/main/
│   │   │   ├── java/driod/dicex/
│   │   │   │   ├── DriodDiceXActivity.java    # Main game activity
│   │   │   │   ├── DriodDiceXDBHelper.java    # Database helper
│   │   │   │   └── DriodDiceXScoreEntity.java # Score data model
│   │   │   ├── res/
│   │   │   │   ├── layout/main.xml            # Main game UI
│   │   │   │   ├── values/strings.xml         # English strings
│   │   │   │   ├── values-ja/strings.xml      # Japanese strings
│   │   │   │   └── drawable/                  # Dice images
│   │   │   └── AndroidManifest.xml
│   │   └── build.gradle
│   ├── gradle/
│   └── build.gradle
```

### Key Components

- **DriodDiceXActivity**: Main game logic and UI controller
- **DriodDiceXDBHelper**: SQLite database operations for score storage
- **DriodDiceXScoreEntity**: Data model for score records
- **Score Calculation**: Complex algorithms for detecting poker hands
- **Dice Rolling**: Random number generation and visual updates

## 📊 Features in Detail

### Score Tracking
- Automatic score calculation for all categories
- Bonus point system (35 points for 63+ in upper section)
- Persistent storage of game results
- Export functionality for score sharing

### Game Mechanics
- 3 rolls per turn with hold functionality
- 13 scoring categories to complete
- Real-time score validation
- Game state management

### User Interface
- Intuitive dice display with hold checkboxes
- Dropdown menu for score category selection
- Real-time score updates
- Game status and bonus indicators

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
- Built with Android development best practices
- Uses MIT License for open source contribution

## 📞 Support

If you encounter any issues or have questions:

1. Check the [Issues](https://github.com/yourusername/droid_dicex/issues) page
2. Create a new issue with detailed description
3. Include device information and Android version

---

**Enjoy playing DroidDiceX! 🎲**