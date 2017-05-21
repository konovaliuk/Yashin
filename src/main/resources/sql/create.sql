-- -----------------------------------------------------
-- Schema railway_system
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `railway_system` ;

-- -----------------------------------------------------
-- Schema railway_system
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `railway_system` DEFAULT CHARACTER SET utf8 ;
USE `railway_system` ;

-- -----------------------------------------------------
-- Table `railway_system`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `railway_system`.`user` ;

CREATE TABLE IF NOT EXISTS `railway_system`.`user` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(255) NOT NULL,
  `password` VARCHAR(35) NOT NULL,
  `name` VARCHAR(35) NOT NULL,
  `surname` VARCHAR(35) NOT NULL,
  `phone` VARCHAR(12) NOT NULL,
  `admin` TINYINT(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `railway_system`.`price`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `railway_system`.`price` ;

CREATE TABLE IF NOT EXISTS `railway_system`.`price` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `compartment_factor` FLOAT NOT NULL,
  `deluxe_factor` FLOAT NOT NULL,
  `berth_factor` FLOAT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `route_id_UNIQUE` (`route_id` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `railway_system`.`route`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `railway_system`.`route` ;

CREATE TABLE IF NOT EXISTS `railway_system`.`route` (
  `id` INT NOT NULL,
  `from_station` VARCHAR(70) NOT NULL,
  `to_station` VARCHAR(70) NOT NULL,
  `from_time` TIME NOT NULL,
  `to_time` TIME NOT NULL,
  `price_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `departure` (`from_station` ASC),
  INDEX `destination` (`to_station` ASC),
  INDEX `fk_route_price1_idx` (`price_id` ASC),
  CONSTRAINT `fk_route_price1`
    FOREIGN KEY (`price_id`)
    REFERENCES `railway_system`.`price` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `railway_system`.`train`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `railway_system`.`train` ;

CREATE TABLE IF NOT EXISTS `railway_system`.`train` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `route_id` INT NOT NULL,
  `date` DATE NOT NULL,
  `compartment_free` INT(11) NOT NULL,
  `deluxe_free` INT(11) NOT NULL,
  `berth_free` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `train_route_idx` (`route_id` ASC),
  CONSTRAINT `train_route`
    FOREIGN KEY (`route_id`)
    REFERENCES `railway_system`.`route` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `railway_system`.`request`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `railway_system`.`request` ;

CREATE TABLE IF NOT EXISTS `railway_system`.`request` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `user_id` INT(11) NOT NULL,
  `train_id` INT(11) NOT NULL,
  `type` ENUM('compartment', 'deluxe', 'berth') NOT NULL,
  `price` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `passenger_idx` (`user_id` ASC),
  INDEX `invoice_train_idx` (`train_id` ASC),
  CONSTRAINT `passenger`
    FOREIGN KEY (`user_id`)
    REFERENCES `railway_system`.`user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `invoice_train`
    FOREIGN KEY (`train_id`)
    REFERENCES `railway_system`.`train` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = '\n';