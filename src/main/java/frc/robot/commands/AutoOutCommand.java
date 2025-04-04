// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.BaseSubsystem;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class AutoOutCommand extends Command {
  /** Creates a new AutoOutCommand. */
  private final BaseSubsystem m_BaseSubsystem;
  private final Timer timer;

  private double nowTime;
  public AutoOutCommand(BaseSubsystem baseSubsystem) {
    // Use addRequirements() here to declare subsystem dependencies.

    this.m_BaseSubsystem = baseSubsystem;
    timer = new Timer();
    addRequirements(m_BaseSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer.reset();
    timer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    nowTime = timer.get();

    if (nowTime <= 2) {
      m_BaseSubsystem.manualDrive(0.8, 0);
    }else{
      m_BaseSubsystem.manualDrive(0, 0);
    }
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
