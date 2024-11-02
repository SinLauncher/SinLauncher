import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

class GameCard extends StatelessWidget {
  final String gameVersion;
  final VoidCallback onLaunch;

  const GameCard({
    super.key,
    required this.gameVersion,
    required this.onLaunch,
  });
 
  static const platform = MethodChannel('');

  Future<void> installGame() async {
    try {
      await platform.invokeMethod('installationManager', {
        'id': 'test-with-23234',
        'version': '1.21.1',
        'cups': 2, 
        'count': 0
      });
    } on PlatformException catch (e) {
      print("Failed to invoke: '${e.message}'.");
    }
  }

  @override
  Widget build(BuildContext context) {
    return Card(
      color: Colors.grey[800],
      shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(10)),
      child: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Text('Minecraft', style: Theme.of(context).textTheme.titleLarge), 

            const SizedBox(height: 10),

            Text('Version: $gameVersion', style: Theme.of(context).textTheme.bodyMedium), 

            const SizedBox(height: 10),
            Align(
              alignment: Alignment.bottomRight,
              child: ElevatedButton(
                onPressed: onLaunch,
                child: const Text('Play'),
              ),
            ),
          ],
        ),
      ),
    );
  }
}