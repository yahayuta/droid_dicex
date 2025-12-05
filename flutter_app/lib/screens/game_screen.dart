import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import '../providers/game_provider.dart';
import '../l10n/app_localizations.dart';

class GameScreen extends StatelessWidget {
  const GameScreen({super.key});

  @override
  Widget build(BuildContext context) {
    final l10n = AppLocalizations.of(context)!;
    
    return Scaffold(
      appBar: AppBar(
        title: Text(l10n.appTitle),
        actions: [
          IconButton(
            icon: const Icon(Icons.refresh),
            onPressed: () {
              context.read<GameProvider>().init();
            },
          ),
        ],
      ),
      body: Consumer<GameProvider>(
        builder: (context, game, child) {
          return Column(
            children: [
              // Score display
              _buildScoreRow(context, game, l10n),
              
              // Dice display
              Expanded(
                child: ListView.builder(
                  itemCount: 5,
                  itemBuilder: (context, index) {
                    return _buildDiceRow(context, game, index, l10n);
                  },
                ),
              ),
              
              // Controls
              _buildControlRow(context, game, l10n),
              
              // Score selection
              _buildScoreSelection(context, game, l10n),
              
              // Bonus display
              _buildBonusRow(game, l10n),
            ],
          );
        },
      ),
    );
  }

  Widget _buildScoreRow(BuildContext context, GameProvider game, AppLocalizations l10n) {
    return Padding(
      padding: const EdgeInsets.all(8.0),
      child: Row(
        mainAxisAlignment: MainAxisAlignment.spaceAround,
        children: [
          Text(
            '${l10n.msgScore} ${game.total}',
            style: Theme.of(context).textTheme.titleLarge,
          ),
          Text(
            '${l10n.msgRollValue} ${_getCurrentScore(game)}',
            style: Theme.of(context).textTheme.titleMedium,
          ),
        ],
      ),
    );
  }

  String _getCurrentScore(GameProvider game) {
    if (game.scoreUsed[game.currentScoreChecked]) {
      return '*';
    }
    return (game.selectedScr1 + game.selectedScr2).toString();
  }

  Widget _buildDiceRow(BuildContext context, GameProvider game, int index, AppLocalizations l10n) {
    return Card(
      margin: const EdgeInsets.symmetric(horizontal: 16, vertical: 4),
      child: Padding(
        padding: const EdgeInsets.all(8.0),
        child: Row(
          children: [
            Expanded(
              child: Image.asset(
                _getDiceImage(game.cardNum[index]),
                height: 60,
              ),
            ),
            Checkbox(
              value: game.hold[index],
              onChanged: (value) {
                context.read<GameProvider>().setHold(index, value ?? false);
              },
            ),
            Text(l10n.hold),
          ],
        ),
      ),
    );
  }

  String _getDiceImage(int value) {
    const diceNames = ['one', 'two', 'three', 'four', 'five', 'six'];
    return 'assets/images/${diceNames[value]}.png';
  }

  Widget _buildControlRow(BuildContext context, GameProvider game, AppLocalizations l10n) {
    return Padding(
      padding: const EdgeInsets.all(8.0),
      child: Row(
        children: [
          Expanded(
            child: ElevatedButton(
              onPressed: game.chance > 0
                  ? () => context.read<GameProvider>().rollDice()
                  : null,
              child: Text('${l10n.roll} (${game.chance})'),
            ),
          ),
          const SizedBox(width: 8),
          Expanded(
            child: ElevatedButton(
              onPressed: () => _enterScore(context, game, l10n),
              child: Text(l10n.enterScore),
            ),
          ),
        ],
      ),
    );
  }

