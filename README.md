# User Test Cases

Use case 1:
  Player opens the app, the game starts.
  
Use case 2:
  To win a game and increase your score you must align 4 X's vericaly, horizontally or diagonally (or O's depending on whick character you play as).
  
Use case e:
  A player chooses a button on the grid to make a move to. Player must click on that button. The player's character (X or O) will now appear on that button.
  
Use case 4:
  A player wins/loses a game and wants to play another round. Player must click on the "NEXT ROUND" button to start anotehr round in order to clear the grid of all player
  characters (X and O).
  
Use case 5:
  A player wants to reset the game and start over. The player must click the "RESET" button in order to reset the scores and start the game all over. This will also clear 
  the grid of all player characters (X and O).
  
Use case 6:
  Players want to see their scores. The text below the grid contains both players scores for the game.
  

Using the app:
  1) To use the app on an Android phone, simply download and install app-release.apk (found at PFath046/TicTacToe)
  2) To modify or try the app on Android Studios, use (File > new > new project) to create a new project folder, then download all the files (at PFath046/TicTacToe)
  and place them in the project folder that you created.

  
App architecture:

  1) The framework used is Android Studios.
  2) The most important parts of the system are the MainActivity.java (found at TicTacToe/app/src/main/res/layout/)
  and activity_mail.xml (found at TicTacToe\app\src\main\java\com\example\simple_tictactoe) files which I have coded to make the resulting app.
  3) To modify the apps main functionalities, the MainActivity.java and activity_mail.xml must be modified. To incrase the size of the grid in the game,
  create and align more buttons using activity_mail.xml in the same style that I have, and just simply increase the size attribute in MainActivity.java

Using Umple I have created a state diagram which describes how the game works. And generated java code for the state diagram. The Umple files for this can be found here in this repository (at PFath046/TicTacToe)
  
  
  
  
