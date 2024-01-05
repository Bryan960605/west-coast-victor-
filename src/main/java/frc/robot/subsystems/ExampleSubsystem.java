// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ExampleSubsystem extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  private final WPI_VictorSPX right1 = new WPI_VictorSPX(1);
  private final WPI_VictorSPX right2 = new WPI_VictorSPX(2);
  private final WPI_VictorSPX left1 = new WPI_VictorSPX(3);
  private final WPI_VictorSPX left2 = new WPI_VictorSPX(4);
  private final MotorControllerGroup right = new MotorControllerGroup(right1, right2);
  private final MotorControllerGroup left = new MotorControllerGroup(left1, left2);
  private final DifferentialDrive drive = new DifferentialDrive(left, right);

  public ExampleSubsystem() {
    left1.setInverted(false);
    left2.setInverted(false);
    right1.setInverted(true);
    right2.setInverted(true);

    left1.setNeutralMode(NeutralMode.Brake);
    left2.setNeutralMode(NeutralMode.Brake);
    right1.setNeutralMode(NeutralMode.Brake);
    right2.setNeutralMode(NeutralMode.Brake);

  }

  public void manualDrive(double move, double turn){
    drive.arcadeDrive(move, turn);
  }

  /**
   * Example command factory method.
   *
   * @return a command
   */
  public CommandBase exampleMethodCommand() {
    // Inline construction of command goes here.
    // Subsystem::RunOnce implicitly requires `this` subsystem.
    return runOnce(
        () -> {
          /* one-time action goes here */
        });
  }

  /**
   * An example method querying a boolean state of the subsystem (for example, a digital sensor).
   *
   * @return value of some boolean subsystem state, such as a digital sensor.
   */
  public boolean exampleCondition() {
    // Query some boolean state, such as a digital sensor.
    return false;
  }

  @Override
  public void periodic() {

    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
