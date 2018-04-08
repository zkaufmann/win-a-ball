
INSERT INTO TERRITORY (id, name, win_period, max_balls, max_balls_per_day) VALUES ( 1, 'Germany', 40, 10000, 250);
INSERT INTO TERRITORY (id, name, win_period, max_balls, max_balls_per_day) VALUES ( 2, 'Hungary', 80,  5000, 100);
INSERT INTO TERRITORY (id, name, win_period, max_balls, max_balls_per_day) VALUES (51, 'LuckyLand', 2,  5000, 100);

INSERT INTO TERRITORY_STATUS (territory_id, redeems_after_win, wins_today, wins_total) VALUES ( 1, 0, 0, 0);
INSERT INTO TERRITORY_STATUS (territory_id, redeems_after_win, wins_today, wins_total) VALUES ( 2, 0, 0, 0);
INSERT INTO TERRITORY_STATUS (territory_id, redeems_after_win, wins_today, wins_total) VALUES (51, 0, 0, 0);
