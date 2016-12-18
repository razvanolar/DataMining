CREATE TABLE entry (
  age             INT,
  height          FLOAT,
  weight          FLOAT,
  sex             INT,
  activity_domain INT,
  effort_level    INT,
  avg_speed       FLOAT,
  distance        FLOAT
);

CREATE TABLE name_maps (
  attribute_index INT,
  display_name    VARCHAR(40)
);

INSERT INTO name_maps (attribute_index, display_name) VALUES
  (0, 'Age'),
  (1, 'Height'),
  (2, 'Weight'),
  (3, 'Sex'),
  (4, 'Activity'),
  (5, 'Effort_Level'),
  (6, 'Avg_Speed'),
  (7, 'Distance');

CREATE TABLE attribute_range (
  attribute_index INT,
  display_name    VARCHAR(40),
  min             FLOAT DEFAULT NULL,
  max             FLOAT DEFAULT NULL
);

INSERT INTO attribute_range (attribute_index, display_name, min, max) VALUES
  (0, 'Adolescent', NULL, 18),
  (0, 'Adult', 18, 35),
  (0, 'Midlife', 35, 50),
  (0, 'Old', 50, NULL),

  (1, 'Short', NULL, 1.60),
  (1, 'Average', 1.60, 1.80),
  (1, 'Tall', 1.80, NULL),

  (2, 'Underweight', NULL, 40),
  (2, 'Healthy', 40, 70),
  (2, 'Overweight', 70, 90),
  (2, 'Obese', 90, NULL),

  (6, 'Slow', NULL, 8),
  (6, 'Average', 8, 16),
  (6, 'Fast', 16, NULL),

  (7, 'Short', NULL, 10),
  (7, 'Average', 10, 30),
  (7, 'Long', 30, NULL),

  (4, 'Programmer', 1, 2),
  (4, 'Lawyer', 2, 3),
  (4, 'Doctor', 3, 4),
  (4, 'Policeman', 4, 5),
  (4, 'Engineer', 5, 6),
  (4, 'Other', 6, 7),

  (3, 'Female', NULL, 1),
  (3, 'Male', 1, NULL );