  void _enterScore(BuildContext context, GameProvider game, AppLocalizations l10n) async {
    if (game.scoreUsed[game.currentScoreChecked]) {
      ScaffoldMessenger.of(context).showSnackBar(
        SnackBar(content: Text(l10n.msgPicked)),
      );
      return;
    }

    await context.read<GameProvider>().enterScore();

    if (game.gameStatus == GameStatus.end) {
      if (!context.mounted) return;
      _showGameOverDialog(context, game, l10n);
    }
  }

  void _showGameOverDialog(BuildContext context, GameProvider game, AppLocalizations l10n) {
    showDialog(
      context: context,
      builder: (context) => AlertDialog(
        title: Text(l10n.msgGameOver),
        content: Text('${l10n.msgResult} ${game.total}'),
        actions: [
          TextButton(
            onPressed: () {
              Navigator.of(context).pop();
              context.read<GameProvider>().init();
            },
            child: const Text('OK'),
          ),
        ],
      ),
    );
  }

  Widget _buildScoreSelection(BuildContext context, GameProvider game, AppLocalizations l10n) {
    final scoreCategories = _getScoreCategories(l10n);
    
    return Padding(
      padding: const EdgeInsets.all(8.0),
      child: Row(
        children: [
          Expanded(
            child: DropdownButton<int>(
              value: game.currentScoreChecked,
              isExpanded: true,
              items: List.generate(13, (index) {
                return DropdownMenuItem(
                  value: index,
                  child: Text(scoreCategories[index]),
                );
              }),
              onChanged: (value) {
                if (value != null) {
                  context.read<GameProvider>().setCurrentScore(value);
                }
              },
            ),
          ),
          const SizedBox(width: 8),
          ElevatedButton(
            onPressed: () => _showStatus(context, game, l10n),
            child: Text(l10n.checkStatus),
          ),
        ],
      ),
    );
  }

  List<String> _getScoreCategories(AppLocalizations l10n) {
    return [
      l10n.scoreAces,
      l10n.scoreTwos,
      l10n.scoreThrees,
      l10n.scoreFours,
      l10n.scoreFives,
      l10n.scoreSixes,
      l10n.score3Kind,
      l10n.score4Kind,
      l10n.scoreFullHouse,
      l10n.scoreSmallStraight,
      l10n.scoreLargeStraight,
      l10n.scoreChance,
      l10n.scoreDiceX,
    ];
  }

  void _showStatus(BuildContext context, GameProvider game, AppLocalizations l10n) {
    final scoreCategories = _getScoreCategories(l10n);
    
    showDialog(
      context: context,
      builder: (context) => AlertDialog(
        title: Text(l10n.msgStatus),
        content: SizedBox(
          width: double.maxFinite,
          child: SingleChildScrollView(
            child: Column(
              mainAxisSize: MainAxisSize.min,
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                for (int i = 0; i < 13; i++)
                  Padding(
                    padding: const EdgeInsets.symmetric(vertical: 4.0),
                    child: Text(
                      '${scoreCategories[i]}: ${game.scoreUsed[i] ? game.scoreGet[i] : ''}',
                      style: const TextStyle(fontSize: 16),
                    ),
                  ),
                if (game.bonusFlag)
                  Padding(
                    padding: const EdgeInsets.only(top: 8.0),
                    child: Text(
                      l10n.msgBonusGet,
                      style: const TextStyle(
                        fontSize: 16,
                        fontWeight: FontWeight.bold,
                        color: Colors.green,
                      ),
                    ),
                  ),
              ],
            ),
          ),
        ),
        actions: [
          TextButton(
            onPressed: () => Navigator.of(context).pop(),
            child: const Text('OK'),
          ),
        ],
      ),
    );
  }

  Widget _buildBonusRow(GameProvider game, AppLocalizations l10n) {
    return Padding(
      padding: const EdgeInsets.all(8.0),
      child: Text(
        game.bonusFlag ? l10n.msgBonus : '',
        style: const TextStyle(
          fontSize: 18,
          fontWeight: FontWeight.bold,
          color: Colors.green,
        ),
      ),
    );
  }
}
