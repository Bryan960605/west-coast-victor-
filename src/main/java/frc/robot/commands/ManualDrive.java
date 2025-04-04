// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.BaseSubsystem;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class ManualDrive extends Command {
  /** Creates a new ManualDrive. */
  private final BaseSubsystem m_BaseSubsystem;
  private final DoubleSupplier xSpeedFunc;
  private final DoubleSupplier zSpeedFunc;
  private final BooleanSupplier isSlowFunc;

  private final SlewRateLimiter xLimiter;
  private final SlewRateLimiter zLimiter;

  private double xSpeed;
  private double zSpeed;
  private boolean isSlow;
  public ManualDrive(BaseSubsystem baseSubsystem, DoubleSupplier xSpeedFunc, DoubleSupplier zSpeedFunc, BooleanSupplier isSlowFunc) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.m_BaseSubsystem = baseSubsystem;
    this.xSpeedFunc = xSpeedFunc;
    this.zSpeedFunc = zSpeedFunc;
    this.isSlowFunc = isSlowFunc;
    xLimiter = new SlewRateLimiter(3);
    zLimiter = new SlewRateLimiter(3);
    addRequirements(m_BaseSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    xSpeed = -xSpeedFunc.getAsDouble();
    zSpeed = zSpeedFunc.getAsDouble();
    isSlow = isSlowFunc.getAsBoolean();

    xSpeed = xLimiter.calculate(xSpeed);
    zSpeed = zLimiter.calculate(zSpeed);

    xSpeed = MathUtil.applyDeadband(xSpeed, 0.1);
    zSpeed = MathUtil.applyDeadband(zSpeed, 0.1);

    if (isSlow) {
      xSpeed = xSpeed * 0.4;
      zSpeed = zSpeed * 0.4;
    }else{
      xSpeed = xSpeed * 0.8;
      zSpeed = zSpeed * 0.8;
    }

    m_BaseSubsystem.manualDrive(xSpeed, zSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_BaseSubsystem.manualDrive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
