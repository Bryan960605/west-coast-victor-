// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class BaseSubsystem extends SubsystemBase {
  /** Creates a new BaseSubsystem. */
  private final WPI_VictorSPX rightFrontMotor;
  private final WPI_VictorSPX rightBackMotor;
  private final WPI_VictorSPX leftFrontMotor;
  private final WPI_VictorSPX leftBackMotor;

  private final DifferentialDrive drive;

  public BaseSubsystem() {
    rightFrontMotor = new WPI_VictorSPX(1);
    rightBackMotor = new WPI_VictorSPX(2);
    leftFrontMotor = new WPI_VictorSPX(3);
    leftBackMotor = new WPI_VictorSPX(4);

    drive = new DifferentialDrive(leftFrontMotor, rightFrontMotor);

    rightBackMotor.follow(rightFrontMotor);
    leftBackMotor.follow(leftFrontMotor);

    rightFrontMotor.setInverted(false);
    rightBackMotor.setInverted(false);
    leftFrontMotor.setInverted(true);
    leftBackMotor.setInverted(true);

    rightFrontMotor.setNeutralMode(NeutralMode.Brake);
    rightBackMotor.setNeutralMode(NeutralMode.Brake);
    leftFrontMotor.setNeutralMode(NeutralMode.Brake);
    leftBackMotor.setNeutralMode(NeutralMode.Brake);
  }

  public void manualDrive(double xSpeed, double zSpeed){
    drive.arcadeDrive(xSpeed, zSpeed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
