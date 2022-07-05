DROP DATABASE IF EXISTS bullsandcowsDBTest;
CREATE DATABASE bullsandcowsDBTest;
USE bullsandcowsDBTest;

CREATE TABLE game (
game_Id INT PRIMARY KEY AUTO_INCREMENT,
answer CHAR (4) NOT NULL,
`status` BOOLEAN 
);   


CREATE TABLE round (
round_Id INT PRIMARY KEY AUTO_INCREMENT,
game_Id INT NOT NULL, 
guess_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
guess CHAR (4) NOT NULL,
result CHAR (10), 
FOREIGN KEY fk_Game_Id (game_Id) REFERENCES game(game_Id)
);

 
SELECT * FROM game;
SELECT * FROM round;