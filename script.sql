-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema questionnaire
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema questionnaire
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `questionnaire` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `questionnaire` ;

-- -----------------------------------------------------
-- Table `questionnaire`.`administrator`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `questionnaire`.`administrator` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `email` VARCHAR(155) NOT NULL COMMENT '',
  `password` VARCHAR(255) NOT NULL COMMENT '',
  `name` VARCHAR(255) NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `questionnaire`.`questionnaire`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `questionnaire`.`questionnaire` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '',
  `administrator_id` INT NOT NULL COMMENT '',
  `published` TINYINT(1) NOT NULL DEFAULT 0 COMMENT '',
  `name` VARCHAR(255) NULL COMMENT '',
  `creation_date` DATETIME NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  INDEX `fk_questionnaire_administrator1_idx` (`administrator_id` ASC)  COMMENT '',
  CONSTRAINT `fk_questionnaire_administrator1`
    FOREIGN KEY (`administrator_id`)
    REFERENCES `questionnaire`.`administrator` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `questionnaire`.`question_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `questionnaire`.`question_type` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `name` VARCHAR(45) NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `questionnaire`.`question`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `questionnaire`.`question` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '',
  `question_type_id` INT NOT NULL COMMENT '',
  `questionnaire_id` BIGINT NOT NULL COMMENT '',
  `question` TEXT NOT NULL COMMENT '',
  `creation_date` DATETIME NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  INDEX `fk_question_question_type_idx` (`question_type_id` ASC)  COMMENT '',
  INDEX `fk_question_questionnaire1_idx` (`questionnaire_id` ASC)  COMMENT '',
  CONSTRAINT `fk_question_question_type`
    FOREIGN KEY (`question_type_id`)
    REFERENCES `questionnaire`.`question_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_question_questionnaire1`
    FOREIGN KEY (`questionnaire_id`)
    REFERENCES `questionnaire`.`questionnaire` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `questionnaire`.`options`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `questionnaire`.`options` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '',
  `description` TEXT NOT NULL COMMENT '',
  `question_id` BIGINT NOT NULL COMMENT '',
  `option_order` INT NOT NULL DEFAULT 0 COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  INDEX `fk_option_question1_idx` (`question_id` ASC)  COMMENT '',
  CONSTRAINT `fk_option_question1`
    FOREIGN KEY (`question_id`)
    REFERENCES `questionnaire`.`question` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `questionnaire`.`user_answer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `questionnaire`.`user_answer` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `email` VARCHAR(155) NULL COMMENT '',
  `creation_date` DATETIME NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `questionnaire`.`answer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `questionnaire`.`answer` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '',
  `open_question` TEXT NULL COMMENT '',
  `questionnaire_id` BIGINT NOT NULL COMMENT '',
  `user_answer_id` INT NOT NULL COMMENT '',
  `creation_date` DATETIME NULL COMMENT '',
  `question_id` BIGINT NOT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  INDEX `fk_answer_questionnaire1_idx` (`questionnaire_id` ASC)  COMMENT '',
  INDEX `fk_answer_user_answer1_idx` (`user_answer_id` ASC)  COMMENT '',
  INDEX `fk_answer_question1_idx` (`question_id` ASC)  COMMENT '',
  CONSTRAINT `fk_answer_questionnaire1`
    FOREIGN KEY (`questionnaire_id`)
    REFERENCES `questionnaire`.`questionnaire` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_answer_user_answer1`
    FOREIGN KEY (`user_answer_id`)
    REFERENCES `questionnaire`.`user_answer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_answer_question1`
    FOREIGN KEY (`question_id`)
    REFERENCES `questionnaire`.`question` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `questionnaire`.`answer_has_option`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `questionnaire`.`answer_has_option` (
  `answer_id` BIGINT NOT NULL COMMENT '',
  `option_id` BIGINT NOT NULL COMMENT '',
  PRIMARY KEY (`answer_id`, `option_id`)  COMMENT '',
  INDEX `fk_answer_has_option_option1_idx` (`option_id` ASC)  COMMENT '',
  INDEX `fk_answer_has_option_answer1_idx` (`answer_id` ASC)  COMMENT '',
  CONSTRAINT `fk_answer_has_option_answer1`
    FOREIGN KEY (`answer_id`)
    REFERENCES `questionnaire`.`answer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_answer_has_option_option1`
    FOREIGN KEY (`option_id`)
    REFERENCES `questionnaire`.`options` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `questionnaire`.`administrator`
-- -----------------------------------------------------
START TRANSACTION;
USE `questionnaire`;
INSERT INTO `questionnaire`.`administrator` (`id`, `email`, `password`, `name`) VALUES (1, 'marcio@jamal.re', '57b472bdf2cf967a8a9b862717be0cfccd5260121823076c9bcd3762bf1a0096', 'Márcio Jamal Resende');

COMMIT;


-- -----------------------------------------------------
-- Data for table `questionnaire`.`question_type`
-- -----------------------------------------------------
START TRANSACTION;
USE `questionnaire`;
INSERT INTO `questionnaire`.`question_type` (`id`, `name`) VALUES (1, 'Aberta');
INSERT INTO `questionnaire`.`question_type` (`id`, `name`) VALUES (2, '1 Opção');
INSERT INTO `questionnaire`.`question_type` (`id`, `name`) VALUES (3, 'Varias opções');

COMMIT;

