-- MySQL Script generated by MySQL Workbench
-- 08/04/15 00:03:24
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema AndroidSickHelper
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema AndroidSickHelper
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `AndroidSickHelper` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `AndroidSickHelper` ;

-- -----------------------------------------------------
-- Table `AndroidSickHelper`.`Usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `AndroidSickHelper`.`Usuario` (
  `cpf` VARCHAR(20) NOT NULL,
  `nome` VARCHAR(45) NULL,
  `doenca` VARCHAR(45) NULL,
  `sala` VARCHAR(45) NULL,
  `especialidade` VARCHAR(45) NULL,
  `categoria` TINYINT(1) NOT NULL,
  `cpfResponsavel` VARCHAR(45) NULL,
  PRIMARY KEY (`cpf`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `AndroidSickHelper`.`Mensagens`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `AndroidSickHelper`.`Mensagens` (
  `Usuario_cpf` VARCHAR(20) NOT NULL,
  `mensagens` VARCHAR(100) NULL,
  `dataEnvio` DATETIME NULL,
  `dataVisto` DATETIME NULL,
  `categoria` VARCHAR(45) NULL,
  `destinatario` VARCHAR(45) NULL,
  CONSTRAINT `fk_Mensagens_Usuario`
    FOREIGN KEY (`Usuario_cpf`)
    REFERENCES `AndroidSickHelper`.`Usuario` (`cpf`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
