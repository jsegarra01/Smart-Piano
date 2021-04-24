CREATE DATABASE piano_OOPD;

DROP TABLE IF EXISTS SongPlaylistsT CASCADE;
DROP TABLE IF EXISTS SongStatisticsGeneralT CASCADE;
DROP TABLE IF EXISTS SongStatisticsHourlyT CASCADE;
DROP TABLE IF EXISTS PlaylistT CASCADE;
DROP TABLE IF EXISTS SongT CASCADE;
DROP TABLE IF EXISTS UserT CASCADE;

DROP TABLE IF EXISTS SongPlaylists CASCADE;
DROP TABLE IF EXISTS SongStatisticsGeneral CASCADE;
DROP TABLE IF EXISTS SongStatisticsHourly CASCADE;
DROP TABLE IF EXISTS Playlist CASCADE;
DROP TABLE IF EXISTS Song CASCADE;
DROP TABLE IF EXISTS User CASCADE;

CREATE TABLE UserT(
                      username VARCHAR(255) PRIMARY KEY,
                      email Varchar(255),
                      password Varchar(255)
);

CREATE TABLE SongT(
                      songId SERIAL PRIMARY KEY,
                      songName Varchar(255),
                      authorsName Varchar(255),
                      duration FLOAT,
                      recordingDate DATE,
                      publicBoolean BOOLEAN,
                      songFile MEDIUMTEXT,
                      username VARCHAR(255),
                      FOREIGN KEY (username) REFERENCES UserT(username)
);

CREATE TABLE SongStatisticsGeneralT(
                                       songId BIGINT UNSIGNED PRIMARY KEY,
                                       timesPlayed INT,
                                       minutesPlayed INT,
                                       numberDifferentUsers INT,
                                       FOREIGN KEY (songId) REFERENCES SongT(songId)
);

CREATE TABLE SongStatisticsHourlyT(
                                      hour INT,
                                      songId BIGINT UNSIGNED,
                                      timesPlayed INT,
                                      minutesListened INT,
                                      FOREIGN KEY (songId) REFERENCES SongT(songId)
);

CREATE TABLE PlaylistT(
                          playlistId SERIAL PRIMARY KEY,
                          playlistName VARCHAR(255),
                          username VARCHAR(255),
                          FOREIGN KEY (username) REFERENCES UserT(username)
);

CREATE TABLE SongPlaylistsT(
                               songId BIGINT UNSIGNED,
                               playlistId BIGINT UNSIGNED,
                               FOREIGN KEY (songId) REFERENCES SongT(songId),
                               FOREIGN KEY (playlistId) REFERENCES PlaylistT(playlistId)
);
#INSERT USERS
INSERT INTO UserT VALUES ('blayaiai','alex.blay@students.salle.url.edu','viscaelbarca');
INSERT INTO UserT VALUES ('MEGAKI295','josep.segarra@students.salle.url.edu','admin');
INSERT INTO UserT VALUES ('sergiENP','sergi.vives@students.salle.url.edu','password');
INSERT INTO UserT VALUES ('styopartist','stepan.batllori@students.salle.url.edu', 'madrepatriaRussia');
INSERT INTO UserT VALUES ('lauranuez','laura.nuez@students.salle.url.edu', 'vivaespanya');
SELECT * FROM UserT;

INSERT INTO SongT VALUES (1,'Bohemian Rhapsody','Queen','6','2001-03-23',true,'Songs/Queen - Bohemian Rhapsody','lauranuez');
INSERT INTO SongT VALUES (2, 'Star Wars Theme','John Williams', 5.46,'1977-11-07',true,'Songs/Star-Wars-Theme','blayaiai');
SELECT * FROM SongT;
INSERT INTO PlaylistT VALUES (1,'Blayaiais Playlist','blayaiai');
INSERT INTO SongPlaylistsT VALUES(1,1);
INSERT INTO SongPlaylistsT VALUES(2,1);

SELECT * FROM SongPlaylistsT;
SELECT * FROM PlaylistT;